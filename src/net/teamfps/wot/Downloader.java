package net.teamfps.wot;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Downloader extends JFrame {

	private JPanel contentPane;
	private JList list = new JList();

	/**
	 * Create the frame.
	 */
	public Downloader() {
		setTitle("War Of Towers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(274, 40, 150, 180);
		contentPane.add(scrollPane_1);

		scrollPane_1.setViewportView(list);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 254, 180);
		contentPane.add(scrollPane);

		JTextArea ta = new JTextArea();
		scrollPane.setViewportView(ta);

		JButton btnDownloadSelected = new JButton("Download Selected");
		btnDownloadSelected.setBounds(274, 11, 150, 23);
		contentPane.add(btnDownloadSelected);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 11, 254, 23);
		contentPane.add(progressBar);

		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(274, 231, 150, 23);
		contentPane.add(btnPlay);

		JLabel lblVersion = new JLabel("");
		lblVersion.setBounds(10, 231, 254, 23);
		contentPane.add(lblVersion);
	}
}
