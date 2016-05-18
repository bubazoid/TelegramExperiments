package Model;

import org.javagram.response.object.UserContact;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by HerrSergio on 06.05.2016.
 */
public class Contact extends KnownPerson {

    public Contact(String lastName, String firstName, String phoneNumber, int id) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
    }

    Contact(UserContact userContact) {
        this(userContact.getLastName(), userContact.getFirstName(), userContact.getPhone(), userContact.getId());
        BufferedImage buffImage = new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB);
        try {
            if (userContact.getPhoto(true) != null) {
                setSmallProfilePhoto(ImageIO.read(new ByteArrayInputStream(userContact.getPhoto(true))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Contact{} " + super.toString();
    }
}
