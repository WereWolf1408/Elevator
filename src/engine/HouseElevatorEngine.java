package engine;
import elevator.Elevator;
import house.House;
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
				releasePeopleOfElevator();
				checkPeopleOnTheStorey();
			}
		}
	}
	
	private void checkPeopleOnTheStorey() throws InterruptedException{
		for (People peopel : house.getPeoples()){
			if (elevator.getCurrentStorey() == peopel.getStartLocation()){
				house.getPeopleCondition().signalAll();
				house.getElevatorCondition().await();
				break;
			}
		}
	}
	
	private void releasePeopleOfElevator() throws InterruptedException{
		if (elevator.getPeopleInElevator().size() != 0){
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
				setStorey();
				peopleMoveUpWithElevator();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
			System.out.println(elevator.getClass() + "release lock");
		}
	}
}
