import java.awt.Point;
import java.util.ArrayList;

public class MemTesterDesktop {

	public ArrayList<Integer> generated = new ArrayList<Integer>(); // ARRAYLISTS
	public ArrayList<Integer> input = new ArrayList<Integer>(); // FOR INPUTS
																// AND GENERATED
	private int round, lives;

	boolean complete; // STARTCHECK TRUE TO CHECK START INPUTS
						// COMPLETE - IF GAME WAS WON
	
	static Point pointy = null;//TODO

	MemTesterDesktop() {
		lives = 3;
		round = 1;
		complete = false;
	}

	public void random(int min, int max) {// THIS PRODUCES A RANDOM NUMBER, STARTING THE GAME
		// SHOULD MAKE A RANDOM NUMBER UP TO 10
		generated.add(min + (int) (Math.random() * ((max - min) + 1)));//ADD RANDOM TO GENERATED ARRAYLIST

	}

	public void check(int x, int size, int round) {
		if (size == round) {// IF THE ARRAYLISTS ARE THE CORRECT LENGTH, COMPARE
			complete = generated.equals(input);// COMPARE ARRAYLISTS
		}
	}// END COMPARE

	public int getLives() {
		return this.lives;
	}// SEND LIVES TO GUI

	public int getRound() {
		return this.round;
	}// SEND ROUND TO GUI

	public ArrayList<Integer> getGen() {
		return this.generated;
	}// SEND GENERATED TO GUI

	public ArrayList<Integer> getIn() {
		return this.input;
	}// SEND INPUT TO GUI

	public boolean getComp() {
		return this.complete;
	}// SEND BOOLEAN COMPLETE TO GUI
	
	public static void setPointy(Point location){//CALLED BY CLASS BEFORE OPENING NEW	//TODO
		pointy = location;
}

public static Point getPointy(){//CALLED BY CLASS TO SET POINTS OF OLD					//TODO
	return pointy;
}
}// END CLASS