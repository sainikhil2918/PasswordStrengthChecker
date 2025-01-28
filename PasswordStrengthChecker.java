import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create a panel for input and button
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Input field for password
        JTextField passwordField = new JTextField();
        passwordField.setToolTipText("Enter your password here");
        panel.add(passwordField);

        // Button to check password strength
        JButton checkButton = new JButton("Check Strength");
        panel.add(checkButton);

        // Output label for result
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(Color.BLUE);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Button action listener
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String strength = evaluatePasswordStrength(password);
                resultLabel.setText("Password Strength: " + strength);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private static String evaluatePasswordStrength(String password) {
        int lengthCriteria = password.length() >= 8 ? 1 : 0;
        int upperCaseCriteria = Pattern.compile("[A-Z]").matcher(password).find() ? 1 : 0;
        int lowerCaseCriteria = Pattern.compile("[a-z]").matcher(password).find() ? 1 : 0;
        int digitCriteria = Pattern.compile("\\d").matcher(password).find() ? 1 : 0;
        int specialCharCriteria = Pattern.compile("[!@#$%^&*(),.?\\\\\":{}|<>]").matcher(password).find() ? 1 : 0;

        int score = lengthCriteria + upperCaseCriteria + lowerCaseCriteria + digitCriteria + specialCharCriteria;

        if (score == 5) {
            return "Strong";
        } else if (score >= 3) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }
}
