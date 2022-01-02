import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
class Server
{
  Socket s;
  ServerSocket ss;
  String o,c_Ip,out;
  InetSocketAddress socketAddress;
  PrintWriter pw;
  Scanner in,sc;
  Server(int port) throws Exception
  {
    ss = new ServerSocket(port);
    System.out.println("SERVER STARTED \nWAITING FOR CLIENT CONNECTION......................... ");
    s = ss.accept();
    System.out.println("CLIENT CONNECTED \nCLIENT INFORMATION : "+ s);
    in = new Scanner(s.getInputStream());
    pw = new PrintWriter(s.getOutputStream(),true);
    do
    {
      o = in.nextLine().trim();
      if(o.startsWith("create"))
      {
        o = o.substring(7);
        File f = new File(o);
        if (f.createNewFile())
        {
          out = "FILE CREATED";
        }
        else
        {
          out = "FILE ALREADY EXIST";
        }
      }
      else if(o.startsWith("cat"))
      {
        o = o.substring(4);
        File f = new File(o);
        sc = new Scanner(f);
        out = "";
        while (sc.hasNextLine())
        {
          String data = sc.nextLine();
          out = out + "\n" + data;
        }
        out = out + "\n\0";
        sc.close();
      }
      else if(o.startsWith("edit"))
      {
        o = o.substring(5);
        FileWriter f = new FileWriter(o,true);
        out = in.nextLine();
        f.write(out+"\n");
        f.close();
        out = "SUCCESSFULLY WROTE TO THE FILE";
      }
      else if(o.startsWith("delete"))
      {
        o = o.substring(7);
        File f = new File(o);
        if (f.delete())
        {
          out = "FILE DELETED";
        }
        else
        {
          out = "FAILED TO DELETE THE FILE";
        }
      }
      else if(o.equals("exit"))
      {
        out = "DISCONNECTING FROM SERVER";
      }
      else
      {
        out = "INVALID COMMAND RECEIVED";
      }
      pw.println(out);
      pw.flush();
      System.out.println(out + " ==> " + o);
    }while(!o.equals("exit"));
    s.close();
    ss.close();
    in.close();
  }
  public static void main(String[] args) throws Exception
  {
    new Server(4444);
  }
}
