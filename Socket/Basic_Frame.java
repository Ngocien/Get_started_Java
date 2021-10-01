package Socket;

import javax.swing.*;
import java.awt.*;
class Basic_Frame extends JFrame{

    public static JFrame setFrame()
    {
        JFrame frame = new JFrame();
        frame.setSize(500, 400);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

}
