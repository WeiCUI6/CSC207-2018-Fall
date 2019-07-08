package fall2018.csc2017.GameCenter.Hanoi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

import fall2018.csc2017.GameCenter.R;
import fall2018.csc2017.GameCenter.Score;
import fall2018.csc2017.GameCenter.UserManager;

/**
 * Class Draw extends View
 */
@SuppressLint("ViewConstructor")
public class Draw extends View {

    /**
     * This context
     */
    Context context;

    /**
     * The number of disks
     */
    int numOfDisks;

    /**
     * The total moves for player to finish the game
     */
    int moves = 0;

    /**
     * The x, y coordinate for user's action
     */
    float x, y;

    /**
     * The xPixel and the yPixel for the disk to display
     */
    float xPixel, yPixel;

    /**
     * Judge whether the touch is valid or not
     * by default: true
     */
    boolean isValidTouch = true;

    /**
     * A text that is designed to display the player's current moves
     */
    TextDrawable text;

    /**
     * The restricted area for user's action to be valid
     * bottomLimit: bottom
     * topLimit: top
     * leftLimit: leftmiddle
     * rightLimit: rightmiddle
     */
    float bottomLimit, topLimit, leftLimitMiddleRod, rightLimitMiddleRod;

    /**
     * The stacks for the leftrod, middlerod and rightrod
     * which contains disks
     */
    private Stack<DiskShape> leftRod, middleRod, rightRod;

    /**
     * The stack for the selected disks
     */
    private Stack<DiskShape> rodWithDiskSelected = null;

    @SuppressWarnings("deprecation")
    public Draw(Context context, float width, float height, int numOfDisksP) {
        super(context);

        this.context = context;

        // Set the background for the screen
        setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeResource(getResources(),
                R.drawable.hanoi)));


        initialStackRod();

        // Get the wanted xPixel, yPixel for screen to display
        xPixel = width / 320;
        yPixel = height / 480;

        // Set the number of disks
        numOfDisks = numOfDisksP;

        // Set the restricted valid action area
        setRestrictedArea();

        // Arrays about default colours of disks
        int[] uColour = {0xFF2BD8EC, 0xFFF2F423, 0xFFFC0101, 0xFF45FC21, 0xFFB121FC, 0xFFFF8844};
        int[] sColour = {0x882BD8EC, 0x88F2F423, 0x88FC0101, 0x8845FC21, 0x88B121FC, 0x88FF8844};

        // Add disks for the leftrod
        for (int i = numOfDisksP; i >= 1; i--) {
            leftRod.push(new DiskShape(i, xPixel, yPixel, sColour[i - 1], uColour[i - 1]));
        }
    }

    /**
     * Set the restricted valid action area
     */
    private void setRestrictedArea() {
        bottomLimit = 10 * yPixel;
        topLimit = 600 * yPixel;
        leftLimitMiddleRod = 110 * xPixel;
        rightLimitMiddleRod = 210 * xPixel;
    }

    /**
     * Initialize the stack for the leftrod, middlerod and rightrod
     * to hold the disks.
     */
    private void initialStackRod() {
        leftRod = new Stack<>();
        middleRod = new Stack<>();
        rightRod = new Stack<>();
    }

    /**
     * Draw the initial setup to display in the screen
     *
     * @param canvas The canvas
     */
    @SuppressLint("DrawAllocation")
    public void onDraw(Canvas canvas) {

        // left rod should be at (52, 390) on 320*480 screen
        drawRod(canvas, xPixel, 52, yPixel * 390, leftRod);

        // middle rod will be at (159, 226)
        drawRod(canvas, 107, xPixel, 0, middleRod);

        // right rod will be at (266, 226)
        drawRod(canvas, 107, xPixel, 0, rightRod);

        String msg = "Moves: " + String.valueOf(moves);
        text = new TextDrawable(msg);
        text.draw(canvas);
    }

    /**
     * Draw leftrod, middlerod and rightrod
     *
     * @param canvas canvas
     * @param Pixel  one Pixel
     * @param i      x coordinate
     * @param v      y coordinate
     * @param Rod    Rod stack
     */
    private void drawRod(Canvas canvas, float Pixel, float i, float v, Stack<DiskShape> Rod) {
        canvas.translate(Pixel * i, v);

        // Canvas save the current coordinate
        canvas.save();
        drawDisks(canvas, Rod);

        // Canvas return to the saved coordinate
        canvas.restore();
    }

    /**
     * Draw the disks in the given rod stack
     *
     * @param canvas The canvas
     * @param rod    draw the disks stored in the given rod stack
     */
    private void drawDisks(Canvas canvas, Stack<DiskShape> rod) {
        for (DiskShape disk : rod) {
            disk.draw(canvas);
            canvas.translate(0, -20 * yPixel);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Get the player's motion's x and y coordinates when finger touching the screen
            x = event.getX();
            y = event.getY();
            isValidArea();
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (isValidTouch) {
                // adjust the player's motion when dragging disks
                adjustTheMove(event);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // move chosen disk into the right stack when finger leaving the screen
            putDisk(event);
        }
        return true;
    }

    /**
     * Put the disk in proper rod selected by player
     *
     * @param event Player's motion event "UP"
     */
    private void putDisk(MotionEvent event) {
        if (isValidTouch)
            if (rodWithDiskSelected.size() != 0) {
                // get the position of the first disk on the chosen rod
                x = event.getX();
                y = event.getY();
                rodWithDiskSelected.lastElement().setBound();
                if (y > bottomLimit && y < topLimit) {
                    // put the disk if it is dragged to the proper position
                    rodWithDiskSelected.lastElement().getBounds()
                            .inset((int) x, (int) y);
                    if (x < leftLimitMiddleRod) {
                        actionOnTouch(leftRod);
                    } else if (x >= leftLimitMiddleRod
                            && x <= rightLimitMiddleRod) {
                        actionOnTouch(middleRod);
                    } else
                        actionOnTouch(rightRod);
                } else
                    // do not put the disk if it is dragged to the improper position
                    rodWithDiskSelected.lastElement().unSelect();
                invalidate();
            }
    }

    /**
     * Adjust the user's motion event to display properly
     *
     * @param event User's motion event "MOVE"
     */
    private void adjustTheMove(MotionEvent event) {
        if (rodWithDiskSelected.size() != 0) {

            // set the base position of motion event display before generating the true position
            int mX = (int) (60 * xPixel);
            int mY = (int) (410 * yPixel);

            if (rodWithDiskSelected == middleRod) {
                mX += 100 * xPixel;
            } else if (rodWithDiskSelected == rightRod) {
                mX += 200 * xPixel;
            }
            // generate the right position of motion event display according to the number of elements in chosen rod
            int mm = (int) (rodWithDiskSelected.size() * 20 * yPixel);

            // int x = (int) event.getX() - mX;
            x = event.getX() - mX;
            y = event.getY() - mY + mm;

            rodWithDiskSelected.lastElement().setBound();
            rodWithDiskSelected.lastElement().getBounds()
                    .inset((int) x, (int) y);
            invalidate();
        }
    }

    /**
     * Judge whether the user's touch is in the area between the bottomlimit and toplimit
     */
    private void isValidArea() {
        if (y > bottomLimit && y < topLimit) {
            // judge whether the chosen position is in vertical range
            isValidTouch = true;

            // determine which rod is chosen according to the horizontal location of the chosen position
            if (x < leftLimitMiddleRod)
                rodWithDiskSelected = leftRod;
            else if (x >= leftLimitMiddleRod && x <= rightLimitMiddleRod)
                rodWithDiskSelected = middleRod;
            else
                rodWithDiskSelected = rightRod;

            if (rodWithDiskSelected.size() != 0)
                rodWithDiskSelected.lastElement().select();

        } else
            isValidTouch = false;
    }

    /**
     * Implement the action and record the total moves for player to finish the game
     *
     * @param touchedRod The rod selected by player
     */
    private void actionOnTouch(Stack<DiskShape> touchedRod) {
        UserManager userManager = UserManager.getInstance();
        Score score = userManager.getCurrentUser().getScore();
        rodWithDiskSelected.lastElement().unSelect();
        rodWithDiskSelected.lastElement().setBound();

        if (isValidMove(touchedRod)) {
            // move the disk from one stack to another
            touchedRod.push(rodWithDiskSelected.pop());
            moves++;
            // record temporary score
            score.setScoreTemp(UserManager.getInstance().getCurrentGameType());
        }
        rodWithDiskSelected = null;
        invalidate();

        // check whether the game is finished
        if (rightRod.size() == numOfDisks || middleRod.size() == numOfDisks) {
            ((HanoiGameActivity) getContext()).gameOver(moves);
        }
    }

    /**
     * Judge whether it is a valid move
     *
     * @param touchedRod Rod selected by player
     * @return whether it is a valid move
     */
    private boolean isValidMove(Stack<DiskShape> touchedRod) {
        return touchedRod.size() == 0
                || rodWithDiskSelected.lastElement().size < touchedRod
                .lastElement().size;
    }
}