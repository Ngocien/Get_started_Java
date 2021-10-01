package Socket;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class LogIn {
    JFrame frame = Basic_Frame.setFrame();
    List<Client> clientList = Handle_file.read_csv();
    String ip_, port_ = "";
    LogIn(String ip, String port)
    {
        ip_ = ip;
        port_ = port;
        setFrame();
    }

    public void setFrame()
    {

        frame.setTitle("Login");
        JLabel user_text = new JLabel("Username");
        JLabel pass_text = new JLabel("Password");
        JTextField user = new JTextField();
        JTextField pass= new JTextField();
        JButton login = new JButton("LogIn");
        JButton signUp = new JButton("SignUp");
        JLabel error = new JLabel("");


        user_text.setBounds(150, 50, 100, 30);
        user.setBounds(150, 80, 200, 30);
        pass_text.setBounds(150, 150, 100, 30);
        pass.setBounds(150, 180,200, 30);
        login.setBounds(250, 250, 100, 30);
        signUp.setBounds(150,250,80,30);

        error.setBounds(150, 300, error.getWidth(), 30);

        signUp.addActionListener(e ->
        {
            new SignUp(ip_,port_);
            frame.setVisible(false);

        });

        login.addActionListener(e ->
        {
            String n = user.getText();
            String p = pass.getText();

            for (Client i: clientList)
            {
                if(i.getName().equals(n) && i.getPass().equals(p))
                {
                    try {
                        new Chat(i.getName(), ip_, port_);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    frame.setVisible(false);
                }
                else
                {
                    error.setText("Please");
                }
            }

        });

        frame.add(user_text);
        frame.add(pass_text);
        frame.add(user);
        frame.add(pass);
        frame.add(login);
        frame.add(signUp);
        frame.add(error);
        frame.setVisible(true);
    }
}
