package com.smoothiemaker.view;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JTextArea statusArea;

    public StatusPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        initializeStatusArea();
    }

    private void initializeStatusArea() {
        statusArea = new JTextArea(5, 20);
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(statusArea);
        scrollPane.setPreferredSize(new Dimension(400, 120));
        add(scrollPane);
    }

    public void updateStatus(String message) {
        statusArea.append(message + "\n");
        statusArea.setCaretPosition(statusArea.getDocument().getLength());
    }
}