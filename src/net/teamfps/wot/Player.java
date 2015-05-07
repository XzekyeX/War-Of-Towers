package net.teamfps.wot;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Player extends Entity {

	public Player(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 32;
		this.h = 64;
		this.d = 32;
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
		moveRelative(xa, za, speed);
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
	}

	private void moveRelative(float xa, float za, float speed) {
		float dist = xa * xa + za * za;
		if (dist < 0.01F) return;
		dist = speed / (float) Math.sqrt(dist);
		xa *= dist;
		za *= dist;
		float sin = (float) Math.sin(rx * Math.PI / 180.0D);
		float cos = (float) Math.cos(rx * Math.PI / 180.0D);
		this.x += xa * cos - za * sin;
		this.z += za * cos + xa * sin;
	}

	@Override
	public void render() {

	}

}
