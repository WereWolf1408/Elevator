package defaultt;
import house.House;

public class Runner {
	
	public static void main(String[] args){
		BuildHouse bh = new BuildHouse(4);

		House house = new House(bh.createElevators(2), bh.createPeopele(50), bh.createHouseStorey());
		
		house.init();
		bh.createElevatorEngine(house);
		bh.createPeopleEngine(house);
		bh.buildingComplete();
	}
}
