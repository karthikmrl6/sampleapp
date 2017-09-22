package JMapReducer.JMapReducer;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashMap;
import java.util.*;
import java.util.zip.GZIPInputStream;
import org.apache.hadoop.io.NullWritable;

public class myMapper extends Mapper<LongWritable,WarcRecord, NullWritable, NullWritable> {
 
	public void map1(LongWritable key, Text value, Context context)throws IOException, InterruptedException 
	{

	}
	
	//================================================================================
    // Static members
    //================================================================================
	
	
	static String regexContent = "HTTP.*\n(.*\n)*Content-Length.*\n";

	//================================================================================
    // Private Members
    //================================================================================
	
	private Map<String, SortedList<Page>> gPage;
	
	public myMapper()
	{
		gPage = new HashMap<String, SortedList<Page>>();
	}
	
	
	/***
	 * Reads WARC archive from given archiveFile.
	 * 
	 */
	
	public void readWarc(String warcFile) throws IOException 
	
	{
		
		// Open Files 
		GZIPInputStream gzWarc=new GZIPInputStream(new FileInputStream(warcFile));
		DataInputStream inputStream=new DataInputStream(gzWarc);
		WarcRecord warcRecord;
		int pageNbr = 0;
		
		// This condition pageNbr!=4 has been added to limit the records with 4 page, this is helpful to debug the project. 
		//while ((warcRecord=WarcRecord.readNextWarcRecord(inputStream))!=null) {
		while ((warcRecord=WarcRecord.readNextWarcRecord(inputStream))!=null && pageNbr!=4) {
			// check whether it's a response record or no
			if (warcRecord.getHeaderRecordType().equals("response")) {
				
				// Debug: Print out the content of warc Record
				//System.out.println(warcRecord.toString()) ;
				
				// create a WarcHTML record
				WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(warcRecord);
				
				// Create Page Object, and extract the relevant information. 
				Page currentPage = new Page(htmlRecord);
				
				
				String pageID = currentPage.getPageID(); 
				
				// Insert and map pages into global list
				insertPageByID(pageID, currentPage);
				
				pageNbr++;


			}
		}

		inputStream.close();
	}
	
	/***
	 * Insert Pages to a global list: gPage.
	 * 
	 */
	public synchronized void insertPageByID(String pageTRECID, Page record) 
	{
	    
	    if (!gPage.containsKey(pageTRECID))
	    {
	    	SortedList<Page> pagesByTRECID = new SortedList<Page>(Page.pageComp);
	    	pagesByTRECID.add(record);
	    	gPage.put(pageTRECID, pagesByTRECID);
	    }
	    else {

			SortedList<Page> recordsByTRECID = gPage.get(pageTRECID);
			
			if(!recordsByTRECID.contains(record))
	    		recordsByTRECID.add(record);
			
			gPage.put(pageTRECID, recordsByTRECID);
	    } 
		    
	    
	}
	
	/***
	 * Generate some statistics on the wrac file.
	 * 
	 */
	public void generateStatistics()
	{
		System.out.println(String.format("Archive contains %d resource URIs", gPage.size())); 
		
		for (String pageID: gPage.keySet())
		{
			SortedList<Page> PagesByID = gPage.get(pageID);
			
			
			int overallSize = PagesByID.stream().mapToInt(p->p.getDocumentSize()).sum();
			int numberOfUniqueWords= PagesByID.stream().mapToInt(p->p.getNbrOfUniqueTerms()).sum();
			System.out.println(String.format("For page with ID %s, Size: %d , Number of Words: %d ", pageID.toString(), overallSize,numberOfUniqueWords));
		    key.write(pageID.toString());
		    value.write(overallSize);
		    context.write(numberOfUniqueWords);
		}
		
	}
	
	/***
	 * Task 2.
	 * the method will output (k,v), k=#unique terms, v= array of PageIDs with the same # of unique terms .  
	 */
	
	public Map<Set<String>, List<String>> groupPagesWithSameUniqueTerms(){
		 
		Map<String,Map<String,Integer>> map = new HashMap <String,Map<String,Integer>>();
		 
		for (String pageID: gPage.keySet())
		{
			SortedList<Page> PagesByID = gPage.get(pageID);
			
			Page item = PagesByID.getFirst(); 
			map.put(item.getPageID(), item.getUniqueTerms()); 
			
		}
		
		// Using Lambda
		
		Map<Set<String>, List<String>> mapCommonTerms = map.entrySet().stream().collect(groupingBy(e -> e.getValue().keySet(),  mapping(Map.Entry::getKey, toList())));
		
		
		for (Set<String> item: mapCommonTerms.keySet())
		{
			List<String> value = mapCommonTerms.get(item);
			
			/*System.out.println(String.format("For Unique value: %s , the page IDs in common are: %s ", item.toString(), value.toString()));*/
			LongWritable context;
			LongWritable key;
			key.write(item.toString());
			context.write(value.toString());
	
		}
		
		
		return mapCommonTerms; 
	}
	
	/***
	 * Task 3.
	 * the method will output (k,v), k=PageID, v= array of unique terms .   
	 */
	
	public Map<String,Map<String,Integer>> mapPagesandTheirUniqueTerms(){
		 
		Map<String,Map<String,Integer>> map = new HashMap <String,Map<String,Integer>>();
		 
		for (String pageID: gPage.keySet())
		{
			SortedList<Page> PagesByID = gPage.get(pageID);
			
			Page item = PagesByID.getFirst(); 
			map.put(item.getPageID(), item.getUniqueTerms()); 
			
		}
		
		
		for (String item: map.keySet())
		{
			Map<String,Integer>value = map.get(item);
			
			/*System.out.println(String.format("For page with ID %s, The array of unique terms: %s ", item.toString(), value.keySet()));*/
			LongWritable context;
			LongWritable key;
			key.write(item.toString());
			context.write(value.keySet());
			
		}
		
		return map;
		
	}	 
	  public void main(String[] args) throws IOException {
	 
		String[] inputWarcFileList = {"ClueWeb09/00.warc.gz"}; 
		int counter = 1;
		for(String inputWarcFile: inputWarcFileList){
			System.out.println(String.format("Processing File number %d : %s ", counter, inputWarcFile));
			// Read the Wrac File
			readWarc(inputWarcFile);
			// Log records inside the file 
			generateStatistics();
			// Extra work1
			System.out.println("Extra Work 1");
			groupPagesWithSameUniqueTerms();	
			// Extra work2
			System.out.println("Extra Work 2");
			mapPagesandTheirUniqueTerms();
			counter++; 
	}
	
	}
	
}

  
  


