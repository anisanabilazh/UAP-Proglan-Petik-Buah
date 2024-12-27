import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class FruitPickingPaymentApp {
    private JFrame frame;
    private JTextField nameField, fruitField, kiloField, totalField;
    private JTextArea resultArea;
    private JLabel imageLabel;
    private List<Ticket> tickets;

    public FruitPickingPaymentApp() {
        tickets = new ArrayList<>();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Fruit Picking Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 248, 220)); // Soft peach background

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 218, 185));
        JLabel titleLabel = new JLabel("Fruit Picking Payment System");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Price List
        JPanel priceListPanel = new JPanel(new GridLayout(5, 1));
        priceListPanel.setBackground(new Color(255, 239, 213));
        priceListPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(205, 133, 63), 2), "Price List", 0, 0, new Font("Comic Sans MS", Font.BOLD, 16), new Color(139, 69, 19)));

        priceListPanel.add(new JLabel("Anggur: IDR 50,000 / Kg"));
        priceListPanel.add(new JLabel("Strawberry: IDR 25,000 / Kg"));
        priceListPanel.add(new JLabel("Jeruk Bali: IDR 20,000 / Kg"));
        priceListPanel.add(new JLabel("Apel Hijau: IDR 35,000 / Kg"));
        priceListPanel.add(new JLabel("Mangga: IDR 30,000 / Kg"));

        frame.add(priceListPanel, BorderLayout.EAST);

        // North Panel: Input Form
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(new Color(255, 239, 213));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(205, 133, 63), 2), "Payment Details", 0, 0, new Font("Comic Sans MS", Font.BOLD, 16), new Color(139, 69, 19)));

        inputPanel.add(new JLabel("Customer Name:", JLabel.RIGHT));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Fruit Name:", JLabel.RIGHT));
        fruitField = new JTextField();
        inputPanel.add(fruitField);

        inputPanel.add(new JLabel("Weight (Kg):", JLabel.RIGHT));
        kiloField = new JTextField();
        inputPanel.add(kiloField);

        inputPanel.add(new JLabel("Total Price (IDR):", JLabel.RIGHT));
        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setBackground(new Color(255, 248, 220));
        inputPanel.add(totalField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(144, 238, 144));
        calculateButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        calculateButton.addActionListener(this::handleCalculate);
        inputPanel.add(calculateButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(173, 216, 230));
        submitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        submitButton.addActionListener(this::handleSubmit);
        inputPanel.add(submitButton);

        frame.add(inputPanel, BorderLayout.WEST);

        // Center Panel: Result Display
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(new Color(255, 248, 220));
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(205, 133, 63), 2), "Payment Records", 0, 0, new Font("Comic Sans MS", Font.BOLD, 16), new Color(139, 69, 19)));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setBackground(new Color(255, 250, 240));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(resultPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void handleCalculate(ActionEvent e) {
        try {
            String fruit = fruitField.getText().trim().toLowerCase();
            if (fruit.isEmpty()) {
                throw new IllegalArgumentException("Fruit name cannot be empty.");
            }

            double pricePerKilo;
            switch (fruit) {
                case "anggur":
                    pricePerKilo = 50000;
                    break;
                case "strawberry":
                    pricePerKilo = 25000;
                    break;
                case "jeruk bali":
                    pricePerKilo = 20000;
                    break;
                case "apel hijau":
                    pricePerKilo = 35000;
                    break;
                case "mangga":
                    pricePerKilo = 30000;
                    break;
                default:
                    throw new IllegalArgumentException("Fruit not available. Please choose: anggur, strawberry, jeruk bali, apel hijau, or mangga.");
            }

            double kilo = Double.parseDouble(kiloField.getText());
            if (kilo <= 0) {
                throw new NumberFormatException("Weight must be greater than zero.");
            }

            double total = kilo * pricePerKilo;

            // Format the total price to display in thousands (IDR 15,000, not IDR 1,000,000)
            NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("id", "ID"));
            numberFormat.setMaximumFractionDigits(0);  // Remove decimal places
            String formattedTotal = "IDR " + numberFormat.format(total);

            totalField.setText(formattedTotal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid weight. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSubmit(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            String fruit = fruitField.getText().trim();
            if (name.isEmpty() || fruit.isEmpty()) {
                throw new IllegalArgumentException("Customer name and fruit name cannot be empty.");
            }
            double kilo = Double.parseDouble(kiloField.getText());
            String totalText = totalField.getText().replace("IDR", "").replaceAll("[^\\d]", "");

            double total = Double.parseDouble(totalText);

            Ticket ticket = new Ticket(name, fruit, kilo, total);
            tickets.add(ticket);

            resultArea.append(ticket + "\n");
            JOptionPane.showMessageDialog(frame, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            nameField.setText("");
            fruitField.setText("");
            kiloField.setText("");
            totalField.setText("");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FruitPickingPaymentApp::new);
    }

    static class Ticket {
        private String name;
        private String fruit;
        private double kilo;
        private double total;

        public Ticket(String name, String fruit, double kilo, double total) {
            this.name = name;
            this.fruit = fruit;
            this.kilo = kilo;
            this.total = total;
        }

        @Override
        public String toString() {
            NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("id", "ID"));
            currencyFormat.setMaximumFractionDigits(0);  // Ensure no decimals
            return String.format("Customer: %s, Fruit: %s, Weight: %.2f Kg, Total: %s",
                    name, fruit, kilo, "IDR " + currencyFormat.format(total));
        }
    }
}