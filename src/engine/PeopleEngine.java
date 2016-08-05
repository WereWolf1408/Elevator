package engine;


import elevator.Elevator;
import house.House;
import people.People;

public class PeopleEngine extends Thread {
	private People people;
	private House house;
	private Elevator elevator;
	
	public PeopleEngine(People people, House house){
		this.people = people;
		this.house = house;
		this.elevator = house.getElevators().get(0);
	}
	
	private void checkElevatorStoreyOut() throws InterruptedException{
		while(people.getFinalLocation() != elevator.getCurrentStorey()){
			house.getElevatorCondition().signalAll();
			house.getWaitInElevator().await();
		}
	}
	
	private void moveInsideElevator() throws InterruptedException{
		if (people.getPosition() >= elevator.getElevatorInside()){
			house.getPeoples().remove(people);
			elevator.getPeopleInElevator().add(people);
			System.out.println("elevator capacity = " + elevator.getPeopleInElevator().size());
			people.setElevatorId(elevator.getElevatorId());
			house.getElevatorCondition().signalAll();
			house.getWaitInElevator().await();
		}
	}
	
	private void moveOutElevator() throws InterruptedException{
		if(people.getPosition() >= 300){
			elevator.getPeopleInElevator().remove(people);
			System.out.println("peopel move out " + 
					"elevator capacity = " + elevator.getPeopleInElevator().size());
			house.getElevatorCondition().signalAll();
			house.getTheEnd().await();
		}
	}
	
	private void checkElevatorEntry() throws InterruptedException{
		while (people.getStartLocation() != elevator.getCurrentStorey() ||
				people.getDirection() != elevator.getDirection() ||
				elevator.getPeopleInElevator().size() == elevator.getMAX_CAPACITY()){
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
		}
	}
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorEntry();
			while(true){
				if (people.getElevatorId() == 0){
					moveInsideElevator();
				} else if(people.getElevatorId() != 0){
					checkElevatorStoreyOut();
					moveOutElevator();
				}
				people.move();
				Thread.sleep(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
		}
	}
}
