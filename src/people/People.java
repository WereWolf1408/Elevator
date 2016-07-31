package people;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	private JPanel people = null;
	private int storey;
	private int direction;
	private int y=0;
	
	{
		this.direction = (int)(Math.random()*2)-1;
		this.storey = (int)(Math.random()*3);
	}

	public People(ConstantVariable cv){
		this.cv = cv;
		y = cv.getStoreyheight()[storey];
		people = new JPanel();
		people.setBounds(0, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
		System.out.println("peopel storey" + storey);
	}
	
	public void move(int x){
		people.setBounds(x, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	
	public JPanel getPeople(){
		return people;
	}

	public int getStorey() {
		return storey;
	}

	public int getDirection() {
		return direction;
	}
}
