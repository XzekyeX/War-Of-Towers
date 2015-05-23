package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

/**
 * 
 * @author Zekye
 *
 */
public class Level {
	protected Block[][][] blocks;
	private int width, height, depth;
	private Player player;

	public Level(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		blocks = new Block[width][height][depth];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					blocks[x][y][z] = new Sand(x * 32, y * 32, z * 32);
				}
			}
		}
		player = new Player(64, -64, 128);
		player.setRot(142, 0);
	}

	private int delay = 20;

	public void update() {
		if (delay > 0) delay--;
		if (player != null) player.update();
	}

	public void renderGlu() {
		if (player != null) {
			player.renderGlu();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					for (int z = 0; z < depth; z++) {
						if (blocks[x][y][z] != null) {
							blocks[x][y][z].renderGlu();
							if (blocks[x][y][z].isMouseOver(player)) {
								blocks[x][y][z].sprite = dirt;
							} else {
								blocks[x][y][z].sprite = sand;
							}
						}
					}
				}
			}
		}
	}

	private Sprite dirt = Sprite.getBlock("dirt.png");
	private Sprite sand = Sprite.getBlock("sand.png");

	class HitResult {
		int x, y, z, o, f;

		public HitResult(int x, int y, int z, int o, int f) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.o = o;
			this.f = f;
		}
	}

	public void renderOrtho() {
		if (player != null) {
			player.renderOrtho();
			glPushMatrix();
			Font.renderString("" + getClosestBlock(player), 16, 12, 12 + 42);
			Font.renderString("" + player, 16, 12, 12 + 64);
			Font.renderString("(" + Mouse.getX() + "," + Mouse.getY() + ")", 16, 12, 12 + 86);
			glPopMatrix();
		}
	}

	public double getDistance(float sx, float sy, float sz, float tx, float ty, float tz) {
		return Math.sqrt(Math.abs((sx - tx) * (sx - tx)) + ((sy - ty) * (sy - ty)) + ((sz - tz) * (sz - tz)));
	}

	public Block getClosestBlock(Entity e) {
		double dist = getDistance(e.getX(), e.getY(), e.getZ(), 0, 0, 0);
		Block closest = null;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					if (blocks[x][y][z] != null) {
						double distance = getDistance(e.getX(), e.getY(), e.getZ(), x * 32, y * 32, z * 32);
						if (distance >= dist) {
							dist = distance;
							closest = blocks[x][y][z];
						}
					}
				}
			}
		}
		return closest;
	}

	public Block getBlock(int x, int y, int z) {
		if (x >= 0 && y >= 0 && z >= 0 && x < width && y < height && z < depth) return blocks[x][y][z];
		return null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

}
