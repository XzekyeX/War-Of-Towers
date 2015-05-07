package net.teamfps.wot;

public abstract class Block {
	protected int x, y, z, w, h, d;
	protected Sprite sprite;

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

	public void render() {
		Bitmap.renderBlock(sprite, x, y, z, w, h, d);
	}
}
