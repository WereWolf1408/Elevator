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
		ArrayList<Elevator> elevators = new ArrayList<>();
		ArrayList<People> peoples = new ArrayList<>();
		ArrayList<Storey> storeys = new ArrayList<>();
		ConstantVariable constantVariable = new ConstantVariable();
		
		for(int i = 0; i < constantVariable.getStoreyheight().length; i++){
			Storey storey = new Storey(constantVariable, constantVariable.getStoreyheight()[i]);
			storeys.add(storey);
		}

		elevators.add(new Elevator(constantVariable, 100, 120, "elevator1"));
		elevators.add(new Elevator(constantVariable, 200, 220, "elevator2"));
		elevators.add(new Elevator(constantVariable, 300, 320, "elevator3"));
		elevators.add(new Elevator(constantVariable, 400, 420, "elevator4"));
		
		peoples.add(new People(constantVariable, 10));
		peoples.add(new People(constantVariable, 10));

		House house = new House(elevators, peoples, constantVariable, storeys);
		
		HouseElevatorEngine engine = new HouseElevatorEngine(house, elevators.get(0));
		HouseElevatorEngine engine1 = new HouseElevatorEngine(house, elevators.get(1));
//		HouseElevatorEngine engine2 = new HouseElevatorEngine(house, elevators.get(2));
//		HouseElevatorEngine engine3 = new HouseElevatorEngine(house, elevators.get(3));
		
		PeopleEngine pe = new PeopleEngine(peoples.get(0), house);
		PeopleEngine pe1 = new PeopleEngine(peoples.get(1), house);
		
		house.init();
		
		engine.start();
//		engine1.start();
//		engine2.start();
//		engine3.start();
		
		pe.start();
		pe1.start();
	}
}
