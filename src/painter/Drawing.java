package painter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author eslam
 */
public class Drawing extends JPanel {

    private Shape rec = new Rectangle();
    private Image bufferImage;
    private Graphics bufferGraphics;
    private ButtonPanel buttonPanel;
    private boolean changeArea;
    private boolean changeX1;
    private boolean changeX2;
    private boolean changeY1;
    private boolean changeY2;
    private PropertiesPanel propertiesPanel;
    ArrayList<Shape> allShapes;

    public Drawing() {
        setSize(842, 595);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseMotionHandler);
        setBackground(Color.white);
        allShapes = new ArrayList<>();
    }
    
   public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }

    public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
        this.propertiesPanel = propertiesPanel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (bufferImage == null) {
            bufferImage = createImage(getWidth(), getHeight());
            bufferGraphics = bufferImage.getGraphics();
        } else {
            if (bufferImage.getWidth(this) != getWidth() || bufferImage.getHeight(this) != getHeight()) {
                bufferImage = createImage(getWidth(), getHeight());
                bufferGraphics = bufferImage.getGraphics();
            }
        }
        super.paintComponent(g);
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.fillRect(0, 0, bufferImage.getWidth(this), bufferImage.getHeight(this));
        for (Shape s : allShapes) {
            s.draw(bufferGraphics);
        }
        g.drawImage(bufferImage, 0, 0, this);
    }
    public MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            changeArea = false;
            changeX1 = false;
            changeX2 = false;
            changeY1 = false;
            changeY2 = false;
            propertiesPanel.removeProperties();
            for (Shape s : allShapes) {
                s.select = false;
            }
            if (buttonPanel.isSelect()) {
                for (int i = allShapes.size() - 1; i >= 0; i--) {
                    Shape s = allShapes.get(i);
                    if (s.Contains(e.getX(), e.getY())) {
                        s.select = true;
                        rec = s;
                        rec.addProperty(propertiesPanel);
                        propertiesPanel.addPropertyListener(new PropertiesPanel.PropertyListener() {
                        @Override
                           public void onPropertyChange(String propName, Object newValue) {
                               rec.setProperty(propName, newValue);
                                repaint();
                           }
                            });
                        changeArea = true;
                        if (e.getX() == rec.x1 && e.getY() == rec.y1) {
                            changeX1 = true;
                            changeY1 = true;
                        } else if (e.getX() == rec.x1 && e.getY() == (rec.y1 + rec.y2) / 2) {
                            changeX1 = true;
                        } else if (e.getX() == rec.x1 && e.getY() == rec.y2) {
                            changeX1 = true;
                            changeY2 = true;
                        } else if (e.getX() == (rec.x1 + rec.x2) / 2 && e.getY() == rec.y2) {
                            changeY2 = true;
                        } else if (e.getX() == (rec.x1 + rec.x2) / 2 && e.getY() == rec.y1) {
                            changeY1 = true;
                        } else if (e.getX() == rec.x2 && e.getY() == rec.y1) {
                            changeX2 = true;
                            changeY1 = true;
                        } else if (e.getX() == rec.x2 && e.getY() == rec.y2) {
                            changeX2 = true;
                            changeY2 = true;
                        } else if (e.getX() == rec.x2 && e.getY() == (rec.y1 + rec.y2) / 2) {
                            changeX2 = true;
                        }
                        break;
                    }
                }
            } else {
                rec = ShapeFactory.CreateShape(buttonPanel.getSelectedShape());
                rec.fillColor = buttonPanel.getSelectedColor();
                rec.x1 = e.getX();
                rec.x2 = e.getX();
                rec.y1 = e.getY();
                rec.y2 = e.getY();
                allShapes.add(rec);
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (buttonPanel.isSelect()) {

            } else {
                rec.x2 = e.getX();
                rec.y2 = e.getY();
                allShapes.add(rec);
                repaint();
            }
        }

    };
    public MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            if (rec.isSelect()) {
                if (e.getX() == Math.min(rec.x1, rec.x2) && e.getY() == Math.max(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                } else if (e.getX() == Math.min(rec.x1, rec.x2) && e.getY() == (rec.y1 + rec.y2) / 2) {
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                } else if (e.getX() == Math.min(rec.x1, rec.x2) && e.getY() == Math.min(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                } else if (e.getX() == (rec.x1 + rec.x2) / 2 && e.getY() ==  Math.max(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                } else if (e.getX() == (rec.x1 + rec.x2) / 2 && e.getY() ==  Math.min(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                } else if (e.getX() ==  Math.max(rec.x1, rec.x2) && e.getY() ==  Math.min(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                } else if (e.getX() ==  Math.max(rec.x1, rec.x2) && e.getY() ==  Math.max(rec.y1, rec.y2)) {
                    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                } else if (e.getX() ==  Math.max(rec.x1, rec.x2) && e.getY() == (rec.y1 + rec.y2) / 2) {
                    setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            propertiesPanel.removeProperties();
            if (buttonPanel.isSelect()) {
                if (changeArea) {
                    if (changeX1) {
                        if (changeY1) {
                            rec.y1 = e.getY();
                            rec.x1 = e.getX();
                            repaint();
                        } else if (changeY2){
                            rec.x1 = e.getX();
                            rec.y2 = e.getY();
                            repaint();
                        } else {
                            rec.x1 = e.getX();
                            repaint();
                        }
                    } else if (changeX2) {
                        if (changeY1) {
                            rec.y1 = e.getY();
                            rec.x2 = e.getX();
                            repaint();
                        } else if (changeY2) {
                            rec.y2 = e.getY();
                            rec.x2 = e.getX();
                            repaint();
                        } else {
                            rec.x2 = e.getX();
                            repaint();
                        }
                    } else if (changeY1) {
                        rec.y1 = e.getY();
                        repaint();
                    } else if (changeY2) {
                        rec.y2 = e.getY();
                        repaint();

                    }
                    rec.addProperty(propertiesPanel);
                }
            } else {
                rec.x2 = e.getX();
                rec.y2 = e.getY();
                repaint();
            }
        }
    };
}
