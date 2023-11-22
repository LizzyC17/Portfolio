package othello;

import java.awt.Color;

public class OthelloPiece {

Color color;
int x,y;
	
	public OthelloPiece(Color color, int x, int y) {
		this.y = y;
		this.x = x;
		this.color = color;
	}
	
	public void changeColor(Color color) {
		this.color = color;
	}
}