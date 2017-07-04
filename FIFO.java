import java.io.*;
public class FIFO
{
    static int q[]; 
    static int f,r,size,n;
    FIFO()
    {
        size=5;
        q=new int[size];
        f=0;
        r=-1;
        n=0;
    }
    static boolean empty()
    {
        if(r<f)
           return true;
        else 
           return false;
    }
    static void insert(int data)   //inserts element at rear of queue
    {
        if(r==(size-1))
            System.out.println("Queue is full");
        else
        {
            r++;
            q[r]=data;
            n++;
        }
    }
    static void remove()  //removes element from front of queue
    {
        if(empty())
        {
            System.out.println("Queue is empty");
        }
        else
        {
            for(int i=0;i<r;i++)
               q[i]=q[i+1];
            r--;
            n--;
        }   
    }
    static int search(int data)  //returns 1 if data is present, else returns 0
    {
        int i;
        if(empty())
           return 0;
        for(i=f;i<r;i++)
           if(q[i]==data)
              return 1;
        return 0;
    }
    static void display()  //displays elements of queue from front to rear
    {
        if(empty())
           System.out.println("Queue is empty");
        else
        {
           //System.out.print("       ");
           for(int i=f;i<=r;i++)
             System.out.print(q[i]+" "); 
           System.out.println();
        }
    }
    public static void main()throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        int len,i,frames,flag,hit=0;
        float hit_ratio;
        FIFO ob=new FIFO();
        System.out.println("Enter the length of string: ");
        len=Integer.parseInt(in.readLine());
        int pg[]=new int[len];
        System.out.println("Enter the string: ");
        for(i=0;i<len;i++)
           pg[i]=Integer.parseInt(in.readLine());
        System.out.println("Enter the no. of frames: ");
        frames=Integer.parseInt(in.readLine());
        System.out.println("PAGE NO.  MAIN MEMORY");
        for(i=0;i<len;i++)
        {
            flag=0;
            flag=search(pg[i]);
            if(flag==1)
               hit++;
            else
            {
                if(n<frames)
                   insert(pg[i]);
                else
                {
                    remove();
                    insert(pg[i]);
                }
            }
            //System.out.println();
            System.out.print("   "+pg[i]+"         ");
            if(flag==1)
	           System.out.print("HIT\n");
	        else
	           display();
        }
        hit_ratio=(float)((hit*100)/len);
        System.out.println("\nNumber of hits: "+hit);
        System.out.println("\nThe hit ratio is: "+hit_ratio+"%");
    }
}