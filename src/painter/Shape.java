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
public abstract class Shape {

    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;
    protected Hashtable<String, Object> allProperties;
    protected Color fillColor;
    protected boolean select;

    public Shape(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        fillColor = Color.WHITE;
    }

    public Shape() {
        fillColor = Color.WHITE;
        allProperties = new Hashtable<>();
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public abstract void draw(Graphics g);

    public boolean Contains(int x, int y) {
        int Xmax = Math.max(x1, x2);
        int Xmin = Math.min(x1, x2);
        int Ymax = Math.max(y1, y2);
        int Ymin = Math.min(y1, y2);
        if ((x >= Xmin && x <= Xmax) && (y >= Ymin && y <= Ymax)) {
            return true;
        } else {
            return false;
        }
    }

    public abstract Hashtable<String, Object> getShapeProperties();

    public abstract void setProperty(String propertyName, Object Value);
    public abstract void addProperty(PropertiesPanel p);
}
