package house;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JPanel;

import people.People;
import variable.ConstantVariable;
import elevator.Elevator;

public class House {
	JFrame mainFraim = new JFrame("house elevator");
	private ArrayList<Elevator> elevators = new ArrayList<>();
	private ArrayList<Storey> storeys = new ArrayList<>();
	private ArrayList<People> peoples = new ArrayList<>();
	private ConstantVariable cv;
	private Map<Integer, Elevator> curElevatorStorey = new HashMap<Integer, Elevator>();
	private Lock lock = new ReentrantLock();
	private Condition elevatorCondition = lock.newCondition();
	private Condition peopleCondition = lock.newCondition();
	//��� ����� ������� ��� �������� �� ���� ���� � ����� �� �����
	private Condition finalDestination = lock.newCondition();
	
	public House(ArrayList<Elevator> elevators, ArrayList<People> peoples, ConstantVariable cv,
			ArrayList<Storey> storeys){
		this.elevators = elevators;
		this.peoples = peoples;
		this.cv = cv;
		this.storeys = storeys;
	}
	
	public Map<Integer, Elevator> getCurElevatorStorey() {
		return curElevatorStorey;
	}

	public void addtCurElevatorStorey(int storey, Elevator elevator) {
		this.curElevatorStorey.put(storey, elevator);
	}
	
	public void removeElevatorStorey(int i){
		this.curElevatorStorey.remove(i);
	}

	public Condition getPeopleCondition() {
		return peopleCondition;
	}

	public Condition getElevatorCondition() {
		return elevatorCondition;
	}

	public Lock getLock() {
		return lock;
	}

	public ArrayList<Storey> getStoreys() {
		return storeys;
	}


	public ArrayList<People> getPeoples() {
		return peoples;
	}

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}

	public JFrame getJFrame(){
		return mainFraim;
	}

	public void init(){
		createFrame();
		createStoreys();
		createElevator();
		createPeoples();
	}
	
	private void createStoreys(){
		for (int i = 0; i < storeys.size(); i++){
			mainFraim.add(storeys.get(i).getStorey());
		}
	}
	
	private void createElevator(){
		for (int i = 0; i < elevators.size(); i++){
			mainFraim.add(elevators.get(i).getElevator());
		}
	}
	
	private void createPeoples(){
		for (int i = 0; i < peoples.size(); i++){
			mainFraim.add(peoples.get(i).getPeople());
		}
	}
	
	private void createFrame(){
		mainFraim.setLayout(null);
		mainFraim.setSize(600,600);
		mainFraim.setBackground(Color.gray);
		mainFraim.setVisible(true);
	}
}
