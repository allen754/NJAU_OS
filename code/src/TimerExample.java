import java.awt.FlowLayout;

import java.util.Date;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

import javax.swing.Timer; //Note the import

public class TimerExample extends JFrame {

    private static final int TIMER_DELAY = 1000;

    private Timer timer;

    public TimerExample () {

        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(200, 200);

        setLocationRelativeTo(null);

        getContentPane().setLayout(new FlowLayout());

        timer = new Timer(TIMER_DELAY, e -> {

            System.out.println("Current Time is: " + new Date(System.currentTimeMillis()));

        });

//timer.setRepeats(false); //Do it once, or repeat it?

        JButton button = new JButton("Start");

        button.addActionListener(e -> timer.start());

        getContentPane().add(button);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TimerExample().setVisible(true));

    }

}



