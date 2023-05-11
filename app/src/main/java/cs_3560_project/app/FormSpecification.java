package cs_3560_project.app;

import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormSpecification {
    public static LinkedList<JTextField> getTextFields(String formName, LinkedList<String> labels, JPanel formsPanel) {
        int count = 0;
        LinkedList<JTextField> fields = new LinkedList<>();

        // Form title
        FormHelper.makeLable(count, formName, formsPanel, true);
        count++;

        // Make form
        for (int i = 0; i < labels.size(); i++)
        {
            FormHelper.makeLable(count, labels.get(i), formsPanel, false);
            fields.add(FormHelper.makeField(count, formsPanel));

            count++;
        }

        return fields;
    }

    public static LinkedList<JTextField> getTextDisplayFields(String formName, LinkedList<String> labels, LinkedList<String> texts, JPanel formsPanel) {
        int count = 0;
        LinkedList<JTextField> fields = new LinkedList<>();

        // Form title
        FormHelper.makeLable(count, formName, formsPanel, true);
        count++;

        // Make form
        for (int i = 0; i < labels.size(); i++) {
            FormHelper.makeLable(count, labels.get(i), formsPanel, false);
            fields.add(FormHelper.makeDisplayField(count, texts.get(i), formsPanel));

            count++;
        }

        return fields;
    }

    public static void showInfo(String formName, LinkedList<String> labels, LinkedList<String> fields, JPanel formsPanel) {
        int count = 0;

        // Form title
        FormHelper.makeLable(count, formName, formsPanel, true);
        count++;

        // Make form
        for (int i = 0; i < labels.size(); i++) {
            FormHelper.makeLable(count, labels.get(i), formsPanel, false);
            FormHelper.makeInfo(count, fields.get(i), formsPanel);

            count++;
        }
    }
}