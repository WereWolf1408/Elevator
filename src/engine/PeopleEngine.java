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
	
	
	private void checkElevatorStoreyIn() throws InterruptedException{
		while(people.getStartLocation() != elevator.getCurrentStorey()){
//			System.out.println("peopel storey = " + people.getStartLocation() + 
//					" elevator storey = " + elevator.getCurrentStorey());
//			System.out.println("people await because storey is not same");
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
		}
	}
	
	private void chackElevatorDirection() throws InterruptedException{
		while(people.getDirection() != elevator.getDirection()){
//			System.out.println("check slevator storey" + elevator.getCurrentStorey() +
//					" people fnal location = " + people.getFinalLocation());
//			System.out.println("--------------------------------------------------");
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
		}
		checkElevatorStoreyIn();
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
			people.setElevatorId(elevator.getElevatorId());
//			System.out.println("peopel came out in elevator elevator capacity = " + 
//					elevator.getPeopleInElevator().size());
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
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorStoreyIn();
			chackElevatorDirection();
			while(true){
				if (people.getElevatorId() == 0){
					people.move();
					moveInsideElevator();
				} else if(people.getElevatorId() != 0){
					checkElevatorStoreyOut();
					people.move();
					moveOutElevator();
				}
				Thread.sleep(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
		}
	}
}
