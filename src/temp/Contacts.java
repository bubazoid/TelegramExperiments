package temp;

import org.javagram.TelegramApiBridge;
import org.javagram.response.MessagesMessages;
import org.javagram.response.object.Message;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.MessagesMessage;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bubnyshev on 04.05.2016.
 */
public class Contacts {
    private TelegramApiBridge bridge;
    private ArrayList<MessagesDialog> messageDialogs;
    //    private DefaultListModel model = new DefaultListModel();
    private ArrayList<UserContact> contactsList;


    public Contacts(TelegramApiBridge bridge) {
        this.bridge = bridge;
        try {
            messageDialogs = bridge.messagesGetDialogs();
//            bridge.messagesGetMessages();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTopMessage(int id) {
        String topMessage = "";
        for (MessagesDialog dialog : messageDialogs) {
            if (dialog.getPeerUser().getId() == id) {
//                dialog.
                Message message = dialog.getTopMessage();
                if (message.isOut()) {
                    topMessage = "Вы: " + message.getMessage();
                } else {
                    topMessage = message.getMessage();
                }
            }
        }
        return topMessage;
    }

    public void refreshDialogs() {
        try {
            messageDialogs = bridge.messagesGetDialogs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserContact> getContactsList(boolean forceUpdate) {

        if (contactsList == null || forceUpdate) {
            try {
                contactsList = bridge.contactsGetContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contactsList;
    }


    public ArrayList<MessagesMessage> getMessages(UserContact contact) {
        ArrayList<MessagesMessage> messages = null;
        try {
            messages = bridge.messagesGetHistory(contact, 0, Integer.MAX_VALUE, 10).getMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }
}