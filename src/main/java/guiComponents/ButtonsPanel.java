package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ButtonsPanel extends JPanel{
    JButton previousMonth, previousDay;
    JButton nextMonth, nextDay;
    Model<LocalDate> dateModel;
    LocalDate selectedDate;

    public ButtonsPanel(MyCalendar calendar, Model<LocalDate> dateModel, Model<String> stringModel) {
        this.dateModel = dateModel;
        selectedDate = dateModel.get(0);

        previousMonth = new JButton("<<");
        previousDay = new JButton("<");
        nextMonth = new JButton(">>");
        nextDay = new JButton(">");

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(200, 35));

        previousMonth.addActionListener(e -> {
            dateModel.update(0, dateModel.get(0).minusMonths(1));
            if (dateModel.get(0).equals(LocalDate.now()))
                stringModel.update(0, calendar.displayTodaysEvents());
            else
                stringModel.update(0,calendar.displaySelectedDay());
        });
        previousDay.addActionListener(e -> {
            dateModel.update(0, dateModel.get(0).minusDays(1));
            if (dateModel.get(0).equals(LocalDate.now()))
                stringModel.update(0, calendar.displayTodaysEvents());
            else
                stringModel.update(0,calendar.displaySelectedDay());
        });
        nextDay.addActionListener(e -> {
            dateModel.update(0,dateModel.get(0).plusDays(1));
            if (dateModel.get(0).equals(LocalDate.now()))
                stringModel.update(0, calendar.displayTodaysEvents());
            else
                stringModel.update(0,calendar.displaySelectedDay());
        });
        nextMonth.addActionListener(e -> {
            dateModel.update(0,dateModel.get(0).plusMonths(1));
            if (dateModel.get(0).equals(LocalDate.now()))
                stringModel.update(0, calendar.displayTodaysEvents());
            else
                stringModel.update(0,calendar.displaySelectedDay());
        });

        previousMonth.setToolTipText("Go to the previous month");
        previousDay.setToolTipText("Go to the previous day");
        nextDay.setToolTipText("Go to the next day");
        nextMonth.setToolTipText("Go to the next month");

        add(previousMonth);
        add(previousDay);
        add(nextDay);
        add(nextMonth);
    }
}
