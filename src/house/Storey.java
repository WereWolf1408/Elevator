package house;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import elevator.Elevator;
import people.People;
import variable.ConstantVariable;

public class Storey {
	private ConstantVariable constantVariable;
	private JPanel storey = null;
	private Lock lock = new ReentrantLock();
	private Condition eCondition = lock.newCondition();
	private Condition pCondition = lock.newCondition();
	private Elevator elevator;
	private ArrayList<People> peoples = new ArrayList<>();
	
	public Storey(ConstantVariable constantVariable, int y){
		this.constantVariable = constantVariable;
		storey = new JPanel();
		storey.setBounds(0, y, constantVariable.getSTOREY_WIDTH(), constantVariable.getSTOREY_HEIGHT());
		storey.setBackground(Color.red);
		storey.setVisible(true);
	}
	
	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public ArrayList<People> getPeoples() {
		return peoples;
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

	public ConstantVariable getConstantVariable() {
		return constantVariable;
	}

	public void setConstantVariable(ConstantVariable constantVariable) {
		this.constantVariable = constantVariable;
	}
	
	public JPanel getStorey(){
		return storey;
	}
	
	public int getY(){
		return storey.getY();
	}
}
