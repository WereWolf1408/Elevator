package Default;

import java.util.ArrayList;

import elevator.Elevator;
import engine.HouseElevatorEngine;
import house.House;
import house.Storey;
import variable.ConstantVariable;

public class Runner {
	
	public static void main(String[] args){
		ArrayList<Elevator> elevators = new ArrayList<>();
		ConstantVariable constantVariable = new ConstantVariable();
		Storey storey = new Storey(constantVariable);

		elevators.add(new Elevator(constantVariable, 100, "elevator1"));
		elevators.add(new Elevator(constantVariable, 200, "elevator2"));
		elevators.add(new Elevator(constantVariable, 300, "elevator3"));
		elevators.add(new Elevator(constantVariable, 400, "elevator4"));

		House house = new House(storey, elevators);
		HouseElevatorEngine engine = new HouseElevatorEngine(house, elevators.get(0));
		HouseElevatorEngine engine1 = new HouseElevatorEngine(house, elevators.get(1));
		HouseElevatorEngine engine2 = new HouseElevatorEngine(house, elevators.get(2));
		HouseElevatorEngine engine3 = new HouseElevatorEngine(house, elevators.get(3));
		
		house.init();
		engine.start();
		engine1.start();
		engine2.start();
		engine3.start();
	}
}
