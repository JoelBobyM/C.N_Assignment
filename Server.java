import java.net.*;
import java.io.*;
class Server
{
  Socket s;
  ServerSocket ss;
  BufferedReader in;
  InputStreamReader bf;
  String o = "";
  Server(int port) throws Exception
  {
    ss = new ServerSocket(port);
    s = ss.accept();
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    while(!o.equalsIgnoreCase("done"))
    {
      o = in.readLine();
      System.out.println(o);
    }
    s.close();
    in.close();
  }
  public static void main(String[] args) throws Exception
  {
    new Server(4444);
  }
}
