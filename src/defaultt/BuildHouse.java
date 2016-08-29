package defaultt;

import java.util.ArrayList;

import constant.ConstVariable;
import elevator.Elevator;
import engine.HouseElevatorEngine;
import engine.PeopleEngine;
import house.House;
import house.Storey;
import people.People;

public class BuildHouse {
	ConstVariable cv = ConstVariable.getConstVariable();
	ArrayList<Storey> storeys = new ArrayList<>();
	ArrayList<Elevator> elevators = new ArrayList<>();
	ArrayList<People> peoples = new ArrayList<>();
	ArrayList<HouseElevatorEngine> elevatorEngines = new ArrayList<>();
	ArrayList<PeopleEngine> peopleEngines = new ArrayList<>();
	
	public BuildHouse(int storeyCount){
		cv.setHouseStorey(storeyCount);
	}
	
	public ArrayList<Storey> createHouseStorey(){
		for(int i = 0; i <= cv.getSTOREY_COUNT(); i++){
			Storey storey = new Storey(cv.getStoreyHeight(i));
			storeys.add(storey);
		}
		System.out.println("house ctorey count is " + storeys.size());
		return storeys;
	}
	
	public ArrayList<Elevator> createElevators(int count){
		String elevatorName = null;
		for(int i = 0; i < count; i++){
			elevatorName = "Elevator" + i;
			elevators.add(new Elevator(elevatorName));
		}
		return elevators;
	}
	
	public ArrayList<People> createPeopele(int count){
		People people;
		for (int i = 0; i < count; i++){
			people = new People();
			people.peopleInit();
			peoples.add(people);
		}
		return peoples;
	}
	
	public ArrayList<HouseElevatorEngine> createElevatorEngine(House house){
		for (Elevator elevator : elevators){
			elevatorEngines.add(new HouseElevatorEngine(house, elevator));
		}
		return elevatorEngines;
	}
	
	public ArrayList<PeopleEngine> createPeopleEngine(House house){
		for (People people: peoples){
			peopleEngines.add(new PeopleEngine(people, house));
		}
		return peopleEngines;
	}
	
	private void runElevatorEngine(){
		for (HouseElevatorEngine e : elevatorEngines){
			e.start();
		}
	}
	
	private void runPeopleEngine(){
		for (PeopleEngine p : peopleEngines){
			p.start();
		}
	}
	
	public void buildingComplete(){
		runElevatorEngine();
		runPeopleEngine();
	}
}
