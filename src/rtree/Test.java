package rtree;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();

        Object[] fields = {
                "Tên:", nameField,
                "Tuổi:", ageField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            System.out.println("Tên: " + name + ", Tuổi: " + age);
        }
    }
}