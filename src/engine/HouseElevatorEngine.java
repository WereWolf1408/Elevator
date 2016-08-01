package engine;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import elevator.Elevator;
import house.House;
import house.Storey;
import people.People;

public class HouseElevatorEngine extends Thread {
	private House house;
	private Elevator elevator;
	int y;
	
	public HouseElevatorEngine(House house, Elevator elevator){
		this.house = house;
		this.elevator = elevator;
	}
	
	private void checkDirection(){
		if (elevator.getY() <= 0){
			elevator.setDirection(-1);
		} else if (elevator.getY() >= 730){
			elevator.setDirection(0);
		}
	}
	
	private void elevatorMove(){
		checkDirection();
		
		if (elevator.getDirection() == -1){
			elevator.move(y += 1);
		} else {
			elevator.move(y -= 1);
		}
	}
	
	private void peopleInElevatorMove(){
		if (elevator.getPeopleInElevator().size() != 0){
			for (int i = 0; i < elevator.getPeopleInElevator().size(); i++){
				elevator.getPeopleInElevator().get(i).move(elevator.getY()+60);
			}
		}
	}
	
	private void checkStoreys() throws InterruptedException{
		ArrayList<Storey> storeys = house.getStoreys();
		for (int storey = 0; storey < storeys.size(); storey++){
			if ((y+60) == storeys.get(storey).getY()){
				elevator.setCurrentStorey(storey);
				peopleGetOut();
				elevatopStop();
			}
		}
	}
	//проверка есть ли на текущем жэтаже люди, 
	//если нет то не останавливаться
	private void elevatopStop() throws InterruptedException{
		for (People people : house.getPeoples()){
			if (people.getStorey() == elevator.getCurrentStorey()){
				house.getPeopleCondition().signalAll();
				house.getElevatorCondition().await();
				break;
			}
		}
	}
	
	private void peopleGetOut() throws InterruptedException{
		if(elevator.getPeopleInElevator().size() != 0){
			house.getWaitInElevator().signalAll();
			house.getElevatorCondition().await();
		}
	}
	
	
	@Override
	public void run(){
		house.getLock().lock();
		try {
			while(true){	
				Thread.sleep(8);
				elevatorMove();
				peopleInElevatorMove();
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
