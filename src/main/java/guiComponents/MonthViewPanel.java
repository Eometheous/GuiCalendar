package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.time.LocalDate;

/**
 * The month view for the calendar which contains buttons for each day of the month, buttons to change the date, and a
 * label for displaying the month and year
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.230423
 */
public class MonthViewPanel extends JPanel {
    /**
     * The constructor for this panel
     * @param dateModel     model containing the selected date
     * @param stringModel   model containing the string for the day view
     * @param calendar      the calendar being used
     */
    public MonthViewPanel(Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        dateModel.add(calendar.getToday());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        MonthViewControllerPanel monthViewControllerPanel = new MonthViewControllerPanel(calendar, dateModel, stringModel);
        add(monthViewControllerPanel);

        MonthViewTextPanel monthViewTextPanel = new MonthViewTextPanel(dateModel);
        add(monthViewTextPanel);
        dateModel.attach(monthViewTextPanel);

        MonthDaysPanel monthDaysPanel = new MonthDaysPanel(calendar, dateModel, stringModel);
        add(monthDaysPanel);
        dateModel.attach(monthDaysPanel);
    }
}
