import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

class Server implements Serializable
{
  Socket s;
  ServerSocket ss;
  BufferedReader in;
  String o,c_Ip,out;
  InetSocketAddress socketAddress;
  PrintWriter pw;
  Scanner sc;
  Server(int port) throws Exception
  {
    ss = new ServerSocket(port);
    s = ss.accept();
    socketAddress = (InetSocketAddress) s.getRemoteSocketAddress();
    c_Ip = socketAddress.getAddress().getHostAddress();
    System.out.println("CONNECTED TO : "+ c_Ip);
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    pw = new PrintWriter(s.getOutputStream(),true);
    while ((o = in.readLine()) != null)
    {
      o = o.trim();
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
        pw.println(out);
        pw.flush();
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
        pw.println(out);
        pw.flush();
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
          out = out + " " + data;
        }
        pw.println(out);
        pw.flush();
        sc.close();
      }
    }
    s.close();
    ss.close();
    in.close();
  }
  public static void main(String[] args) throws Exception
  {
    new Server(4444);
  }
}
