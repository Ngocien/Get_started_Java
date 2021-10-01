package Socket;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class SignUp{
    JFrame frame = Basic_Frame.setFrame();

    String ip_, port_ = "";
    SignUp(String ip, String port)
    {
        ip_ = ip;
        port_ = port;
        setFrame();
    }

    public void setFrame()
    {
        frame.setTitle("SignUp");
        JLabel user_text = new JLabel("Username");
        JLabel pass_text = new JLabel("Password");
        JTextField user = new JTextField();
        JTextField pass= new JTextField();
        JButton submit = new JButton("SignUp");
        JButton back = new JButton("Back");

        user_text.setBounds(150, 50, 100, 30);
        user.setBounds(150, 80, 200, 30);
        pass_text.setBounds(150, 150, 100, 30);
        pass.setBounds(150, 180,200, 30);
        submit.setBounds(250, 250, 100, 30);
        back.setBounds(150,250,80,30);

        back.addActionListener(e->
        {
            new LogIn(ip_,port_);
            frame.setVisible(false);
        });

        submit.addActionListener(e->
        {
            try {
                new Chat("hello", "127.0.0.1", "6666");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        });

        frame.add(user_text);
        frame.add(pass_text);
        frame.add(user);
        frame.add(pass);
        frame.add(submit);
        frame.add(back);
        frame.setVisible(true);
    }
}
