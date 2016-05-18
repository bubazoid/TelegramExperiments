package temp;

import org.javagram.response.object.Message;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 12.05.2016.
 */
public class MessageTextPane extends JTextPane {

    public MessageTextPane(String message, Color background) {
        setText(message);
        setBackground(background);

    }

}
