/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Hashtable;


/**
 *
 * @author eslam
 */
public class Rectangle extends Shape{
     
  
    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        if(fillColor==Color.WHITE){
            g.setColor(Color.BLACK);
            g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2),Math.abs(y1-y2));
        }
        else{
            g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2),Math.abs(y1-y2));
        }
        if(isSelect()){
            drawBorder(g);
        }
    }
    public void drawBorder(Graphics g){
        int Xmin = Math.min(x1, x2);
        int Xmax = Math.max(x1, x2);
        int Ymin = Math.min(y1, y2);
        int Ymax = Math.max(y1, y2);
        int n = 4;
        int smallWidth = 8;
        int smallHeight = 8; 
            g.setColor(Color.BLUE);
            g.drawRect(Xmin-n, Ymin-n, smallWidth, smallHeight);
            g.drawRect((x1+x2)/2-n, Ymin-n, smallWidth, smallHeight);
            g.drawRect(Xmax-n, Ymin-n, smallWidth, smallHeight);
            g.drawRect(Xmax-n, (y1+y2)/2-n, smallWidth, smallHeight);
            g.drawRect(Xmax-n, Ymax-n,smallWidth,smallHeight);
            g.drawRect((x1+x2)/2-n, Ymax-n, smallWidth, smallHeight);
            g.drawRect(Xmin-n, Ymax-n, smallWidth, smallHeight);
            g.drawRect(Xmin-n, (y1+y2)/2-n, smallWidth, smallHeight);
            //Draw Big Rectangle
            g.drawRect(Xmin, Ymin, Xmax-Xmin,Ymax-Ymin); 
    }
    @Override
    public void setProperty(String propertyName, Object Value) {
           if(propertyName.equals("x1")){
           x1 = (Integer.valueOf((String) Value));
           }else if(propertyName.equals("x2")){
               x2 = (Integer.valueOf((String) Value));
           }else if(propertyName.equals("y1")){
               y1 = (Integer.valueOf((String) Value));
           }else if(propertyName.equals("y2")){
               y2 = (Integer.valueOf((String) Value));
           }
    }
    @Override
    public void addProperty(PropertiesPanel p){
        p.addProperty("x1", Integer.class, x1);
        p.addProperty("x2", Integer.class, x2);
        p.addProperty("y1", Integer.class, y1);
        p.addProperty("y2", Integer.class, y2);
    }

    @Override
    public Hashtable<String, Object> getShapeProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
