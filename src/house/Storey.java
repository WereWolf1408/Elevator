package house;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class Storey {
	ConstantVariable constantVariable;
	JPanel storey = null;
	
	public Storey(ConstantVariable constantVariable){
		this.constantVariable = constantVariable;
	}
	
	public ConstantVariable getConstantVariable() {
		return constantVariable;
	}

	public void setConstantVariable(ConstantVariable constantVariable) {
		this.constantVariable = constantVariable;
	}

	public void setStoreyHeight(int y){
		storey = new JPanel();
		storey.setBounds(0, y, constantVariable.getSTOREY_WIDTH(), constantVariable.getSTOREY_HEIGHT());
		storey.setBackground(Color.red);
		storey.setVisible(true);
	}
	
	public JPanel getStorey(){
		return storey;
	}
	
	public int getY(){
		return storey.getY();
	}
}
