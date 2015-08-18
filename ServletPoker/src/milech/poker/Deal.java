package milech.poker;
import java.util.ArrayList;
import java.util.Collections;

public class Deal {
	private int mHandsNum;
	private ArrayList<Hand> mHands = new ArrayList<Hand>();
    public void loadDeal(String inDeal) throws Exception {
    	mHands.clear(); // if array was previously used
    	int handLength = inDeal.length() / mHandsNum + 1;
    	String inHand;
    	int k = 0;
        for(int i = 0; i < mHandsNum; i++) {
        	if(i == 1)
    			inHand = inDeal.substring(k, k + handLength-1);
        	else
        		inHand = inDeal.substring(k, k + handLength); // change 15 to handLength
        	mHands.add(new Hand(inHand));
        	k += handLength;
        }
	}
    public Deal(int inHandsNum) {
    	mHandsNum = inHandsNum;
    }
	public void prtDeal() {
		for(Hand hand : mHands) {
			hand.prtHand();
			System.out.print("\t");
		}
	}
	public void sortHands() {
		Collections.sort(mHands, Collections.reverseOrder(new HandComparator()));
	}
	public Hand getBestHand() {
		sortHands();
		return mHands.get(1);
	}
	public Hand getHand(int i) {
		return mHands.get(i);
	}
}
