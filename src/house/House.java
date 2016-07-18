package house;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Stroke;
import java.util.ArrayList;

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
	
	public House(ArrayList<Elevator> elevators, ArrayList<People> peoples, ConstantVariable cv,
			ArrayList<Storey> storeys){
		this.elevators = elevators;
		this.peoples = peoples;
		this.cv = cv;
		this.storeys = storeys;
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
