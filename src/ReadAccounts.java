import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private LinkedList<String> firstNames = new LinkedList<>();
    private LinkedList<String> lastNames = new LinkedList<>();
    private LinkedList<Integer> accounts = new LinkedList<>();
    private LinkedList<Integer> balances = new LinkedList<>();

    public ReadAccounts(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip the header line
                }
                String[] values = line.split(",");
                firstNames.add(values[0]);
                lastNames.add(values[1]);
                accounts.add(Integer.parseInt(values[2]));
                balances.add(Integer.parseInt(values[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getFirstNames() {
        return firstNames;
    }

    public LinkedList<String> getLastNames() {
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        return accounts;
    }

    public LinkedList<Integer> getBalances() {
        return balances;
    }
}
