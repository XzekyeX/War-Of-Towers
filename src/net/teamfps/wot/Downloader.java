package net.teamfps.wot;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Downloader extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Version> list = new JList<Version>();

	/**
	 * Create the frame.
	 */
	public Downloader() {
		setResizable(false);
		setTitle("War Of Towers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		list.setCellRenderer(new CellRenderer());

		JButton btnDownloadSelected = new JButton("Download Selected");
		btnDownloadSelected.setBounds(274, 11, 150, 23);
		contentPane.add(btnDownloadSelected);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 11, 254, 23);
		contentPane.add(progressBar);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				list.setListData(toVersions(getVersions()));
			}
		});
		btnPlay.setBounds(274, 238, 150, 23);
		contentPane.add(btnPlay);

		JLabel lblVersion = new JLabel("");
		lblVersion.setBounds(10, 238, 254, 23);
		contentPane.add(lblVersion);

		JScrollPane sp_ta = new JScrollPane();
		sp_ta.setBounds(12, 49, 252, 178);
		contentPane.add(sp_ta);

		JTextArea ta = new JTextArea();
		sp_ta.setViewportView(ta);

		JScrollPane sp_list = new JScrollPane();
		sp_list.setBounds(274, 49, 148, 178);
		contentPane.add(sp_list);
		sp_list.setViewportView(list);
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
		List<String> list = getListOfStringsFromURL(file_list);
		int tableIndex = 0;
		int tableLastIndex = 0;
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).trim();
			if (str.equals("<table class=\"files\" data-pjax>")) {
				tableIndex = i;
			}
			if (str.equals("</table>")) {
				tableLastIndex = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int j = tableIndex; j < tableLastIndex + 1; j++) {
			String s = list.get(j);
			sb.append(s);
		}
		List<String> bt = Betweens(sb.toString(), "<", ">");
		for (int i = 0; i < bt.size(); i++) {
			String b = bt.get(i);
			if (b.contains("<a href=")) {
				System.out.println("bt[" + i + "]: " + b);
				List<String> c = Betweens(b, "class=\"", "\"");
				List<String> title = Betweens(b, "title=\"", "\"");
				List<String> href = Betweens(b, "href=\"", "\"");
				System.out.println("class: " + c);
				System.out.println("title: " + title);
				System.out.println("href: " + href);
				if (title.size() > 0 && href.size() > 0) {
					String t = title.get(0);
					String h = href.get(0);
					if (t.contains(".jar")) {
						Version v = new Version(t, h);
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
			int br = str.indexOf(par2);
			String ss = "" + str.substring(bl + 1, br);
			return ss;
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
