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

	public Entity add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Entity minus(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Entity addRot(float x, float y) {
		this.rx += x;
		this.ry += y;
		return this;
	}

	public Entity minusRot(float x, float y) {
		this.rx -= x;
		this.ry -= y;
		return this;
	}

	public Entity setRot(float rx, float ry) {
		this.rx = rx;
		this.ry = ry;
		return this;
	}

	public Entity setPos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
}
