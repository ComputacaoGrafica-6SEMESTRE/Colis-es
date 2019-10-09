/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colisions;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Megsoft
 */
public class Desenho extends JPanel {
    private boolean antalign = true;
    private List<Objects> listaObjetcs;
    final int CIRCULO = 1, QUADRADO = 2, TRIANGULO = 3;
    private Timer timer;
    private double height;
    private double width;
    private int count = 0;
    
    public Desenho() {
        listaObjetcs = new ArrayList<>();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                
                // Clique Botão Esquerdo
                if(e.getButton() == MouseEvent.BUTTON1) {
                    listaObjetcs.add(new Objects(CIRCULO, ++count, e));
                }
                // Clique Botão do Meio
                if(e.getButton() == MouseEvent.BUTTON2) 
                    listaObjetcs.add(new Objects(QUADRADO, ++count, e));

                // Clique Botão direito
                if(e.getButton() == MouseEvent.BUTTON3) 
                    listaObjetcs.add(new Objects(TRIANGULO, ++count, e));
                repaint();
            }
        });
        
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Objects o : listaObjetcs) {
                    o.move(height, width, listaObjetcs);
                    repaint();
                }
            }
        });
        
        timer.start();
        
        timer.setInitialDelay(0);
        timer.setRepeats(true);
        timer.setCoalesce(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(antalign) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            for (Objects o : listaObjetcs) {
                o.drawObjects(g2d);
            }
        }
    }
    
    public void setDesenho(Objects o) {
        this.listaObjetcs.add(o);
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public List<Objects> getLista() {
        return listaObjetcs;
    }
}
