package net.teamfps.wot;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 * 
 * @author Zekye
 *
 */
public class CellRenderer extends JLabel implements ListCellRenderer<Version> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Version> list, Version value, int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) setText(value.getName());
		return this;
	}

}
