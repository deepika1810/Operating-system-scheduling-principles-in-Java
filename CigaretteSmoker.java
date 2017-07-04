import  java.io.*;
import  java.util.*;
public class CigaretteSmoker
{       public static int Num=3;               /* the number of smokers*/
        public static Semaphore mutex=new Semaphore(1);   /*semaphore variable for mutual exclusion*/
        public static Smokers s[]=new Smokers[Num];   /* 3 smokers */
        public static Agent agent=new Agent();                 /*1 agent*/
	
          public static void main(String args[])
	{   System.out.println("Smoker1       Smoker2      Smoker3");
	    System.out.println("-------       -------      -------");
                   agent.start();                                             /* Initializing & Launching threads */
		for(int i=0;i<Num;i++)
		{       s[i]=new Smokers(i,agent);
		        s[i].start();                                }
	}
}	
class P
{	public static synchronized void Print()
	{             for(int i=0;i<CigaretteSmoker.Num;i++)
		      System.out.print(CigaretteSmoker.s[i].status+"       "); /*printing status of smokers*/
		System.out.println("");                                                               }	
}
class Agent extends Thread                                                  /*agent*/
{           public String generatedIngredients;
            public Agent()
	  {             generatedIngredients="";           }  /*constructor*/
            public synchronized void run()                             /*agent places ingredients*/
                {     Random r=new Random();               
	        while(true)
	           {	 int x=r.nextInt(3);                  
     	                if(x==0)
	                         generatedIngredients="TOBACCO and PAPER";
	               else if(x==1)
                                       generatedIngredients="PAPER and MATCHES";
                             else 
                                       generatedIngredients="TOBACCO and MATCHES";	  }
               }              
}	 
class Smokers extends Thread                                              /*smokers*/
{          private Agent agent;
            public String status;
            private String ownIngredient;
            private String missingIngredients;
            private int Factor=3000;
            public Smokers(int x,Agent agent)                                  /*constructor*/
	   {              this.agent=agent;
	                   status="Waiting";
	                  if(x==0)
	                     {	 ownIngredient="TOBACCO";
		                missingIngredients="PAPER and MATCHES";           }
	                  else if(x==1)
	                      {	 ownIngredient="PAPER";
		                missingIngredients="TOBACCO and MATCHES";     }
	                  else
	                      {	 ownIngredient="MATCHES";
		               missingIngredients="TOBACCO and PAPER";            }
	 }
            public synchronized void run()
	 {	   while(true)
	                 if(agent.generatedIngredients==missingIngredients)
	                 {            CigaretteSmoker.mutex.Wait();                          /*smoker enters CS*/
			  status="SMOKING";
			  P.Print();
			  int SleepTime=(int)(Math.random()*Factor);
			  try
			   {
				Thread.sleep(SleepTime);
			   }
			  catch (InterruptedException e){}
			  status="Waiting";
			  CigaretteSmoker.mutex.Signal();                   /*smoker leaves CS*/
			   SleepTime=(int)(Math.random()*Factor);
			  try
			   {
				Thread.sleep(SleepTime);
			   }
			  catch (InterruptedException e){}
	              }			
             }
}   
