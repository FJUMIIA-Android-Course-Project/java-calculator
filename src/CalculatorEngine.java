import java.util.*;

public class CalculatorEngine {

    private static final Map<String, Integer> precedence = new HashMap<>();
    static {
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);
        precedence.put("sin", 4);
        precedence.put("cos", 4);
        precedence.put("tan", 4);
        precedence.put("log", 4);
        precedence.put("ln", 4);
        precedence.put("e", 4);
        precedence.put("√", 4);
    }

    public double evaluate(String expression) {
        List<String> tokens = infixToRPN(expression);
        return evaluateRPN(tokens);
    }

    private List<String> infixToRPN(String expression) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/^() ", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            }
            if (isNumber(token) || token.equals("e")) {
                if (isNumber(token)){
                    output.add(token);
                }
                if (token.equals("e")) {
                    output.add(String.valueOf(Math.exp(1)));
                }
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence.containsKey(operators.peek())
                        && precedence.get(operators.peek()) >= precedence.get(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    private double evaluateRPN(List<String> tokens) {
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isFunction(token)) {
                double value = stack.pop();
                switch (token) {
                    case "sin" -> stack.push(Math.sin(Math.toRadians(value)));
                    case "cos" -> stack.push(Math.cos(Math.toRadians(value)));
                    case "tan" -> stack.push(Math.tan(Math.toRadians(value)));
                    case "log" -> stack.push(Math.log10(value));
                    case "ln" -> stack.push(Math.log(value));
                    case "√" -> stack.push(Math.sqrt(value));
                }
            } else if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                    case "^" -> stack.push(Math.pow(a, b));
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return "+-*/^".contains(token);
    }

    private boolean isFunction(String token) {
        return "sin cos tan ln log √".contains(token);
    }
}