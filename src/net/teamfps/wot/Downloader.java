package net.teamfps.wot;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class Downloader extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CellRenderer cellRenderer = new CellRenderer();
	private JComboBox<Version> comboBox = new JComboBox<Version>();

	/**
	 * Create the frame.
	 */
	public Downloader() {
		setTitle("War Of Towers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 100);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JProgressBar progressBar = new JProgressBar();
		contentPane.add(progressBar);

		comboBox.setRenderer(cellRenderer);
		contentPane.add(comboBox);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		contentPane.add(btnPlay);
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		contentPane.add(btnDownload);
		setItems();
	}

	public void setItems() {
		comboBox.removeAllItems();
		for (Version v : getVersions()) {
			comboBox.addItem(v);
		}
	}

	public Version[] toVersions(List<Version> list) {
		Version[] result = new Version[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public String getStringFromURL(String link) {
		try {
			URL url = new URL(link);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<String> getListOfStringsFromURL(String link) {
		List<String> result = new ArrayList<String>();
		try {
			URL url = new URL(link);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			while ((s = br.readLine()) != null) {
				result.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String file_list = "https://github.com/XzekyeX/War-Of-Towers/file-list/master";

	public List<Version> getVersions() {
		List<Version> result = new ArrayList<Version>();
		String str = getStringFromURL(file_list);
		List<String> bt = Betweens(str, "<", ">");
		for (int i = 0; i < bt.size(); i++) {
			String b = bt.get(i);
			if (b.contains("<a href=")) {
				List<String> titles = Betweens(b, "title=\"", "\"");
				List<String> hrefs = Betweens(b, "href=\"", "\"");
				if (titles.size() > 0 && hrefs.size() > 0) {
					String title = Between(titles.get(0), '"', '"');
					String href = Between(hrefs.get(0), '"', '"');
					if (title.contains(".jar")) {
						Version v = new Version(title, href);
						result.add(v);
					}
				}
			}
		}
		return result;
	}

	private String Between(String str, char par1, char par2) {
		if (str.contains("" + par1) && str.contains("" + par2)) {
			int bl = str.indexOf(par1);
			String ss = "" + str.substring(bl + 1);
			int br = ss.indexOf(par2);
			String result = "" + ss.substring(0, br);
			return result;
		}
		return "";
	}

	private List<String> Betweens(String str, String par1, String par2) {
		List<String> result = new ArrayList<String>();
		if (str.contains(par1) && str.contains(par2)) {
			Pattern p = Pattern.compile("" + par1 + "(.*?)" + par2);
			Matcher m = p.matcher(str);
			while (m.find()) {
				String t = m.group(0);
				result.add(t);
			}
		}
		return result;
	}
}
