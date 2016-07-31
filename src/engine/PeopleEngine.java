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
	
	//проверка не выйдет из цикла пока лифт не окажеться на том же этаже что и человек
	private void checkElevatorStorey() throws InterruptedException{
		Elevator elevator = null;
		while(elevator == null){
			elevator = house.getCurElevatorStorey().get(people.getStorey());
			if(elevator == null){
				house.getPeopleCondition().await();
			}
		}
	}
	
	@Override
	public void run (){
		house.getLock().lock();
		try {
			checkElevatorStorey();
			int x = 10;
			int a = 0;
			while(true){
				if (a == 200){
					house.getPeopleCondition().await();
				}
				a++;
				Thread.sleep(10);
				people.move(x++);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			house.getLock().unlock();
		}
	}
}
