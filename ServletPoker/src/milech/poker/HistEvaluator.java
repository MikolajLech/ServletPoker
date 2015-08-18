package milech.poker;

public class HistEvaluator {
	public HandConfig evalConfType(CardHistogram cardHist, Hand hand) {
		cardHist = new CardHistogram(cardHist);
		cardHist.sortHist();
		//System.out.println();
		int fMaxC = cardHist.getField(0); // first max count
		int sMaxC = cardHist.getField(1); // second max count
		switch(fMaxC) {
			case 4: // four of a kind (quads)
				return HandConfig.FOUROFKIND ;
			case 3: {
				if(sMaxC == 2) // full house (boat)
					return HandConfig.FULLHOUSE;
				else // three of a kind (set)	
					return HandConfig.THREEOFKIND;
			}
			case 2: {
				if(sMaxC == 2) // two pairs
					return HandConfig.TWOPAIRS;
				else // one pair
					return HandConfig.ONEPAIR;
			}
			default: {
				if(ifFlush(hand))
					if(ifStraight(hand))
						return HandConfig.STRAIGHTFLUSH; // straight flush
					else
						return HandConfig.FLUSH; // flush
				else if(ifStraight(hand))
					return HandConfig.STRAIGHT; // straight
				else if(ifWheel(hand))
					return HandConfig.STRAIGHT; // worst straight (wheel : A, 2, 3, 4, 5)
				else // highest card
					return HandConfig.HIGHCARD;
			}
		}
	}
	private boolean ifFlush(Hand hand) {
		for(int i = 0; i < hand.handSize() - 1; i++) {
			if(hand.getCardEnumSuite(i) != hand.getCardEnumSuite(i+1))
				return false;
		}
		return true;
	}
	private boolean ifStraight(Hand hand) {
		if((hand.getCardIntVal(0) - hand.getCardIntVal(4)) == 4)
			return true;
		return false;
	}
	private boolean ifWheel(Hand hand) {
		if(hand.getCardEnumVal(0) == Value.ACE && hand.getCardEnumVal(1) == Value.FIVE)
			return true;
		return false;
	}

}
