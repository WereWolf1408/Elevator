package house;

import java.awt.Color;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import constant.ConstVariable;
import elevator.Elevator;

public class Storey {
	private ConstVariable cv = ConstVariable.getConstVariable();
	private JPanel storey = null;
	private int peopleCount = 0;
	private Lock lock = new ReentrantLock();
	private Condition eCondition = lock.newCondition();
	private Condition pCondition = lock.newCondition();
	private Elevator elevator;
	
	public Storey(int y){
		storey = new JPanel();
		storey.setBounds(0, y, cv.getSTOREY_WIDTH(), cv.getSTOREY_HEIGHT());
		storey.setBackground(Color.red);
		storey.setVisible(true);
	}
	
	public int getPeopleCount(){
		return peopleCount;
	}
	
	public void addPeople(){
		peopleCount++;
	}
	
	public void removePeople(){
		peopleCount--;
	}
	
	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public Elevator getElevator() {
		return elevator;
	}

	public Lock getLock() {
		return lock;
	}
	
	public Condition geteCondition() {
		return eCondition;
	}

	public Condition getpCondition() {
		return pCondition;
	}

	public ConstVariable getConstantVariable() {
		return cv;
	}

	public void setConstantVariable(ConstVariable constantVariable) {
		this.cv = constantVariable;
	}
	
	public JPanel getStorey(){
		return storey;
	}
	
	public int getY(){
		return storey.getY();
	}
}
