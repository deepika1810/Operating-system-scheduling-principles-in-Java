import java.io.*;
class FCFS
{
   public static void main(String args[])  throws IOException
   {
       InputStreamReader isr=new InputStreamReader(System.in);
       BufferedReader stdin=new BufferedReader(isr);
       int n,i,temp=0,j,l=0, current=-1,total=0,m=0;
       float avg_wait=0,avg_turnaround=0;
       System.out.println("Enter the no. of processes");
       n=Integer.parseInt(stdin.readLine());
       int burst[]=new int[n];
       int arrival[]=new int[n];
       int p[]=new int[n];
       int wait[]=new int[n];
       int turn[]=new int[n];
       System.out.println("Enter the burst time of processes in nanoseconds");
       for(i=0;i<n;i++)
            burst[i]=Integer.parseInt(stdin.readLine());
       for(i=0;i<n;i++)
            total=total+burst[i];	
       System.out.println("Enter the arrival time of processes in nanoseconds");
       for(i=0;i<n;i++)
            arrival[i]=Integer.parseInt(stdin.readLine());
       for(i=0;i<n;i++)
            p[i]=i+1;		
       for(i=0;i<n-1;i++)
       for(j=0;j<n-1-i;j++)
	 if(arrival[j]>arrival[j+1])
	    {                                      temp=arrival[j];
				arrival[j]=arrival[j+1];
				arrival[j+1]=temp;
				temp=burst[j];
				burst[j]=burst[j+1];
				burst[j+1]=temp;
				temp=p[j];
				p[j]=p[j+1];
				p[j+1]=temp;
				}
		
       j=0;
	   while(j<total)
	   for(i=0;i<n;i++)
	       if(burst[i]>0&&arrival[i]<=j)
	       { System.out.println("Process "+p[i]+" from interval "+j+" to "+(l=j+burst[i]));				          for(m=0;m<n;m++)
	               if(burst[m]!=0&&m!=i)
		     wait[m]=wait[m]+l-j;
	          burst[i]=0;		
	         if(l!=total&&burst[i]==0)
                                   turn[i]=l;				
	          if(l==total&&burst[i]==0)
		      turn[i]=total;
	          j=l;		
	      }			
	for(i=0;i<n;i++)
	{ avg_turnaround=avg_turnaround+turn[i]-arrival[i];
	  avg_wait=avg_wait+wait[i]-arrival[i];
	}
        System.out.println("The average turn-around time is "+(avg_turnaround/n)+"nS");	
        System.out.println("The average waiting time is "+(avg_wait/n)+"nS");		
   }
}
