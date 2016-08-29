package elevator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel ;

import constant.ConstVariable;
import people.People;

public class Elevator{
	private JPanel elevatorPanel = new JPanel();
	private ConstVariable cv = ConstVariable.getConstVariable();
	private String elevatorName;
	private int direction = 0;
	private static final int MAX_CAPACITY = 5;
	private int elevatorCapacity = 0;
	private int id;
	//координата при достижении который человек считаеться вошедшим в лифт
	//у каждого она разная
	private int elevatorInside;
	private ArrayList<People> peopleInElevator = new ArrayList<>();
	private int currentStorey = -1;
	//нужна что смотреть сколько человек идет в лифт, пока она не равна нулю лифт не поедет
	//если goIn != 0 значит в лифт еще сели н евсе люди 
	private int goInElevator;
	private int goOutElevator;
	private JLabel label = new JLabel("0");
	
	public Elevator(String elevatorName){
		this.elevatorName = elevatorName;
		this.goInElevator = 0;
		this.goOutElevator = 0;
		elevatorPanel.setBounds(cv.elevatorStartPosition(), cv.getFRAME_WIDTH(), cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		this.elevatorInside = elevatorPanel.getX();
		elevatorPanel.setBackground(Color.gray);
		label.setForeground(Color.white);
		elevatorPanel.add(label);
	}
	
	public int getGoIn() {
		return goInElevator;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void addElevatorCapacity(){
		label.setText(String.valueOf(++elevatorCapacity));
	}
	
	public void subElevatorCapacity(){
		label.setText(String.valueOf(--elevatorCapacity));
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
