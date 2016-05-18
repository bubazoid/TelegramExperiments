package Form;

import Model.Contact;
import View.ContactCellRenderer;
import View.ResManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bubnyshev on 12.05.2016.
 */
public class ContactListPane {
    private JPanel contactListPane;
    private JPanel findPanel;
    private JTextField findContactField;
    private JScrollPane contactScrollPane;
    private JList contactList;
    private JPanel rootPanel;


    public JPanel getContactListPane() {
        return contactListPane;
    }

    public JPanel getFindPanel() {
        return findPanel;
    }

    public JScrollPane getContactScrollPane() {
        return contactScrollPane;
    }

    public JList getContactList() {
        return contactList;
    }

    public JTextField getFindContactField() {
        return findContactField;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void fillContactList(ArrayList<Contact> contacts, ContactCellRenderer cellRenderer) {
        DefaultListModel model = new DefaultListModel();
        for (Contact contact : contacts) {
            model.addElement(contact);

        }
        contactList.setModel(model);
        contactList.setCellRenderer(cellRenderer);
    }

    private void createUIComponents() {
        findContactField = new JTextField() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        contactScrollPane = new JScrollPane() {
            {
                JScrollBar vertical = getVerticalScrollBar();
                vertical.setPreferredSize(new Dimension(0, 0));
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        contactList = new JList() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        findPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconSearch(), 15, 10, null);

            }
        };
        contactList = new JList() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
    }


}

