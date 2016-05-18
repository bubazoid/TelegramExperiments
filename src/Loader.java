import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.object.UserContact;
import org.telegram.api.TLUserContact;
import org.telegram.api.messages.TLMessage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Sergey on 21.11.2015.
 */
public class Loader {
    public static void main(String[] args) throws IOException, ParseException {
        FormController formController = new FormController();
//        formController.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formController.setVisible(true);

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 43373, "6d84f56fa896639638ff7b941bc83f45");
//
////        System.out.println("Введите номер телефона");
////        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(reader.readLine().trim());
////        System.out.println("Статус регистрации: " + checkedPhone.isRegistered());
//        System.out.println("Введите номер телефона для авторизации");
//        String phoneNumber = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim();
//        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
//        bridge.authSendCode(phoneNumber);
//        System.out.println("Введите код из смс ");
//
//        BufferedReader code = new BufferedReader(new InputStreamReader(System.in));
//        AuthAuthorization authAuthorization = bridge.authSignIn(code.readLine().trim());
//        System.out.println(authAuthorization.getUser().getFirstName() + " " + authAuthorization.getUser().getLastName() + ", Вы успешно авторизированы");
//        ArrayList<UserContact> contacts = bridge.contactsGetContacts();
//        for (int i =0;i<contacts.size();i++){
//            System.out.println(contacts.get(i).getFirstName()+" "+contacts.get(i).getLastName());
//        }
//        Boolean logoutStatus = bridge.authLogOut();
//        System.out.println("Логаут " + logoutStatus);

    }
}
