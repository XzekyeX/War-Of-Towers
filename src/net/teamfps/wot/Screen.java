package net.teamfps.wot;
/**
 * 
 * @author Zekye
 *
 */
public class Screen {
	private Level level;
	private int delay = 20;

	public Screen() {
		level = new Level(4, 1, 4);
	}

	public void update() {
		if (delay > 0) delay--;
		if (level != null) level.update();
	}

	public void renderGlu() {
		if (level != null) level.renderGlu();
	}

	public void renderOrtho() {
		if (level != null) level.renderOrtho();
	}

}
