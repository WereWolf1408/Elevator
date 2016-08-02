package people;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	private JPanel people = null;
	//этаж на котором появился человечек
	private int startLocation;
	//направление движения вверх(0)/низ(-1)
	private int direction;
	//этаж на который нужно человечку
	private int finalLocation;
	private int y=0;
	private int position;
	private int elevatorId = 0;
	
	{
		this.startLocation = (int)(Math.random()*5);
		this.finalLocation = (int)(Math.random()*5);
	}

	public People(ConstantVariable cv, int position){
		this.cv = cv;
		this.position = position;
		y = cv.getStoreyheight()[startLocation];
		people = new JPanel();
		people.setBounds(0, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
	}

	public int getElevatorId(){
		return elevatorId;
	}
	
	public void setElevatorId(int id){
		elevatorId = id;
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
		if (startLocation > finalLocation){
			direction = 0;
		} else if (startLocation < finalLocation){
			direction = -1;
		}
		if (startLocation == finalLocation){
			if (finalLocation == 0){
				finalLocation++;
				direction = -1;
			} else if (finalLocation == cv.getSTOREY_COUNT()){
				finalLocation--;
				direction = 0;
			} else {
				if (direction == 0){
					finalLocation--;
				} else {
					finalLocation++;
				}
			}
		}
		System.out.println("peopel start location = " + startLocation);
		System.out.println("peopel end location = " + finalLocation);
		System.out.println("peopel direction = " + direction);
		System.out.println("-----------------------------------------");
	}
	
	public JPanel getPeople(){
		return people;
	}

	public int getStartLocation() {
		return startLocation;
	}

	public int getDirection() {
		return direction;
	}

	public int getFinalLocation() {
		return finalLocation;
	}
}
