package guiComponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;

/**
 * The text panel containing the month and year, as well as the days of the week
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.230423
 */
public class MonthViewTextPanel extends JPanel implements ChangeListener {
    JLabel monthYear, daysOfWeek;
    Model<LocalDate> dateModel;

    /**
     * The constructor for this panel
     * @param dateModel the model containing the selected date
     */
    public MonthViewTextPanel(Model<LocalDate> dateModel) {
        this.dateModel = dateModel;
        monthYear = new JLabel();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        Font monthYearFont = new Font(Font.SERIF, Font.BOLD, 20);
        monthYear.setFont(monthYearFont);
        setText(monthYear, dateModel.get(0).getMonth().toString() + " " + dateModel.get(0).getYear());

        Font daysOfWeekFont = new Font(Font.SERIF, Font.PLAIN, 20);
        daysOfWeek = new JLabel("Sun   Mon   Tue   Wed   Thu     Fri    Sat");
        daysOfWeek.setFont(daysOfWeekFont);

        setPreferredSize(new Dimension( 200, 75));

        add(monthYear);
        add(daysOfWeek);
    }

    /**
     * Updates the text within the label
     * @param label the label being updated
     * @param text  the string being used
     */
    private void setText(JLabel label, String text) {
        label.setText(text);
    }

    /**
     * Updates the label within this panel with the information within the date model
     * @param e  a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        setText(monthYear, dateModel.get(0).getMonth().toString() + " " + dateModel.get(0).getYear());
    }
}
