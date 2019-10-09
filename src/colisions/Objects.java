/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colisions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

/**
 *
 * @author Megsoft
 */
public class Objects {
    private int typeObject, x, y, width, height, velX, velY;
    private int yInicial, xInicial;
    private int x1, x2, y1, y2;
    private Random random;
    private Polygon triangle;
    private Color color;
    private boolean moveBack = true;
    
    /**
     * Construtor
     * @param typeObject 
     */
    public Objects(int typeObject) {
        this.typeObject = typeObject;
        random = new Random();
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        x = random.nextInt(300) + 10;
        y = random.nextInt(300) + 10;
        xInicial = x;
        yInicial = y;
        
        velX = random.nextInt(2 + 2) - 2;
        velY = random.nextInt(2 + 2) - 2;
        
        if(velX < 1 && velY < 1) {
            velX = 1;
            velY = 1;
        }
        
        if(velX > -1 && velX < 0 && velY > -1 && velY < 0) {
            velX = -1;
            velY = -1;
        }
        
        // Switch feito para se adequar a cada uma das figuras
        switch(typeObject) {
            case 1:
                width = random.nextInt(110) + 10;
                height = width;
                break;
            case 2:
                width = random.nextInt(110) + 10;
                height = random.nextInt(110) + 10;
                break;
            case 3:
                x1 = random.nextInt(40) + 10;
                x2 = x1 * 2;
                y1 = x2;
                y2 = x2;
        }
    }
    
    /**
     * Função que realiza desenho de objetos
     * @param g2d 
     */
    public void drawObjects(Graphics2D g2d) {        
        // 1 - Circulo; 2 - Quadrado; default - Triângulo
        switch(typeObject) {
            case 1:
                g2d.setColor(color);
                g2d.fillOval(x,y,width,height);
                break;
            case 2:
                g2d.setColor(color);
                g2d.fillRect(x, y, width, height);
                System.out.println(height);
                break;
            default:
                triangle = new Polygon(
                    new int[]{x1, x2, 0},
                    new int[]{0, y1, y2},
                    3
                );
                triangle.translate(x, y);
                
                g2d.setColor(color);
                g2d.fill(triangle);
        }
    }
    
    /**
     * Setando o valor do eixo X
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Recupera valor do eixo X
     * @return x
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Setando o valor do eixo Y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Recupera valor do eixo Y
     * @return y
     */
    public int getY() {
        return this.y;
    }
    
    public void move(double heightPanel, double widthPanel) {
        if(x <= 0 || x >= widthPanel - width) {
            velX = -velX;
        }
        
        if(y <= 0 || y >= heightPanel - height) {
            velY = -velY;
        }

        x = x + velX;
        y = y + velY;
    }
}
