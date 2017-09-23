import sys
def deleteFromCir(n,m,k):
	a=0
	temp_circle= list(range(0,n))
	while True:
		if len(temp_circle)==k:
			break
		a=(a+(m-1))%len(temp_circle)
		del temp_circle[a]
	print('The last 4 nos remaining is:', temp_circle[-k:])
	
if __name__=='__main__':

    deleteFromCir(int(sys.argv[1]),int(sys.argv[2]),int(sys.argv[3]))