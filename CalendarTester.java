import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * @author Nick Fong
 */

/**
 * A calendar application.
 */

public class CalendarTester {
    public int firstOfMonth;
    public static LocalDate currentDate;
    public static JLabel label;
    public static JFrame frame;
    public static MonthPanel monthPanel;
    public static JPanel bottomPanel;
    public static JPanel topPanel;


    /**
     * Captures the current date upon construction of the calendar application.
     */
    public CalendarTester() {

    }

    public static void removeMonth() {
        monthPanel.removeAll();
        frame.revalidate();
    }


    /**
     * A tester method for the calendar application.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        currentDate = LocalDate.now();

        frame = new JFrame();
        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 950;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        final Container contentPane = frame.getContentPane();
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));


        label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                .getYear() + "                            ");
        JPanel textTitle = new JPanel();
        textTitle.add(label);

        topPanel = new JPanel();
        monthPanel = new MonthPanel(currentDate);
        topPanel.add(monthPanel);

        JPanel monthButtons = new JPanel();
        JButton backMonth = new JButton("<");
        backMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusMonths(1);
                topPanel.remove(monthPanel);
                textTitle.removeAll();
                label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                        .getYear() + "                            ");
                textTitle.add(label);
                monthPanel = new MonthPanel(currentDate);
                topPanel.add(monthPanel);

                frame.revalidate();
            }
        });
        JButton forwardMonth = new JButton(">");
        forwardMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusMonths(1);
                //monthPanel.removeAll();
                topPanel.remove(monthPanel);
                textTitle.removeAll();
                label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                        .getYear() + "                            ");
                textTitle.add(label);
                monthPanel = new MonthPanel(currentDate);
                topPanel.add(monthPanel);

                frame.revalidate();
            }
        });
        monthButtons.add(backMonth);
        monthButtons.add(forwardMonth);


        JLabel label2 = new JLabel("S                   M                   T                   W                   TH                   F                   S");
        JPanel daysOfWeek = new JPanel();
        daysOfWeek.add(label2);

        JPanel viewButtons = new JPanel();


        bottomPanel = new JPanel();
        bottomPanel.add(new DayView(currentDate));

        JButton day = new JButton("Day");
        day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                bottomPanel.add(new DayView(currentDate));
                bottomPanel.revalidate();
            }
        });

        JButton week = new JButton("Week");
        week.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                bottomPanel.add(new WeekView(currentDate));
                bottomPanel.revalidate();
            }
        });

        JButton month = new JButton("Month");
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                bottomPanel.add(new MonthView(currentDate));
                bottomPanel.revalidate();
            }
        });

        JButton agenda = new JButton("Agenda");
        agenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                bottomPanel.add(new AgendaView(currentDate));
                bottomPanel.revalidate();
            }
        });
        JButton create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                bottomPanel.add(new CreateView(currentDate));
                bottomPanel.revalidate();
            }
        });
        JButton fromFile = new JButton("From File");
        viewButtons.add(day);
        viewButtons.add(week);
        viewButtons.add(month);
        viewButtons.add(agenda);
        viewButtons.add(create);
        viewButtons.add(fromFile);

        JPanel currentViewButtons = new JPanel();
        JButton today = new JButton("Today");
        JButton back = new JButton("<");
        JButton next = new JButton(">");
        currentViewButtons.add(today);
        currentViewButtons.add(back);
        currentViewButtons.add(next);


        frame.add(textTitle);
        frame.add(monthButtons);
        frame.add(daysOfWeek);
        frame.add(topPanel);
        frame.add(viewButtons);
        frame.add(currentViewButtons);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
