package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by HerrSergio on 28.04.2016.
 */
public class MyLayeredPane extends JLayeredPane {


    private Component background;
    private Component[] foregrounds;
    private int index;

    private JPanel fakeBackground = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            background.paint(g);
        }
    };

    public MyLayeredPane(Component background, Component... foregrounds) {

        this.background = background;
        this.background.setVisible(true);
        add(background, new Integer(0));

        fakeBackground.setVisible(false);
        add(fakeBackground, new Integer(1));

        this.foregrounds = Arrays.copyOf(foregrounds, foregrounds.length);
        for (Component foreground : foregrounds) {
            foreground.setVisible(false);
            add(foreground, new Integer(2));
        }

        index = -1;
    }

    public void setIndex(int index) {

        if (index < 0)
            index = -1;
        else //Проверка на диапазон
            foregrounds[index] = foregrounds[index];

        if (this.index != index) {
            if (this.index >= 0) {
                foregrounds[this.index].setVisible(false);
                fakeBackground.setVisible(false);
                background.setVisible(true);
                this.index = -1;
            }

            if (index >= 0) {
                foregrounds[index].setVisible(true);
//                fakeBackground.setVisible(true);
                this.background.setVisible(true);
                this.index = index;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void doLayout() {
        super.doLayout();
        for (Component component : getComponents()) {
            component.setBounds(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
