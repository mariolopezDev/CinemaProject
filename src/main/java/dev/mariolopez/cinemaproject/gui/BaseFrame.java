package dev.mariolopez.cinemaproject.gui;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    protected JLabel lblStatus;

    public BaseFrame(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        add(createStatusPanel(), BorderLayout.SOUTH);  // Panel de estado común
    }

    protected JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setPreferredSize(new Dimension(getWidth(), 20)); 
        lblStatus = new JLabel(" ", SwingConstants.CENTER);
        lblStatus.setOpaque(true);
        statusPanel.add(lblStatus);
        return statusPanel;
    }

    protected void showStatusMessage(String message, Color color) {
        lblStatus.setText(message);
        lblStatus.setBackground(color);
        lblStatus.setOpaque(true);
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 14));  
        lblStatus.setForeground(Color.WHITE);

        Timer timer = new Timer(2000, e -> {
            lblStatus.setText(" ");
            lblStatus.setOpaque(false);
            lblStatus.setBackground(getBackground());
            lblStatus.setForeground(Color.BLACK);
            lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));  
        });
        timer.setRepeats(false);
        timer.start();
    }
}
