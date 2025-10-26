package src;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TemperatureConverterGUI {

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Input label and text field
        JLabel inputLabel = new JLabel("Enter Temperature:");
        JTextField inputField = new JTextField(10);

        // Result label
        JLabel resultLabel = new JLabel("");

        // Buttons
        JButton toCelsiusBtn = new JButton("Convert to Celsius");
        JButton toFahrenheitBtn = new JButton("Convert to Fahrenheit");

        // Name and Reg No
        JLabel nameLabel = new JLabel("<html>Name: Janise Deepthi YP<br>Reg No: 2117240070124</html>");

        // Add components to frame
        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(toCelsiusBtn);
        frame.add(toFahrenheitBtn);
        frame.add(resultLabel);
        frame.add(nameLabel);

        // Action listeners
        toCelsiusBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double f = Double.parseDouble(inputField.getText());
                    double c = (f - 32) * 5 / 9;
                    resultLabel.setText(f + "°F = " + String.format("%.2f", c) + "°C");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a number.");
                }
            }
        });

        toFahrenheitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double c = Double.parseDouble(inputField.getText());
                    double f = (c * 9 / 5) + 32;
                    resultLabel.setText(c + "°C = " + String.format("%.2f", f) + "°F");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a number.");
                }
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
}
