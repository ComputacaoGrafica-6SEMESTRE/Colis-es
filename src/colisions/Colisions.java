/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colisions;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Megsoft
 */
public class Colisions extends JFrame {
    
    public Colisions() {
    }
    
    public void generateWindow() {
        setTitle("Trabalho de colisões");
        setSize(300, 300);
        
        // Centralizando Tela
        setLocationRelativeTo(null);
        
        Desenho desenho = new Desenho();
        add(desenho);
        
        // Janela Visível
        setVisible(true);
        
        desenho.setHeight(desenho.getSize().getHeight());
        desenho.setWidth(desenho.getSize().getWidth());
        
        setBackground(Color.white);
        
        setResizable(false);
 
        // Comportamento da Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Colisions app = new Colisions();
        app.generateWindow();
    }
}
