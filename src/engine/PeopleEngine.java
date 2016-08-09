package engine;


import javax.swing.plaf.basic.BasicTreeUI.CellEditorHandler;

import elevator.Elevator;
import house.House;
import people.People;

public class PeopleEngine extends Thread {
	private People people;
	private House house;
	
	public PeopleEngine(People people, House house){
		this.people = people;
		this.house = house;
	}
	
	private void checkElevatorCondition(Elevator elevator){
		if(people.getStartLocation() == elevator.getCurrentStorey() && 
				people.getDirection() == elevator.getDirection()){
			house.getPeoples().remove(people);
			moveInElevator(elevator);
		} else {
			elevator.getElevatorCondition().signalAll();
			elevator.getLock().unlock();
		}
	}
	

	private void tryingGetInElevator() throws InterruptedException{
		while(true){
			//проверяю все 4 лока на доступность
			for(Elevator elevator : house.getElevators()){
				if (elevator.getLock().tryLock()){
					checkElevatorCondition(elevator);
				}
			}
		}
	}
	
	private void moveInElevator(Elevator elevator){
		try{
			while(true){
				if(people.getPosition() >= 350){
					elevator.getElevatorCondition().signal();
					System.out.println("peopel inside elevatir");
					elevator.getPeopleCondition().await();
				}
				people.move();
				Thread.sleep(8);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run (){
		try {
//			tryingGetInElevator();
			while(true){
				people.move();
				Thread.sleep(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
//			house.getLock().unlock();
		}
	}
}
