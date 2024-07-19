import java.util.LinkedList;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/Accounts.csv";
        LinkedList<Account> accounts = new LinkedList<>();

        ReadAccounts readAccounts = new ReadAccounts(filePath);
        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalances();

        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
        }

        GUI gui = new GUI(accounts);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(800, 600);
        gui.setVisible(true);
    }
}
