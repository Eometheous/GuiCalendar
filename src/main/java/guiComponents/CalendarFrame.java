package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CalendarFrame extends JFrame {
    public CalendarFrame(MyCalendar calendar) {

        Model<LocalDate> dateModel = new Model<>();
        Model<String> stringModel = new Model<>();
        stringModel.add(calendar.displayTodaysEvents());
        setLayout(new BorderLayout());

        MonthViewPanel monthViewPanel = new MonthViewPanel(dateModel, stringModel, calendar);
        add(monthViewPanel, BorderLayout.WEST);

        DayViewPanel dayViewPanel = new DayViewPanel(stringModel);
        stringModel.attach(dayViewPanel);
        add(dayViewPanel, BorderLayout.EAST);

        JMenuBar menuBar = new JMenuBar();
        JMenu eventsMenu = new JMenu("Events");
        menuBar.add(eventsMenu);

        JMenuItem addEventButton = new JMenuItem("Add Event");
        eventsMenu.add(addEventButton);

        JMenuItem removeEventButton = new JMenuItem("Remove Event");
        eventsMenu.add(removeEventButton);



        add(menuBar, BorderLayout.NORTH);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
