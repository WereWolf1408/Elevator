package engine;

import java.util.ArrayList;

import javax.swing.JPanel;

import elevator.Elevator;
import house.House;
import house.Storey;

public class HouseElevatorEngine extends Thread {
	private House house;
	private Elevator elevator;
	private boolean direction = false;
	int y;
	
	public HouseElevatorEngine(House house, Elevator elevator){
		this.house = house;
		this.elevator = elevator;
		y = elevator.getY();
	}
	
	private void checkDirection(){
		if (y <= 0){
			direction = true;
		} else if (y >= 500){
			direction = false;
		}
	}
	
	private void elevatorMove(){
		if (direction){
			elevator.move(y += 1);
		} else {
			elevator.move(y -= 1);
		}
		checkDirection();
	}
	
	private void storeys(){
		ArrayList<JPanel> storeys = house.getStoreys();
		for (int i = 0; i < storeys.size(); i++){
			if ((y+100) == storeys.get(i).getY()){
				System.out.println(elevator.getElevatorName() + " storey = " + i);
			}
		}
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(10);
				elevatorMove();
				storeys();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
