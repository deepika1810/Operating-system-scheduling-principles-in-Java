import java.io.*;
class LRU
{
    static int stackarr[];
    static int top,c,bottom,size;  
    LRU()
    {
        top=-1;
        bottom=0;
        c=0;
        size=5;
        stackarr=new int[size];
    }
    static void push(int value)
    {
        if(top==size-1)
           System.out.println("Stack overflow");
        else
        {
            top++;
            stackarr[top]=value;
            c++;
        }
    }
    static int search(int data)
    {
        int i;
        for(i=bottom;i<=top;i++)
           if(stackarr[i]==data)
              return i;
        return -1;
    }    
    static void pop(int x)
    {     
        int i,pos;
        pos=search(x);
        if(pos==-1)
           return;
        if(pos!=top)
          for(i=pos;i<top;i++)
             stackarr[i]=stackarr[i+1];
        top--;
        c--;
    }
    static void display()
    {
        if(top<bottom)
           return;
        for(int i=bottom;i<=top;i++)
            System.out.print(stackarr[i]+" ");
    }
    public static void main()throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        int i,j,k,len,frames,hit,flag,swap,pos;
        float hit_ratio;
        hit=k=0;
        LRU ob=new LRU();
        System.out.println("Enter the length of string: ");
        len=Integer.parseInt(in.readLine());
        int pg[]=new int[len];
        int check[]=new int[len];
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
            if(flag!=-1)
              hit++;
            else
            {
                if(c<frames)
                {
                    push(pg[i]);
                    check[k++]=0;
                }
                else
                {
                    swap=-1;
                    for(j=i-1;j>=0;j--)
                       if((pos=search(pg[j]))!=-1)
                          if(check[pos]==0)
                             {
                                 swap=pg[j];
                                 check[pos]=1;                              
                             }
                    for(j=bottom;j<=top;j++)
                       check[j]=0;
                    pop(swap);
                    push(pg[i]);
                    check[k++]=0;
                }
            }
            System.out.print("\n   "+pg[i]+"         ");
            if(flag!=-1)
               System.out.print("HIT");
            else
               display();
        }
        hit_ratio=((hit*100f)/len);
        System.out.println("\n\nNumber of hits: "+hit);
        System.out.println("The hit ratio is: "+hit_ratio+"%");
    }
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        