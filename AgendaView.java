import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgendaView extends JPanel {
    public AgendaView(LocalDate l) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.GRAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("AGENDA VIEW  " + formatter.format(l).toString());
        this.add(date);
    }


}
