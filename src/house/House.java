package house;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

import elevator.Elevator;

public class House {
	JFrame mainFraim = new JFrame("house elevator");
	Storey storey;
	Elevator elevator;
	
	public House(Storey storey, Elevator elevator){
		setStorey(storey);
		setElevator(elevator);
	}
	
	public Elevator getElevator() {
		return elevator;
	}


	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
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
		logger("create main panel");
	}
	
	private void createStoreys(){
		int j = 120;
		for (int i = 0; i < 3; i++){
			mainFraim.add(storey.getPanel(j));
			j += 160;
		}
	}
	
	private void createElevator(){
		int j = 50;
		
		for (int i = 0; i < 1; i++){
			mainFraim.add(elevator.getElevator(j));
			j +=50;
		}
	}
	
	private void createFrame(){
		mainFraim.setLayout(null);
		mainFraim.setSize(600,600);
		mainFraim.setBackground(Color.gray);
		mainFraim.setVisible(true);
	}
	
	private static void logger(String value){
		System.out.println(value);
	}
}
