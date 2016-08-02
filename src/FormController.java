import Model.*;
import Model.Dialog;
import Form.*;
import View.ComponentResizerExtended;
import View.ContactCellRenderer;
import View.MyOverlayPanel;
import View.MyBufferedPopupDialog;
import org.javagram.response.object.ContactStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bubazoid on 10.04.2016.
 */
public class FormController extends JFrame {

    private MainWindow mainWindow = new MainWindow();
    private LoginForm loginForm = new LoginForm();
    private PhoneVerifyForm phoneVerifyForm = new PhoneVerifyForm();
    private AddContact addContact = new AddContact();
    private EditContact editContact = new EditContact();
    private FinishCheckIn finishCheckIn = new FinishCheckIn();
    private RootForm rootForm = new RootForm();
    private ProfileSettings profileSettings = new ProfileSettings();
    private ContactListPane contactListPane = new ContactListPane();
    private AddContactButton addContactButton = new AddContactButton();
    MyBufferedPopupDialog layoutPanel = new MyBufferedPopupDialog(mainWindow.getRootPanel(), profileSettings.getRootPanel(), addContact.getRootPanel(), editContact.getRootPanel());
    MyOverlayPanel contactListLayout = new MyOverlayPanel(contactListPane.getRootPanel(), addContactButton.getRootPanel());
    private ArrayList<Contact> contacts;

    private int pX, pY;

    //========================================================================
    TelegramDAO apiBridgeTelegramDAO = new ApiBridgeTelegramDAO();
    private ContactCellRenderer contactCellRenderer = new ContactCellRenderer();
    Me me;
    private ArrayList<ContactStatus> userContactsStatus;
    private ArrayList<Contact> userContacts;
    private HashMap<Contact, ArrayList<Message>> messagesMap = new HashMap<>();
    private Timer timer;


    //===========================================================================
    private static final int EDIT_CONTACT = 2;
    private static final int ADD_CONTACT = 1;
    private static final int EDIT_PROFILE = 0;
    private static final int MAIN_WINDOW = -1;
//===========================================================================

    public FormController() throws IOException, ParseException {
//        layoutPanel.doLayout();
        contactListLayout.setIndex(0);
        mainWindow.getLeftPanel().add(contactListLayout);
//        loginForm = new LoginForm(resManager);
        setUndecorated(true);
        ComponentResizerExtended cr = new ComponentResizerExtended(ComponentResizerExtended.KEEP_RATIO_CENTER);
        setTitle("Javagram");
        setSize(800, 600);
        cr.setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        cr.registerComponent(this);
        setContentPane(rootForm.getRootPanel());
        switchToLoginInForm();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                loginForm.getPhoneNumberField().requestFocus();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                exitProgram();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                exitProgram();
            }
        });
//        profileSettings.getPanel().addMouseListener(new MouseAdapter(){});
//        editContact.getRootPanel().addMouseListener(new MouseAdapter(){});
//        addContact.getRootPanel().addMouseListener(new MouseAdapter(){});
//================== Root Form ======================
        rootForm.getTopPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Get x,y and store them
                pX = e.getX();
                pY = e.getY();
            }

            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });

        rootForm.getTopPanel().addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });

//================== Main Form ============================
        rootForm.getExitButton().addActionListener(e -> dispose());
        rootForm.getMinimizeButton().addActionListener(e -> setState(JFrame.ICONIFIED));
//================== Login Form ============================
        loginForm.getPhoneNumberAcceptButton().addActionListener(e -> loginAction());
        loginForm.getPhoneNumberField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });

//================== Verify Code Form ============================

        phoneVerifyForm.getPhoneCodeAcceptButton().addActionListener(e -> verifyPhoneCode());
        phoneVerifyForm.getPhoneVerifyCodeField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyPhoneCode();
                }
            }
        });
//================== Main Window ========================
        mainWindow.getEditContactButton().addActionListener(e -> switchToEditContact());
        addContactButton.getAddContactButton().addActionListener(e -> switchToAddContact());
        mainWindow.getProfileSettingsButton().addActionListener(e -> switchToProfileEdit());
        contactListPane.getContactList().addListSelectionListener(e -> selectContactInList());
//================== Add Contact Form ===================
        addContact.getBackButton().addActionListener((e) -> backForm());
        addContact.getAddContactButton().addActionListener(e -> addContact());
//================== Edit Contact Form ===================
        editContact.getBackButton().addActionListener((e) -> backForm());
        editContact.getDeleteUserButton().addActionListener(e -> delContact());
        editContact.getEditContactAcceptButton().addActionListener(e -> updateContact());
//================== Finish CheckIn Form ===================
        finishCheckIn.getFinishCheckInButton().addActionListener(e -> verifyFIO());

//================== Profile Setting Form ==================
        profileSettings.getBackButton().addActionListener(e -> backForm()
        );
        profileSettings.getLogOffButton().addActionListener(e -> loqOff());
        profileSettings.getProfileSettingsAcceptButton().addActionListener(e -> {
//            selfUser.updateProfile();
            switchToMainForm(true);
        });
    }

    private void updateContact() {
//        selfUser.updateContact(getSelectedUser(), editContact.getFioFTF().getText());
        switchToMainForm(true);
    }

    private void addContact() {
//        selfUser.addContact(addContact.getPhoneNumberFTF().getText(), addContact.getFirstNameFTF().getText(), addContact.getLastNameFTF().getText());
        switchToMainForm(true);
    }

    private void delContact() {
//        selfUser.deleteContact(getSelectedUser());
        switchToMainForm(true);
    }


    private void verifyFIO() {
        switchToPhoneVerifyForm();
    }


    private void loginAction() {
//        selfUser = new SelfUser(loginForm.getPhoneNumberField().getText().replaceAll("[^0-9]+", ""));
        try {
            apiBridgeTelegramDAO.acceptNumber(loginForm.getPhoneNumberField().getText().replaceAll("[^0-9]+", ""));
            switch (apiBridgeTelegramDAO.isContactOnline()) {
                case REGISTERED:
                    switchToPhoneVerifyForm();
                    break;
                case INVITED:
                    switchToCheckInForm();
                    break;
                case NOT_REGISTERED:
                    switchToCheckInForm();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private Contact getSelectedUser() {

        return contacts.get(contactListPane.getContactList().getSelectedIndex());

    }

    private void loqOff() {

        apiBridgeTelegramDAO.logOut();
        backForm();
        loginForm.clearFields();
        phoneVerifyForm.clearFields();
        finishCheckIn.clearForm();
        switchToLoginInForm();
    }


    private void backForm() {
        layoutPanel.setIndex(MAIN_WINDOW);
    }


    private void verifyPhoneCode() {
        String code = new String(phoneVerifyForm.getPhoneVerifyCodeField().getPassword());
        if (code.matches("^[0-9]{5}$")) {
            try {
                if (apiBridgeTelegramDAO.canSignIn()) {
                    me = apiBridgeTelegramDAO.signIn(code);
                } else {
                    me = apiBridgeTelegramDAO.signUp(code, finishCheckIn.getFirstNameFTF().getText(), finishCheckIn.getLastNameFTF().getText());
                }
                if (me != null) {
                    contacts = apiBridgeTelegramDAO.getContacts();

                    switchToMainForm(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void switchToPhoneVerifyForm() {
//        selfUser.sendSMSCode();
        try {
            apiBridgeTelegramDAO.sendCode();
            phoneVerifyForm.getPhoneNumberField().setText(apiBridgeTelegramDAO.getPhoneNumber());
            rootForm.switchFormTo(phoneVerifyForm.getRootPanel());
            phoneVerifyForm.getPhoneVerifyCodeField().requestFocusInWindow();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        phoneVerifyForm.getPhoneNumberField().setText(selfUser.getFormattedPhoneNumber());
//        rootForm.switchFormTo(phoneVerifyForm.getRootPanel());
//        phoneVerifyForm.getPhoneVerifyCodeField().requestFocusInWindow();

    }

    private void switchToMainForm(boolean renew) {
        layoutPanel.setIndex(MAIN_WINDOW);
        if (renew || messagesMap == null || userContactsStatus == null || userContacts == null) {
            init();
            mainWindow.setUser(me);
        }

        rootForm.switchFormTo(layoutPanel);
        layoutPanel.setVisible(true);
    }

    private void selectContactInList() {
        Contact contact = getSelectedUser();
        if (contact != null) {
            mainWindow.setContact(contact);
//            mainWindow.setMessages(contacts.getMessages(contact));
            mainWindow.setMessages(messagesMap.get(contact));

        }

    }

    private void switchToCheckInForm() {
        rootForm.switchFormTo(finishCheckIn.getRootPanel());
    }

    private void switchToEditContact() {
        editContact.setContact(getSelectedUser());
        layoutPanel.setIndex(EDIT_CONTACT);

    }

    private void switchToAddContact() {
        addContact.clearForm();
        layoutPanel.setIndex(ADD_CONTACT);
    }

    private void switchToProfileEdit() {
        profileSettings.fillForm(me);
        layoutPanel.setIndex(EDIT_PROFILE);
    }

    private void switchToLoginInForm() {

        rootForm.switchFormTo(loginForm.getRootPane());
    }

    private void exitProgram() {
        apiBridgeTelegramDAO.logOut();
        apiBridgeTelegramDAO.close();
        System.exit(0);
    }

    //=====================================================

    //Временно создадим пустышку


    private void init() {
        updateContactList();
        updateMessages();
        updateContactsStatuses();
        timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContactsStatuses();
            }
        });
        Timer updMessagesTimer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMessages();
            }
        });
        timer.start();
        updMessagesTimer.start();


    }

    private void updateMessages() {
        userContacts.forEach(contact -> {
            try {
                ArrayList<Message> messages = new ArrayList<>(apiBridgeTelegramDAO.getMessagesOfContact(contact.getId(), 0, 15));
                messagesMap.put(contact, messages);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        contactCellRenderer.setDialogs(messagesMap);

    }

    private void updateContactList() {
        try {
            userContacts = apiBridgeTelegramDAO.getContacts();
            contactListPane.fillContactList(contacts, contactCellRenderer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private ArrayList<Message> getNewMessage(Contact contact, int lastId) throws IOException {
//        return apiBridgeTelegramDAO.getMessagesOfContact(contact.getId(), lastId, Short.MAX_VALUE);
//
//    }

    private void updateContactsStatuses() {
        try {
            userContactsStatus = apiBridgeTelegramDAO.getContactsStatuses();
            contactCellRenderer.setStatuses(userContactsStatus);
            contactListPane.getContactList().repaint();
            //timer.restart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
