import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private int speed;
  private boolean isDragging;
  private boolean isSwimming;
  private static int oldMouseX;
  private static int oldMouseY;

  // Creates a new fish object located at a specific (x, y) position of the display window
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    // processing PApplet object that represents the display window
    // x x-position of the image of this fish in the display window
    // y y-position of the image of this fish in the display window
    // speed the swimming speed of this fish
    // fishImageFileName file name of the image of the fish to be created
    Fish.processing = processing;
    this.x = x;
    this.y = y;
    this.speed = speed;
    isDragging = false;
    isSwimming = false;
    image = processing.loadImage(fishImageFileName);
  }

  // Creates a new fish object positioned at the center of the display window.
  public Fish(PApplet processing) {
    // processing PApplet object that represents the display window
    // This constructor sets the image instance field to
    // a PImage whose file name is "images" + File.separator + "orange.png"
    // Sets speed instance field to 5
    // Sets the x and y position of the fish to the center of the display window
    // The created fish won’t be dragging nor swimming.
    Fish.processing = processing;
    image = processing.loadImage("images" + File.separator + "orange.png");
    speed = 0.00001;
    x = processing.width / 2;
    y = processing.height / 2;
    isDragging = false;
    isSwimming = false;
  }

  // Returns the image of type PImage of this fish
  public PImage getImage() {
    // getter of the image instance field
    return image;
  }

  // Returns the x-position of this fish in the display window
  public float getPositionX() {
    // getter of the x-position of this fish
    return x;
  }

  // Returns the y-position of this fish in the display window
  public float getPositionY() {
    // getter of the y-position of this fish
    return y;
  }

  // Moves this fish with dx and dy
  public void move(int dx, int dy) {
    // adds dx move to the x-position of this fish
    // adds dy move to the y-position of this fish
    x += (dx);
    y += (dy);
  }

  // Checks whether this fish is being dragged
  public boolean isDragging() {
    // a getter for the isDragging instance field
    return isDragging;
  }

  // Starts dragging this fish
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    // sets oldMouseY data field to the current y-position of the mouse
    // sets the isDragging data field of this fish to true
    oldMouseX = (int) processing.mouseX;
    oldMouseY = (int) processing.mouseY;
    isDragging = true;
  }

  // Stops dragging this fish
  public void stopDragging() {
    // sets the isDragging data field of this fish to false
    isDragging = false;
  }

  // Draws this fish to the display window.
  // This method sets also the position of this fish to follow the moves of the
  // mouse if it is being dragged
  public void draw() {
    // 1. if this fish is dragging, move it with (dx, dy) to follow the moves of the mouse
    // [HINT] use the current position (processing.mouseX and processing.mouseY)
    // of the mouse with respect to the old position of the mouse to compute dx and dy moves
    // Make sure to update oldMouseX and oldMouseY after moving the fish
    // 2. draw this fish to the display window by calling processing.image() method
    if (isSwimming)
    {
      this.swim();
    }
    if (isDragging) {
      move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      oldMouseX = (int) processing.mouseX;
      oldMouseY = (int) processing.mouseY;
    }
    processing.image(image, x, y);
  }

  /**
   * Checks if the mouse is over a given fish whose reference is provided as input parameter
   * 
   * @param fish reference to a given fish object
   * @return true if the mouse is over the given fish object (i.e. over the image of the fish),
   *         false otherwise
   */
  public boolean isMouseOver() {
    int fishWidth = this.getImage().width;
    int fishHeight = this.getImage().height;

    // checks if the mouse is over the provided fish
    return processing.mouseX >= this.getPositionX() - fishWidth / 2
        && processing.mouseX <= this.getPositionX() + fishWidth / 2
        && processing.mouseY >= this.getPositionY() - fishHeight / 2
        && processing.mouseY <= this.getPositionY() + fishHeight / 2;
  }

  // Starts swimming this fish
  public void startSwimming() {
    // 1. stops dragging the fish
    // 2. sets the isSwimming instance field to true
    this.isSwimming = true;
    this.stopDragging();
  }

  // Stops swimming this fish
  public void stopSwimming() {
    // Sets the isSwimming instance field of this fish to false
    isSwimming = false;
  }

  // Moves horizontally the fish one speed step from left to right
  public void swim() {
    // The speed step is the instance field speed defined for each fish
    // Note that x-position of the fish is bounded by the width of the display
    // window. If the x-position of this fish exceeds the width of the display
    // window, it is going to be set to zero. You may think of using the
    // modulo operator to implement this property.
    this.move(speed, 0);
    if (x > processing.width)
    {
      x = 0;
    }
  }
}
