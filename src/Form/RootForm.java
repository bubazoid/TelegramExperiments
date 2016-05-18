package Form;

import View.ResManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bubnyshev on 20.04.2016.
 */
public class RootForm {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JButton turnButton;
    private JButton exitButton;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getMinimizeButton() {
        return turnButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    private void createUIComponents() {
        turnButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconHide(), 0, 0, null);
            }
        };
        exitButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconClose(), 0, 0, null);
            }
        };
        // TODO: place custom component creation code here
//        mainPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(ResManager.getBackground(), 0, 0, null);
//            }
//        };
    }

    public void switchFormTo(Component panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        rootPanel.revalidate();
        rootPanel.repaint();
    }
}
