package house;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class Storey {
	ConstantVariable constantVariable;
	JPanel panel = null;
	
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
		panel = new JPanel();
		panel.setBounds(0, y, constantVariable.getSTOREY_WIDTH(), constantVariable.getSTOREY_HEIGHT());
		panel.setBackground(Color.red);
		panel.setVisible(true);
		return panel;
	}
	
	public int getY(){
		return panel.getY();
	}
}
