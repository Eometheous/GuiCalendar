package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MonthDaysPanel extends JPanel implements ChangeListener {
    MyCalendar calendar;
    Model<LocalDate> dateModel;
    Model<String> stringModel;
    private final int SELECTED_DAY = 0;
    public MonthDaysPanel(MyCalendar calendar, Model<LocalDate> dateModel, Model<String> stringModel) {
        this.calendar = calendar;
        this.dateModel = dateModel;
        this.stringModel = stringModel;
        setLayout(new GridLayout(0, 7));
        generateDays(this.calendar, this.dateModel);
        setPreferredSize(new Dimension(340,200));
    }

    private void generateDays(MyCalendar calendar, Model<LocalDate> dateModel) {
        removeAll();
        int firstColumn = calendar.getFirstDay().getDayOfWeek().getValue();
        for (int i = 0; i < firstColumn; i++) {
            add(new JPanel());
        }

        LocalDate date = calendar.getFirstDay();
        for (int i = 1; i <= calendar.getLastDay(); i++) {
            DayButton button = new DayButton(String.format("%d", i), date);
            button.addActionListener(goToDate(button.getDate()));

            if (calendar.hasEventsOn(date)) {
                button.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), ".",
                        TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, Font.getFont(Font.SANS_SERIF), Color.RED));
            }
            else button.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), " ",
                    TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, Font.getFont(Font.SANS_SERIF), Color.RED));
            
            button.setEnabled(i != dateModel.get(SELECTED_DAY).getDayOfMonth());

            add(button);

            date = date.plusDays(1);
        }
        revalidate();
        repaint();
    }

    private ActionListener goToDate(LocalDate date) {
        return e -> {
            dateModel.update(SELECTED_DAY, date);
            if (date.equals(LocalDate.now()))
                stringModel.update(SELECTED_DAY, calendar.displayTodaysEvents());
            else
                stringModel.update(SELECTED_DAY,calendar.displaySelectedDay());
        };
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        calendar.goTo(dateModel.get(SELECTED_DAY));
        generateDays(calendar, dateModel);
    }
}
