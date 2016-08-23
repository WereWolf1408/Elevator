package house;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;

import constant.ConstVariable;
import people.People;
import elevator.Elevator;

public class House {
	private JFrame mainFraim = new JFrame("house elevator");
	private ArrayList<Elevator> elevators = new ArrayList<>();
	private ArrayList<Storey> storeys = new ArrayList<>();
	private ArrayList<People> peoples = new ArrayList<>();
	private ArrayList<Condition> elevatorCondirion = new ArrayList<>();
	private ArrayList<Condition> peopleCondition = new ArrayList<>();
	private ArrayList<Condition> peopelWaitInElevator = new ArrayList<>();
	private ConstVariable cv;
	private Lock lock = new ReentrantLock();
	private Condition theEnd = lock.newCondition();
//	private Condition peopleCondition = lock.newCondition();
	//для людей которые уже приехали на свой этаж и вышли из лифта
	
	
	public House(ArrayList<Elevator> elevators, ArrayList<People> peoples, ConstVariable cv,
			ArrayList<Storey> storeys){
		this.elevators = elevators;
		this.peoples = peoples;
		this.cv = cv;
		this.storeys = storeys;
	}
	
	public ConstVariable getConstVariable(){
		return cv;
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
		addPeopleOnStorey();
	}
	
	
	public Condition getPeopelWaitInElevator(int index) {
		return peopelWaitInElevator.get(index);
	}

	private void createPeopleAndElevatorCondition(){
		for(int i = 0; i <= cv.getSTOREY_COUNT(); i++){
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
			elevators.get(i).setId(i);
			mainFraim.add(elevators.get(i).getElevator());
			peopelWaitInElevator.add(lock.newCondition());
		}
	}
	
	private void createPeoples(){
		for (int i = 0; i < peoples.size(); i++){
			mainFraim.add(peoples.get(i).getPeople());
		}
	}
	
	private void addPeopleOnStorey(){
		for (People people : peoples){
			storeys.get(people.getStartLocation()).addPeople();
		}
		for(Storey storey : storeys){
			System.out.println(storey.getPeopleCount());
		}
	}
	
	private void createFrame(){
		mainFraim.setLayout(null);
		mainFraim.setSize(cv.getFRAME_HEIGHT(), cv.getFRAME_WIDTH());
		mainFraim.setBackground(Color.gray);
		mainFraim.setVisible(true);
	}
}
