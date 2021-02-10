package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameFrame extends JFrame{
    GamePanel panel;
    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//makes appear middle
    }

}
