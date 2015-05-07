package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.awt.image.*;
import java.io.*;
import java.util.*;

import org.newdawn.slick.opengl.*;

/**
 * @author Zekye
 *
 */
public class Sprite {
	protected String path;
	protected String name;
	protected BufferedImage image;
	protected Texture texture;
	protected int textureID;
	protected int width, height;
	protected int[] pixels;

	public static Sprite[] blocks = loadFolder("blocks");

	public static Sprite font = new Sprite("/font_new.png");

	public static Sprite[] chars_upper = loadChars("font_upper", new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" });
	public static Sprite[] chars_lower = loadChars("font_lower", new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" });
	public static Sprite[] chars = loadChars("font", new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "colon", "semicolon", "bracket_right", "bracket_left", "equal", "dash", "question_mark", "greater_than_left", "greater_than_right", "period", "comma", "parenthese_left", "parenthese_right", "curly_left", "curly_right" });

	// a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
	public Sprite(String path, String name) {
		this.path = path;
		this.name = name;
		loadTexture("/" + path + "/" + name);
	}

	public Sprite(String path) {
		this.path = path;
		loadTexture(path);
	}

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.image.setRGB(0, 0, width, height, pixels, 0, width);
	}

	public Sprite(String name, int[] pixels, int width, int height) {
		this.path = name;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.image.setRGB(0, 0, width, height, pixels, 0, width);
	}

	public static Sprite getBlock(String str) {
		for (Sprite s : blocks) {
			if (s != null && s.name.equals(str)) {
				return s;
			}
		}
		return null;
	}

	public static Sprite[] loadChars(String path, String[] chars) {
		Sprite[] sprites = new Sprite[chars.length];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(path, chars[i] + ".png");
		}
		return sprites;
	}

	public static Sprite SplitSprite(Sprite s, String name, int x, int y, int width, int height) {
		int[] pixels = new int[width * height];
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				pixels[w + h * width] = s.pixels[((w + x) + (h + y) * s.getWidth())];
			}
		}
		return new Sprite(name, pixels, width, height);
	}

	public static Sprite[] toArray(Sprite[] s, Sprite[] s1) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		for (int i = 0; i < s.length; i++) {
			sprites.add(s[i]);
		}
		for (int i = 0; i < s1.length; i++) {
			sprites.add(s1[i]);
		}
		Sprite[] result = new Sprite[sprites.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = sprites.get(i);
		}
		return result;
	}

	public static Sprite[] loadFolder(String fold) {
		File f = new File("res/" + fold);
		File[] files = f.listFiles();
		if (files != null) {
			if (files.length > 0) {
				List<Sprite> sprites = new ArrayList<Sprite>();
				for (int i = 0; i < files.length; i++) {
					String name = files[i].getName().toUpperCase();
					String last = name.substring(name.length() - 4, name.length());
					if (last.equals(".PNG")) {
						sprites.add(new Sprite(fold, files[i].getName()));
					} else {
						System.err.println("Format Type Exception! File: " + files[i].getName());
					}
				}
				Sprite[] result = new Sprite[sprites.size()];
				for (int i = 0; i < result.length; i++) {
					result[i] = sprites.get(i);
				}
				return result;
			}
		}
		return null;
	}

	public static Sprite ContainSprite(Sprite[] arr, String name) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].name.contains(name)) {
				return arr[i];
			}
		}
		return null;
	}

	public static Sprite EqualSprite(Sprite[] arr, String name) {
		for (int i = 0; i < arr.length; i++) {
			String n = arr[i].name;
			String[] split = n.split(".png");
			if (split.length > 0) {
				String s = split[0];
				if (s.equals(name)) {
					return arr[i];
				}
			}
		}
		return null;
	}

	private void loadTexture(String path) {
		try {
			texture = TextureLoader.getTexture("PNG", getClass().getResourceAsStream(path));
			width = texture.getImageWidth();
			height = texture.getImageHeight();
			glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glBindTexture(GL_TEXTURE_2D, 0);
			textureID = texture.getTextureID();
			System.out.println("new Texture: " + path + " texure ID: " + textureID);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load: " + path);
		}
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public Texture getTexture() {
		return texture;
	}

	public int getTextureID() {
		return textureID;
	}

}
