package com.ubb.game;

import javax.swing.JLabel;

// JLabelExtension extends from JLabel
// Subclass created for if image is selected or not.

/**
 * JLabelExtension extends from JLabel
 * Subclass created for if image is selected or not.
 */
public class JLabelExtension extends JLabel{

	
	private boolean control_case;
	private int id;
	private boolean selected_image;
	
	// Constructor
	JLabelExtension () {
		
		this.control_case = true;
		this.selected_image = false;
	}
	
	public boolean isControl_case() {
		return control_case;
	}

	public void setControl_case(boolean control_case) {
		this.control_case = control_case;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSelected_image() {
		return selected_image;
	}

	public void setSelected_image(boolean selected_image) {
		this.selected_image = selected_image;
	}
	
	
	

}
