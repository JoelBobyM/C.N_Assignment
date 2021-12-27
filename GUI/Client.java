import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
class Client
{
  String ip;
  JFrame f1,f2;
  JLabel lb_title,lb_ip;
  JTextField txt_ip;
  JButton btn_cnct;
  Socket s;
  Client()
  {
    f1 = new JFrame("Client - SetUp");
    lb_title = new JLabel("CONNECT -> SERVER");
    lb_ip = new JLabel("SERVER IP ADDRESS : ");
    txt_ip = new JTextField(20);
    btn_cnct = new JButton("CONNECT");

    lb_title.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC, 20));

    lb_title.setBounds(95,20,250,20);
    lb_ip.setBounds(20,60,150,30);
    txt_ip.setBounds(180,60,200,30);
    btn_cnct.setBounds(130,110,170,40);

    f1.add(lb_title);
    f1.add(lb_ip);
    f1.add(txt_ip);
    f1.add(btn_cnct);

    btn_cnct.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          ip = txt_ip.getText();
          s = new Socket(ip,4444);
          System.out.println("CONNECTION SUCEEDED");
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
  }
  public static void main(String[] args)
  {
    new Client();
  }
}
