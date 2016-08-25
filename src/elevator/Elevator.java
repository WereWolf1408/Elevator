package elevator;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel ;

import constant.ConstVariable;
import people.People;

public class Elevator{
	private JPanel elevatorPanel = new JPanel();
	private ConstVariable cv = ConstVariable.getConstVariable();
	private String elevatorName;
	private int direction = 0;
	private final int MAX_CAPACITY = 5;
	private int id;
	//���������� ��� ���������� ������� ������� ���������� �������� � ����
	//� ������� ��� ������
	private int elevatorInside;
	private ArrayList<People> peopleInElevator = new ArrayList<>();
	private int currentStorey = -1;
	//����� ��� �������� ������� ������� ���� � ����, ���� ��� �� ����� ���� ���� �� ������
	//���� goIn != 0 ������ � ���� ��� ���� � ���� ���� 
	private int goInElevator;
	private int goOutElevator;
	
	public Elevator(int x, String elevatorName, int startLocation){
		this.elevatorName = elevatorName;
		this.goInElevator = 0;
		this.goOutElevator = 0;
		this.elevatorInside = x;
		elevatorPanel.setBounds(x, cv.getFRAME_WIDTH(), cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		elevatorPanel.setBackground(Color.gray);
	}
	
	public int getGoIn() {
		return goInElevator;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	public void incGoIn() {
		this.goInElevator++;
	}
	
	public void decGoIn(){
		this.goInElevator--;
	}

	public int getGoOut() {
		return goOutElevator;
	}

	public void incGoOut() {
		this.goOutElevator++;
	}
	
	public void decGoOut(){
		this.goOutElevator--;
	}

	public ArrayList<People> getPeopleInElevator() {
		return peopleInElevator;
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
