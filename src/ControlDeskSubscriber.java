import java.util.Iterator;
import java.util.Vector;

public class ControlDeskSubscriber {

    /** The collection of subscribers */
    private Vector subscribers;

    public ControlDeskSubscriber(){
        subscribers = new Vector();
    }

    public void subscribe(ControlDeskObserver adding) {
        subscribers.add(adding);
    }

    public void publish(ControlDeskEvent event) {
        for (Object subscriber : subscribers) {
            ((ControlDeskObserver) subscriber).receiveControlDeskEvent(event);
        }
    }
}
