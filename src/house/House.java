package house;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import elevator.Elevator;

public class House {
	JFrame mainFraim = new JFrame("house elevator");
	Storey storey;
	private ArrayList<Elevator> elevators = new ArrayList<>();
	private ArrayList<JPanel> storeys = new ArrayList<>();
	
	public House(Storey storey, ArrayList<Elevator> elevators){
		this.elevators = elevators;
		this.storey = storey;
	}
	
	
	public ArrayList<JPanel> getStoreys() {
		return storeys;
	}


	public void setStoreys(ArrayList<JPanel> storeys) {
		this.storeys = storeys;
	}

	

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}


	public Storey getStorey() {
		return storey;
	}

	public void setStorey(Storey storey) {
		this.storey = storey;
	}
	
	public JFrame getJFrame(){
		return mainFraim;
	}

	public void init(){
		createFrame();
		createStoreys();
		createElevator();
	}
	
	private void createStoreys(){
		int j = 120;
		for (int i = 0; i < 3; i++){
			JPanel st = storey.getPanel(j);
			storeys.add(st);
			mainFraim.add(st);
			j += 160;
		}
	}
	
	private void createElevator(){
		for (int i = 0; i < elevators.size(); i++){
			mainFraim.add(elevators.get(i).getElevator());
		}
	}
	
	private void createFrame(){
		mainFraim.setLayout(null);
		mainFraim.setSize(600,600);
		mainFraim.setBackground(Color.gray);
		mainFraim.setVisible(true);
	}
}
