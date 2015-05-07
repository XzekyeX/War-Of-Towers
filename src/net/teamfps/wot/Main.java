/**
 * 
 */
package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * @author Zekye
 *
 */
public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int width = 640;
	public static int height = 480;
	private static String version = "V.0.1";
	private static JFrame f = new JFrame("War Of Towers " + version);
	private Thread thread;
	private boolean running;

	private Screen screen;
	private Input input;
	private Camera camera;

	private void init() {
		createDisplay();
		input = new Input();
		camera = new Camera();
		screen = new Screen();
		initGL();
		addKeyListener(input);
		addMouseListener(input);
		addMouseWheelListener(input);
		addMouseMotionListener(input);
	}

	private void createFrame() {
		f.add(this, "Center");
		f.pack();
		f.setSize(width, height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(true);
		f.setVisible(true);
	}

	private boolean createDisplay() {
		try {
			createFrame();
			Display.setParent(this);
			Display.create();
			System.out.println("OpenGL Version: " + glGetString(GL_VERSION));
		} catch (LWJGLException e) {
			System.err.println("Failed to create Display!");
			return false;
		}
		return true;
	}

	private void initGL() {
		Bitmap.OrthoGraphics(width, height);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Bitmap.clear();
		Bitmap.clear(0.1f, 0.3f, 1.0f, 1.0f);
	}

	protected static boolean CurrentVersion() {
		String newest = getNewestVersion();
		System.out.println("Newest: " + newest);
		return version.equals(newest);
	}

	protected static String getNewestVersion() {
		String link = "https://raw.githubusercontent.com/XzekyeX/War-Of-Towers/master/Version.fps";
		try {
			URL url = new URL(link);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			return "" + br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return version;
	}

	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "Main Thread");
		thread.start();
	}

	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double delta = 0;
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		int updates = 0;
		int frames = 0;
		init();
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			Display.update();
			render();
			frames++;
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				setFpsAndUps(frames, updates);
				frames = 0;
				updates = 0;
			}
			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(1);
			}
		}
	}

	private int fps, ups;

	private void setFpsAndUps(int frames, int updates) {
		this.fps = frames;
		this.ups = updates;
	}

	public String getFpsAndUps() {
		return "fps[" + fps + "],ups[" + ups + "]";
	}

	public int getFps() {
		return fps;
	}

	private void update() {
		screen.update();
		camera.update();
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setGrabbed(false);
		}
	}

	private void render() {
		Bitmap.clear();
		glPushMatrix();
		Bitmap.GluPerspective(65, 1f, -1f, width, height);
		{
			screen.renderGlu();
		}
		glPopMatrix();
		glPushMatrix();
		Bitmap.OrthoGraphics(width, height);
		{
			screen.renderOrtho();
			Font.renderString(getFpsAndUps(), 16, 12, 12);
		}
		glPopMatrix();
	}

	private static boolean offline = false;

	public static void main(String[] args) {
		if (args.length > 0) {
			String s = args[0].toLowerCase();
			if (s.equals("offline")) {
				offline = true;
			}
		}
		if (Main.CurrentVersion() || offline) {
			new Main().start();
		} else {
			new Downloader();
		}
	}
}
