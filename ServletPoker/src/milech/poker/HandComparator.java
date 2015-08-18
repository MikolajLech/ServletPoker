package milech.poker;
import java.util.Comparator;

public class HandComparator implements Comparator<Hand> { // when Comparator is raw, compare() expects Object arguments
	private CardHistogram mCardHist = new CardHistogram();
	private CardHistogram mCardHist2 = new CardHistogram();
	private HistEvaluator mHistEval = new HistEvaluator();
	//private HandConfig mHandConfig;

	@Override
	public int compare(Hand hand1, Hand hand2) {
		int result = 0;
		hand1.sortHand();
		fillCardHistogram(mCardHist, hand1);
		//mCardHist.prtHist();
		//System.out.println(mHistEval.evalConfType(mCardHist, hand1));
		hand2.sortHand();
		fillCardHistogram(mCardHist2, hand2);
		//System.out.println();
		//mCardHist2.prtHist();
		//System.out.println(mHistEval.evalConfType(mCardHist2, hand2));
		//System.out.println((mHistEval.evalConfType(mCardHist, hand1)).getValue() - (mHistEval.evalConfType(mCardHist2, hand2)).getValue());
		HandConfig hand1Conf = mHistEval.evalConfType(mCardHist, hand1);
		HandConfig hand2Conf = mHistEval.evalConfType(mCardHist2, hand2);
		System.out.println(hand1Conf + " ? " + hand2Conf);
		if(equalHands())
			result = 0;
		else if(hand1Conf == hand2Conf ) 
			result = compSameTypeConfigs(hand1Conf, hand1, hand2);
		else 
			result = ((mHistEval.evalConfType(mCardHist, hand1)).getValue() - (mHistEval.evalConfType(mCardHist2, hand2).getValue()));
		mCardHist.clearHist();
		mCardHist2.clearHist();
		return result;
	}
	private int compSameTypeConfigs(HandConfig handConf, Hand hand1, Hand hand2) {
		switch(handConf) {
			case STRAIGHTFLUSH:
				return compStraightFlush(hand1, hand2);
			case FOUROFKIND: 
				return compFourOfKind(hand1, hand2);
			case FULLHOUSE:
				return compFullHouse(hand1, hand2);
			case FLUSH:
				return compFlush(hand1, hand2);
			case STRAIGHT:
				return compStraight(hand1, hand2);
			case THREEOFKIND:
				return compThreeOfKind(hand1, hand2);
			case TWOPAIRS:
				return compTwoPairs(hand1, hand2);
			case ONEPAIR:
				return compOnePair(hand1, hand2);
			case HIGHCARD:
				return compHighCard(hand1, hand2);
			default:
				return 100;
		}
	}
	private int compStraightFlush(Hand hand1, Hand hand2) {
		return hand1.getCardIntVal(0) - hand2.getCardIntVal(0);
	}
	private int compFourOfKind(Hand hand1, Hand hand2) {
		// TODO Auto-generated method stub
		return 0;
	}
	private int compFullHouse(Hand hand1, Hand hand2) {
		if(mCardHist.getTripletRank() > mCardHist2.getTripletRank())
			return 10;		
		else if(mCardHist.getTripletRank() == mCardHist2.getTripletRank() && mCardHist.getPairRank() > mCardHist2.getPairRank())
			return 10;
		return -10;
	}
	private int compFlush(Hand hand1, Hand hand2) {
		for(int i = 0; i < hand1.handSize(); i++) {
			if(hand1.getCardIntVal(i) > hand2.getCardIntVal(i))
				return 10;
			else if(hand1.getCardIntVal(i) < hand2.getCardIntVal(i))
				return -10;
		}
		return 0;
	}
	private int compStraight(Hand hand1, Hand hand2) {
		return hand1.getCardIntVal(0) - hand2.getCardIntVal(0);
	}
	private int compThreeOfKind(Hand hand1, Hand hand2) {
		if(mCardHist.getTripletRank() > mCardHist2.getTripletRank())
			return 10;
		else if(mCardHist.getTripletRank() == mCardHist2.getTripletRank() && firstHandHasHigherCard())
			return 10;		
		return -10;
	}
	private int compTwoPairs(Hand hand1, Hand hand2) {
		// TODO Auto-generated method stub
		return 0;
	}
	private int compOnePair(Hand hand1, Hand hand2) {
		if(mCardHist.getPairRank() > mCardHist2.getPairRank())
			return 10;	
		else if(mCardHist.getPairRank() == mCardHist2.getPairRank() && firstHandHasHigherCard())
			return 10;
		return -10;
	}
	private int compHighCard(Hand hand1, Hand hand2) {
		for(int i = 0; i < hand1.handSize(); i++) {
			if(hand1.getCardIntVal(i) > hand2.getCardIntVal(i))
				return 10;
			else if(hand1.getCardIntVal(i) < hand2.getCardIntVal(i))
				return -10;
		}
		return 0;
	}
	
	private boolean firstHandHasHigherCard() {
		for(int i = 0; i < mCardHist.getHistSize(); i++) {
			if(mCardHist.getField(i) > mCardHist2.getField(i))
				return true;
			else if(mCardHist.getField(i) < mCardHist2.getField(i))
				return false;
		}
		return false;
	}
	private boolean equalHands() {
		for(int i = 0; i < mCardHist.getHistSize(); i++) {
			if(mCardHist.getField(i) != mCardHist2.getField(i))
				return false;
		}
		return true;
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
}

