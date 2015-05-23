package net.teamfps.wot;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.SwingWorker;

/**
 * 
 * @author Zekye
 *
 */
public class Version {
	protected String link;
	protected String name;
	protected String href;
	private Task task;

	public Version(String name, String href) {
		this.name = name;
		this.href = href;
		this.link = "https://raw.githubusercontent.com" + href;
	}

	public void Download() {
		task = new Task();
		task.execute();
	}

	public void openLink() {
		try {
			URL url = new URL(link);
			Desktop.getDesktop().browse(url.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public boolean openJar() {
		File f = new File(name);
		if (f.exists()) {
			try {
				Process p = Runtime.getRuntime().exec("java -Xmx1G -Xms1G -jar \"" + f + "\" offline");
				handleStream(p.getInputStream());
				handleStream(p.getErrorStream());
				System.out.println(p + " Is trying to open: " + f);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public void handleStream(InputStream stream) {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					String s = "";
					while ((s = br.readLine()) != null) {
						System.out.println("" + s);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	class Task extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			int count;
			try {
				URL url = new URL(link);
				URLConnection conection = url.openConnection();
				conection.connect();
				int lenghtOfFile = conection.getContentLength();
				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(new File(name));
				byte[] data = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1) {
					total += count;
					setProgress((int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			return null;
		}

		@Override
		protected void done() {
			task = null;
			System.out.println("" + name + " has downloaded.");
		}

	}
}
