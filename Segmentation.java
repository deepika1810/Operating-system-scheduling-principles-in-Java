import java.io.*;
public class Segmentation
{
   static int add;
   static int addr[];
   static int offset[];
   static int seg[];
   Segmentation()
   {
      addr=new int[16];
      offset=new int[12];
      seg=new int[4];
   }

   static void input()throws IOException
   {
      InputStreamReader read=new InputStreamReader(System.in);
      BufferedReader in=new BufferedReader(read);
      System.out.println("Enter the virtual address: ");
      add=Integer.parseInt(in.readLine()); 
   }

   static void binary(int x[],int n,int t)
   {
      int r,i=t-1;
      while(n>0)
      {
         r=n%2;
         x[i--]=r;
         n=n/2;
      }
   }

   static int decimal(int x[],int n)
   {
      int i,dec=0,t=0;
      int pow[]={1024,512,256,128,64,32,16,8,4,2,1};
      for(i=n-1;i>=0;i--)
      {
         if(x[i]==1)
            dec=dec+x[i]*pow[10-t];
         t++;
      }
      return dec;
   }

   static void segment()
   {
      int table[]={345,119,1001,782,492,613,564,931,25,0,12243,5664,3451,6592,11894,10201};
      int j=0,i;
      for(i=0;i<16;i++)
      {   
         if(i>3)
            offset[j++]=addr[i];
         else
            seg[i]=addr[i];
      }
    
      int segno=decimal(seg,4);
      int base=table[segno];
      System.out.println("Segment no.= "+segno);
      System.out.println("Base address= "+base);
      int off=decimal(offset,12);
      System.out.println("Offset= "+off);
      int address=base+off;
      System.out.println("Physical address: "+address);
   }

   public static void main(String args[])throws IOException
   {
      InputStreamReader read=new InputStreamReader(System.in);
      BufferedReader in=new BufferedReader(read);
      System.out.println("Secondary memory: 64KB");
      System.out.println("Main memory: 4KB");   
      Segmentation ob=new Segmentation();
      ob.input();
      ob.binary(addr,add,16);
      ob.segment();
   }
}

