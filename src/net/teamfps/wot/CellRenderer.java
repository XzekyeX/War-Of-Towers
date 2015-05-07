package net.teamfps.wot;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CellRenderer extends JLabel implements ListCellRenderer<Version> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Version> list, Version value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.getName());
		if (isSelected) {
			setBackground(new Color(0x000000));
			setForeground(new Color(0xffffff));
		} else {
			setBackground(new Color(0xffffff));
			setForeground(new Color(0x000000));
		}
		return this;
	}

}
