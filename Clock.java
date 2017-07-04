import java.io.*;
public class Clock
{
   int page;
   int r;
   Clock()
   {
       r=0;
   }
   public static int search(Clock x[],int data)
    {
       int i,l;
       l=x.length;
       for(i=0;i<l;i++)
          if(x[i].page==data)
          {
              x[i].r=1;
	          return i;
	      }
       return -1;
    }
   public static void insert(Clock x[],int y,int n)
   {
       x[n].page=y;
       x[n].r=1;
   }
   public static void show(Clock x[])
   {
       int i,l;
       l=x.length;
       for(i=0;i<l;i++)
       {
           if(x[i].page!=0)
              System.out.print(x[i].page+" ");
       }
   }
   public static void main(String args[])throws IOException
   {
       InputStreamReader read=new InputStreamReader(System.in);
       BufferedReader in=new BufferedReader(read);
       int i,j,len,frames,hit=0,flag=0,f=0,l=0;
       float hit_ratio;
       System.out.println("Enter the length of string: ");
       len=Integer.parseInt(in.readLine());
       int pg[]=new int[len];
       int check[]=new int[5];
       System.out.println("Enter the string: ");
       for(i=0;i<len;i++)
          pg[i]=Integer.parseInt(in.readLine());
       System.out.println("Enter the no. of frames: ");
       frames=Integer.parseInt(in.readLine());
       Clock frame[]=new Clock[frames];
       for(i=0;i<frames;i++)
       {
           frame[i]=new Clock();
       }
       System.out.println("PAGE NO.   MAIN MEMORY");
       for(i=0;i<len;i++)
       {
           flag=0;
           flag=search(frame,pg[i]);
           if(flag!=-1)
           {
               hit++;
           }
           else
           {
               if(f<frames)
               {
                  insert(frame,pg[i],l);
		          f++; 
		          l++;
		        }
	           else               
               {
                   if(frame[frames-1].r==1)
                   {
                      for(j=0;j<frames;j++)
                      {
                          if(frame[j].r==1)
                             frame[j].r=0;
                      }
                   }
                   for(j=0;j<frames;j++)
                   {
                       if(frame[j].r==0)
                       {
                           insert(frame,pg[i],j);
                           break;
                        }
                    }         
               }
	       }
       
           System.out.print("\n   "+pg[i]+"       ");
           if(flag!=-1)
              System.out.print("HIT");
           else
	          show(frame);
	   }
       hit_ratio=(hit*100f)/len;
       System.out.println("\n\nNumber of hits : "+hit);
       System.out.println("The hit ratio is "+hit_ratio+"%");
    }
}
                  
                  
    