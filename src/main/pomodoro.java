import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Application Pomodoro Timer
 * Gère les cycles Focus (25min) / Break (5min) avec configuration des durées
 * Architecture : Swing + Timer + GridBagLayout
 */
public class StudyWithMePomodoro extends JFrame {
    
    // Composants interface utilisateur
    private JLabel timeLabel, statusLabel, workLabel, breakLabel;
    private JButton playBtn, pauseBtn, settingsBtn;
    private JButton workMinusBtn, workPlusBtn, breakMinusBtn, breakPlusBtn;
    
    // État du timer et configuration
    private Timer timer;
    private int seconds = 25 * 60;
    private int workTime = 25, breakTime = 5;
    private boolean isRunning = false, isWorkSession = true;
    
    // Dialogue de configuration
    private JDialog settingsDialog;
    private JSpinner workSpinner, breakSpinner;
    private JCheckBox showNotifications, spotifyToggle;

    /**
     * Constructeur principal - initialise et affiche la fenêtre
     */
    public StudyWithMePomodoro() {
        setTitle("Study With Me - Tomodoro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 650);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    /**
     * Initialise tous les composants de l'interface utilisateur
     */
    private void initUI() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(25, 25, 35));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(25, 25, 35));
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Initialisation des composants
        statusLabel = createLabel("FOCUS", Color.WHITE, 28);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeLabel = createLabel("25:00", Color.WHITE, 64);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        workLabel = createLabel("25 min", new Color(200, 200, 220), 16);
        breakLabel = createLabel("05 min", new Color(200, 180, 160), 16);

        workMinusBtn = createSmallRoundBtn("-");
        workPlusBtn = createSmallRoundBtn("+");
        breakMinusBtn = createSmallRoundBtn("-");
        breakPlusBtn = createSmallRoundBtn("+");

        playBtn = createControlBtn("▶", new Color(255, 120, 80));
        pauseBtn = createControlBtn("⏸", new Color(255, 120, 80));
        settingsBtn = createControlBtn("⚙", new Color(180, 180, 200));
        pauseBtn.setEnabled(false);

        // Configuration du timer (appel toutes les secondes)
        timer = new Timer(1000, e -> {
            if (isRunning) {
                seconds--;
                timeLabel.setText(formatTime(seconds));
                if (seconds <= 0) endSession();
            }
        });

        // Positionnement des composants avec GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 20, 25, 20);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        mainPanel.add(statusLabel, gbc);

        gbc.gridy = 1; gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 20, 40, 20);
        mainPanel.add(timeLabel, gbc);

        // Ligne Work time
        gbc.gridy = 2; gbc.gridwidth = 1; gbc.insets = new Insets(0, 20, 15, 10);
        gbc.gridx = 0; mainPanel.add(workMinusBtn, gbc);
        gbc.gridx = 1; gbc.weightx = 1; mainPanel.add(workLabel, gbc); gbc.weightx = 0;
        gbc.gridx = 2; mainPanel.add(workPlusBtn, gbc);

        // Ligne Break time
        gbc.gridy = 3; gbc.insets = new Insets(0, 20, 40, 10);
        gbc.gridx = 0; mainPanel.add(breakMinusBtn, gbc);
        gbc.gridx = 1; mainPanel.add(breakLabel, gbc);
        gbc.gridx = 2; mainPanel.add(breakPlusBtn, gbc);

        // Ligne contrôles
        gbc.gridy = 4; gbc.insets = new Insets(0, 20, 20, 20);
        gbc.gridx = 0; mainPanel.add(playBtn, gbc);
        gbc.gridx = 1; mainPanel.add(pauseBtn, gbc);
        gbc.gridx = 2; mainPanel.add(settingsBtn, gbc);

        // Association des gestionnaires d'événements
        workPlusBtn.addActionListener(e -> adjustWorkTime(1));
        workMinusBtn.addActionListener(e -> adjustWorkTime(-1));
        breakPlusBtn.addActionListener(e -> adjustBreakTime(1));
        breakMinusBtn.addActionListener(e -> adjustBreakTime(-1));
        
        playBtn.addActionListener(e -> startTimer());
        pauseBtn.addActionListener(e -> pauseTimer());
        settingsBtn.addActionListener(e -> showSettings());
    }

    /**
     * Crée un JLabel avec style uniforme
     */
    private JLabel createLabel(String text, Color color, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        label.setForeground(color);
        label.setOpaque(false);
        return label;
    }

    /**
     * Crée un petit bouton rond pour +/- (40x40px)
     * Personnalisation complète du rendu graphique
     */
    private JButton createSmallRoundBtn(String text) {
        JButton btn = new JButton(text) {
            @Override 
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Effet glassmorphism : 3 cercles concentriques
                g2.setColor(new Color(255, 255, 255, 30));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(100, 120, 160, 180));
                g2.fillOval(4, 4, getWidth()-8, getHeight()-8);
                
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawOval(2, 2, getWidth()-5, getHeight()-5);
                
                // Texte centré
                FontMetrics fm = g2.getFontMetrics(getFont());
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2;
                g2.setColor(new Color(60, 70, 90));
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setMinimumSize(new Dimension(40, 40));
        btn.setMaximumSize(new Dimension(40, 40));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        return btn;
    }

    /**
     * Crée un bouton de contrôle principal (60x60px)
     */
    private JButton createControlBtn(String icon, Color color) {
        JButton btn = new JButton(icon) {
            @Override 
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (isEnabled()) {
                    g2.setColor(color);
                    g2.fillOval(0, 0, getWidth(), getHeight());
                    g2.setColor(new Color(255, 255, 255, 200));
                    g2.setStroke(new BasicStroke(2));
                    g2.drawOval(2, 2, getWidth()-5, getHeight()-5);
                }
                
                FontMetrics fm = g2.getFontMetrics(getFont());
                int x = (getWidth() - fm.stringWidth(icon)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2;
                g2.setColor(Color.WHITE);
                g2.drawString(icon, x, y);
                g2.dispose();
            }
        };
        
        btn.setPreferredSize(new Dimension(60, 60));
        btn.setMinimumSize(new Dimension(60, 60));
        btn.setMaximumSize(new Dimension(60, 60));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        return btn;
    }

    /**
     * Ajuste la durée de travail (limites : 1-60 minutes)
     */
    private void adjustWorkTime(int delta) {
        workTime = Math.max(1, Math.min(60, workTime + delta));
        workLabel.setText(workTime + " min");
        if (!isRunning && isWorkSession) {
            seconds = workTime * 60;
            timeLabel.setText(formatTime(seconds));
        }
    }

    /**
     * Ajuste la durée de pause (limites : 1-30 minutes)
     */
    private void adjustBreakTime(int delta) {
        breakTime = Math.max(1, Math.min(30, breakTime + delta));
        breakLabel.setText(String.format("%02d min", breakTime));
    }

    /**
     * Démarre ou reprend le timer
     */
    private void startTimer() {
        seconds = isWorkSession ? workTime * 60 : breakTime * 60;
        isRunning = true;
        timer.start();
        playBtn.setEnabled(false);
        pauseBtn.setEnabled(true);
    }

    /**
     * Met en pause le timer
     */
    private void pauseTimer() {
        isRunning = false;
        timer.stop();
        playBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
    }

    /**
     * Gère la fin automatique d'une session (Focus→Break ou Break→Focus)
     */
    private void endSession() {
        isRunning = false;
        timer.stop();
        playBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        
        if (isWorkSession) {
            // Transition Focus → Break
            seconds = breakTime * 60;
            isWorkSession = false;
            statusLabel.setText("BREAK");
            statusLabel.setForeground(new Color(255, 200, 150));
            JOptionPane.showMessageDialog(this, "Time for a break!");
        } else {
            // Transition Break → Focus
            seconds = workTime * 60;
            isWorkSession = true;
            statusLabel.setText("FOCUS");
            statusLabel.setForeground(Color.WHITE);
            JOptionPane.showMessageDialog(this, "Back to focus!");
        }
        timeLabel.setText(formatTime(seconds));
    }

    /**
     * Affiche la fenêtre de configuration des paramètres
     */
    private void showSettings() {
        if (settingsDialog == null) {
            settingsDialog = new JDialog(this, "Settings", true);
            settingsDialog.setSize(350, 320);
            settingsDialog.setLocationRelativeTo(this);
            
            JPanel settingsPanel = new JPanel(new GridBagLayout());
            settingsPanel.setBackground(new Color(30, 30, 40));
            settingsDialog.setContentPane(settingsPanel);

            JLabel title = new JLabel("Settings", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setForeground(Color.WHITE);
            title.setBorder(new EmptyBorder(20, 0, 30, 0));

            workSpinner = new JSpinner(new SpinnerNumberModel(workTime, 1, 60, 1));
            breakSpinner = new JSpinner(new SpinnerNumberModel(breakTime, 1, 30, 1));
            
            JLabel workL = new JLabel("Work time (minutes):");
            JLabel breakL = new JLabel("Break time (minutes):");
            workL.setForeground(Color.WHITE);
            breakL.setForeground(Color.WHITE);

            showNotifications = new JCheckBox("Show notifications");
            spotifyToggle = new JCheckBox("Show Spotify playlist");
            showNotifications.setForeground(Color.WHITE);
            showNotifications.setBackground(new Color(30, 30, 40));
            spotifyToggle.setForeground(Color.WHITE);
            spotifyToggle.setBackground(new Color(30, 30, 40));

            JButton saveBtn = new JButton("Save changes");
            saveBtn.setBackground(new Color(80, 200, 120));
            saveBtn.setForeground(Color.WHITE);
            saveBtn.setFocusPainted(false);
            saveBtn.setBorderPainted(false);
            saveBtn.setPreferredSize(new Dimension(120, 40));

            JButton resetBtn = new JButton("Reset all");
            resetBtn.setForeground(new Color(220, 80, 80));
            resetBtn.setBackground(new Color(30, 30, 40));
            resetBtn.setBorderPainted(false);
            resetBtn.setFocusPainted(false);
            resetBtn.setContentAreaFilled(false);

            JButton closeBtn = new JButton("Close");
            closeBtn.setForeground(Color.WHITE);
            closeBtn.setBackground(new Color(30, 30, 40));
            closeBtn.setBorderPainted(false);
            closeBtn.setFocusPainted(false);
            closeBtn.setContentAreaFilled(false);

            // Layout des paramètres
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 15, 10, 15);

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            settingsPanel.add(title, gbc);

            gbc.gridy = 1; gbc.gridwidth = 1;
            gbc.gridx = 0; settingsPanel.add(workL, gbc);
            gbc.gridx = 1; settingsPanel.add(workSpinner, gbc);

            gbc.gridy = 2;
            gbc.gridx = 0; settingsPanel.add(breakL, gbc);
            gbc.gridx = 1; settingsPanel.add(breakSpinner, gbc);

            gbc.gridy = 3; gbc.gridwidth = 2;
            gbc.gridx = 0; settingsPanel.add(showNotifications, gbc);

            gbc.gridy = 4;
            gbc.gridx = 0; settingsPanel.add(spotifyToggle, gbc);

            gbc.gridy = 5; gbc.gridwidth = 1;
            gbc.gridx = 0; settingsPanel.add(resetBtn, gbc);
            gbc.gridx = 1; settingsPanel.add(closeBtn, gbc);

            gbc.gridy = 6; gbc.gridwidth = 2;
            gbc.gridx = 0; settingsPanel.add(saveBtn, gbc);

            // Gestionnaires d'événements des boutons de paramètres
            saveBtn.addActionListener(e -> {
                workTime = (Integer) workSpinner.getValue();
                breakTime = (Integer) breakSpinner.getValue();
                workLabel.setText(workTime + " min");
                breakLabel.setText(String.format("%02d min", breakTime));
                settingsDialog.dispose();
            });

            closeBtn.addActionListener(e -> settingsDialog.dispose());
        }
        settingsDialog.setVisible(true);
    }

    /**
     * Formate le temps au format MM:SS
     */
    private String formatTime(int totalSeconds) {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    /**
     * Point d'entrée de l'application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudyWithMePomodoro());
    }
}
