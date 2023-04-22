package guiComponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DayViewPanel extends JPanel implements ChangeListener {
    Model<String> stringModel;
    JTextArea textArea;
    public DayViewPanel(Model<String> stringModel) {
        this.stringModel = stringModel;
        textArea = new JTextArea(20, 45);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        textArea.setText(stringModel.get(0));
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jScrollPane);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        textArea.setText(stringModel.get(0));
    }
}
