package guiComponents;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * This is the model of the MVC model. It holds data and
 * listeners so that the data can be viewed in many ways.
 * @param <T> The type of data that will be stored
 * @author Jonathan Stewart Thomas
 * @version 1.0.1.230413
 */
public class Model<T> {
    private final ArrayList<T> dataList;
    private final ArrayList<ChangeListener> listeners;
    public Model() {
        dataList = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    public void add(T data) {
        dataList.add(data);
    }
    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }
    public void update(int position, T updated) {
        dataList.set(position, updated);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
    public T get(int index) {return dataList.get(index);}
}
