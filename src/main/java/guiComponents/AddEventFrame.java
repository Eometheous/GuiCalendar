package guiComponents;

import calendar.Event;
import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A frame that adds an event to the calendar.
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.230423
 */
public class AddEventFrame extends JFrame {

    public static final int SELECTED_DATE = 0;

    /**
     * Constructor for this frame. It takes in two models and the calendar the events are being added too.
     * @param dateModel     model containing the selected date
     * @param stringModel   model containing the string
     * @param calendar      calendar to add the event to
     */
    public AddEventFrame(Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        super("Creating Event");
        setLocationRelativeTo(null);
        setVisible(true);

        JTextField eventName = new JTextField("Event Name");

        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yyyy");
        JTextField date = new JTextField(monthDayYear.format(dateModel.get(SELECTED_DATE)));

        DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("H:mm");
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
                if (dateModel.get(SELECTED_DATE).equals(LocalDate.now())) stringModel.update(SELECTED_DATE,calendar.displayTodaysEvents());
                else stringModel.update(SELECTED_DATE,calendar.displaySelectedDay());
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
