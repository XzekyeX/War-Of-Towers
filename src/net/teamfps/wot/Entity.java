package net.teamfps.wot;

public abstract class Entity {
	protected float x, y, z, w, h, d;
	protected float rx, ry;

	public abstract void update();

	public abstract void render();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	public float getD() {
		return d;
	}

	public float getRotX() {
		return rx;
	}

	public float getRotY() {
		return ry;
	}
}
