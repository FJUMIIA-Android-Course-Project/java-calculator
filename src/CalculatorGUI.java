import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField field;
    private String currentExpression = "";
    private CalculatorEngine engine;
    private MemoryHandler memory;

    public CalculatorGUI() {
        setTitle("Flood Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        engine = new CalculatorEngine();
        memory = new MemoryHandler();

        field = new JTextField(16);
        /*
        * Let the text field editable to free the cursor.
         * */
        field.setEditable(true);
        field.setFont(new Font("Arial", Font.BOLD, 24));
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setPreferredSize(new Dimension(300, 50));

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 3, 5, 5));
        add(field, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        String[] buttonLabels = {
                "MC", "MR", "M+", "M-",
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", "(", ")", "/",
                "sin", "cos", "tan", ".",
                "log", "ln", "C", "=",
                "e", "^", "!", "\u221a"
        };

        for (String label : buttonLabels) {
            JButton button = ButtonFactory.createButton(label, buttonFont, this);
            panel.add(button);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

//        if (e.getSource() == field) {
//            currentExpression = field.getText();
//            double result = engine.evaluate(currentExpression);
//            currentExpression = String.valueOf(result);
//            field.setText(currentExpression);
//            return;
//        }

        if (command.equals("C")) {
//            Clear the field
            currentExpression = "";
            field.setText("");
        } else if (command.equals("=")) {
            try {
                double result = engine.evaluate(currentExpression);
                currentExpression = String.valueOf(result);
                field.setText(currentExpression);
            } catch (Exception ex) {
                field.setText("Invalid Expression");
                currentExpression = "";
            }
        } else if (command.equals("M+")) {
            memory.addToMemory(engine.evaluate(currentExpression));
        } else if (command.equals("M-")) {
            memory.subtractFromMemory(engine.evaluate(currentExpression));
        } else if (command.equals("MR")) {
            currentExpression = String.valueOf(memory.recallMemory());
            field.setText(currentExpression);
        } else if (command.equals("MC")) {
            memory.clearMemory();
        } else if (isFunction(command)) {
//            If the op command is a function, we add "(" to the expression
            currentExpression += command.toLowerCase() + "(";
            field.setText(currentExpression);
        } else {
            currentExpression += command;
            field.setText(currentExpression);
        }
    }

    private boolean isFunction(String command) {
        return "sin cos tan log ! √".contains(command);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
