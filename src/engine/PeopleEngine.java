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
	}
	
	//проверка не выйдет из цикла пока лифт не окажеться на том же этаже что и человек
	private void checkElevatorStorey() throws InterruptedException{
		while(elevator == null){
			elevator = house.getCurElevatorStorey().get(people.getStorey());
			if(elevator == null){
				house.getElevatorCondition().signalAll();
				house.getPeopleCondition().await();
			}
		}
	}
	
	//проверка зашел ли человек в лифт
	private void peopleInElevator(int position) throws InterruptedException{
		if (position >= elevator.getElevatorInside()){
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
		}
	}
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorStorey();
			int peoplePosition = people.getStartPosition();
			while(true){
				Thread.sleep(10);
				people.move(peoplePosition++);
				peopleInElevator(peoplePosition);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
		}
	}
}
