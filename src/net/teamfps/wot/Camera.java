package net.teamfps.wot;

import java.awt.event.KeyEvent;

/**
 * @author Zekye
 *
 */
public class Camera {
	private double x, y, rot, speed = 1.0D, movSpeed = 0.15D, rotSpeed = 0.15D;
	private boolean movement = true;

	public Camera() {
	}

	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		if (movement) {
			double xm = 0;
			double zm = 0;
			if (Input.isKeyDown(KeyEvent.VK_A)) xm += speed;
			if (Input.isKeyDown(KeyEvent.VK_D)) xm -= speed;
			if (Input.isKeyDown(KeyEvent.VK_W)) zm -= speed;
			if (Input.isKeyDown(KeyEvent.VK_S)) zm += speed;
			if (Input.isKeyDown(KeyEvent.VK_Q)) rot += rotSpeed;
			if (Input.isKeyDown(KeyEvent.VK_E)) rot -= rotSpeed;
			x -= (xm * Math.cos(rot) + zm * Math.sin(rot)) * movSpeed;
			y -= (zm * Math.cos(rot) - xm * Math.sin(rot)) * movSpeed;
		}
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	public double getRot() {
		return rot;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setMovementSpeed(double speed) {
		this.movSpeed = speed;
	}

	public void setRotationSpeed(double speed) {
		this.rotSpeed = speed;
	}

	public void disable() {
		movement = false;
	}

	public void enable() {
		movement = true;
	}

	@Override
	public String toString() {
		return "Pos(" + x + "," + y + "), Rot(" + rot + "), Mov Speed(" + movSpeed + "), Rot Speed(" + rotSpeed + "), Speed(" + speed + ")";
	}
}
