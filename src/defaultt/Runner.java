package defaultt;

import java.util.ArrayList;

import constant.ConstVariable;
import people.People;
import elevator.Elevator;
import engine.HouseElevatorEngine;
import engine.PeopleEngine;
import house.House;
import house.Storey;

public class Runner {
	
	public static void main(String[] args){
		int peopelCount = 30;
		ArrayList<Elevator> elevators = new ArrayList<>();
		ArrayList<People> peoples = new ArrayList<>();
		ArrayList<Storey> storeys = new ArrayList<>();
		ArrayList<PeopleEngine> pe = new ArrayList<>();
		ConstVariable cv = ConstVariable.getConstVariable();
		
		for(int i = 0; i <= cv.getSTOREY_COUNT(); i++){
			Storey storey = new Storey(cv.getStoreyHeight(i));
			storeys.add(storey);
		}

		elevators.add(new Elevator(100, 120, "elevator1", 530));
		elevators.add(new Elevator(200, 220, "elevator2", 0));
		elevators.add(new Elevator(300, 320, "elevator3", 300));
		
		for (int i = 0; i < peopelCount; i++){
			People people = new People(10);
			people.peopleInit();
			peoples.add(people);
		}
		

		House house = new House(elevators, peoples, cv, storeys);
		
		HouseElevatorEngine engine = new HouseElevatorEngine(house, elevators.get(0));
		HouseElevatorEngine engine1 = new HouseElevatorEngine(house, elevators.get(1));
		HouseElevatorEngine engine2 = new HouseElevatorEngine(house, elevators.get(2));

		for (int i = 0; i < peopelCount; i++){
			PeopleEngine p = new PeopleEngine(peoples.get(i), house);
			pe.add(p);
		}
		
		house.init();
		
		engine.start();
		engine1.start();
		engine2.start();
		
		for (int i = 0; i < pe.size(); i++){
			pe.get(i).start();
		}
		
	}
}
