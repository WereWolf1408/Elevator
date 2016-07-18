package people;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	JPanel people = null;
	private int storey;
	private boolean direction;
	
	public People(ConstantVariable cv){
		this.cv = cv;
		initPeople();
	}
	
	private void initPeople(){
		people = new JPanel();
		people.setBounds(0, 0, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
	}
	
	public void move(int x){
		people.setBounds(x, 0, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	
	public JPanel getPeople(){
		return people;
	}
}
