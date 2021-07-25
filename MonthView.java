import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MonthView extends JPanel {
    public MonthView(LocalDate l) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.RED);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("MONTH VIEW  " + formatter.format(l).toString());
        this.add(date);
    }


}
