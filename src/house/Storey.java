package house;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import people.People;
import variable.ConstantVariable;

public class Storey {
	ConstantVariable constantVariable;
	JPanel storey = null;
	ArrayList<People> peoples = new ArrayList<>();
	Lock lock = new ReentrantLock();
	Condition elevatorCondition = lock.newCondition();
	Condition peopelCondition = lock.newCondition();
	
	public Storey(ConstantVariable constantVariable, int y){
		this.constantVariable = constantVariable;
		storey = new JPanel();
		storey.setBounds(0, y, constantVariable.getSTOREY_WIDTH(), constantVariable.getSTOREY_HEIGHT());
		storey.setBackground(Color.red);
		storey.setVisible(true);
	}
	
	public ArrayList<People> getPeoples() {
		return peoples;
	}
	
	public Condition getElevatorCondition() {
		return elevatorCondition;
	}

	public Lock getLock() {
		return lock;
	}

	public Condition getPeopelCondition() {
		return peopelCondition;
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
