import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client
{
  Socket s;
  static Scanner sc = new Scanner(System.in);
  PrintWriter pw;
  BufferedReader inp;
  static String in,str;
  Client(String add , int port)
  {
    try
    {
      s = new Socket(add,port);
      System.out.println("SUCCESSFULLY CONNECTED TO SERVER");
      pw = new PrintWriter(s.getOutputStream(),true);
      inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    catch (IOException e)
    {
      System.out.println("CONNECTION TO SERVER FAILED");
    }
    System.out.println("\n\t       COMMAND : DESCRIPTION");
    System.out.println("create <file_name.txt> : CREATE AN EMPTY FILE ");
    System.out.println("   cat <file_name.txt> : READ THE CONTENTS OF FILE ");
    System.out.println("  edit <file_name.txt> : WRITE SOME TEXT TO THE FILE");
    System.out.println("delete <file_name.txt> : DELETE A FILE");
    System.out.println("                  exit : EXIT FROM THE PROGRAM");
    do
    {
      System.out.print("\nENTER THE COMMAND : ");
      in = sc.nextLine();
      if(in.equalsIgnoreCase("exit"))
        break;
      else
      {
        pw.println(in);
        pw.flush();
        try
        {
          System.out.println(inp.readLine());
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }while(!in.equals("exit"));
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
    str = sc.nextLine();
    new Client(str,4444);
  }
}
