import java.io.ObjectOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
class Server
{
  Socket s;
  ServerSocket ss;
  BufferedReader in,reader;
  String o,c_Ip;
  //String line = "";
  Process process;
  InetSocketAddress socketAddress;
  ObjectOutputStream out;
  Server(int port) throws Exception
  {
    ss = new ServerSocket(port);
    s = ss.accept();
    socketAddress = (InetSocketAddress) s.getRemoteSocketAddress();
    c_Ip = socketAddress.getAddress().getHostAddress();
    System.out.println("CONNECTED TO : "+ c_Ip);
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    out = new ObjectOutputStream(s.getOutputStream());
    while(true)
    {
      o = in.readLine();
      if (o.equalsIgnoreCase("done"))
      {
        break;
      }
      process = Runtime.getRuntime().exec(o.trim());
      reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      out.writeObject(reader);
      /*while ((line = reader.readLine()) != null)
      {
        System.out.println(line);
      }*/
    }
    s.close();
    in.close();
  }
  public static void main(String[] args) throws Exception
  {
    new Server(4444);
  }
}
