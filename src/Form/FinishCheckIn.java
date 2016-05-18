package Form;

import View.HintFormattedTextField;
import View.ResManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;

/**
 * Created by bubnyshev on 18.04.2016.
 */
public class FinishCheckIn {
    private JPanel rootPanel;
    private JButton backButton;
    private JFormattedTextField lastNameFTF;
    private JButton finishCheckInButton;
    private JFormattedTextField firstNameFTF;
    private JTextPane введитеВашиИмяИTextPane;
    private JPanel logoPanel;
    private JPanel panel;

    public FinishCheckIn() {

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JFormattedTextField getFirstNameFTF() {
        return firstNameFTF;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getFinishCheckInButton() {
        return finishCheckInButton;
    }

    public JFormattedTextField getLastNameFTF() {
        return lastNameFTF;
    }

    public void clearForm() {
        lastNameFTF.setText("");
        firstNameFTF.setText("");

    }

    private void createUIComponents() {
        finishCheckInButton = new JButton() {
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
                g.drawString("ПРОДОЛЖИТЬ", 100, 37);
            }
        };
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getBackground(), 0, 0, panel.getWidth(), panel.getHeight(), null);
            }
        };
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getLogoMini(), 0, 0, null);
            }
        };
        lastNameFTF = new HintFormattedTextField("Фамилия", Color.LIGHT_GRAY, Color.WHITE) {
            {
                setCaretColor(Color.WHITE);
                setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 32));
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }
        };
        firstNameFTF = new HintFormattedTextField("Имя", Color.LIGHT_GRAY, Color.WHITE) {
            {
                setCaretColor(Color.WHITE);
                setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 32));
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }
        };
    }
}
