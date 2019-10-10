/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colisions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.List;
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
    private int id;
    
    /**
     * Construtor
     * @param typeObject
     * @param id
     * @param e
     */
    public Objects(int typeObject, int id, MouseEvent e) {
        this.typeObject = typeObject;
        this.id = id;
        random = new Random();
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        x = e.getX();
        y = e.getY();
        xInicial = x;
        yInicial = y;
        
        velX = random.nextInt(7 + 2) - 2;
        velY = random.nextInt(7 + 2) - 2;
        
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
                height = width;
                break;
            case 3:
                x1 = random.nextInt(40) + 10;
                x2 = x1 * 2;
                y1 = x2;
                y2 = x2;
                break;
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
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) { 
        this.id = id;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getX1() {
        return this.x1;
    }
    
    public int getWidthTriangle() {
        return this.x2 - this.x1;
    }
    
    public int getType() {
        return this.typeObject;
    }
    
    public Polygon getTriangle() {
        return triangle;
    }
    
    /**
     * Responsável por movimentos e colisões
     * @param heightPanel
     * @param widthPanel 
     * @param lista
     */
    public void move(double heightPanel, double widthPanel, List<Objects> lista) {
        switch(typeObject) {
            case 3:
                if(x <= 0 || x >= widthPanel - triangle.getBounds().width) {
                    velX = -velX;
                }

                if(y <= 0 || y >= heightPanel - triangle.getBounds().height) {
                    velY = -velY;
                }

                for (Objects o : lista) {
                    if(o.getType() == 3 && id != o.getId()) {
                        if(x <= o.getX() + o.getWidthTriangle() && 
                            x >= o.getX() + (o.getWidthTriangle() / 2) &&
                            o.getY() + o.getTriangle().getBounds().height >= y &&
                            o.getY() + o.getTriangle().getBounds().height <= y + triangle.getBounds().height) {
                            velX = -velX;
                        }
                        
//                        if(x == (o.getX() - o.height)) {;
//                            velX = -velX;
//                            System.out.println("teste");
//                        }
//                        
//                        if(x == (o.getX() + (o.width / 2)) && y == o.getX() - o.height) {
//                            velX = -velX;
//                            System.out.println("teste");                            
//                        }
                    }
                }
                x = x + velX;
                y = y + velY;
                break;
            default:
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
}
