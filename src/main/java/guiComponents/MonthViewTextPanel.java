package guiComponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;

public class MonthViewTextPanel extends JPanel implements ChangeListener {
    JLabel monthYear, daysOfWeek;
    Model<LocalDate> dateModel;

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
    private void setText(JLabel label, String text) {
        label.setText(text);
    }
    public void stateChanged(ChangeEvent e) {
        setText(monthYear, dateModel.get(0).getMonth().toString() + " " + dateModel.get(0).getYear());
    }
}
