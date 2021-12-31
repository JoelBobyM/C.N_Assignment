import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client
{
  Socket s;
  static Scanner sc = new Scanner(System.in);
  static Scanner sci = new Scanner(System.in);
  PrintWriter pw;
  String in = "";
  BufferedReader reader;
  String line;
  Client(String add , int port)
  {
    try
    {
      s = new Socket(add,port);
      System.out.println("SUCCESSFULLY CONNECTED TO SERVER");
      pw = new PrintWriter(s.getOutputStream(),true);
    }
    catch (IOException e)
    {
      System.out.println("CONNECTION TO SERVER FAILED");
    }
    while(true)
    {
      System.out.println("\t\tMENU\n1.CREATE FILE IN SERVER'S DIRECTORY\n2.EDIT FILE IN SERVER'S DIRECTORY\n3.DISPLAY CONTENTS OF A FILE IN SERVER'S DIRECTORY\n4.DELETE A FILE IN SERVER'S DIRECTORY\n5.LIST ALL FILES IN SERVER'S DIRECTORY\n6.EXIT");
      System.out.print("ENTER YOUR CHOICE : ");
      int ch = sci.nextInt();
      if(ch == 6)
      {
        pw.println("done");
        pw.flush();
        break;
      }
      else if(ch == 5)
      {
        pw.println("ls");
        pw.flush();
        try
        {
          reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
          while ((line = reader.readLine()) != null)
          {
            System.out.println(line);
          }
          reader.close();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else if(ch>=1 && ch<=4)
      {
        System.out.print("ENTER THE NAME OF FILE (WITH EXTENSION) : ");
        in = sc.nextLine();
        in = in.trim();
        switch (ch)
        {
          case 1 ->
          {
            in = "touch " + in;
            pw.println(in);
            pw.flush();
          }
          case 3 ->
          {
            in = "cat " + in;
            pw.println(in);
            pw.flush();
          }
          case 4 ->
          {
            in = "rm " + in;
            pw.println(in);
            pw.flush();
          }
        }
      }
    }
    try
    {
      pw.close();
      s.close();
    }
    catch (IOException e)
    {
      System.out.println("IO EXCEPTION "+e);
    }
  }
  public static void main(String[] args)
  {
    System.out.print("ENTER THE SERVER ADDRESS : ");
    String str = sc.nextLine();
    new Client(str,4444);
  }
}
