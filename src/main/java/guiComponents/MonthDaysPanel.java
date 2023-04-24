package guiComponents;

import calendar.MyCalendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * The panel containing the day buttons that change the date
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.230423
 */
public class MonthDaysPanel extends JPanel implements ChangeListener {
    MyCalendar calendar;
    Model<LocalDate> dateModel;
    Model<String> stringModel;

    /**
     * The constructor for this panel
     * @param calendar      the calendar being used
     * @param dateModel     the model containing the selected date
     * @param stringModel   model containing the string for the day view
     */
    public MonthDaysPanel(MyCalendar calendar, Model<LocalDate> dateModel, Model<String> stringModel) {
        this.calendar = calendar;
        this.dateModel = dateModel;
        this.stringModel = stringModel;
        setLayout(new GridLayout(0, 7));
        generateDays(this.calendar, this.dateModel);
        setPreferredSize(new Dimension(340,200));
    }

    /**
     * Generates the day buttons
     * @param calendar  the calendar being used
     * @param dateModel the model containing the selected date
     */
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
            
            button.setEnabled(i != dateModel.get(0).getDayOfMonth());

            add(button);

            date = date.plusDays(1);
        }
        revalidate();
        repaint();
    }

    /**
     * An action listener which updates both models
     * @param date  the date we are going to
     * @return      the action listener
     */
    private ActionListener goToDate(LocalDate date) {
        return e -> {
            dateModel.update(0, date);
            if (date.equals(LocalDate.now()))
                stringModel.update(0, calendar.displayTodaysEvents());
            else
                stringModel.update(0,calendar.displaySelectedDay());
        };
    }

    /**
     * Updates this panel and generates new buttons for the month view
     * @param e  a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        calendar.goTo(dateModel.get(0));
        generateDays(calendar, dateModel);
    }
}
