package UMLComponent;

import java.awt.Graphics;

public interface BasicObject {
	
	public abstract void setSlected(boolean isSlected);
	public abstract boolean isSlected();
	public abstract void paint(Graphics g);
	public abstract int getX1();
	public abstract int getX2();
	public abstract int getY1();
	public abstract int getY2();
	public abstract void resetLocation(int moveX, int moveY);
	
	public abstract String getName();
	public abstract void rename(String newName);
}
