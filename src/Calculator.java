import javax.swing.*;

public class Calculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
        System.out.println(Math.exp(1));
    }
}