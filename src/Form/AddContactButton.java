package Form;

import View.ResManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bubnyshev on 12.05.2016.
 */
public class AddContactButton {
    private JPanel rootPanel;
    private JButton addContactButton;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getAddContactButton() {
        return addContactButton;
    }

    private void createUIComponents() {
        rootPanel = new JPanel() {
            {
                setBackground(new Color(0, 0, 255, 0));
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
                g.drawImage(ResManager.getIconPlus(), 0, 0, null);

            }
        };// TODO: place custom component creation code here
    }
}
