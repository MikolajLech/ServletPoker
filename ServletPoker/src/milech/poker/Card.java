package milech.poker;
import java.lang.IllegalArgumentException;

public class Card {
	private Value mValue;
	private	Suite mSuite;
	private Value convValue(String strValue) {
		switch(strValue) {
		case "2":
			return Value.DEUCE;
		case "3":
			return Value.THREE;
		case "4":
			return Value.FOUR;
		case "5":
			return Value.FIVE;
		case "6":
			return Value.SIX;
		case "7":
			return Value.SEVEN;
		case "8":
			return Value.EIGHT;
		case "9":
			return Value.NINE;
		case "T":
			return Value.TEN;
		case "J":
			return Value.JACK;
		case "Q":
			return Value.QUEEN;
		case "K":
			return Value.KING;
		case "A":
			return Value.ACE;
		default:
			throw new IllegalArgumentException("BadCardValue: " + strValue);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mSuite == null) ? 0 : mSuite.hashCode());
		result = prime * result + ((mValue == null) ? 0 : mValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (mSuite != other.mSuite)
			return false;
		if (mValue != other.mValue)
			return false;
		return true;
	}

	private Suite convSuite(String strSuite) {
		switch(strSuite) {
		case "S":
			return Suite.SPADES;
		case "H":
			return Suite.HEARTS;
		case "D":
			return Suite.DIAMONDS;
		case "C":
			return Suite.CLUBS;
		default:
			throw new IllegalArgumentException("BadCardSuite: " + strSuite);
		}
	}
	public Card(String inCard) {
		try {
			mValue = convValue(inCard.substring(0, 1));
			mSuite = convSuite(inCard.substring(1,2));
		}
		catch(IllegalArgumentException exception) {
			System.out.println(exception);
		}
	}
	public void prtCard() {
		System.out.printf("%-15s", mValue + " " + mSuite);
	}
	
	public String toString() {
		return String.format("%15s", mValue + " " + mSuite);
	}
	public void setSuite(Suite suite) {
		mSuite = suite;
	}
	public void setValue(Value value) {
		mValue = value;
	}
	public Suite getSuite() {
		return mSuite;
	}
	public Value getValue() {
		return mValue;
	}
}
