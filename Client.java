import java.io.*;
import java.net.*;
import java.util.*;
class Client
{
  Socket s;
  static Scanner sc = new Scanner(System.in);
  PrintWriter pw;
  String in = "";
  Client(String add , int port) throws Exception
  {
    s = new Socket(add,port);
    System.out.println("CONNECTED TO SERVER : " + str);
    pw = new PrintWriter(s.getOutputStream(),true);
    while(!in.equalsIgnoreCase("done"))
    {
      in = sc.nextLine();
      pw.println(in);
      pw.flush();
    }
    pw.close();
    s.close();
  }
  public static void main(String[] args) throws Exception
  {
    System.out.print("ENTER THE SERVER ADDRESS : ");
    String str = sc.nextLine();
    new Client(str,4444);
  }
}
