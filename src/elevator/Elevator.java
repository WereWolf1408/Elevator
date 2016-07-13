package elevator;

import java.awt.Color;

import javax.swing.JPanel;

public class Elevator{
	JPanel elevator;

	public JPanel getElevator(int x){
		elevator = new JPanel();
		elevator.setBounds(x, 340, 50,100);
		elevator.setBackground(Color.gray);
		return elevator;
	}
	
	public int getX(){
		return elevator.getX();
	}
	
	public int getY(){
		return elevator.getY();
	}
	
	public void elevatorMove(int x, int y){
		elevator.setBounds(x, y, 50, 100);
	}
}
