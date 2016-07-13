package engine;

import elevator.Elevator;
import house.House;

public class HouseEngine extends Thread {
	private House house;
	private Elevator elevator;
	
	public HouseEngine(House house){
		setHouse(house);
		elevator = house.getElevator();
	}
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
	
	private void moveUp(){
		
	}

	@Override
	public void run(){
		int x = elevator.getX();
		int y = elevator.getY();
		
		while(y < 600){
			try {
				elevator.elevatorMove(x, y);
				y += 1;
				System.out.println(y);
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
