package temp;

import Model.Contacts;
import View.ResManager;
import org.javagram.response.object.UserContact;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by bubnyshev on 19.04.2016.
 */
public class ContactCellRenderer extends JLabel implements ListCellRenderer<Object> {
    private UserContact user;
    private boolean isSelected;

    public ContactCellRenderer() {
        setOpaque(true);
    }

    private Contacts contacts;
    private String topMessage = "";

    public ContactCellRenderer(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected) {
            if (user.isOnline()) {
                g.drawImage(ResManager.getMaskWhiteOnline(), 0, 0, null);
            } else {
                g.drawImage(ResManager.getMaskWhite(), 0, 0, null);
            }
        } else {
            if (user.isOnline()) {
                g.drawImage(ResManager.getMaskGrayOnline(), 0, 0, null);
            } else {
                g.drawImage(ResManager.getMaskGray(), 0, 0, null);
            }
        }
        if (!topMessage.equals("")) {
            g.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 10));
            g.drawString(topMessage, 50, 37);
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        user = (UserContact) value;
        this.isSelected = isSelected;
        topMessage = contacts.getTopMessage(user.getId());
        ImageIcon icon = null;
//        try {
//            icon =new ImageIcon(user.getPhoto(true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            BufferedImage image = new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB);
            if (user.getPhoto(true) != null) {
                image = ImageIO.read(new ByteArrayInputStream(user.getPhoto(true)));
//            Image newIcon = image.getScaledInstance(41,41,Image.SCALE_SMOOTH);
            }
            icon = new ImageIcon(image.getScaledInstance(41, 41, Image.SCALE_SMOOTH));
            setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }


        setText(value.toString());
        Color background;
        Color foreground;
        Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
//        JList.DropLocation dropLocation = list.getDropLocation();
//        if (dropLocation != null
//                && !dropLocation.isInsert()
//                && dropLocation.getIndex() == index) {
//
//            background = Color.BLUE;
//            foreground = Color.WHITE;
//
//            // check if this cell is selected
//        } else if (isSelected) {
        if (isSelected) {
            background = ResManager.getWhiteColor();
            foreground = Color.BLACK;
            border = BorderFactory.createMatteBorder(0, 0, 0, 3, ResManager.getBlueColor());


            // unselected, and not the DnD drop location
        } else {
            background = ResManager.getLightGrayColor();
            foreground = Color.BLACK;
        }

        setBackground(background);
        setForeground(foreground);
        setBorder(border);
        setSize(-1, 42);
        return this;
    }
}
