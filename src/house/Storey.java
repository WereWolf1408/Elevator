package house;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class Storey {
	ConstantVariable constantVariable;
	
	public Storey(ConstantVariable constantVariable){
		this.constantVariable = constantVariable;
	}
	
	public ConstantVariable getConstantVariable() {
		return constantVariable;
	}

	public void setConstantVariable(ConstantVariable constantVariable) {
		this.constantVariable = constantVariable;
	}

	public JPanel getPanel(int y){
		JPanel panel = new JPanel();
		panel.setBounds(0, y, constantVariable.getStoreyWidth(), constantVariable.getStoreyHeight());
		panel.setBackground(Color.red);
		panel.setVisible(true);
		return panel;
	}
}
