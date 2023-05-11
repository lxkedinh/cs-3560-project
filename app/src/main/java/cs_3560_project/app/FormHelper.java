package cs_3560_project.app;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormHelper {

    private static GridBagConstraints gbc = new GridBagConstraints();

    public static void makeLable(int labelNum, String labelName, JPanel formsPanel, boolean title) {
        gbc.gridx = 0;
        gbc.gridy = 0 + labelNum;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 20);

        JLabel label = new JLabel(labelName);
        if (title) {
            label.setFont(new Font("Arial", Font.BOLD, 16));
        }
        formsPanel.add(label, gbc);
    }

    public static JTextField makeField(int fieldNum, JPanel formsPanel) {
        gbc.gridx = 1;
        gbc.gridy = 0 + fieldNum;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 0);

        JTextField field = new JTextField(20);
        formsPanel.add(field, gbc);
        return field;
    }

    public static void makeInfo(int fieldNum, String labelName, JPanel formsPanel) {
        gbc.gridx = 1;
        gbc.gridy = 0 + fieldNum;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel label = new JLabel(labelName);
        formsPanel.add(label, gbc);
    }
}
