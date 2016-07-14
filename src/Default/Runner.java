package Default;

import elevator.Elevator;
import engine.HouseEngine;
import house.House;
import house.Storey;
import variable.ConstantVariable;

public class Runner {
	
	public static void main(String[] args){
		ConstantVariable constantVariable = new ConstantVariable();
		Storey storey = new Storey(constantVariable);
		Elevator elevator = new Elevator();
		System.out.println(elevator.getY());
		House house = new House(storey, elevator);
		HouseEngine engine = new HouseEngine(house);
		house.init();
		engine.run();
	}
}
