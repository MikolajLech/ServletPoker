package milech.poker;

public enum Suite {
	SPADES(4), HEARTS(3), DIAMONDS(2), CLUBS(1); // spades, hearts, diamonds, clubs
	private int suite;
	private Suite(int suite) {
		this.suite = suite;
	}
	public int getSuite() {
		return suite;
	}
}
