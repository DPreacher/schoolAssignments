/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import model.DrawingModel;
import model.LineStrokeModel;
import view.View;

/**
 * 
 * @author dingram20
 */
public class Controller 
implements MouseListener, MouseMotionListener, 
        ActionListener, ComponentListener, WindowListener,WindowFocusListener, WindowStateListener {
    int resized =0;
    View view;
    LineStrokeModel stroke;
    DrawingModel drawingModel;
    
     Color currentColour=Color.BLACK;
    int currentThickness=1;
    /** Controller
     * 
     * @param view 
     * @param drawingModel
     * @param lineModel 
     */
    public Controller(View view,DrawingModel drawingModel) {
        this.view = view;
        
        this.drawingModel=drawingModel;
        view.setSize(600, 400);
        
        // Set Listener
        view.getDrawingPanel().addMouseListener(this);
        view.getDrawingPanel().addMouseMotionListener(this);
        view.getDrawingPanel().addComponentListener(this);
        view.addWindowListener(this);
        view.addWindowFocusListener(this);

        //Create Panels
        
        view.addSidePanel(view.getEastPanel(), BorderLayout.EAST);
        view.addSidePanel(view.getWestPanel(), BorderLayout.WEST);
        view.addSidePanel(view.getSouthPanel(), BorderLayout.SOUTH);
        
        //Create Colour Buttons
        view.addButton(view.getWestPanel(), "RED", BorderLayout.WEST,view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "BLUE", BorderLayout.WEST,view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "GREEN", BorderLayout.WEST,view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "BLACK", BorderLayout.WEST,view.getMedBtnSize(), this);
        
        //Create Thickness Buttons
        view.addButton(view.getEastPanel(), "THIN", BorderLayout.EAST,view.getSmallBtnSize(), this);
        view.addButton(view.getEastPanel(), "MEDIUM", BorderLayout.EAST,view.getMedBtnSize(), this);
        view.addButton(view.getEastPanel(), "THICK", BorderLayout.EAST,view.getLargeBtnSize(),this);
        
        //Create Save/Load Options
        view.addButton(view.getInnerPanel(), "SAVE", BorderLayout.WEST,view.getMedBtnSize(), this);
        view.addButton(view.getInnerPanel(), "OPEN", BorderLayout.WEST,view.getMedBtnSize(), this);
        view.addTextArea(view.getTextArea(), view.getInnerPanel(), BorderLayout.EAST);
        //Set Panel Colours
        view.getDrawingPanel().setBackground(Color.LIGHT_GRAY);
//        add(view.getScrollPane());
        // Show Screen
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Controller() {
    }
    
    private void redraw(){
        for(int i=0;i<this.drawingModel.getNumberLines();i++){
            stroke=drawingModel.getLine(i);
            
            
            view.drawPoints(stroke.getPoints(),stroke.getColour(),stroke.getThickness());
        }
        
        //view.drawPoints(stroke.getPoints(),this.currentColour,this.currentThickness);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.stroke=new LineStrokeModel(this.currentColour,this.currentThickness);
        Point pt = e.getPoint();
        view.setCurrentPosition(pt);
        stroke.addPoint(pt);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point pt = e.getPoint();
        view.addPoint(pt,this.currentColour,this.currentThickness);
        stroke.addPoint(pt);
        this.drawingModel.addLine(stroke);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point pt = e.getPoint();
        view.addPoint(pt, this.currentColour,this.currentThickness);
        stroke.addPoint(pt);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Color  colour = checkColour(e.getActionCommand());
         if(colour!=null){
            this.currentColour=colour;
            view.getTextArea().append("Colour changed: "+colour.toString()+"\n");
         }
         else{
             switch(e.getActionCommand()){
                 case "THIN":
                     this.currentThickness=1;
                     view.getTextArea().append("Thickness changed: THIN\n");
                     break;
                 case "MEDIUM":
                     this.currentThickness=5;
                     view.getTextArea().append("Thickness changed: MEDIUM\n");
                     break;
                 case "THICK":
                     this.currentThickness=10;
                     view.getTextArea().append("Thickness changed: THICK\n");
                     break;
                 case "SAVE":
                     save();
                     break;
                 case "OPEN":
                     open();
                     break;
             }
         }
    }
    private Color checkColour(String name){
        Color colour;
        try {
            Field field = Class.forName("java.awt.Color").getField(name);
            colour = (Color) field.get(null);

        } catch (Exception e) {
            colour = null; // Not defined
        }
        return colour;
    }
    @Override
    public void componentResized(ComponentEvent e) {
        redraw();
        System.out.println("I'm Resized");
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        redraw();
       
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
       
    }

    @Override
    public void windowIconified(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       redraw();
    }

    @Override
    public void windowActivated(WindowEvent e) {
        redraw();
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        redraw();
         
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowStateChanged(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void save(){
        
    }
    public void open(){
        FileInputStream inputStream = null;
        
        JFileChooser fc = new JFileChooser(System.clearProperty("user.home"));
        int result = fc.showOpenDialog(null);
        switch(result){
            case JFileChooser.APPROVE_OPTION:
                
                //TODO DESIRIALIZE
                //this.drawingModel = (ArrayList<DrawingModel>)inputStream.readObject();
                break;
            case JFileChooser.CANCEL_OPTION:
                //TODO CANCEL OPEN FILE CODE 
                break;
            case JFileChooser.ERROR_OPTION:
                //TODO handle open error
                break;
        }
        //TODO OPEN SERIALIZATION CODE HERE
        //TODO store SERIALIZED Data
    }
}
