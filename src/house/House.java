package house;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;

import people.People;
import variable.ConstantVariable;
import elevator.Elevator;

public class House {
	private JFrame mainFraim = new JFrame("house elevator");
	private ArrayList<Elevator> elevators = new ArrayList<>();
	private ArrayList<Storey> storeys = new ArrayList<>();
	private ArrayList<People> peoples = new ArrayList<>();
	private ConstantVariable cv;
	private Lock lock = new ReentrantLock();
	private Condition theEnd = lock.newCondition();
//	private Condition peopleCondition = lock.newCondition();
	//для людей которые уже приехали на свой этаж и вышли из лифта
	private ArrayList<Condition> elevatorCondirion = new ArrayList<>();
	private ArrayList<Condition> peopleCondition = new ArrayList<>();
	private ArrayList<Condition> peopelWaitInElevator = new ArrayList<>();
	
	
	public House(ArrayList<Elevator> elevators, ArrayList<People> peoples, ConstantVariable cv,
			ArrayList<Storey> storeys){
		this.elevators = elevators;
		this.peoples = peoples;
		this.cv = cv;
		this.storeys = storeys;
	}
	
	public Condition getTheEnd() {
		return theEnd;
	}

	public Lock getLock() {
		return lock;
	}

	public ArrayList<Storey> getStoreys() {
		return storeys;
	}
	
	public Storey getStoreys(int storey){
		return storeys.get(storey);
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
		createPeopleAndElevatorCondition();
		createFrame();
		createStoreys();
		createElevator();
		createPeoples();
	}
	
	
	public Condition getPeopelWaitInElevator(int index) {
		return peopelWaitInElevator.get(index);
	}

	private void createPeopleAndElevatorCondition(){
		for(int i = 0; i <= storeys.size(); i++){
			peopleCondition.add(lock.newCondition());
			elevatorCondirion.add(lock.newCondition());
		}
	}
	
	public Condition getPeopleCondition(int index){
		return peopleCondition.get(index);
	}
	
	public Condition getElevatorCondition(int index){
		return elevatorCondirion.get(index);
	}
	
	private void createStoreys(){
		for (int i = 0; i < storeys.size(); i++){
			mainFraim.add(storeys.get(i).getStorey());
		}
	}
	
	private void createElevator(){
		for (int i = 0; i < elevators.size(); i++){
			mainFraim.add(elevators.get(i).getElevator());
			peopelWaitInElevator.add(lock.newCondition());
		}
	}
	
	private void createPeoples(){
		for (int i = 0; i < peoples.size(); i++){
			Storey storey = storeys.get(peoples.get(i).getStartLocation());
			storey.getPeoples().add(peoples.get(i));
			mainFraim.add(peoples.get(i).getPeople());
		}
	}
	
	private void createFrame(){
		mainFraim.setLayout(null);
		mainFraim.setSize(600,800);
		mainFraim.setBackground(Color.gray);
		mainFraim.setVisible(true);
	}
}
