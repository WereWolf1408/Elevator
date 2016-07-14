package engine;

import java.util.ArrayList;

import javax.swing.JPanel;

import elevator.Elevator;
import house.House;
import house.Storey;

public class HouseEngine extends Thread {
	private House house;
	private Elevator elevator;
	private boolean direction = false;
	int y;
	
	public HouseEngine(House house){
		setHouse(house);
		elevator = house.getElevator();
		y = elevator.getY();
	}
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	private void checkDirection(){
		if (y <= 0){
			direction = true;
		} else if (y >= 600 - 140){
			direction = false;
		}
	}
	
	private void elevatorMove(){
		if (direction){
			elevator.elevatorMove(y += 1);
		} else {
			elevator.elevatorMove(y -= 1);
		}
		checkDirection();
	}
	
	private void storeys(){
		ArrayList<JPanel> storeys = house.getStoreys();
		for (int i = 0; i < storeys.size(); i++){
			if (y == storeys.get(i).getY()){
				System.out.println("lift check storey = " + i);
			}
		}
	}
	
	@Override
	public void run(){
		while(true){
			try {
				elevatorMove();
				storeys();
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
