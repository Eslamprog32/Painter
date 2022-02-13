
package painter;

/**
 *
 * @author eslam
 */
public class ShapeFactory {
    
    public static Shape CreateShape(ShapeType shape){
        switch(shape){
            case RECTANGLE -> {
                return new Rectangle();
            }
            case OVAL -> {
                return new Oval();
            }
            case TRIANGLE ->{
                return new Triangle();
            }
            case HEXGEON->{
                return new Hexgoen();
            }
        }
        return new Rectangle();
    }
}
