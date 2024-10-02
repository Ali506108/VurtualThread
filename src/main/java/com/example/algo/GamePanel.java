package com.example.algo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // Existing variables
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    // New variables for the AI-controlled snake
    final int aiX[] = new int[GAME_UNITS];
    final int aiY[] = new int[GAME_UNITS];
    int aiBodyParts = 6;
    char aiDirection = 'L';
    int aiApplesEaten;
    boolean aiRunning = true;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        // Initialize AI snake's starting position
        for (int i = 0; i < aiBodyParts; i++) {
            aiX[i] = SCREEN_WIDTH - (i * UNIT_SIZE) - UNIT_SIZE;
            aiY[i] = SCREEN_HEIGHT / 2;
        }

        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        aiRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw the apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw the player's snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Head
                } else {
                    g.setColor(new Color(45, 180, 0)); // Body
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Draw the AI snake
            for (int i = 0; i < aiBodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.blue); // AI Head
                } else {
                    g.setColor(new Color(0, 0, 180)); // AI Body
                }
                g.fillRect(aiX[i], aiY[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Display scores
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            g.drawString("Your Score: " + applesEaten, 10, g.getFont().getSize());
            g.drawString("AI Score: " + aiApplesEaten, SCREEN_WIDTH - 150, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        // Move player's snake
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Update player's snake head position
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void moveAI() {
        // Move AI snake body
        for (int i = aiBodyParts; i > 0; i--) {
            aiX[i] = aiX[i - 1];
            aiY[i] = aiY[i - 1];
        }

        // Simple AI logic to move towards the player
        if (aiX[0] < x[0]) {
            aiDirection = 'R';
        } else if (aiX[0] > x[0]) {
            aiDirection = 'L';
        } else if (aiY[0] < y[0]) {
            aiDirection = 'D';
        } else if (aiY[0] > y[0]) {
            aiDirection = 'U';
        }

        // Update AI snake head position
        switch (aiDirection) {
            case 'U':
                aiY[0] = aiY[0] - UNIT_SIZE;
                break;
            case 'D':
                aiY[0] = aiY[0] + UNIT_SIZE;
                break;
            case 'L':
                aiX[0] = aiX[0] - UNIT_SIZE;
                break;
            case 'R':
                aiX[0] = aiX[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        // Player eats apple
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }

        // AI eats apple
        if ((aiX[0] == appleX) && (aiY[0] == appleY)) {
            aiBodyParts++;
            aiApplesEaten++;
            newApple();

            // Increase AI speed to simulate learning
            if (DELAY > 30) {
                DELAY -= 5;
                timer.setDelay(DELAY);
            }
        }
    }

    public void checkCollisions() {
        // Check if player's head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }

        // Check if player's head collides with AI snake
        for (int i = 0; i < aiBodyParts; i++) {
            if ((x[0] == aiX[i]) && (y[0] == aiY[i])) {
                running = false;
                break;
            }
        }

        // Check if player's head touches borders
        if (x[0] < 0 || x[0] > SCREEN_WIDTH - UNIT_SIZE || y[0] < 0 || y[0] > SCREEN_HEIGHT - UNIT_SIZE) {
            running = false;
        }

        // Stop timer if game over
        if (!running) {
            timer.stop();
        }
    }

    public void checkAICollisions() {
        // Check if AI's head collides with its body
        for (int i = aiBodyParts; i > 0; i--) {
            if ((aiX[0] == aiX[i]) && (aiY[0] == aiY[i])) {
                aiRunning = false;
                break;
            }
        }

        // Check if AI's head collides with player's snake
        for (int i = 0; i < bodyParts; i++) {
            if ((aiX[0] == x[i]) && (aiY[0] == y[i])) {
                running = false;
                break;
            }
        }

        // Check if AI's head touches borders
        if (aiX[0] < 0 || aiX[0] > SCREEN_WIDTH - UNIT_SIZE || aiY[0] < 0 || aiY[0] > SCREEN_HEIGHT - UNIT_SIZE) {
            aiRunning = false;
        }
    }

    public void gameOver(Graphics g) {
        // Display Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        // Display scores
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        g.drawString("Your Score: " + applesEaten, (SCREEN_WIDTH / 2) - 100, SCREEN_HEIGHT / 2 + 50);
        g.drawString("AI Score: " + aiApplesEaten, (SCREEN_WIDTH / 2) - 100, SCREEN_HEIGHT / 2 + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            moveAI();
            checkApple();
            checkCollisions();
            checkAICollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // Player controls
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}












