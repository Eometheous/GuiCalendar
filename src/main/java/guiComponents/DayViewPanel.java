package guiComponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * The day view of the calendar which contains a text area
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.230423
 */
public class DayViewPanel extends JPanel implements ChangeListener {
    Model<String> stringModel;
    JTextArea textArea;

    /**
     * The constructor for this panel
     * @param stringModel   model containing the string for the day view
     */
    public DayViewPanel(Model<String> stringModel) {
        this.stringModel = stringModel;
        textArea = new JTextArea(20, 45);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        textArea.setText(stringModel.get(0));
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jScrollPane);
    }

    /**
     * Updates the text area with the text from the string model
     * @param e  a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        textArea.setText(stringModel.get(0));
    }
}
