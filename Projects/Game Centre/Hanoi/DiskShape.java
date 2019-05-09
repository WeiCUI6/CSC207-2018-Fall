package fall2018.csc2017.GameCenter.Hanoi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

/**
 * The class DiskShape for showing the disk
 */
public class DiskShape extends ShapeDrawable {

    /**
     * An array of 8 radius values, for the outer round rectangle.
     */
    private static float[] diskOuterRadius = new float[]{25, 25, 25, 25, 0, 0, 0, 0};
    /**
     * The width size of the disk
     */
    int size;
    /**
     * The height size of the disk
     */
    private double yRatio;
    /**
     * Colour of the disk when selected
     */
    private int sColour;

    /**
     * Colour of the disk when unselected
     */
    private int uColour;

    /**
     * A new disk with appropriate width and height.
     *
     * @param size    the size for the disk width
     * @param xRatio  ratio for x coordinate
     * @param yRatio  ratio for y coordinate
     * @param sColour colour of the disk when selected
     * @param uColour colour of the disk when unselected
     */
    DiskShape(int size, float xRatio, float yRatio, int sColour, int uColour) {
        super(new RoundRectShape(diskOuterRadius, null, null));

        this.yRatio = 0.8 * yRatio;
        this.size = (int) (size * 16 * xRatio);
        this.sColour = sColour;
        this.uColour = uColour;
        this.unSelect();

        setBound();
    }

    /**
     * Set the restricted area for the disk to display
     */
    void setBound() {
        this.setBounds(0, 0, this.size, (int) (24 * yRatio));
    }

    /**
     * Call the setColor method in Paint to set color for the selected disk.
     */
    void select() {
        this.getPaint().setColor(this.sColour);
    }

    /**
     * Call the setColor method in Paint to set color for the disk in general.
     */
    void unSelect() {
        this.getPaint().setColor(this.uColour);
    }

    @Override
    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        canvas.save();

        // translates the half of the size to the left, to draw
        // the disk on the center of the rod
        canvas.translate(-size / 2, 10);
        shape.draw(canvas, paint);

        canvas.restore();
    }
}