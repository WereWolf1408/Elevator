package elevator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel ;

import people.People;
import variable.ConstantVariable;

public class Elevator{
	private JPanel elevator;
	private ConstantVariable cv;
	private String elevatorName;
	private int distance;
	private int direction = 0;
	private final int MAX_CAPACITY = 3;
	//координата при достижении который человек считаеться вошедшим в лифт
	private int elevatorInside;
	private ArrayList<People> peopleInElevator = new ArrayList<>();
	

	public Elevator(ConstantVariable cv, int distance, int elevatorInside, String elevatorName){
		this.elevatorName = elevatorName;
		this.elevatorInside = elevatorInside;
		this.cv = cv;
		this.distance = distance;
		elevator = new JPanel();
		elevator.setBounds(distance, 460, cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		elevator.setBackground(Color.gray);
	}
	
	public ArrayList<People> getPeopleInElevator() {
		return peopleInElevator;
	}

	public void addElevatorPeople(People people){
		peopleInElevator.add(people);
	}
	
	public void removeElevatorPeople(People people){
		peopleInElevator.remove(people);
	}
	
	public int getMAX_CAPACITY() {
		return MAX_CAPACITY;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
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
