package Form;

import View.ResManager;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Created by bubnyshev on 15.04.2016.
 */
public class AddContact {
    private JPanel rootPanel;
    private JButton backButton;

    private JFormattedTextField firstNameFTF;
    private JFormattedTextField lastNameFTF;
    private JFormattedTextField phoneNumberFTF;
    private JPanel panel;
    private JTextPane введитеКодСтраныИTextPane;
    private JButton addContactButton;
    private JLabel addContactTitle;
    private JPanel phoneNumberPanel;
    private JFormattedTextField phoneNumberField;

    //    public AddContact() {
//        GridBagConstraints c = new GridBagConstraints();
//        c.anchor = GridBagConstraints.PAGE_END;
//        c.gridy = 6;
//        panel.add(phoneNumberAcceptButton,c);
//
//    }
    public void clearForm() {
//        firstNameFTF.setText("");
//        lastNameFTF.setText("");
//        phoneNumberFTF.setText("");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


    public JFormattedTextField getFirstNameFTF() {
        return firstNameFTF;
    }

    public JFormattedTextField getLastNameFTF() {
        return lastNameFTF;
    }

    public JFormattedTextField getPhoneNumberFTF() {
        return phoneNumberFTF;
    }

    public JButton getAddContactButton() {
        return addContactButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    private void createUIComponents() throws ParseException {
        panel = new JPanel() {
            {
//                setOpaque( false );
//                setVisible(true);
                setBackground(new Color(0, 0, 250, 255));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
//                g.drawImage(ResManager.getBackground(),0,0,null);
                Graphics2D g2d = (Graphics2D) g.create();
                // 50% transparent Alpha
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));

                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.dispose();

            }
        };
        addContactTitle = new JLabel() {
            {
                setFont(ResManager.getSansLight().deriveFont(Font.PLAIN, 36));
            }
        };
        backButton = new JButton() {
            {
                setOpaque(false);
                setBorderPainted(false);
                setContentAreaFilled(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconBack(), 0, 0, null);

            }
        };
        addContactButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getButtonBackground(), 0, 0, null);
                g.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.drawString("ДОБАВИТЬ", 120, 37);
            }
        };
        firstNameFTF = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));

            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 40));
            }
        };
        lastNameFTF = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
                setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 25));
            }
        };
        phoneNumberField = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder());
                MaskFormatter formatter = new MaskFormatter("# ### ### ## ##");
                DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter,
                        formatter, formatter);
                setFormatterFactory(factory);
            }
        };
        phoneNumberPanel = new JPanel() {
            {
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconPhone(), 10, 5, null);
                g.setColor(Color.WHITE);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));

                g2d.drawLine(40, 25, 55, 25);
                g2d.drawLine(48, 18, 48, 33);
//                g.drawLine();

            }
        };
    }
}
