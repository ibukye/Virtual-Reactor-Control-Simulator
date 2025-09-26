import javax.swing.*;  // Swing Library for GUI
import java.awt.FlowLayout;     // Layout to organise Components

public class app {
    public static void main(String[] args) {
        // Start with special Thread
        SwingUtilities.invokeLater(() -> {

            // Window (JFrame)
            JFrame frame = new JFrame("Reactor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Close the program by press CLOSE button
            frame.setSize(300, 150);

            // Panel (JPanel) for set Layout
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());  // Left to Right

            // Components
            JLabel label = new JLabel("Message");
            JButton button = new JButton("CLick here");

            // Add EventListener to the button
            button.addActionListener(e -> {
                label.setText("Button is clicked");
            });

            // Add Components to the panel
            panel.add(label);
            panel.add(button);

            // Add panel to the window
            frame.add(panel);

            // display window
            frame.setVisible(true);
        });
    }
}