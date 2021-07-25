import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateView extends JPanel {
    public CreateView(LocalDate l) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.PINK);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("CREATE VIEW  " + formatter.format(l).toString());
        this.add(date);
    }


}
