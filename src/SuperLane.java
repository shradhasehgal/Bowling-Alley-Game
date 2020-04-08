import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class SuperLane extends Thread {
    protected Party party;
    protected HashMap scores;
    protected Vector subscribers;
    protected boolean gameIsHalted;
    protected boolean partyAssigned;
    protected boolean gameFinished;
    protected int ball;
    protected int bowlIndex;
    protected int frameNumber;
    protected boolean tenthFrameStrike;
    protected int[][] cumulScores;
    protected Bowler currentThrower;			// = the thrower who just took a throw

    public SuperLane() {
        scores = new HashMap();
        subscribers = new Vector();
        gameIsHalted = false;
        partyAssigned = false;
    }

    /** resetScores()
     *
     * resets the scoring mechanism, must be called before scoring starts
     *
     * @pre the party has been assigned
     * @post scoring system is initialized
     */
    protected void resetScores() {
        Iterator bowlIt = (party.getMembers()).iterator();

        while ( bowlIt.hasNext() ) {
            int[] toPut = new int[25];
            for ( int i = 0; i != 25; i++){
                toPut[i] = -1;
            }
            scores.put( bowlIt.next(), toPut );
        }




        gameFinished = false;
        frameNumber = 0;
    }

    /** lanePublish()
     *
     * Method that creates and returns a newly created laneEvent
     *
     * @return		The new lane event
     */
    protected LaneEvent lanePublish() {
        LaneEvent laneEvent = new LaneEvent(party, bowlIndex, currentThrower, cumulScores, scores, frameNumber+1, ball, gameIsHalted);
        return laneEvent;
    }

    /** isPartyAssigned()
     *
     * checks if a party is assigned to this lane
     *
     * @return true if party assigned, false otherwise
     */
    public boolean isPartyAssigned() {
        return partyAssigned;
    }

    /** subscribe
     *
     * Method that will add a subscriber
     *
     * @param subscribe	Observer that is to be added
     */

    public void subscribe( LaneObserver adding ) {
        subscribers.add( adding );
    }

    /** unsubscribe
     *
     * Method that unsubscribes an observer from this object
     *
     * @param removing	The observer to be removed
     */

    public void unsubscribe( LaneObserver removing ) {
        subscribers.remove( removing );
    }

    /** publish
     *
     * Method that publishes an event to subscribers
     *
     * @param event	Event that is to be published
     */

    public void publish( LaneEvent event ) {
        if( subscribers.size() > 0 ) {
            Iterator eventIterator = subscribers.iterator();

            while ( eventIterator.hasNext() ) {
                ( (LaneObserver) eventIterator.next()).receiveLaneEvent( event );
            }
        }
    }

    /**
     * Pause the execution of this game
     */
    public void pauseGame() {
        gameIsHalted = true;
        publish(lanePublish());
    }

    /**
     * Resume the execution of this game
     */
    public void unPauseGame() {
        gameIsHalted = false;
        publish(lanePublish());
    }
}
