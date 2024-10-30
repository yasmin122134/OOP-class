// 329233472 yasmin haddad

// imports
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * draws 10 random lines and their middle points in blue.
 * draws a red dot on the intersection of any two lines.
 */
public class AbstractArtDrawing {
    /**
     * draws a line and its middle point in blue.
     * @param l the line to draw
     * @param d the surface to draw on
     * @param c the color to draw with
     */
    public void drawLine(Line l, DrawSurface d, Color c) {
        l.drawLine(d, c);
        Point middle = l.middle();
        middle.drawDot(d, Color.BLUE, 3);
    }
    /**
     * draws 10 random lines and their middle points in blue.
     * draws a red dot on the intersection of any two lines.
     */
    public void drawRandomLine() {
        int numberOfLines = 10;
        int r = 3;
        // Create a window with the title "Random Lines".
        GUI gui = new GUI("Random Lines", Board.BOUND_X, Board.BOUND_Y);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[numberOfLines];

        // Draw 10 random lines and their middle points in blue.
        for (int i = 0; i < numberOfLines; ++i) {
            lines[i] = Line.getRandomLine(Board.BOUND_X, Board.BOUND_Y);
            drawLine(lines[i], d, Color.BLACK);
        }
        // Draw a red dot on the intersection of any two lines.
        for (int i = 0; i < numberOfLines; ++i) {
            for (int j = i + 1; j < numberOfLines; ++j) {
                Point inter = lines[i].intersectionWith(lines[j]);
                if (inter != null) {
                    inter.drawDot(d, Color.RED, r);
                    // draw the triangles
                    for (int k = 0; k < numberOfLines; ++k) {
                        if (lines[k].isIntersecting(lines[i], lines[j])) {
                            Point inter2, inter3;
                            inter2 = lines[k].intersectionWith(lines[i]);
                            inter3 = lines[k].intersectionWith(lines[j]);
                            new Line(inter, inter2).drawLine(d, Color.GREEN);
                            new Line(inter2, inter3).drawLine(d, Color.GREEN);
                            new Line(inter3, inter).drawLine(d, Color.GREEN);

                        }
                    }
                }
            }

        }
        gui.show(d);
    }

    /**
     * main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLine();
    }
}