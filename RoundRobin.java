import java.util.*;
 public class RR
{
   public static void main(String args[])  
      {
       Scanner s=new Scanner(System.in);
       int n=0,i,t=0,total=0,j,temp=0,k,m;
       float avg_turnaround=0,avg_wait=0;
       System.out.println("Enter the no. of processes");
       n=s.nextInt();
       int burst[]=new int[n];
       int arrival[]=new int[n];
       int p[]=new int[n];
       int wait[]=new int[n];
       int turn[]=new int[n];
       System.out.println("Enter the burst time of processes in nanoseconds");
       for(i=0;i<n;i++)
            burst[i]=s.nextInt();
       for(i=0;i<n;i++)
	total=total+burst[i];	
       System.out.println("Enter the arrival time of processes in nanoseconds");
       for(i=0;i<n;i++)
            arrival[i]=s.nextInt();
       System.out.println("Enter the time quantum:");	
            t=s.nextInt();
       for(i=0;i<n;i++)
            p[i]=i+1;	
        for(i=0;i<n-1;i++)
            for(j=0;j<n-1-i;j++)
	         if(arrival[j]>arrival[j+1])
		{
		    temp=arrival[j];
		    arrival[j]=arrival[j+1];
		    arrival[j+1]=temp;
		     temp=burst[j];
		     burst[j]=burst[j+1];
		      burst[j+1]=temp;
		       temp=p[j];
		       p[j]=p[j+1];
		       p[j+1]=temp;
		}
       j=0; k=0;
      while(j<total)
	for(i=0;i<n;i++)
                   { if(burst[i]>0)
                         {	if(burst[i]<t)
		   {  k=j;
		     System.out.println("Process "+p[i]+" from interval "+j+" to "+(j=j+burst[i]));
		      burst[i]=0; 
		   }
                      else
                       {  k=j;
                           burst[i]=burst[i]-t;
		System.out.println("Process "+p[i]+" from interval "+j+" to "+(j=j+t));
	                }
                      for(m=0;m<n;m++)
	                if(burst[m]!=0&&m!=i)
		         wait[m]=wait[m]+j-k;
                        if(j!=total&&burst[i]==0)
                                     turn[i]=turn[i]+j;				
                       if(j==total&&burst[i]==0)
	                         turn[i]=turn[i]+total;
	          }		
                    }
         for(i=0;i<n;i++)
            {avg_turnaround=avg_turnaround+turn[i]-arrival[i];
	avg_wait=avg_wait+wait[i]-arrival[i];
            }
System.out.println("The average turn-around time is "+(avg_turnaround/n)+"nS");	
 System.out.println("The average waiting time is "+(avg_wait/n)+"nS");			  
   }
}			  
