package guiComponents;

import calendar.Event;
import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddEventFrame extends JFrame {
    public AddEventFrame(String name, Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        super(name);
        setLocationRelativeTo(null);
        setVisible(true);

        JTextField eventName = new JTextField("Event Name");

        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yyyy");
        JTextField date = new JTextField(monthDayYear.format(dateModel.get(0)));

        DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("HH:mm");
        JTextField startTime = new JTextField(hourMinute.format(LocalTime.now()));
        JTextField endTime = new JTextField(hourMinute.format(LocalTime.now().plusHours(1)));

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> {
            Event event = new Event(eventName.getText(), LocalDate.parse(date.getText(), monthDayYear),
                    LocalTime.parse(startTime.getText(), hourMinute), LocalTime.parse(endTime.getText(), hourMinute));
            if (!calendar.add(event)) {
                JOptionPane.showMessageDialog(this, String.format("%s is conflicting with another event!",
                                eventName.getText()),"Conflicting Events!", JOptionPane.ERROR_MESSAGE);
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
        add(startTime);
        add(endTime);
        add(addButton);
        add(cancelButton);
        setSize(300, 125);
    }
}
