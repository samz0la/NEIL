package samuelandazola.com.neilservices;

import android.graphics.Rect;

/**
 * The type GameObject will have all other classes extending it, to have these attributes.
 */
//All objects in the game have these attributes
public abstract class GameObject {


  int x;
  int y;
  int dy;
  int dx;
  int width;

  /** The Height. */
  protected int height;



  public void setX(int x) {
    this.x = x;
  }


  void setY(int y) {
    this.y = y;
  }


  int getX() {
    return x;
  }
  int getY() {
    return y;
  }

  /** Sets height.
   * @param height the height
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /** Gets height.
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /** Sets width.
   * @param width the width
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /** Gets width.
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**Get rectangle rect.
   * @return the rect
   */
  Rect getRectangle(){
    return new Rect(x, y, x+width, y+height);
  }

  /** Gets dy.
   * @return the dy
   */
  public int getDy() {
    return dy;
  }

  /** Sets dy.
   * @param dy the dy
   */
  public void setDy(int dy) {
    this.dy = dy;
  }

  /** Gets dx.
   *
   * @return the dx
   */
  public int getDx() {
    return dx;
  }

  /**
   * Sets dx.
   *
   * @param dx the dx
   */
  public void setDx(int dx) {
    this.dx = dx;
  }

}
