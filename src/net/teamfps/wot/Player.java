package net.teamfps.wot;

import org.lwjgl.input.Keyboard;

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
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			z -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			z += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			y -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			rx -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			rx += speed;
		}
	}

	@Override
	public void render() {

	}

}
