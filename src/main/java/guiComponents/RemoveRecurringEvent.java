package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RemoveRecurringEvent extends JFrame{
    public RemoveRecurringEvent(String name, Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        super(name);
        setLocationRelativeTo(null);
        setVisible(true);

        JTextField eventName = new JTextField("Event Name");

        JButton removeButton = new JButton("Remove Recurring Event");
        removeButton.addActionListener(e -> {
            if (!calendar.deleteRecurringEvent(eventName.getText())) {
                JOptionPane.showMessageDialog(this, String.format("Could not find event \"%s\" in the Calendar!",
                        eventName.getText()),"Event Not Found!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                dateModel.pingListeners();
                if (dateModel.get(0).equals(LocalDate.now())) stringModel.update(0,calendar.displayTodaysEvents());
                else stringModel.update(0,calendar.displaySelectedDay());
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        setLayout(new GridLayout(3, 0, 10, 10));

        add(eventName);
        add(removeButton);
        add(cancelButton);
        setSize(400, 125);
    }
}
