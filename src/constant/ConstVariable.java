package constant;

public class ConstVariable {
	private static ConstVariable cv;
	private final int STOREY_COUNT = 5;
	private final int STOREY_LINE_HEIGHT = 2;
	private final int STOREY_HEIGHT = 120;
	private final int FRAME_HEIGHT = 600;
	private final int FRAME_WIDTH = STOREY_COUNT * STOREY_HEIGHT + STOREY_HEIGHT;
	private final int STOREY_WIDTH = FRAME_WIDTH;
	private final int ELEVATOR_HEIGHT = 50;
	private final int ELEVATOR_WIDTH = 60;
	private final int PEOPLE_HEIGHT = 20;
	private final int PEOPLE_WIDTH = 20;
	
	private ConstVariable(){}
	
	public static ConstVariable getConstVariable(){
		if(cv == null){
			cv = new ConstVariable();
		}
		return cv;
	}
	
	public int getStoreyHeight(int storey){
		if (storey == 0){
			storey = 1;
		}
		return STOREY_HEIGHT * storey;
	}
	public int getPEOPLE_HEIGHT() {
		return PEOPLE_HEIGHT;
	}
	public int getSTOREY_COUNT() {
		return STOREY_COUNT;
	}
	public int getPEOPLE_WIDTH() {
		return PEOPLE_WIDTH;
	}
	public int getELEVATOR_HEIGHT() {
		return ELEVATOR_HEIGHT;
	}
	public int getELEVATOR_WIDTH() {
		return ELEVATOR_WIDTH;
	}
	public int getSTOREY_HEIGHT() {
		return STOREY_LINE_HEIGHT;
	}
	public int getFRAME_HEIGHT() {
		return FRAME_HEIGHT;
	}
	public int getFRAME_WIDTH() {
		return FRAME_WIDTH;
	}
	public int getSTOREY_WIDTH() {
		return STOREY_WIDTH;
	}
}
