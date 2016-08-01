package people;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	private JPanel people = null;
	//этаж на котором появился человечек
	private int storey;
	//направление движения вверх(0)/низ(-1)
	private int direction;
	//этаж на который нужно человечку
	private int finalDectination;
	private int y=0;
	private int position;
	private int elevatorId = 0;
	
	{
		this.direction = (int)(Math.random()*2)-1;
		this.storey = (int)(Math.random()*5);
		this.finalDectination = (int)(Math.random()*5);
	}

	public People(ConstantVariable cv, int position){
		this.cv = cv;
		this.position = position;
		y = cv.getStoreyheight()[storey];
		people = new JPanel();
		people.setBounds(0, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		setDirection();
		checkFinalDestination();
		people.setBackground(Color.blue);
		System.out.println("peopel start location = " + storey);
		System.out.println("peopel end location = " + finalDectination);
		System.out.println("peopel direction = " + direction);
		System.out.println("-----------------------------------------");
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

	public void setDirection(){
		if (storey > finalDectination){
			direction = 0;
		} else if(storey < finalDectination){
			direction = -1;
		}
	}
	
	//проверка что бы чел не содился и выходил на одном и том же этаже
	public void checkFinalDestination(){
		if (finalDectination == storey){
			if (finalDectination == cv.getSTOREY_COUNT()){
				finalDectination--;
				direction = 0;
			} else if (finalDectination == 0){
				finalDectination++;
				direction = -1;
			}
		}
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

	public int getFinalDectination() {
		return finalDectination;
	}
}
