package people;

import java.awt.Color;

import javax.swing.JPanel;

import variable.ConstantVariable;

public class People {
	private ConstantVariable cv;
	private JPanel people = null;
	//этаж на котором появился человечек
	private int storey;
	//направление движения вверх(0)/низ(-1)
	private int direction;
	//этаж на который нужно человечку
	private int finalDectination = 0;
	private int y=0;
	private int startPosition;
	
	{
		this.direction = (int)(Math.random()*2)-1;
		this.storey = (int)(Math.random()*3);
		setFinalDestination();
	}

	public People(ConstantVariable cv, int startPosition){
		this.cv = cv;
		this.startPosition = startPosition;
		y = cv.getStoreyheight()[storey];
		people = new JPanel();
		people.setBounds(0, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
		people.setBackground(Color.blue);
		System.out.println("peopel start location = " + storey);
		System.out.println("peopel end location = " + finalDectination);
		System.out.println("peopel direction = " + direction);
	}
	
	public void move(int x){
		people.setBounds(x, y-20, cv.getPEOPLE_HEIGHT(), cv.getPEOPLE_WIDTH());
	}
	
	//проверка что бы чел не содился и выходил на одном и том же этаже
	private void setFinalDestination(){
		finalDectination = (int)(Math.random()*3);
		if (storey == finalDectination){
			if (direction == 0 && finalDectination > 0){
				finalDectination--;
			} else {
				direction = -1;
				finalDectination++;
			}
		}else if(direction == -1){
			if(storey == finalDectination && finalDectination < cv.getSTOREY_COUNT()){
				finalDectination++;
			}else{
				direction = 0;
				finalDectination--;
			}
		}
	}
	
	public JPanel getPeople(){
		return people;
	}
	
	public int getStartPosition() {
		return startPosition;
	}

	public int getStorey() {
		return storey;
	}

	public int getDirection() {
		return direction;
	}

	public int getFinalDectination() {
		return finalDectination;
	}
}
