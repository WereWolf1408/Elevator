package engine;

import javax.swing.plaf.basic.BasicTreeUI.CellEditorHandler;

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
	
	//проверка не выйдет из цикла пока лифт не окажеться на том же этаже что и человек
	private void checkElevatorStorey() throws InterruptedException{
		while(people.getStorey() != elevator.getCurrentStorey()){
			System.out.println("checkelevator storey " + people.getStorey());
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
		}
	}
	
	//проверка зашел ли человек в лифт
	private void comeInElevator() throws InterruptedException{
		if (people.getPosition() >= elevator.getElevatorInside()){
			elevator.addElevatorPeople(people);
			System.out.println("people.come in elevator" + "elevator capacity = " + elevator.getPeopleInElevator().size());
			house.getPeoples().remove(people);
			people.setElevatorId(elevator.getElevatorId());
			house.getElevatorCondition().signalAll();
			house.getWaitInElevator().await();
		}
	}
	
	private void comeOutElevator() throws InterruptedException{
		if (people.getPosition() >= 560){
			elevator.removeElevatorPeople(people);
			house.getElevatorCondition().signalAll();
			house.getTheEnd().await();
		}
	}
	
	private void checkElevatorCapacity() throws InterruptedException{
		while (elevator.getPeopleInElevator().size() >= elevator.getMAX_CAPACITY()){
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
			checkElevatorStorey();
		}
	}

	private void checkElevatorDirection() throws InterruptedException{
		while (people.getDirection() != elevator.getDirection()){
			System.out.println("check elevator direction");
			house.getElevatorCondition().signalAll();
			house.getPeopleCondition().await();
			checkElevatorStorey();
		}
	}
	
	private void  checComeOut() throws InterruptedException{
		while(people.getFinalDectination() != elevator.getCurrentStorey()){
			house.getElevatorCondition().signalAll();
			house.getWaitInElevator().await();
		}
	}
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorStorey();
			checkElevatorDirection();
//			checkElevatorCapacity();
			while(true){
				Thread.sleep(10);
				if (people.getElevatorId() == 0){
					people.move();
					comeInElevator();
				} else if (people.getElevatorId() != 0){
					checComeOut();
					comeOutElevator();
					people.move();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
		}
	}
}
