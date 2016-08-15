package engine;
import elevator.Elevator;
import house.House;
import house.Storey;

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
				house.getLock().lock();
				checkComeOutPeople();
				checkPeopelOnTheStorey();
			}
		}
	}
	
	private void checkComeOutPeople() throws InterruptedException{
		if(elevator.getPeopleInElevator().size() != 0){
			house.getPeopelWaitInElevator(elevator.getElevatorId()).signalAll();
			house.getElevatorCondition(elevator.getCurrentStorey()).await();
			waitPeopelGoOut();
		}
	}
	
	private void waitPeopelGoOut() throws InterruptedException{
		while(elevator.getGoOut() != 0){
			house.getElevatorCondition(elevator.getCurrentStorey()).await();
		}
	}
	
	private void waitWhilePeopleGoIn() throws InterruptedException{
		while(elevator.getGoIn() != 0){
			house.getElevatorCondition(elevator.getCurrentStorey()).await();
		}
	}
	
	private void checkPeopelOnTheStorey() throws InterruptedException{
		try{
			Storey storey = house.getStoreys(elevator.getCurrentStorey());
			if(!storey.getPeoples().isEmpty()){
				house.getPeopleCondition(elevator.getCurrentStorey()).signalAll();
				house.getElevatorCondition(elevator.getCurrentStorey()).await();
				waitWhilePeopleGoIn();
			}
		}finally{
			house.getLock().unlock();
		}
	}
	
	@Override
	public void run(){
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
			System.out.println(elevator.getClass() + "release lock");
		}
	}
}
