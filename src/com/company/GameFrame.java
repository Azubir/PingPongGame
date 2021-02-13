package com.company;

import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameFrame extends JFrame{
    GamePanel panel;
    public GameFrame(){
        ImageIcon img =new ImageIcon("com/company/rsz_flat-pin-pong-table-top-view-ping-pong-field-w-vector-21075789.jpg");


        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(new Color(70,160,126));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//makes appear middle

    }


}


