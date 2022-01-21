import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
class Server
{
  Socket s;
  ServerSocket ss;
  String o,out;
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
        FileWriter fw;
        if (f.createNewFile())
        {
          out = "FILE CREATED";
          fw = new FileWriter(o);
        }
        else
        {
          out = "FILE ALREADY EXIST";
          fw = new FileWriter(o,true);
        }
        fw.write("Hello World\n");
        fw.close();
      }
      else if(o.startsWith("cat"))
      {
        o = o.substring(4);
        File f = new File(o);
        if(f.exists())
        {
          sc = new Scanner(f);
          out = "";
          while (sc.hasNextLine())
          {
            String data = sc.nextLine();
            out = out + "\n" + data;
          }
          sc.close();
        }
        else
        {
          out = "FILE NOT FOUND";
        }
        out = out.trim();
        out = out + "\n\0";
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
          if(f.exists())
          {
            out = "FAILED TO DELETE THE FILE";
          }
          else
          {
            out = "FILE NOT FOUND";
          }
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
      System.out.println(out.trim() + " ==> " + o);
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
