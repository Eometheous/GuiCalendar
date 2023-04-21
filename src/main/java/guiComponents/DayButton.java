package guiComponents;

import javax.swing.*;
import java.time.LocalDate;

public class DayButton extends JButton {
    LocalDate date;
    public DayButton(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
