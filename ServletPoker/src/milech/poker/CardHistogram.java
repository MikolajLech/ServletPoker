package milech.poker;
import java.util.ArrayList;
import java.util.Collections;

public class CardHistogram {
	private ArrayList<Integer> mCardHistogram = new ArrayList<Integer>(); // A,K,Q,J,10,9,8,7,6,5,4,3,2
	public CardHistogram() {
		init();
	}
	
	private void init() {
		for (int i = 0; i < 13; i++) {
			mCardHistogram.add(0);
		}
	}
	
	public CardHistogram(Hand hand) {
		init();
		fillCardHistogram(this, hand);
	}
	
	private void fillCardHistogram(CardHistogram cardHist, Hand hand) {
		for(int i = 0; i < hand.handSize(); i++) {
			switch(hand.getCardEnumVal(i)) {
			case ACE:
				cardHist.incr(0);
				break;
			case KING:
				cardHist.incr(1);
				break;
			case QUEEN:
				cardHist.incr(2);
				break;
			case JACK:
				cardHist.incr(3);
				break;
			case TEN:
				cardHist.incr(4);
				break;
			case NINE:
				cardHist.incr(5);
				break;
			case EIGHT:
				cardHist.incr(6);
				break;
			case SEVEN:
				cardHist.incr(7);
				break;
			case SIX:
				cardHist.incr(8);
				break;
			case FIVE:
				cardHist.incr(9);
				break;
			case FOUR:
				cardHist.incr(10);
				break;
			case THREE:
				cardHist.incr(11);
				break;
			case DEUCE:
				cardHist.incr(12);
				break;
			default:
				throw new IllegalArgumentException("BadCardValue: " + hand.getCardEnumVal(i));
			}
		}
	}
	
	public CardHistogram(CardHistogram cardHist) {
		this.mCardHistogram = new ArrayList<Integer>(cardHist.mCardHistogram);
	}
	public void incr(int index) {
		mCardHistogram.set(index, mCardHistogram.get(index) + 1);
	}
	public void sortHist() {
		Collections.sort(mCardHistogram, Collections.reverseOrder());
	}
	public void prtHist() {
		for(int i: mCardHistogram) {
			System.out.printf(i + " ");
		}
	}
	
	public String toString() {
		String resultStr = "";
		for(int i: mCardHistogram) {
			resultStr += i + " ";
		}
		return resultStr;
	}
	public int getField(int i) {
		return mCardHistogram.get(i);
	}
	public void clearHist() {
		for(int i = 0; i < mCardHistogram.size(); i++) {
			mCardHistogram.set(i, 0);
		}
	}
	public int getTripletRank() {
		for(int i = 0; i < mCardHistogram.size(); i++) {
			if(mCardHistogram.get(i) == 3)
				return mCardHistogram.size() - i;
		}
		return 0;
	}
	public int getPairRank() {
		for(int i = 0; i < mCardHistogram.size(); i++) {
			if(mCardHistogram.get(i) == 2)
				return mCardHistogram.size() - i;
		}
		return 0;
	}
	public int getHistSize() {
		return mCardHistogram.size();
	}
}
