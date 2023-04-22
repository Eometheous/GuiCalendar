package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarFrame extends JFrame {
    public CalendarFrame(MyCalendar calendar) {
        super("My Calendar");
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
        addEventButton.addActionListener(e -> new AddEventFrame("Creating Event", dateModel, stringModel, calendar));

        JMenuItem removeEventButton = new JMenuItem("Remove Event");
        eventsMenu.add(removeEventButton);

        JMenu navigateMenu = new JMenu("Navigate");
        JMenuItem goToToday = new JMenuItem("Go To Today");
        goToToday.addActionListener(e -> {
            dateModel.update(0, LocalDate.now());
            stringModel.update(0,calendar.displaySelectedDay());
        });
        navigateMenu.add(goToToday);

        JMenuItem goToDate = new JMenuItem("Go To...");
        goToDate.addActionListener(e -> {
            DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yyyy");
            String stringDate = JOptionPane.showInputDialog(this, "Enter Date (mm/dd/yyyy): ");
            if (stringDate != null) {
                LocalDate date = LocalDate.parse(stringDate, monthDayYear);
                dateModel.update(0, date);
                stringModel.update(0, calendar.displaySelectedDay());
            }
        });
        navigateMenu.add(add(goToDate));

        JMenu view = new JMenu("View");

        JMenuItem viewAllEvents = new JMenuItem("View All Events");
        viewAllEvents.addActionListener(e -> stringModel.update(0,calendar.displayEventsList()));
        view.add(viewAllEvents);

        menuBar.add(view);

        menuBar.add(navigateMenu);

        add(menuBar, BorderLayout.NORTH);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
