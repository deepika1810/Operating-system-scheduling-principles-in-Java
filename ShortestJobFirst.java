import java.util.*;
 public class SJF
{
   public static void main(String args[])  
    {
       Scanner s=new Scanner(System.in);
       int n=0,i,total=0,j,temp=0,m,min,current=-1,l=0;
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
		
	   j=0;
       while(j<total)
         {
	min=total;	
               for(i=0;i<n;i++)
               if(burst[i]>0&&arrival[i]<=j)
               if(burst[i]<min)
		{ min=burst[i]; current=i;}
	System.out.println("Process "+p[current]+" from interval "+j+" to "+(l=j+burst[current]));			for(m=0;m<n;m++)
		if(burst[m]!=0&&m!=current)
			wait[m]=wait[m]+l-j;
	burst[current]=0;		
	if(l!=total&&burst[current]==0)
                     turn[current]=l;				
	if(l==total&&burst[current]==0)
	       turn[current]=total;
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
