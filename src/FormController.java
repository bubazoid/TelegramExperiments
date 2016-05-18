import Form.*;
import Model.Contacts;
import Model.SelfUser;
import View.*;
import org.javagram.response.object.UserContact;
import temp.DialogCellRenderer;
import temp.MyBufferedPopupDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

import static Model.SelfUser.INVITED;
import static Model.SelfUser.NOT_REGISTRED;

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
    //    MyLayeredPane layoutPanel = new MyLayeredPane(mainWindow.getRootPanel(), profileSettings.getRootPanel(), addContact.getRootPanel(), editContact.getRootPanel());
    MyBufferedPopupDialog layoutPanel = new MyBufferedPopupDialog(mainWindow.getRootPanel(), profileSettings.getRootPanel(), addContact.getRootPanel(), editContact.getRootPanel());
    MyOverlayPanel contactListLayout = new MyOverlayPanel(contactListPane.getRootPanel(), addContactButton.getRootPanel());
    private SelfUser selfUser;
    private Contacts contacts;
    //    private DialogCellRenderer dialogCellRenderer;
    private ContactCellRenderer contactCellRenderer;
    private int pX, pY;

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
            selfUser.updateProfile();
            switchToMainForm(true);
        });
    }

    private void updateContact() {
        selfUser.updateContact(getSelectedUser(), editContact.getFioFTF().getText());
        switchToMainForm(true);
    }

    private void addContact() {
        selfUser.addContact(addContact.getPhoneNumberFTF().getText(), addContact.getFirstNameFTF().getText(), addContact.getLastNameFTF().getText());
        switchToMainForm(true);
    }

    private void delContact() {
        selfUser.deleteContact(getSelectedUser());
        switchToMainForm(true);
    }


    private void verifyFIO() {
        selfUser.setFirstName(finishCheckIn.getFirstNameFTF().getText());
        selfUser.setLastName(finishCheckIn.getLastNameFTF().getText());
        switchToPhoneVerifyForm();
    }


    private void loginAction() {
        selfUser = new SelfUser(loginForm.getPhoneNumberField().getText().replaceAll("[^0-9]+", ""));
        switch (selfUser.getAuthStatus()) {
            case SelfUser.REGISTRED:
                switchToPhoneVerifyForm();
                break;
            case INVITED:
                switchToCheckInForm();
                break;
            case NOT_REGISTRED:
                switchToCheckInForm();
                break;
        }

    }

    private UserContact getSelectedUser() {

        return contacts.getContactsList(false).get(contactListPane.getContactList().getSelectedIndex());

    }

    private void loqOff() {

        selfUser.logOut();
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
            if (selfUser.auth(code)) {
                contacts = selfUser.getContacts();
                contactCellRenderer = new ContactCellRenderer(contacts);
//                dialogCellRenderer = new DialogCellRenderer();

                switchToMainForm(true);
            }
        }
    }


    private void switchToPhoneVerifyForm() {
        selfUser.sendSMSCode();
        phoneVerifyForm.getPhoneNumberField().setText(selfUser.getFormatetPhoneNumber());
        rootForm.switchFormTo(phoneVerifyForm.getRootPanel());
        phoneVerifyForm.getPhoneVerifyCodeField().requestFocusInWindow();

    }

    private void switchToMainForm(boolean renew) {
        if (renew) {
            contactListPane.fillContactList(contacts.getContactsList(renew), contactCellRenderer);
            mainWindow.setUser(selfUser);
        }

        rootForm.switchFormTo(layoutPanel);
        layoutPanel.setVisible(true);
    }

    private void selectContactInList() {
        UserContact contact = getSelectedUser();
        if (contact != null) {
            mainWindow.setContact(contact);
            mainWindow.setMessages(contacts.getMessages(contact));


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
        profileSettings.fillForm(selfUser);
        layoutPanel.setIndex(EDIT_PROFILE);
    }

    private void switchToLoginInForm() {
        rootForm.switchFormTo(loginForm.getRootPane());
    }

    private void exitProgram() {
        if (selfUser != null && selfUser.isAuth()) {
            selfUser.logOut();
        }
        System.exit(0);
    }

}
