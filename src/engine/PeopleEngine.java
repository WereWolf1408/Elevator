package engine;

import people.People;

public class PeopleEngine extends Thread {
	private People people;
	
	public PeopleEngine(People people){
		this.people = people;
	}
	
	
	private void peopleMove(int x){
		people.move(x);
	}
	
	@Override
	public void run (){
		int x = 10;
		while(true){
			try {
				Thread.sleep(10);
				peopleMove(x += 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
