import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI extends JFrame {
    private Transaction transferObject;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;

    private JLabel showAllData;
    private JButton showAllButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JTextField accDeposit;
    private JTextField accWithdraw;
    private JTextField acc1Transfer;
    private JTextField acc2Transfer;
    private JTextField depositInput;
    private JTextField withdrawInput;
    private JTextField transferAmount;

    public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setLayout(null);
        setSize(800, 600); // Set a size for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        globalAccounts = accounts;
        sbAllData = new StringBuilder();

        for (Account account : globalAccounts) {
            sbAllData.append("Account number: ").append(account.getAccountNumber())
                    .append(", Balance: ").append(account.getBalance()).append("\n");
        }

        showAllButton = new JButton("Show All");
        showAllButton.setBounds(50, 50, 150, 30);
        add(showAllButton);

        showAllData = new JLabel();
        showAllData.setBounds(50, 90, 300, 300);
        add(showAllData);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 400, 150, 30);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 440, 150, 30);
        add(withdrawButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(50, 480, 150, 30);
        add(transferButton);

        accDeposit = createPlaceholderTextField("Account Number");
        accDeposit.setBounds(220, 400, 150, 30);
        add(accDeposit);

        depositInput = createPlaceholderTextField("Amount");
        depositInput.setBounds(380, 400, 150, 30);
        add(depositInput);

        accWithdraw = createPlaceholderTextField("Account Number");
        accWithdraw.setBounds(220, 440, 150, 30);
        add(accWithdraw);

        withdrawInput = createPlaceholderTextField("Amount");
        withdrawInput.setBounds(380, 440, 150, 30);
        add(withdrawInput);

        acc1Transfer = createPlaceholderTextField("From Account");
        acc1Transfer.setBounds(220, 480, 150, 30);
        add(acc1Transfer);

        acc2Transfer = createPlaceholderTextField("To Account");
        acc2Transfer.setBounds(380, 480, 150, 30);
        add(acc2Transfer);

        transferAmount = createPlaceholderTextField("Amount");
        transferAmount.setBounds(540, 480, 150, 30);
        add(transferAmount);

        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);

        setVisible(true);
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == showAllButton) {
                showAllData.setText("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
            }

            if (event.getSource() == depositButton) {
                int accountNumber = Integer.parseInt(accDeposit.getText());
                int amount = Integer.parseInt(depositInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNumber() == accountNumber) {
                        account.deposit(amount);
                        break;
                    }
                }
                updateAllData();
            }

            if (event.getSource() == withdrawButton) {
                int accountNumber = Integer.parseInt(accWithdraw.getText());
                int amount = Integer.parseInt(withdrawInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNumber() == accountNumber) {
                        account.withdraw(amount);
                        break;
                    }
                }
                updateAllData();
            }

            if (event.getSource() == transferButton) {
                int fromAccount = Integer.parseInt(acc1Transfer.getText());
                int toAccount = Integer.parseInt(acc2Transfer.getText());
                int amount = Integer.parseInt(transferAmount.getText());
                Account source = null, destination = null;
                for (Account account : globalAccounts) {
                    if (account.getAccountNumber() == fromAccount) {
                        source = account;
                    } else if (account.getAccountNumber() == toAccount) {
                        destination = account;
                    }
                }
                if (source != null && destination != null) {
                    transferObject = new Transaction();
                    transferObject.transfer(source, destination, amount);
                }
                updateAllData();
            }
        }

        private void updateAllData() {
            sbAllData.setLength(0);
            for (Account account : globalAccounts) {
                sbAllData.append("Account number: ").append(account.getAccountNumber())
                        .append(", Balance: ").append(account.getBalance()).append("\n");
            }
        }
    }
}
