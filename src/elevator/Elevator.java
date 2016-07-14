package elevator;

import java.awt.Color;

import javax.swing.JPanel;

public class Elevator{
	JPanel elevator;

	public Elevator(){
		elevator = new JPanel();
		elevator.setBounds(100, 340, 50,100);
		elevator.setBackground(Color.gray);
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
	
	public void elevatorMove(int y){
		elevator.setBounds(100, y, 50, 100);
	}
}
