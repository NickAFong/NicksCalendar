import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayView extends JPanel {
    public DayView(LocalDate l) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.BLUE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("DAY VIEW  " + formatter.format(l).toString());
        this.add(date);
    }

}
