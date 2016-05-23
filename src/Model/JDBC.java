package Model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bubnyshev on 20.05.2016.
 */
public class JDBC {
    TelegramDAO apiBridgeTelegramDAO;

    public JDBC(TelegramDAO apiBridgeTelegramDAO) {
        this.apiBridgeTelegramDAO = apiBridgeTelegramDAO;

    }

    public ArrayList<Message> getMessagesByContact(Contact contact) throws IOException {
        return apiBridgeTelegramDAO.getMessagesOfContact(contact.getId(), 0, Short.MAX_VALUE);
    }
}
