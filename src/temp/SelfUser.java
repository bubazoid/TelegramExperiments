package temp;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.object.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by bubnyshev on 19.04.2016.
 */
public class SelfUser extends Contact {
    private TelegramApiBridge bridge;
    private AuthAuthorization authAuthorization;
    private Contacts contacts;

    //    private boolean auth;
    private boolean newSignUp;
    private int authStatus;
    public static final int REGISTRED = 1;
    public static final int INVITED = 2;
    public static final int NOT_REGISTRED = 3;
    public static final int UNKNOWN = 4;


    public SelfUser(String phoneNumber) {
        super(phoneNumber);
        try {
            bridge = new TelegramApiBridge("149.154.167.50:443", 43373, "6d84f56fa896639638ff7b941bc83f45");
        } catch (IOException e) {
            e.printStackTrace();
        }
        authorization();

    }

    private void authorization() {
        newSignUp = false;
//        String phoneNumber = loginForm.getPhoneNumberField().getText().replaceAll("[^0-9]+", "");
        authStatus = UNKNOWN;
        if (phoneNumber.matches("^[1-9][0-9]{10}$")) {
            try {
                AuthCheckedPhone authCheckedPhone = bridge.authCheckPhone(phoneNumber);
                if (authCheckedPhone.isRegistered()) {

                    authStatus = REGISTRED;
                } else if (authCheckedPhone.isInvited()) {
                    authStatus = INVITED;
                    newSignUp = true;

                } else {

                    authStatus = NOT_REGISTRED;
                    newSignUp = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void sendSMSCode() {
        try {
            bridge.authSendCode(phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isAuth() {
        return authAuthorization != null;
    }


    public void updateProfile() {

    }


    public Integer getAuthStatus() {
        return authStatus;
    }


    public boolean auth(String code) {
        try {
            if (newSignUp) {
                authAuthorization = bridge.authSignUp(code, firstName, lastName);
            } else {
                authAuthorization = bridge.authSignIn(code);
                firstName = authAuthorization.getUser().getFirstName();
                lastName = authAuthorization.getUser().getLastName();

//                int id  = authAuthorization.getUser().getId();
//                authAuthorization.

            }
            contacts = new Contacts(bridge);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authAuthorization != null;
    }

    public void logOut() {
        try {
            bridge.authLogOut();
            authAuthorization = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void deleteContact(Model.Contact selectedUser) {
        try {
            bridge.contactsDeleteContact(selectedUser.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContact(String phone, String firstName, String lastName) {

    }

    public void updateContact(Model.Contact contact, String fio) {

    }

    public Image getIconImage() {
        User user = authAuthorization.getUser();
        Image image = null;
        try {
            BufferedImage buffImage = new BufferedImage(29, 29, BufferedImage.TYPE_INT_ARGB);
            if (user.getPhoto(true) != null) {
                buffImage = ImageIO.read(new ByteArrayInputStream(user.getPhoto(true)));
//            Image newIcon = image.getScaledInstance(41,41,Image.SCALE_SMOOTH);
            }
            image = buffImage.getScaledInstance(29, 29, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
//    DefaultListModel model = new DefaultListModel();
//    try {
//
//        selfUser.getContactsList(false).forEach(n -> {
//            model.addElement(n);
//        });
//        contactList.setModel(model);
//        contactList.setCellRenderer(new ContactCellRenderer());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    public SelfUser(String phoneNumber) {
//        super(phoneNumber);
//    }
//
//
//    public void sendSMSCode() {
//
//    }
//
//
//    public String getFormatetPhoneNumber() {
//        return "+7 987 505 25-89";
//    }
//
//
//    public ArrayList<UserContact> getContactsList(boolean forceUpdate) throws IOException {
//        ArrayList<UserContact> userContactArrayList = new ArrayList<>();
//        userContactArrayList.add(new UserContact(new TLUserContact(12545,"User1","LastName1",89875052,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(13455,"User2","LastName2",89875052,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(34566,"User3","LastName3",45345345,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(12235,"User4","LastName4",45345345,"89874534255",null,null)));
//        return userContactArrayList;
//    }
//
//
//    public boolean isAuth() {
//        firstName = "Сергей";
//        lastName = "Бубнышев";
//        return true;
//    }
//
//
//    public void updateProfile() {
//
//    }
//
//
//    public Integer getAuthStatus() {
//        return 1;
//    }
//
//
//    public boolean auth(String code) {
//        return true;
//    }
//
//
//    public void logOut() {
//
//    }
}
