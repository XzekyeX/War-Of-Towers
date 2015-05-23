package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * 
 * @author Zekye
 *
 */
public class Player extends Entity {

	public Player(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 32;
		this.h = 32;
		this.d = 32;
		this.sprite = Sprite.getBlock("lapis_block.png");
	}

	@Override
	public void update() {
		float speed = 0.5f;
		float mspeed = 0.25f;
		float xa = 0;
		float za = 0;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			za++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			za--;
		}
		move(xa, za, speed);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			y -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			y += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			rx -= mspeed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			rx += mspeed;
		}
		rx += Mouse.getDX() * mspeed;
		ry += Mouse.getDY() * mspeed;
		if (ry >= 90) {
			ry = 90;
		}
		if (ry <= -90) {
			ry = -90;
		}
	}

	@Override
	public void renderGlu() {
		// System.out.println("pos(" + x + "," + y + "," + z + ")");
		Bitmap.renderBlock(sprite, x, y, z - 128, w, h, d);
	}

	@Override
	public void renderOrtho() {

	}

	@Override
	public String toString() {
		return "Player(P(" + (int) x + "," + (int) y + "," + (int) z + "),R(" + (int) rx + "," + (int) ry + "))";
	}

}
