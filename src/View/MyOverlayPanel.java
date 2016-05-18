package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by HerrSergio on 28.04.2016.
 */
public class MyOverlayPanel extends JPanel {

    private Component background;
    private Component[] foregrounds;
    private int index;
    private OverlayLayout overlayLayout;

    {
        overlayLayout = new OverlayLayout(this);
        setLayout(overlayLayout);
    }

    private JPanel fakeBackground = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            background.paint(g);
        }
    };

    public MyOverlayPanel(Component background, Component... foregrounds) {

        this.foregrounds = Arrays.copyOf(foregrounds, foregrounds.length);
        for (Component foreground : foregrounds) {
            foreground.setVisible(false);
            add(foreground);
        }

        fakeBackground.setVisible(false);
        add(fakeBackground);

        this.background = background;
        this.background.setVisible(true);
        add(background);

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
                //fakeBackground.setVisible(true);
//                this.background.setVisible(false);
                this.index = index;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
}
