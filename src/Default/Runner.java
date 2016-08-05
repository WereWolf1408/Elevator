package Default;

import java.util.ArrayList;

import javax.swing.JPanel;

import people.People;
import elevator.Elevator;
import engine.HouseElevatorEngine;
import engine.PeopleEngine;
import house.House;
import house.Storey;
import variable.ConstantVariable;

public class Runner {
	
	public static void main(String[] args){
		int peopelCount = 10;
		ArrayList<Elevator> elevators = new ArrayList<>();
		ArrayList<People> peoples = new ArrayList<>();
		ArrayList<Storey> storeys = new ArrayList<>();
		ArrayList<PeopleEngine> pe = new ArrayList<>();
		ConstantVariable constantVariable = new ConstantVariable();
		
		for(int i = 0; i < constantVariable.getStoreyheight().length; i++){
			Storey storey = new Storey(constantVariable, constantVariable.getStoreyheight()[i]);
			storeys.add(storey);
		}

		elevators.add(new Elevator(constantVariable, 100, 120, "elevator1", 1));
		elevators.add(new Elevator(constantVariable, 200, 220, "elevator2", 2));
		elevators.add(new Elevator(constantVariable, 300, 320, "elevator3", 3));
		elevators.add(new Elevator(constantVariable, 400, 420, "elevator4", 4));
		
		for (int i = 0; i < peopelCount; i++){
			People people = new People(constantVariable, 10);
			people.peopleInit();
			peoples.add(people);
		}
		

		House house = new House(elevators, peoples, constantVariable, storeys);
		
		HouseElevatorEngine engine = new HouseElevatorEngine(house, elevators.get(0));
//		HouseElevatorEngine engine1 = new HouseElevatorEngine(house, elevators.get(1));
//		HouseElevatorEngine engine2 = new HouseElevatorEngine(house, elevators.get(2));
//		HouseElevatorEngine engine3 = new HouseElevatorEngine(house, elevators.get(3));

		for (int i = 0; i < peopelCount; i++){
			PeopleEngine p = new PeopleEngine(peoples.get(i), house);
			pe.add(p);
		}
		
		house.init();
		
		engine.start();
//		engine1.start();
//		engine2.start();
//		engine3.start();
		
		for (int i = 0; i < pe.size(); i++){
			pe.get(i).start();
		}
		
	}
}
