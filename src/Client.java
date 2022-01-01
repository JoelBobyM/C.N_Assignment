import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
class Client
{
  Socket s;
  static Scanner sc,inp;
  PrintWriter pw;
  static String in,str,in_edit;
  Client(String add , int port)
  {
    try
    {
      s = new Socket(add,port);
      System.out.println("SUCCESSFULLY CONNECTED TO SERVER");
      pw = new PrintWriter(s.getOutputStream(),true);
      inp = new Scanner(s.getInputStream());
    }
    catch (IOException e)
    {
      System.out.println("CONNECTION TO SERVER FAILED");
    }
    System.out.println("\n\t       COMMAND : DESCRIPTION");
    System.out.println("create <file_name.txt> : CREATE AN EMPTY FILE ");
    System.out.println("   cat <file_name.txt> : READ THE CONTENTS OF FILE ");
    System.out.println("  edit <file_name.txt> : WRITE (APPEND) A STRING TO THE FILE");
    System.out.println("delete <file_name.txt> : DELETE A FILE");
    System.out.println("                  exit : EXIT FROM THE PROGRAM");
    do
    {
      System.out.print("\nENTER THE COMMAND : ");
      in = sc.nextLine().trim();
      pw.println(in);
      pw.flush();
      if(in.startsWith("cat"))
      {
        while(!(in_edit = inp.nextLine()).equals("\0"))
        {
          System.out.println(in_edit);
        }
      }
      else if(in.startsWith("edit"))
      {
        System.out.print("ENTER THE STRING TO BE WRITTEN : ");
        in_edit = sc.nextLine().trim();
        pw.println(in_edit);
        System.out.println(inp.nextLine());
      }
      else
      {
        System.out.println(inp.nextLine());
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
    sc = new Scanner(System.in);
    System.out.print("ENTER THE SERVER ADDRESS : ");
    str = sc.nextLine();
    new Client(str,4444);
  }
}
