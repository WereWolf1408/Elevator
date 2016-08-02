package variable;

public class ConstantVariable {
	private final int STOREY_HEIGHT = 2;
	private final int FRAME_HEIGHT = 600;
	private final int FRAME_WIDTH = 600;
	private final int STOREY_WIDTH = FRAME_WIDTH;
	private final int ELEVATOR_HEIGHT = 50;
	private final int ELEVATOR_WIDTH = 60;
	private final int PEOPLE_HEIGHT = 20;
	private final int PEOPLE_WIDTH = 20;
	private final int STOREY_COUNT = 4;
	private int[] storeyheight = {120, 270, 420, 570, 720};
	
	
	public int[] getStoreyheight() {
		return storeyheight;
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
		return STOREY_HEIGHT;
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
