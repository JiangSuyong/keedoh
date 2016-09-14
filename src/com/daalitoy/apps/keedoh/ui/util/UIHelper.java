package com.daalitoy.apps.keedoh.ui.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.daalitoy.apps.keedoh.data.common.FILE_TYPE;
import com.daalitoy.apps.keedoh.system.KeedohCache;

public class UIHelper {

	public static Font STANDARD_FONT = new Font("calibri", Font.PLAIN, 12);
	public static Font STANDARD_BOLD_FONT = new Font("calibri", Font.BOLD, 12);
	private static final ImageIcon IMAGE_ICON = new ImageIcon(
			ClassLoader.getSystemResource("keedoh_node.gif"));

	public static JMenuItem newJMenuItem(String label, int keyCode,
			ActionListener actionListener, String actionCommand) {

		JMenuItem item = new JMenuItem(label);
		item.setMnemonic(keyCode);
		item.addActionListener(actionListener);
		item.setFont(STANDARD_FONT);
		item.setActionCommand(actionCommand);

		return (item);
	}

	public static JMenu newJMenu(String label, int keyCode,
			ActionListener listener) {
		JMenu menu = new JMenu(label);
		menu.setMnemonic(keyCode);
		menu.addActionListener(listener);
		menu.setFont(STANDARD_FONT);

		return (menu);
	}

	public static JTextField newTextField(int i) {
		JTextField field = new JTextField(i);
		field.setFont(STANDARD_FONT);
		return (field);
	}

	public static JButton newButton(String title, String actionCommand,
			ActionListener listener) {
		JButton button = new JButton();
		button.setText(title);
		button.setActionCommand(actionCommand);
		button.addActionListener(listener);
		button.setFont(STANDARD_FONT);
		return (button);
	}

	public static Component newLabel(String msg) {
		JLabel field = new JLabel(msg);
		field.setFont(STANDARD_FONT);
		return (field);
	}

	public static JComboBox newComboBox() {
		JComboBox box = new JComboBox();
		box.setFont(STANDARD_FONT);
		return (box);
	}

	public static JCheckBox newCheckBox(String label) {
		JCheckBox box = new JCheckBox(label);
		box.setSelected(false);
		box.setFont(STANDARD_FONT);
		return (box);
	}

	public static Image getAppImage() {
		return (IMAGE_ICON.getImage());
	}

	public static ImageIcon getAppIcon() {
		return (IMAGE_ICON);
	}

	public static JTextArea newTextArea(int row, int column) {
		JTextArea field = new JTextArea(row, column);
		field.setLineWrap(true);
		field.setWrapStyleWord(true);
		field.setFont(STANDARD_FONT);
		return (field);
	}

	public static File getUserSelectedFile(Component parentComponent,
			FILE_TYPE fileType, boolean open) {
		JFileChooser chooser = null;
		if (!open)
			chooser = new JFileChooser("Save");
		else
			chooser = new JFileChooser("Open");
		if (KeedohCache.getRecentDirectory() != null) {
			chooser.setCurrentDirectory(KeedohCache.getRecentDirectory());
		}
		chooser.setFont(STANDARD_FONT);
		if (fileType == FILE_TYPE.TEXT) {
			chooser.setFileFilter(new FileNameExtensionFilter("Text Files",
					"txt"));
		} else if (fileType == FILE_TYPE.HTML) {
			chooser.setFileFilter(new FileNameExtensionFilter("HTML Files",
					"html"));

		}
		setFont(chooser);
		File file = null;

		if (open) {
			if (chooser.showOpenDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
			}
		} else {
			if (chooser.showSaveDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
			}

		}
		if (file != null) {
			KeedohCache.setRecentDirectory(file.getParentFile());
		}
		return (file);

	}

	private static void setFont(JComponent chooser) {
		for (Component component : chooser.getComponents()) {
			if (component instanceof JComponent) {
				component.setFont(STANDARD_FONT);
				setFont((JComponent) component);
			}
		}

	}

	public static void showErrorDialog(Component component, String stringMsg,
			Exception e) {
		JOptionPane.showMessageDialog(component,
				stringMsg + ":" + e.getMessage());
	}

	public static Point getPreferredLocation(JComponent parent) {
		return (new Point(parent.getX() + (parent.getWidth() / 2),
				parent.getY() + (parent.getHeight() / 2)));
	}

	public static JList newList(Vector v) {
		JList lst = new JList(v);
		lst.setFont(STANDARD_FONT);
		return (lst);
	}
}
