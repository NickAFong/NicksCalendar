import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeekView extends JPanel {
    public WeekView(LocalDate l) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.YELLOW);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("WEEK VIEW  " + formatter.format(l).toString());
        this.add(date);
    }


}
