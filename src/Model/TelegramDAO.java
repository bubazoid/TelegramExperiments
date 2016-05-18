package Model;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by HerrSergio on 06.05.2016.
 */
public interface TelegramDAO extends Closeable {
    Status isContactOnline();
    void acceptNumber(String phoneNumber) throws IOException;
    void sendCode() throws IOException;

    default void acceptNumberAndSendCode(String phoneNumber) throws IOException {
        acceptNumber(phoneNumber);
        sendCode();
    }

    String getPhoneNumber();

    Me signIn(String code) throws IOException;
    Me signUp(String code, String firstName, String lastName) throws IOException;
    boolean logOut();
    boolean isLoggedIn();
    boolean canSignUp();
    boolean canSignIn();
    boolean isClosed();
    Me getMe() throws IOException;

    ArrayList<Contact> getContacts() throws IOException;
    ArrayList<Dialog> getDialogs() throws  IOException;
    ArrayList<Message> getMessagesOfContact(int id, int lastMessageId, int limit) throws IOException;
    ArrayList<Message> getMessagesOfForeign(int id, long accessHash, int lastMessageId, int limit) throws IOException;

    boolean isContactOnline(Contact contact);

    default ArrayList<Message> getMessages(Person person, Message last, int limit) throws IOException {
        int lastMessageId = 0;
        if(last != null)
            lastMessageId = last.getId();
        if(person instanceof Contact) {
            return getMessagesOfContact(person.getId(), lastMessageId, limit);
        } else if(person instanceof Foreign) {
            return getMessagesOfForeign(person.getId(), ((Foreign) person).getAccessHash(), lastMessageId, limit);
        } else {
            throw new IllegalArgumentException();
        }
    }

    default LinkedHashMap<Person, Dialog> getList(boolean excludeForeign, boolean excludeEmpty) throws IOException {
        LinkedHashMap<Person, Dialog> list = new LinkedHashMap<>();
        for(Dialog dialog : getDialogs()) {
            if(dialog.getBuddy() instanceof Foreign && excludeForeign)
                continue;
            list.put(dialog.getBuddy(), dialog);
        }
        if(!excludeEmpty) {
            for (Contact contact : getContacts()) {
                list.putIfAbsent(contact, null);

            }
        }
        return list;
    }

    void close();

    State getState() throws IOException;
    Updates getUpdates(State state) throws IOException;
}
