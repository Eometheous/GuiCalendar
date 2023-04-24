package guiComponents;

import javax.swing.*;
import java.time.LocalDate;

/**
 * A button containing a date
 * @author Jonathan Stewart Thomas
 * @version 1.0.0.2304023
 */
public class DayButton extends JButton {
    LocalDate date;

    /**
     * The constructor for the day button
     * @param name  The name of the button
     * @param date  The date the button is holding
     */
    public DayButton(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Returns the date that this button is holding
     * @return  the date
     */
    public LocalDate getDate() {
        return date;
    }
}
