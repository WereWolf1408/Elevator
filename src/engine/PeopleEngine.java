package engine;



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
	
	private void moveInsideElevator(Elevator elevator) throws InterruptedException{
		while (true){
			if(people.getPosition() >= elevator.getElevatorInside()){
				house.getLock().lock();
				try{
					elevator.decGoIn();
					house.getElevatorCondition(people.getStartLocation()).signalAll();
					house.getPeopelWaitInElevator(elevator.getId()).await();
					checkPeopleMoveOut(elevator);
				}finally{
					house.getLock().unlock();
				}
			}
			people.move();
			Thread.sleep(2);
		}
	}
	
	private void checkPeopleMoveOut(Elevator elevator) throws InterruptedException{
		while(people.getFinalLocation() != elevator.getCurrentStorey()){
			house.getElevatorCondition(elevator.getCurrentStorey()).signalAll();
			house.getPeopelWaitInElevator(elevator.getId()).await();
		}
		moveOutElevator(elevator);
	}
	
	private void moveOutElevator(Elevator elevator) throws InterruptedException{
		try{
			elevator.getPeopleInElevator().remove(people);
			elevator.incGoOut();
			house.getElevatorCondition(people.getFinalLocation()).signalAll();
		}finally{
			house.getLock().unlock();
		}
		finalDestination(elevator);
	}
	
	private void finalDestination(Elevator elevator) throws InterruptedException{
		while(true){
			if(people.getPosition() >= 500){
				house.getLock().lock();
				elevator.decGoOut();
				house.getElevatorCondition(people.getFinalLocation()).signalAll();
				house.getTheEnd().await();
			}
			people.move();
			Thread.sleep(2);
		}
	}
	
	private void checkElevatorOptions() throws InterruptedException{
		while(true){
			for (Elevator elevator : house.getElevators()){
				if(people.getStartLocation() == elevator.getCurrentStorey() &&
						people.getDirection() == elevator.getDirection() &&
						elevator.getMAX_CAPACITY() > elevator.getPeopleInElevator().size()){
					elevator.incGoIn();
					elevator.getPeopleInElevator().add(people);
					house.getPeoples().remove(people);
					house.getStoreys(elevator.getCurrentStorey()).removePeople();
					house.getLock().unlock();
					moveInsideElevator(elevator);
				}
			}
			house.getElevatorCondition(people.getStartLocation()).signalAll();
			house.getPeopleCondition(people.getStartLocation()).await();
		}
	}
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorOptions();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			house.getLock().unlock();
		}
	}
}
