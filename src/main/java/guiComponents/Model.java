package guiComponents;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * This is the model of the MVC model. It holds data and
 * listeners so that the data can be viewed in many ways.
 * @param <T> The type of data that will be stored
 * @author Jonathan Stewart Thomas
 * @version 1.1.0.230422
 */
public class Model<T> {
    private final ArrayList<T> dataList;
    private final ArrayList<ChangeListener> listeners;

    /**
     * A model which holds data to be used by viewers and updated by controllers
     */
    public Model() {
        dataList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    /**
     * Adds data to the model
     * @param data  the data being added
     */
    public void add(T data) {
        dataList.add(data);
    }

    /**
     * Attaches ChangeListeners to the model
     * @param listener  the ChangeListener
     */
    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Updates the data at a specific index and pings the ChangeListeners
     * @param position  the index of the data being updated
     * @param updated   the new information
     */
    public void update(int position, T updated) {
        dataList.set(position, updated);
        pingListeners();
    }

    /**
     * Pings the ChangeListeners
     */
    public void pingListeners() {
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Gets the data at a specific index
     * @param index the index of the data
     * @return      the data
     */
    public T get(int index) {return dataList.get(index);}
}
