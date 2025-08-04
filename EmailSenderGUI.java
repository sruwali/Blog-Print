package com.blog.ai;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EmailSenderGUI {

    public interface EmailCallback {
        void sendEmail(String to, String from, String password);
    }

    public static void showEmailForm(String topic, File[] attachments, EmailCallback callback) {
        JFrame frame = new JFrame("Send Blog via Email");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel labelTo = new JLabel("Recipient Email:");
        JTextField textTo = new JTextField();

        JLabel labelFrom = new JLabel("Your Gmail:");
        JTextField textFrom = new JTextField();

        JLabel labelPass = new JLabel("App Password:");
        JPasswordField textPass = new JPasswordField();

        JButton sendBtn = new JButton("Send Email");
        JButton cancelBtn = new JButton("Cancel");

        frame.add(labelTo); frame.add(textTo);
        frame.add(labelFrom); frame.add(textFrom);
        frame.add(labelPass); frame.add(textPass);
        frame.add(sendBtn); frame.add(cancelBtn);

        sendBtn.addActionListener(e -> {
            String to = textTo.getText().trim();
            String from = textFrom.getText().trim();
            String password = new String(textPass.getPassword());

            if (to.isEmpty() || from.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
                return;
            }

            frame.dispose();
            callback.sendEmail(to, from, password);
        });

        cancelBtn.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
