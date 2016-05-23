package View;

import Model.*;
import Model.Dialog;
import org.javagram.response.object.ContactStatus;
import temp.Contacts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sergey on 15.05.2016.
 */
public class ContactCellRenderer extends JPanel implements ListCellRenderer<Object> {
    private JPanel rootPanel;
    private JLabel fioLable;
    private JLabel topMessageLable;
    private JPanel labelPanel;
    private JPanel contactPane;
    private Contact user;
    private boolean isSelected;
    Image icon = null;
    private Contacts contacts;
    private String topMessage = "";
    private ArrayList<Model.Dialog> dialogs;
    //    TelegramDAO apiBridgeTelegramDAO;
    private ArrayList<ContactStatus> statuses;
    private Calendar calendar = Calendar.getInstance();

//    public ContactCellRenderer(TelegramDAO apiBridgeTelegramDAO) {
//        this.apiBridgeTelegramDAO = apiBridgeTelegramDAO;
//    }

    private void createUIComponents() {
        rootPanel = this;
        labelPanel = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        contactPane = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        // TODO: place custom component creation code here
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        user = (Contact) value;
        this.isSelected = isSelected;
        if (user.getSmallProfilePhoto() != null) {
            icon = user.getSmallProfilePhoto().getScaledInstance(41, 41, Image.SCALE_SMOOTH);
            }


        fioLable.setText(((Contact) value).getLable());
        topMessageLable.setText(getTopMessage(user));
        dialogs.forEach(e -> {
            if (e.getBuddy().getId() == user.getId()) {
                topMessageLable.setText(e.getLastMessage().getText());
            }
        });


        Color background;
        Color foreground;
        Border mainBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY);

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
        setBorder(mainBorder);
        labelPanel.setBorder(border);

        return this;
    }

    private String getTopMessage(Contact contact) {
        String topMessage = "";
        for (Dialog dialog :
                dialogs) {
            if (dialog.getBuddy().getId() == user.getId()) {
                topMessage = dialog.getLastMessage().getText();
                break;
            }
        }


        return topMessage;
    }

    public void setDialogs(ArrayList<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    @Override
    protected void paintComponent(Graphics g) {
//        topMessageLable.setText(contacts.getTopMessage(user.getId()));
        super.paintComponent(g);

        g.drawImage(icon, 15, 9, null);

        if (isContactOnline(user)) {
            if (isSelected) {
                g.drawImage(ResManager.getMaskWhiteOnline(), 15, 9, null);
            } else {
                g.drawImage(ResManager.getMaskGrayOnline(), 15, 9, null);
            }
        } else {
            if (isSelected) {
                g.drawImage(ResManager.getMaskWhite(), 15, 9, null);

            } else {
                g.drawImage(ResManager.getMaskGray(), 15, 9, null);
            }
        }

    }

    public void setStatuses(ArrayList<ContactStatus> statuses) {
        this.statuses = statuses;
    }

    private boolean isContactOnline(Contact contact) {
        Date expireDate = new Date();
        for (ContactStatus status : statuses) {
            if (status.getUserId() == contact.getId()) {
                expireDate = status.getExpires();
                break;
            }
        }
        assert expireDate != null;
        return expireDate.compareTo(calendar.getTime()) > 0;

    }
}