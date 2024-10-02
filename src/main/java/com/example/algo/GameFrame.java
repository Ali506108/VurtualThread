package com.example.algo;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // Adjusts frame to fit its components
        this.setLocationRelativeTo(null); // Centers the frame
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }

}