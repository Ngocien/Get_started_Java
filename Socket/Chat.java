package Socket;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chat
{
    JFrame frame = Basic_Frame.setFrame();
    JTextArea textField = new JTextArea("");
    String content ="";
    String name, ip, port ="";


    Chat(String username, String ip_, String port_) throws IOException {
        ip = ip_;
        port = port_;
        name = username;
        chatting_field();
        setFrame();
    }
    public void setFrame()
    {
        frame.setTitle("Chat");
        frame.setVisible(true);
    }

    public void chatting_field() throws IOException {

        Icon icon = new ImageIcon("src/Socket/send_.jpg");
        JButton send = new JButton(icon);
        JTextField mess = new JTextField();

        textField.setEditable(false);
        Connection client = new Connection();
        client.startConnection(ip, Integer.parseInt(port));

        mess.setBounds(10,280, 380, 50);
        send.setBounds(400, 290, 60, 30);
        frame.add(mess);
        frame.add(send);
        frame.setVisible(true);

        send.addActionListener(e->
        {
            String text = mess.getText();
//            textField.append(name + ": " + text);
            try {
                client.sendMessage(text+"\n");
                mess.setText("");
                textField.setText(client.receiveMessage());
                JScrollPane scroll = new JScrollPane(textField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scroll.setBounds(10, 11, 455, 249);
                frame.getContentPane().add(scroll);
                frame.setLocationRelativeTo ( null );
                frame.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            Mess t = new Mess(name, text);
//            content.add(t);
//            Content_mess(content);

        });

    }

}
