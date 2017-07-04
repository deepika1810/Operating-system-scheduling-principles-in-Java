import java.io.*;
class LFU
{
    int pgno;
    int freq;
   public static int search(LFU x[],int data, int n)
    {
       int i;
       for(i=0;i<n;i++)
          if(x[i].pgno==data)
	         return i;
       return -1;
    }
   public static void incr(LFU x[],int data, int n)
    {
       int pos=-1;
       pos=search(x,data,n);
       if(pos!=-1)
          x[pos].freq++;
    }
  public static void insert(LFU x[], LFU y,int n)
    {
       x[n].pgno=y.pgno;
       x[n].freq=y.freq;
     }
   public static  void replace(LFU x[], LFU old, LFU next, int n)
    {
       int pos=-1;
       pos=search(x,old.pgno,n);
       x[pos].pgno=next.pgno;
       x[pos].freq=next.freq;
    }
   public static void show(LFU x[], int n)
    {
       int i;
       for(i=0;i<n;i++)
           System.out.print(x[i].pgno+" ");
	 //  System.out.print("\t");
	  // for(i=0;i<n;i++)
      //     System.out.print(x[i].freq+" ");
		   
    }
    public static void main(String args[])throws IOException
    {
       InputStreamReader read=new InputStreamReader(System.in);
       BufferedReader in=new BufferedReader(read);
       LFU list[]=new LFU[10];
       LFU main[]=new LFU[10];
       LFU fifo[]=new LFU[10];
      
       int i,j,k,len,m, frames, hit,flag1,flag2,f,l,pos;
       float hit_ratio;
       for(i=0;i<10;i++)
	   {
		   list[i]=new LFU();
           main[i]=new LFU();
           fifo[i]=new LFU();
       }
       System.out.println("Enter the length of string: ");
       len=Integer.parseInt(in.readLine());
       int pg[]=new int[len];
       int check[]=new int[len];
       System.out.println("Enter the string: ");
       for(i=0;i<len;i++)
           pg[i]=Integer.parseInt(in.readLine());
       System.out.println("Enter the no. of frames: ");
       frames=Integer.parseInt(in.readLine());
       f=l=hit=0;     
       System.out.println("PAGE NO.   MAIN MEMORY");
       for(i=0;i<len;i++)
       {
           flag1=-1;
           flag1=search(main,pg[i],f);
           if(flag1!=-1)
           {
               hit++;
               main[flag1].freq++;
               incr(list,pg[i],l);
           }
           else
           {
               if(f<frames)
               {
                  list[l].pgno=pg[i];
			      list[l].freq=1;
			      insert(main,list[l],f);
		          f++; 
		          l++;
		        }
	           else               
               {
                  LFU swap=new LFU();
				  swap.pgno=-1;
				  swap.freq=20;
	              for(j=0;j<f;j++)
		             if(main[j].freq<swap.freq)
		                {swap=main[j];  
						//System.out.print("   "+swap.pgno+ "   "+swap.freq);
						}
		          k=0;
	              for(j=0;j<f;j++)
		             if(main[j].pgno!=swap.pgno&&main[j].freq==swap.freq)
                     {  
                         fifo[k]=main[j];  
                         check[k]=5; 
                         k++;
                     }
                  if(k!=0)
                  {
	                 fifo[k]=swap;  
	                 check[k]=5; 
	                 k++;
					// show(fifo,k);
					//System.out.print("     ");
	                 m=0; 
	                 for(j=i-1;j>=0&&m<k;j--)
		               {  pos=-1; pos=search(fifo,pg[j],k);
					      if(pos!=-1)
						    if(check[pos]==5)
		                {   
		                    swap=fifo[pos]; 
		                    check[pos]=1; 
		                    m++;
		                }
					}
	              }
	              flag2=0;
	              flag2=search(list,pg[i],l);
	              if(flag2!=-1)      
	                 replace(main,swap,list[flag2],f);
	              else
                  {
	                 list[l].pgno=pg[i];
	                 list[l].freq=1;
	                 replace(main,swap,list[l],f);  
	                 l++;
	              }
	          }
          }
          System.out.print("\n   "+pg[i]+"       ");
          if(flag1!=-1)
	         System.out.print("HIT");
          else
	         show(main,f);
       }
       hit_ratio=(hit*100f)/len;
       System.out.println("\n\nNumber of hits : "+hit);
       System.out.println("The hit ratio is "+hit_ratio+"%");
    }
}
                  

       


