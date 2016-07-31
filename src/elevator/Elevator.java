package elevator;

import java.awt.Color;

import javax.swing.JPanel ;

import variable.ConstantVariable;

public class Elevator{
	private JPanel elevator;
	private ConstantVariable cv;
	private String elevatorName;
	private int distance;
	private String direction = "up";
	private final int MAX_CAPACITY = 3;
	private int elevatorCapacity = 10;
	//координата при достижении который человек считаеться вошедшим в лифт
	private int elevatorInside;

	public Elevator(ConstantVariable cv, int distance, int elevatorInside, String elevatorName){
		this.elevatorName = elevatorName;
		this.elevatorInside = elevatorInside;
		this.cv = cv;
		this.distance = distance;
		elevator = new JPanel();
		elevator.setBounds(distance, 460, cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		elevator.setBackground(Color.gray);
	}
	
	public int getElevatorCapacity() {
		return elevatorCapacity;
	}

	public void addElevatorCapacity() {
		this.elevatorCapacity++;
	}
	
	public void decElevatorCapacity(){
		this.elevatorCapacity--;
	}

	public int getMAX_CAPACITY() {
		return MAX_CAPACITY;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getElevatorName() {
		return elevatorName;
	}

	public JPanel getElevator(){
		return elevator;
	}
	
	public int getX(){
		return elevator.getX();
	}
	
	public int getY(){
		return elevator.getY();
	}
	
	public int getElevatorInside() {
		return elevatorInside;
	}

	public void move(int y){
		elevator.setBounds(distance, y,  cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
	}
}
