/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author dingram20
 */
public class View extends JFrame {

    JPanel drawingPanel;
    Point currentPosition;

    // Create Panels
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel southInnerPanel = new JPanel();
    
    
    private JTextArea logText = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(logText);
    //Define button Sized
    private Dimension sidePanelSize = new Dimension(200, 400);
    private Dimension northSize = new Dimension(200, 400);
    private Dimension smallBtn = new Dimension(100, 17);
    private Dimension mediumBtn = new Dimension(100, 22);
    private Dimension largeBtn = new Dimension(100, 30);
    private Dimension dmTxtArea = new Dimension(400,45);
    
  
    
    BorderLayout layout;

    public View(String title) {
        super(title);
        
        drawingPanel = new JPanel();
        add(drawingPanel);
        
        southPanel.setLayout(layout);
        
        //logText.setSize(dmTxtArea);
        logText.setLineWrap(true);
        logText.setEditable(true);
        logText.setVisible(true);
        
        scrollPane.setBounds(10, 101, 742, 276);
        
        //southInnerPanel.add(scrollPane,BorderLayout.EAST);
        scrollPane.setSize(dmTxtArea);
        southInnerPanel.add(logText);
        
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        southPanel.add(southInnerPanel, BorderLayout.WEST);
        
        add(southPanel, BorderLayout.SOUTH);
        //TODO add scrollable textfield
        
    }

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

   
    public void addPoint(Point pt, Color colour, int thickness) {
        
        Graphics2D graphics = (Graphics2D) drawingPanel.getGraphics();
        graphics.setStroke(new BasicStroke(thickness));
        graphics.setColor(colour);
        graphics.drawLine(
                currentPosition.x, currentPosition.y,
                pt.x, pt.y);
        currentPosition = pt;
    }

    public void drawPoints(ArrayList<Point> ptList, Color colour, int thickness) {
        Point pt1, pt2;
        Graphics2D graphics = (Graphics2D) drawingPanel.getGraphics();
        graphics.setStroke(new BasicStroke(thickness));
        graphics.setColor(colour);
        
        for (int p = 1; p < ptList.size(); p++) {
            pt1 = ptList.get(p - 1);
            pt2 = ptList.get(p);
            graphics.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
        }
        graphics.dispose();
    }

    
    public void addButton(JPanel panel, String name, String location, Dimension size, ActionListener listener) {
        JButton button = new JButton(name);
        button.addActionListener(listener);
        button.setMaximumSize(size);
        panel.add(button);
    }
    
   
    public void addSidePanel(JPanel panel, String location) {
        //panel.setBackground(colour);

      if(panel == southPanel){
          //panel.setLayout();
      }
      if(panel == southInnerPanel){
          panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
      }
      else
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
       
        add(panel, location);
    }
    public void addTextArea(JTextArea textArea, JPanel panel, String location){
        textArea.setLineWrap(false);
        textArea.setForeground(Color.RED);
        textArea.setPreferredSize(dmTxtArea);
        panel.add(textArea,location);
    }
    
    
    public JPanel getWestPanel() {
        return westPanel;
    }
    public JTextArea getTextArea(){
        return this.logText;
    }
    public JPanel getEastPanel() {
        return eastPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    //public void addPanel(JPanel panel, )
    public Dimension getSmallBtnSize() {
        return smallBtn;
    }

    public Dimension getMedBtnSize() {
        return mediumBtn;
    }

    public Dimension getLargeBtnSize() {
        return largeBtn;
    }

    public void drawPoints(ArrayList<Point> points) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void createInnerPanel(JPanel panel){
        panel.setLayout(layout);
        
    }
    public JPanel getInnerPanel(){
        return southInnerPanel;
    }
}
