package people;

import java.awt.Color;

import javax.swing.JPanel;

import constant.ConstVariable;

public class People {
	private ConstVariable cv = ConstVariable.getConstVariable();
	private JPanel people = null;
	//этаж на котором появился человечек
	private int startStorey;
	//направление движения вверх(0)/низ(-1)
	private int direction;
	//этаж на который нужно человечку
	private int finalStorey;
	private int position = 10;
	private int y;
	
	{
		this.startStorey = (int)(Math.random() * cv.getSTOREY_COUNT()+1);
		this.finalStorey = (int)(Math.random() * cv.getSTOREY_COUNT()+1);
	}

	public People(){
		//нужно что бы разместить челове над линией этажа 
		people = new JPanel();
		y = cv.getStoreyHeight(startStorey)-20;
		people.setBounds(0, y, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
	}
	
	public void move(){
		people.setBounds(position++, y, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	
	
	public void moveWithElevator(){
		if(direction == 0){
			y--;
		}else{
			y++;
		}
		people.setBounds(people.getX(), y, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
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
