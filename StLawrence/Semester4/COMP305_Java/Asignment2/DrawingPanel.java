/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cbanger
 */
class DrawingPanel extends JPanel
implements MouseListener, MouseMotionListener, ActionListener
{
    Point currentPosition;
    Graphics2D graphics;
    Color colour;
    BasicStroke line;

    public DrawingPanel() {
        line =new BasicStroke(1);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        JOptionPane.showMessageDialog(this, point);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentPosition = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point nextPosition = e.getPoint();
        graphics = (Graphics2D)getGraphics();
        graphics.setColor(colour);
        graphics.setStroke(line);
        graphics.drawLine(currentPosition.x, currentPosition.y, 
                nextPosition.x, nextPosition.y);
        currentPosition = nextPosition;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Red"){
            colour= Color.red;
        }
        else if(e.getActionCommand()=="Blue"){
            colour=Color.BLUE;
        }
        else if(e.getActionCommand()=="Green"){
            colour=Color.GREEN;
        }
        else if(e.getActionCommand()=="Thin"){
            line = new BasicStroke(1);
        }
        else if(e.getActionCommand()=="Medium"){
            line = new BasicStroke(5);
        }
        else if(e.getActionCommand()=="Thick"){
            line = new BasicStroke(10);
        }
        
    }
    
}
