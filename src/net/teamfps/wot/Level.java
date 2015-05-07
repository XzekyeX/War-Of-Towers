package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

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

	public void update() {
		if (player != null) player.update();
	}

	public void renderGlu() {
		if (player != null) {
			player.render();
			glRotatef(player.getRotX(), 0, 1, 0);
			glTranslatef(player.getX(), player.getY(), player.getZ());
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					if (blocks[x][y][z] != null) {
						blocks[x][y][z].render();
					}
				}
			}
		}
	}

	public void renderOrtho() {

	}

	public Block getBlock(int x, int y, int z) {
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
