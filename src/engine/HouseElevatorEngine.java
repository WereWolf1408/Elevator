package engine;
import javax.swing.plaf.basic.BasicTreeUI.CellEditorHandler;
import javax.swing.text.ChangedCharSetException;

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
		this.y = elevator.getY();
	}
	
	private void checkDirection(){
		if (y <= 0){
			elevator.setDirection(-1);
		} else if (y >= 730){
			elevator.setDirection(0);
		}
	}

	private void elevatorMove(){
		checkDirection();
		if (elevator.getDirection() == 0){
			elevator.move(y--);
		} else if (elevator.getDirection() == -1){
			elevator.move(y++);
		}
	}
	
	private void peopleMoveUpWithElevator(){
		if (elevator.getPeopleInElevator().size() != 0){
			for (int i = 0; i < elevator.getPeopleInElevator().size(); i++){
				elevator.getPeopleInElevator().get(i).move(elevator.getY()+60);
			}
		}
	}
	
	private void setStorey() throws InterruptedException{
		for (int i = 0 ; i < house.getStoreys().size(); i++){
			if (elevator.getY()+60 == house.getStoreys().get(i).getY()){
				elevator.setCurrentStorey(i);
				checkPeopleOnStorey(i);
			}
		}
	}
	
	private void checkPeopleOnStorey(int storey) throws InterruptedException{
		Storey st = house.getStoreys().get(storey);
		st.getLock().lock();
		try{
			if(st.getPeoples().size() != 0){
				elevator.getLock().unlock();
				st.getPeopelCondition().signalAll();
				st.getElevatorCondition().await();
			}
		}finally{
			st.getLock().unlock();
		}
	}
	
	@Override
	public void run(){
		elevator.getLock().lock();
		try {
			while(true){	
				Thread.sleep(8);
				elevatorMove();
				setStorey();
				peopleMoveUpWithElevator();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			elevator.getLock().unlock();
			System.out.println(elevator.getClass() + "release lock");
		}
	}
}
