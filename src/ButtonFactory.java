import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public static JButton createButton(String text, Font font, CalculatorGUI c) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.addActionListener(c);
        return button;
    }
}