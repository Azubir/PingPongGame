package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int Game_Width = 1000;
    static final int Game_Height = (int)(Game_Width * (0.5555));
    static final Dimension Screen_Size = new Dimension(Game_Width,Game_Height);
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH =50;
    static final int PADDLE_HEIGHT =50;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;


    public GamePanel(){
        newPaddles();
        newBall();
        score = new Score(Game_Width,Game_Height);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(Screen_Size);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        //random = new Random();
        Random random = new Random();
        ball = new Ball((Game_Width/2) - (BALL_DIAMETER/2), (random.nextInt(Game_Height)) - (BALL_DIAMETER-2),BALL_DIAMETER,BALL_DIAMETER);

    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(Game_Height/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle((Game_Width - PADDLE_WIDTH),(Game_Height/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        //bounce ball off top & bottom window edges
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= Game_Height - BALL_DIAMETER ){
            ball.setYDirection(-ball.yVelocity);
        }

        //bounces ball of paddle
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;//MORE DIFICULTY
            if(ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;//MORE DIFICULTY
            if(ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //stops paddles at window edges
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (Game_Height - PADDLE_HEIGHT))
            paddle1.y = (Game_Height - PADDLE_HEIGHT);
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (Game_Height - PADDLE_HEIGHT))
            paddle2.y = (Game_Height - PADDLE_HEIGHT);

        //give a player one point and creates new paddles & ball
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("pLAYER 1 : " + score.player2);
        }

        if(ball.x >= (Game_Width - BALL_DIAMETER)){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("pLAYER 2: " + score.player1);
        }
    }

    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            paddle2.keyReleased(e);
            paddle1.keyReleased(e);
        }
    }

}
