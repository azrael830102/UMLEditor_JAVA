package UMLComponent;

import java.awt.Graphics;

public interface BasicObject {
	
	public abstract void setSlected(boolean isSlected);
	public abstract boolean isSlected();
	public abstract void draw(Graphics g);
}
