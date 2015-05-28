package net.peakgames.pisti.bot;

import net.peakgames.pisti.deck.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mertysn on 28.05.2015.
 */
public class CaseStudyBot implements Bot {
    private List<Card> hand;
    private Card lastDiscardCard;
    private int seat;
    private boolean[] cardPlayed;
    private HashMap<Integer, Integer> priority;
    private boolean danger; // There can be a Pişti

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {
        danger = false;
        priority = new HashMap<Integer, Integer>();
        initializePriority();
        cardPlayed = new boolean[52];
        for(Card c : discardPile){
            int offset = calculateOffset(c);
            for(int i = 0; i<4; i++){
                priority.put((i*13+offset)%52, priority.get((i*13+offset)%52) - 1);
            }
            cardPlayed(c);
        }
        lastDiscardCard = discardPile.get(0);
        this.seat = seat;
    }

    @Override
    public void dealt(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void played(int seat, Card card) {
        danger = false;
        int offset = calculateOffset(card);
        for(int i = 0; i<4; i++){
            priority.put((i*13+offset)%52, priority.get((i*13+offset)%52) - 1);
        }
        cardPlayed(card);
        lastDiscardCard = card;
    }

    @Override
    public Card play() {
        if(danger){
            Card c = determineLeastCard(hand);
            return hand.remove(hand.indexOf(c));
        } else {
            List<Card> sameValue = new ArrayList<Card>();

            int value = lastDiscardCard.getValue();
            for(int i = 0; i < hand.size(); i++){
                if(hand.get(i).getValue() == value){
                    sameValue.add(hand.get(i));
                }
            }

            if(!sameValue.isEmpty()){
                Card c;
                for(int i = 0; i < sameValue.size(); i++){
                    if(sameValue.get(i).getType() == Card.Type.DIAMONDS || sameValue.get(i).getType() == Card.Type.CLUBS){
                        c = sameValue.get(i);
                        return hand.remove(hand.indexOf(c));
                    }
                }
                return hand.remove(hand.indexOf(sameValue.get(0)));
            } else {
                Card c = determineCard(hand);
                return hand.remove(hand.indexOf(c));
            }
        }
    }

    @Override
    public void collected(int seat, List<Card> cards) {
        danger = true;
    }

    private void cardPlayed(Card c){
        int offset = calculateOffset(c);
        offset += c.getValue();
        cardPlayed[offset-1] = true;
    }

    private int calculateOffset(Card c){
        int offset = 0;
        switch(c.getType()){
            case SPADES:
                offset = 0;
                break;
            case HEARTS:
                offset = 13;
                break;
            case DIAMONDS:
                offset = 26;
                break;
            case CLUBS:
                offset = 39;
                break;
        }
        return offset;
    }

    private int timesPlayed(Card c){
        int count = 0;
        int value = c.getValue();
        for(int i=0; i<4; i++){
            if(cardPlayed[i * 13 + value - 1]){
                count ++;
            }
        }
        return count;
    }

    private void initializePriority(){
        for(int i = 0; i<13; i++){
            priority.put(i, 4);
            priority.put(i+13, 4);
            priority.put(i+26, 14);
            priority.put(i+39, 10);
        }
        for(int i = 0; i<4; i++ ){
            priority.put(i*13+1, priority.get(i*13+1) + 5);
            priority.put(i*13+10, priority.get(i*13+10) + 7);
        }

    }

    private Card determineCard(List<Card> hand){
        Card minCard = hand.get(0);
        int minPriority = 0;
        for(int i = 1; i < hand.size(); i++){
            Card c = hand.get(i);
            int pr = priority.get(calculateOffset(c));
            if(minPriority > pr){
                minCard = c;
                minPriority = pr;
            }
        }
        return minCard;
    }

    private Card determineLeastCard(List<Card> hand){
        Card c;
        int numTimesPlayed;
        c = hand.get(0);
        numTimesPlayed = timesPlayed(c);
        for(int i = 1; i < hand.size(); i++){
            Card x = hand.get(i);
            int val = timesPlayed(x);
            if(val > numTimesPlayed){
                c = x;
                numTimesPlayed = val;
            }
        }
        return c;
    }
}
