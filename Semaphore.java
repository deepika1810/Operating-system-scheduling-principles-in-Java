public class Semaphore
{	private int value;                                                 /*the value of semaphore*/
	public Semaphore()                                          /*constructor*/
	         {           value=0;         }
	public Semaphore(int i)
	         {          value=i;           }
	/*Wait() implemented as synchronized to ensure that it is done atomically*/
	public synchronized void Wait()
	        {	       value--;	                                   /*decrement the semaphore*/
		       if(value<0)                                     /*force the process to wait if value<0 */
		           {	   try
			     {     wait();               }
			   catch (InterruptedException e){}     }
                       }
	/*Signal() is implemented as synchronized to ensure it is done atomically*/
	public synchronized void Signal()
	{	      value++;                            /* increment semaphore value*/
		      if(value<=0)	                  /*wake up any waiting processes*/
			notify();
	}
}
 
