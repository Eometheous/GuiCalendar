package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.time.LocalDate;

public class MonthViewPanel extends JPanel {
    public MonthViewPanel(Model<LocalDate> dateModel, Model<String> stringModel, MyCalendar calendar) {
        dateModel.add(calendar.getToday());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        ButtonsPanel buttonsPanel = new ButtonsPanel(calendar, dateModel, stringModel);
        add(buttonsPanel);

        MonthViewTextPanel monthViewTextPanel = new MonthViewTextPanel(dateModel);
        add(monthViewTextPanel);
        dateModel.attach(monthViewTextPanel);

        MonthDaysPanel monthDaysPanel = new MonthDaysPanel(calendar, dateModel, stringModel);
        add(monthDaysPanel);
        dateModel.attach(monthDaysPanel);
    }
}
