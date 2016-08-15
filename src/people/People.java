package people;

import java.awt.Color;

import javax.swing.JPanel;

import elevator.Elevator;
import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	private JPanel people = null;
	//этаж на котором появился человечек
	private int startStorey;
	//направление движения вверх(0)/низ(-1)
	private int direction;
	//этаж на который нужно человечку
	private int finalStorey;
	private int y=0;
	private int position;
	
	{
		this.startStorey = (int)(Math.random()*5);
		this.finalStorey = (int)(Math.random()*5);
	}

	public People(ConstantVariable cv, int position){
		this.cv = cv;
		this.position = position;
		//нужно что бы разместить челове над линией этажа 
		y = cv.getStoreyheight()[startStorey];
		people = new JPanel();
		people.setBounds(0, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
	}
	
	public void move(){
		people.setBounds(position++, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	public void move(int y){
		setY(y);
		people.setBounds(position, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPosition() {
		return position;
	}

	public void peopleInit(){
		if (startStorey > finalStorey){
			direction = 0;
		} else if (startStorey < finalStorey){
			direction = -1;
		}
		if (startStorey == finalStorey){
			if (finalStorey == 0){
				finalStorey++;
				direction = -1;
			} else if (finalStorey == cv.getSTOREY_COUNT()){
				finalStorey--;
				direction = 0;
			} else {
				if (direction == 0){
					finalStorey--;
				} else {
					finalStorey++;
				}
			}
		}
		System.out.println("peopel start location = " + startStorey);
		System.out.println("peopel end location = " + finalStorey);
		System.out.println("peopel direction = " + direction);
		System.out.println("-----------------------------------------");
	}
	
	public JPanel getPeople(){
		return people;
	}

	public int getStartLocation() {
		return startStorey;
	}

	public int getDirection() {
		return direction;
	}

	public int getFinalLocation() {
		return finalStorey;
	}
}
