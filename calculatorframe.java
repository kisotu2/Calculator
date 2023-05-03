import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculatorframe extends JFrame implements ActionListener {
    private JTextField display = new JTextField("0");
    private JButton[] buttons = new JButton[16];
    private String[] labels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "%", "/", "="
    };

    public calculatorframe() {
        super("Calculator");
        setLayout(new BorderLayout());
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(labels[i]);
            panel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setVisible(true);
    }

    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.charAt(0) == '+' ||
                command.charAt(0) == '-' ||
                command.charAt(0) == '*' ||
                command.charAt(0) == '%' ||
                command.charAt(0) == '/') {
            operator = command;
            result = Double.parseDouble(display.getText());
            calculating = true;
        } else if (command.charAt(0) == '=') {
            double value = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result += value;
                    break;
                case "-":
                    result -= value;
                    break;
                case "*":
                    result *= value;
                    break;
                case "%":
                    result %= value;
                    break;
                case "/":
                    result /= value;
                    break;
                case "=":
                    result = value;
                    break;
            }
            display.setText("" + result);
            operator = command;
            calculating = true;
        } else {
            if (calculating) {
                display.setText(command);
                calculating = false;
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        new calculatorframe();
    }

}
