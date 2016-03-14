
import controller.Controller;
import model.DrawingModel;
import model.LineStrokeModel;
import view.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dingram20
 */
public class MyPaint {
    
    public static void main(String[] args)
    {
        
        LineStrokeModel lineModel = new LineStrokeModel();
        DrawingModel drawModel = new DrawingModel();
        View view = new View("MyDrawings");
        Controller controller = new Controller(view,drawModel);
        
        
    }
}
