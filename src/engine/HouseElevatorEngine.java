package engine;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
		checkDirection();
		
		if (direction){
			elevator.move(y += 1);
		} else {
			elevator.move(y -= 1);
		}
	}
	
	private void checkStoreys() throws InterruptedException{
		ArrayList<Storey> storeys = house.getStoreys();
		for (int i = 0; i < storeys.size(); i++){
			if ((y+100) == storeys.get(i).getY()){
				System.out.println(elevator.getElevatorName() + " storey = " + i);
				house.addtCurElevatorStorey(i, elevator);
				house.getPeopleCondition().signalAll();
				house.getElevatorCondition().await();
			}
		}
	}
	
	@Override
	public void run(){
		house.getLock().lock();
		try {
			while(true){	
				Thread.sleep(10);
				elevatorMove();
				checkStoreys();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
			System.out.println(elevator.getClass() + "release lock");
		}
	}
}
