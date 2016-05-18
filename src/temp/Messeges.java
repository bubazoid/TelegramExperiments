package temp;

import org.javagram.TelegramApiBridge;
import org.javagram.response.object.MessagesDialog;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergey on 01.05.2016.
 */
public class Messeges {
    private TelegramApiBridge bridge;
    ArrayList<MessagesDialog> messagesDialogs;

    public Messeges(TelegramApiBridge bridge) {
        this.bridge = bridge;
        try {
            messagesDialogs = bridge.messagesGetDialogs();
            for (MessagesDialog dialog : messagesDialogs) {
                dialog.getTopMessage().getMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
