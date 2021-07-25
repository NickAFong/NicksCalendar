import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthPanel extends JPanel {

    ArrayList<JButton> buttons = new ArrayList<>();

    public MonthPanel(LocalDate l) {
        int rows = 6;
        int cols = 7;

        this.setPreferredSize(new Dimension(700, 300));

        GridLayout grid = new GridLayout(rows, cols);
        this.setLayout(grid);

        int monthDays = l.lengthOfMonth();
        int monthDayCount = monthDays;
        for (int i = 1; i <= 42; i++) {
            if (i < getFirstOfMonth(l)) {
                JButton button = new JButton();
                this.add(button);
            }
            else if (monthDays <= 0) {
                JButton button = new JButton();
                this.add(button);
            }
            else {
                monthDays = monthDays - 1;
                JButton button = new JButton(Integer.toString(monthDayCount - monthDays));
                //add listeners and action handlers here
                this.add(button);
            }
        }

    }

    public int getFirstOfMonth(LocalDate l) {
        LocalDate x = LocalDate.of(l.getYear(), l.getMonth(), 1);
        return weekTool(x.getDayOfWeek().toString());
    }

    /**
     * [METHOD DESCRIPTION
     *
     * @return [FILL THIS IN]
     */
    public static int weekTool(String day) {
        if (day.equals("SUNDAY")) {
            return 1;
        }
        else if (day.equals("MONDAY")) {
            return 2;
        }
        else if (day.equals("TUESDAY")) {
            return 3;
        }
        else if (day.equals("WEDNESDAY")) {
            return 4;
        }
        else if (day.equals("THURSDAY")) {
            return 5;
        }
        else if (day.equals("FRIDAY")) {
            return 6;
        }
        else {
            return 7;
        }
    }
}
