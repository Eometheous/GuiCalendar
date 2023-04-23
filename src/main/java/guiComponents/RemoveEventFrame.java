package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RemoveEventFrame extends JFrame {
    public RemoveEventFrame(String name, Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        super(name);
        setLocationRelativeTo(null);
        setVisible(true);

        JTextField eventName = new JTextField("Event Name");

        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yyyy");
        JTextField date = new JTextField(monthDayYear.format(dateModel.get(0)));

        JButton removeButton = new JButton("Remove Event");
        removeButton.addActionListener(e -> {
            if (!calendar.deleteEvent(eventName.getText(), LocalDate.parse(date.getText(),monthDayYear))) {
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

        setLayout(new GridLayout(0, 2, 10, 10));

        add(eventName);
        add(date);
        add(removeButton);
        add(cancelButton);
        setSize(300, 125);
    }
}
