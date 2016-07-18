package elevator;

import java.awt.Color;

import javax.swing.JPanel ;

import variable.ConstantVariable;

public class Elevator{
	private JPanel elevator;
	private String str;
	private ConstantVariable cv;
	private String elevatorName;
	private int distance;

	public Elevator(ConstantVariable cv, int distance, String name){
		elevatorName = name;
		this.cv = cv;
		this.distance = distance;
		elevatorInit(distance);
	}
	
	private void elevatorInit(int distance){
		elevator = new JPanel();
		elevator.setBounds(distance, 460, cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
		elevator.setBackground(Color.gray);
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
	
	public void move(int y){
		elevator.setBounds(distance, y,  cv.getELEVATOR_HEIGHT(), cv.getELEVATOR_WIDTH());
	}
}
