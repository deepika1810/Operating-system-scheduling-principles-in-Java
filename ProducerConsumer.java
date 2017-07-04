import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 class ProConMonitor 
{             
    Vector v=new Vector();
    private final int n;
    private int count=0;
    Integer iobj; Object obj;
    private final Lock lock=new ReentrantLock();
    private final Condition notFull=lock.newCondition();
    private final Condition notEmpty=lock.newCondition();
    public ProConMonitor(int n)
        {
	 this.n=n;
         }
   public synchronized void additem(int item) throws InterruptedException //adding item into the buffer
   {  
      lock.lock();
      try
        {  if(count>=n)
	    notFull.wait();
           iobj=new Integer(item);                           //changing type for vector compatibility
           v.addElement(iobj);
           count++;
           notEmpty.signal();
          System.out.println("Producer produces "+item);
       }
      Finally
      {  lock.unlock();     }  
   }
  public synchronized Object removeitem() throws InterruptedException //removing the item from the buffer
   { 
      lock.lock();
      try
       {  if(count<=0)
	   notEmpty.wait();
          obj=v.firstElement();                                             //removing  the first element
          v.removeElementAt(v.indexOf(obj));  
          count--;
         notFull.signal(); 
         System.out.println("Consumer consumes "+obj);
         return(obj);
       }
     Finally
        {  lock.unlock();       }	 
   } 
}
 class Producer implements Runnable
{  
    private final int n;
    private final ProConMonitor pc;
    public Producer(ProConMonitor pc,int n)
         {
	 this.pc=pc;
	 this.n=n;
          }
   public synchronized void run() 
         {        Random r=new Random();
	   for(int i=0;i<n;i++)
	      { int x=r.nextInt(100);                                     //generating random item
	        Integer iobj=new Integer(x); 			
	         try     {           Thread.sleep(100);
			    pc.additem(iobj);           }
	         catch(Exception e)  {}
                     }			
          }
}	
class Consumer implements Runnable
{   private final int n;
     private final ProConMonitor pc;
     Object m;
    public Consumer(ProConMonitor pc,int n)
           {
	 this.pc=pc;
	 this.n=n;
             }
    public synchronized void run() 
    {          for(int i=0;i<n;i++)
	   {      try      {      Thread.sleep(200);
	                              m=pc.removeitem();           } 
	            catch(Exception e){} 
               }
    } 
}	 
  public class ProducerConsumer
{  
   public static void main(String args[])
  {           Scanner s=new Scanner(System.in);
              int n;
              System.out.println("Enter the size of the buffer:");
	n=s.nextInt();
	ProConMonitor pc=new ProConMonitor(n);
	Producer p=new Producer(pc,n);
	Consumer c=new Consumer(pc,n);
	Thread t1=new Thread(p);
	t1.start();                                                        //starting producer thread
	Thread t2=new Thread(c);
               t2.start();                                                        //starting consumer thread
  }	
  }
