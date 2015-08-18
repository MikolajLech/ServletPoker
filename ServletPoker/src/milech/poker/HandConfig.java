package milech.poker;

public enum HandConfig {
	STRAIGHTFLUSH(9), FOUROFKIND(8), FULLHOUSE(7), FLUSH(6), STRAIGHT(5), THREEOFKIND(4), TWOPAIRS(3), ONEPAIR(2), HIGHCARD(1);
	private int value;
	private HandConfig(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}