package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

/**
 * 
 * @author Zekye
 *
 */
public abstract class Block extends Entity {

	public Block(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 32;
		this.h = 32;
		this.d = 32;
	}

	public boolean solid() {
		return false;
	}

	@Override
	public void update() {

	}

	public void renderGlu() {
		Bitmap.renderBlock(sprite, x, y, z, w, h, d);
	}

	public void renderOrtho() {

	}

	public boolean isMouseOver(Player p) {
		int mx = Mouse.getX();
		int my = Mouse.getY();
		int px = (int) p.getX();
		int pw = (int) p.getW();
		int py = (int) p.getY();
		int ph = (int) p.getH();
		int pz = (int) p.getZ();
		int pd = (int) p.getD();
		return (x >= px && px + pw <= x + w && y >= py && py + ph <= y + h && z >= pz && pz + pd <= z + d);
	}

	@Override
	public String toString() {
		return "Block(" + x / w + "," + y / h + "," + z / d + ")";
	}
}
