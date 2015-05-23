package net.teamfps.wot;

/**
 * 
 * @author Zekye
 *
 */
public abstract class Entity {
	protected float x, y, z, w, h, d;
	protected float rx, ry;
	protected Sprite sprite;

	public abstract void update();

	public abstract void renderGlu();

	public abstract void renderOrtho();

	public void move(float xa, float za, float speed) {
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

	public boolean Collision(Entity e) {
		return (x >= e.getX() && e.getX() + e.getW() <= x + w && y >= e.getY() && e.getY() + e.getH() <= y + h && z >= e.getZ() && e.getZ() + e.getD() <= z + d);
	}

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
