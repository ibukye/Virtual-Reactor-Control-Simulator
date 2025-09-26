import javax.swing.*;  // Swing Library for GUI
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {
    public static void main(String[] args) {

        // Start with special Thread
        SwingUtilities.invokeLater(() -> {

            final double[] temperature = {0.0};
            final double[] pressure = {0.0};

            final double[] coolance = {0.0};

            // String reactance1;
            // String reactance2;
            
            // double control;     // ???
            final double[] max_temperature = {100.0};
            final double[] max_pressure = {100.0};

            // gameOver flag
            final boolean[] isGameOver = {false};

            // Window (JFrame)
            JFrame frame = new JFrame("Reactor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Close the program by press CLOSE button
            frame.setSize(1500, 750);

            // Panel (JPanel) for set Layout
            JPanel panel = new JPanel();
            panel.setLayout(null);

            // Components
            JLabel label_gameOver = new JLabel("");
            
            JLabel label_temperature = new JLabel("temperature(c): ");
            JLabel label_pressure = new JLabel("pressure(hPa): ");

            JButton button_start = new JButton("CLick here to start chemical reaction");
            JButton button_coolance = new JButton("Click here to add coolance");
            JButton button_heatance = new JButton("Click here to add heatance");

            // Progress bar
            JProgressBar progressBar_temperature = new JProgressBar(0, (int) max_temperature[0]);
            JProgressBar progressBar_pressure = new JProgressBar(0, 297);

            // setBounds(x, y, width, height)
            label_temperature.setBounds(50, 50, 400, 30);
            progressBar_temperature.setBounds(200, 50, 400, 30);
            button_coolance.setBounds(800, 50, 200, 30);
            button_heatance.setBounds(1200, 50, 200, 30);
            
            label_pressure.setBounds(50, 100, 400, 30);
            progressBar_pressure.setBounds(200, 100, 400, 30);
            
            button_start.setBounds(300, 200, 400, 50);
            

            label_gameOver.setBounds(250, 300, 300, 50);

            panel.add(label_gameOver);
            panel.add(label_temperature);
            panel.add(label_pressure);
            
            panel.add(button_start);
            panel.add(button_coolance);
            panel.add(button_heatance);

            panel.add(progressBar_temperature);
            panel.add(progressBar_pressure);


            // Game Over Timer
            Timer gameOverTimer = new Timer(1000, new ActionListener() {
                private int gameOverTime = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameOverTime++;
                    if (gameOverTime <= 5) {
                        label_gameOver.setText(gameOverTime + "s");
                    } else {
                        ((Timer) e.getSource()).stop();
                        label_gameOver.setText("The Experiment was Failed!!!");
                        isGameOver[0] = true;
                    }
                }
            });



            // Temperature
            Timer temperature_scope = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    temperature[0] += (max_temperature[0] /10) - coolance[0];   // max within 10s
                    if (temperature[0] <= max_temperature[0]) {
                        progressBar_temperature.setValue((int) temperature[0]);
                        label_temperature.setText("temperature(c): " + temperature[0]);
                    } else {
                        ((Timer) e.getSource()).stop();
                        label_temperature.setText("Temperature Exceeded the Limit!!!");
                        gameOverTimer.start();
                    }

                }
            });

            // Pressure
            Timer pressure_scope = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pressure[0] += (max_pressure[0] /10);   // max within 5s
                    if (pressure[0] <= max_pressure[0]) {
                        progressBar_pressure.setValue((int) pressure[0]);
                        label_pressure.setText("pressure(hPa): " + pressure[0]);
                    } else {
                        ((Timer) e.getSource()).stop();
                        label_pressure.setText("Pressure Exceeded the Limit!!!");
                        gameOverTimer.start();
                    }

                }
            });


            // Add EventListener to the button
            button_start.addActionListener(e -> {
                max_temperature[0] = 100;
                max_pressure[0] = 300;
                temperature_scope.start();
                pressure_scope.start();
            });

            button_coolance.addActionListener(e -> {
                coolance[0]++;
            });
            button_heatance.addActionListener(e -> {
                coolance[0]--;
            });
            

            // Add panel to the window
            frame.add(panel);

            // display window
            frame.setVisible(true);
        });
    }
}