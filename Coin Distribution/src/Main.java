import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Algorithm {
    private final int n; // quantity
    private final int k; // value

    Algorithm(int n, int k) {
        this.n = n;
        this.k = k;
    }

    List<List<Integer>> dispose() {
        List<List<Integer>> listOfResults = new ArrayList<>();
        for (int z = 0; z < n; z++) {
            if (5 * z > k) {
                break;
            }
            int S = k - 5 * z;
            int y = S - (n - z);
            int x = n - z - y;
            if (x >= 0 && y >= 0) {
                List<Integer> result = new ArrayList<>();
                result.add(x);
                result.add(y);
                result.add(z);
                listOfResults.add(result);
            }
        }
        return listOfResults;
    }

    String formatResults(List<List<Integer>> listOfResults) {
        StringBuilder sb = new StringBuilder();
        if (listOfResults.isEmpty()) {
            sb.append("Impossible");
        } else if (listOfResults.size() == 1) {
            sb.append("There is only one possibility to get $")
                    .append(k).append(" when we have ").append(n).append(" coins: \n")
                    .append("\t$1: ").append(listOfResults.get(0).get(0)).append("\n")
                    .append("\t$2: ").append(listOfResults.get(0).get(1)).append("\n")
                    .append("\t$5: ").append(listOfResults.get(0).get(2));
        } else {
            int quantity = listOfResults.size();
            sb.append("The number of possibilities to get $").append(k)
                    .append(" when the number of coins is ").append(n).append(" is: ").append(quantity).append("\n");
            for (int i = 0; i < quantity; i++) {
                sb.append("Option ").append(i + 1).append(": \n")
                        .append("\t$1: ").append(listOfResults.get(i).get(0)).append("\n")
                        .append("\t$2: ").append(listOfResults.get(i).get(1)).append("\n")
                        .append("\t$5: ").append(listOfResults.get(i).get(2)).append("\n");
            }
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Coin Distribution");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            frame.setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(3, 2));

            JLabel labelN = new JLabel("Enter the amount of coins:");
            JTextField fieldN = new JTextField();
            JLabel labelK = new JLabel("Enter the value:");
            JTextField fieldK = new JTextField();
            JButton calculateButton = new JButton("Calculate!");

            inputPanel.add(labelN);
            inputPanel.add(fieldN);
            inputPanel.add(labelK);
            inputPanel.add(fieldK);
            inputPanel.add(new JLabel());
            inputPanel.add(calculateButton);

            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);

            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            calculateButton.addActionListener(e -> {
                try {
                    int n = Integer.parseInt(fieldN.getText());
                    int k = Integer.parseInt(fieldK.getText());

                    if (n <= 0 || k <= 0) {
                        resultArea.setText("Wrong input!");
                        return;
                    }

                    Algorithm algorithm = new Algorithm(n, k);
                    List<List<Integer>> listOfResults = algorithm.dispose();
                    resultArea.setText(algorithm.formatResults(listOfResults));
                } catch (NumberFormatException ex) {
                    resultArea.setText("Enter a valid parameters!");
                }
            });

            frame.setVisible(true);
        });
    }
}
