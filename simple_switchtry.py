from ryu.base import app_manager
from ryu.controller import ofp_event
import ryu.app.ofctl.api as api
from ryu.controller.handler import MAIN_DISPATCHER
from ryu.controller.handler import set_ev_cls
from ryu.ofproto import ofproto_v1_0
from ryu.lib.mac import haddr_to_bin
from ryu.lib.packet import packet
from ryu.lib.packet import ethernet, arp
from ryu.lib.packet import ether_types



class SimpleSwitch(app_manager.RyuApp):
    OFP_VERSIONS = [ofproto_v1_0.OFP_VERSION]
    
   
    def __init__(self, *args, **kwargs):
        super(SimpleSwitch, self).__init__(*args, **kwargs)
        self.mac_to_port = {}

    def add_flow(self, datapath, in_port, dst, actions):
        ofproto = datapath.ofproto

        match = datapath.ofproto_parser.OFPMatch(
            in_port=in_port, dl_dst=haddr_to_bin(dst))   # Objects of this class describe packet header fields and an input port to match on


        mod = datapath.ofproto_parser.OFPFlowMod(
            datapath=datapath, match=match, cookie=0,
            command=ofproto.OFPFC_ADD, idle_timeout=0, hard_timeout=0,
            priority=ofproto.OFP_DEFAULT_PRIORITY,
            flags=ofproto.OFPFF_SEND_FLOW_REM, actions=actions)
        
        datapath.send_msg(mod) 

    def arp_rep(self,datapath, src, dst, dst_ip, src_ip, eth, msg):
        ofproto = datapath.ofproto
        parser = datapath.ofproto_parser # datapath.ofproto_parser object that represent the OpenFlow protocol that Ryu and the switch negotiated
        pkt = packet.Packet()
        pkt.add_protocol(ethernet.ethernet(ethertype=eth.ethertype, dst=src, src=dst))
        pkt.add_protocol(arp.arp(opcode=2, src_mac=dst, src_ip=dst_ip, dst_mac=src, dst_ip=src_ip)) # opcode=2 for reply
        pkt.serialize() # Generate a corresponding sequence of bytes of the packet(encode the packet)
        actions = [parser.OFPActionOutput(port=msg.in_port)] # OFPActionOutputclass specifies a switchport that you wish to send the packet outof
        data = pkt.data
     
        #The ofp_packet_out message instructs a switch to send a packet. The packet might be one constructed at the controller,
        #or it might be one that the switch received, buffered, and forwarded to the controller (and is now referenced by a buffer_id)

        out = parser.OFPPacketOut(datapath=datapath,
                                  buffer_id=ofproto.OFP_NO_BUFFER,
                                  in_port=ofproto.OFPP_CONTROLLER,
                                  actions=actions,
                                  data=data)    
        datapath.send_msg(out)
        

    def add_ent(self, datapath, in_port, dst, out_port):
        
        actions = [datapath.ofproto_parser.OFPActionOutput(out_port)]
        self.add_flow(datapath, in_port, dst, actions)


    @set_ev_cls(ofp_event.EventOFPPacketIn, MAIN_DISPATCHER)
    def _packet_in_handler(self, ev):
        msg = ev.msg   # ev.msg is an object that represents a packet_in data structure.
        datapath = msg.datapath # msg.datapath is an object that represents a datapath (switch)
        ofproto = datapath.ofproto # datapath.ofproto is an object that represent the OpenFlow protocol that Ryu and the switch negotiated

        pkt = packet.Packet(msg.data)  
        eth = pkt.get_protocol(ethernet.ethernet)
        pkt_arp = pkt.get_protocol(arp.arp)

        dst = eth.dst
        src = eth.src
        dpid = datapath.id #datapath to add the entry to
      
        datapathswitch2 = api.get_datapath(self,2)
        
        if eth.ethertype == ether_types.ETH_TYPE_LLDP:
            # ignore lldp packet
            return
        
        if eth.ethertype == ether_types.ETH_TYPE_ARP:
            if pkt_arp.opcode == arp.ARP_REQUEST:
              dst_ip = pkt_arp.dst_ip
              src_ip = pkt_arp.src_ip

              # ARP spoofing
            
              if dpid == 1:            # ARP reply to host 1      
               self.arp_rep(datapath, src, '00:00:00:00:00:02', dst_ip, src_ip, eth, msg)   
               self.add_ent(datapath, 1, '00:00:00:00:00:02', 2)                            
               self.add_ent(datapath, 2, '00:00:00:00:00:01', 1)
               self.add_ent(datapathswitch2, 1, '00:00:00:00:00:01', 2)
               self.add_ent(datapathswitch2, 2, '00:00:00:00:00:02', 1)

              if dpid == 2:     # ARP reply to host 2
               self.arp_rep(datapath, src, '00:00:00:00:00:01', dst_ip, src_ip, eth, msg)

              
                  
          
            

   
