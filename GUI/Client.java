import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
class Client
{
  String ip,msg;
  JFrame f1,f2;
  JLabel lb_title,lb_ip;
  JTextField txt_ip,txt_msg;
  JButton btn_cnct,btn_snd;
  Socket s;
  PrintWriter pw;
  Client()
  {
    f1 = new JFrame("Client - SetUp");
    lb_title = new JLabel("CONNECT -> SERVER");
    lb_ip = new JLabel("SERVER IP ADDRESS : ");
    txt_ip = new JTextField(20);
    btn_cnct = new JButton("CONNECT");

    f1.add(lb_title);
    f1.add(lb_ip);
    f1.add(txt_ip);
    f1.add(btn_cnct);

    lb_title.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC, 20));
    lb_title.setBounds(95,20,250,20);
    lb_ip.setBounds(20,60,150,30);
    txt_ip.setBounds(180,60,200,30);
    btn_cnct.setBounds(130,110,170,40);

    btn_cnct.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          ip = txt_ip.getText();
          s = new Socket(ip,4444);
          System.out.println("CONNECTION SUCCEEDED");
          f2.setVisible(true);
          f1.setVisible(false);
        }
        catch(Exception ex)
        {
          System.out.println("CONNECTION FAILED : " + ex);
        }
      }
    });

    f1.setSize(400,210);
    f1.setLayout(null);
    f1.setVisible(true);
    f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);

    f2 = new JFrame("SERVER COMMUNICATION");
    txt_msg = new JTextField(15);
    btn_snd = new JButton("SEND");

    f2.add(txt_msg);
    f2.add(btn_snd);

    txt_msg.setBounds(10,85,270,30);
    btn_snd.setBounds(290,85,100,30);

    btn_snd.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          pw = new PrintWriter(s.getOutputStream(),true);
          msg = txt_msg.getText();
          pw.println(msg);
          pw.flush();
          pw.close();
        }
        catch (IOException ex)
        {
          System.out.println(ex);
        }
      }
    });

    f2.addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
        super.windowClosing(e);
        try
        {
          s.close();
        }
        catch (IOException ex)
        {
          ex.printStackTrace();
        }
      }
    });

    f2.setSize(400,200);
    f2.setLayout(null);
    f2.setVisible(false);
    f2.setDefaultCloseOperation(f2.EXIT_ON_CLOSE);
  }
  public static void main(String[] args)
  {
    new Client();
  }
}
