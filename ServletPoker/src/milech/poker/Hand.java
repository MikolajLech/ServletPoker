package milech.poker;
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	private ArrayList<Card> mCards = new ArrayList<Card>();

	public Hand(String inHand) throws Exception {
		String inCard;
		int k = 0;
		for(int i = 0; i < 5; i++) {
			if(i == 4 )
				inCard = inHand.substring(k, k + 2);
			else
				inCard = inHand.substring(k, k + 3);
			if(mCards.contains(new Card(inCard))) {
				System.out.println("Wrong hand. Duplicates.");
				mCards.clear();
				throw(new Exception("Wrong hand. Duplicates"));
			}
			mCards.add(new Card(inCard));
			k += 3;
		}
	}
	
	public void prtHand() {
		for(Card card : mCards) {
			card.prtCard();
			System.out.print(" ");
		}
	}
	public void sortHand() {
		Collections.sort(mCards, new CardValueComparator());
	}
	public int handSize() {
		return mCards.size();
	}
	public Value getCardEnumVal(int i) {
		return mCards.get(i).getValue();
	}
	public int getCardIntVal(int i) {
		return mCards.get(i).getValue().getValue();
	}
	public Suite getCardEnumSuite(int i) {
		return mCards.get(i).getSuite();
	}
	
	public String toString() {
		String resultStr = "";
		for(Card card : mCards) {
			resultStr += card.toString();
		}
		return resultStr;
	}
}
