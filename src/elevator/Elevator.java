package elevator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel ;

import people.People;
import variable.ConstantVariable;

public class Elevator{
	private JPanel elevatorPanel;
	private ConstantVariable cv;
	private String elevatorName;
	private int direction = 0;
	private final int MAX_CAPACITY = 6;
	//���������� ��� ���������� ������� ������� ���������� �������� � ����
	//� ������� ��� ������
	private int elevatorInside;
	private ArrayList<People> peopleInElevator = new ArrayList<>();
	private int currentStorey = -1;
	private int elevatorId;
	

	public Elevator(ConstantVariable cv, int x, int elevatorInside, 
			String elevatorName, int elevatorId){
		this.elevatorName = elevatorName;
		this.elevatorInside = elevatorInside;
		this.cv = cv;
		this.elevatorId = elevatorId;
		elevatorPanel = new JPanel();
		elevatorPanel.setBounds(x, 730, cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		elevatorPanel.setBackground(Color.gray);
	}
	
	public ArrayList<People> getPeopleInElevator() {
		return peopleInElevator;
	}
	
	public int getElevatorId() {
		return elevatorId;
	}

	public void setCurrentStorey(int storey){
		currentStorey = storey;
	}
	
	public int getCurrentStorey(){
		return currentStorey;
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
		return elevatorPanel;
	}
	
	public int getX(){
		return elevatorPanel.getX();
	}
	
	public int getY(){
		return elevatorPanel.getY();
	}
	
	public int getElevatorInside() {
		return elevatorInside;
	}

	public void move(int y){
		elevatorPanel.setBounds(elevatorPanel.getX(), y,  cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
	}
}
