package net.teamfps.wot;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.*;

/**
 * @author Zekye
 *
 */
public class Bitmap {

	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
	}

	public static void clear(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}

	public static void OrthoGraphics(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public static void GluPerspective(float fov, float zNear, float zFar, int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float) width / (float) height, zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
	}

	public static void renderBlock(Sprite sprite, int x, int y, int z, int w, int h, int d) {
		if (sprite == null) return;
		glPushMatrix();
		glTranslatef(x, y, z);
		glBindTexture(GL_TEXTURE_2D, sprite.getTextureID());
		glBegin(GL_QUADS);
		{
			// TOP
			glColor3f(1.0f, 1.0f, 1.0f);
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(0.0f, d, 0.0f);
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(w, d, 0.0f);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(w, 0.0f, 0.0f);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(0.0f, 0.0f, 0.0f);
			// BOT
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(0.0f, d, h);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(0.0f, 0.0f, h);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(w, 0.0f, h);
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(w, d, h);
			// LEFT
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(0.0f, d, h);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(0.0f, d, 0.0f);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(0.0f, 0.0f, 0.0f);
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(0.0f, 0.0f, h);
			// RIGHT
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(w, d, h);
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(w, 0.0f, h);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(w, 0.0f, 0.0f);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(w, d, 0.0f);
			// FRONT
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(0.0f, d, h);
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(w, d, h);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(w, d, 0.0f);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(0.0f, d, 0.0f);
			// BACK
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(0.0f, 0.0f, h);
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(0.0f, 0.0f, 0.0f);
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(w, 0.0f, 0.0f);
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(w, 0.0f, h);
		}
		glBindTexture(GL_TEXTURE_2D, 0);
		glEnd();
		glPopMatrix();
	}

	public static void renderSprite(Sprite sprite, int x, int y, int w, int h) {
		if (sprite == null) return;
		glPushMatrix();
		glTranslatef(x, y, 0);
		glBindTexture(GL_TEXTURE_2D, sprite.getTextureID());
		glBegin(GL_QUADS);
		{
			glColor3f(1.0f, 1.0f, 1.0f);
			glTexCoord2f(0.0F, 0.0F);
			glVertex2f(0.0F, 0.0F);
			glTexCoord2f(0.0F, 1.0F);
			glVertex2f(0.0F, 0.0F + h);
			glTexCoord2f(1.0F, 1.0F);
			glVertex2f(0.0F + w, 0.0F + h);
			glTexCoord2f(1.0F, 0.0F);
			glVertex2f(0.0F + w, 0.0F);
		}
		glBindTexture(GL_TEXTURE_2D, 0);
		glEnd();
		glPopMatrix();
	}

}
