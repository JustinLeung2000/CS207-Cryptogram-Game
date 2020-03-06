import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI implements ActionListener {

    private JFrame frame;
    private JButton ok;
    private JButton quit;
    private JRadioButton babutton;
    private JRadioButton bscbutton;
    private JComboBox<String> courseLength;
    private JTextField cname;

    public GUI() {
        this.makeFrame();
    }

    private void makeFrame() {
        frame = new JFrame("Simple Course GUI");
        Container contentPane = frame.getContentPane();

        JLabel qlabel = new JLabel("Qualification");
        JLabel tlabel = new JLabel("Title");
        JLabel dlabel = new JLabel("Length (years)");

        ok = new JButton("OK");
        ok.addActionListener(this);

        quit = new JButton("Quit");

        // Example of how to define the ActionListener using an anonymous inner class

        /**
         quit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         frame.dispose();
         System.exit(0);
         }
         });
         */

        // Example of lambda expression to define event handler
        quit.addActionListener(e -> {frame.dispose(); System.exit(0);} );

        babutton = new JRadioButton("BA");
        bscbutton = new JRadioButton("BSc");
        ButtonGroup group = new ButtonGroup();
        group.add(babutton);
        group.add(bscbutton);

        String[] yearStrings = { "3", "4", "5" };
        courseLength = new JComboBox<String>(yearStrings);
        courseLength.setSelectedIndex(0);

        cname = new JTextField(20);

        JPanel p1 = new JPanel(new GridLayout(7, 1));

        p1.add(qlabel);
        p1.add(babutton);
        p1.add(bscbutton);
        p1.add(dlabel);
        p1.add(courseLength);
        p1.add(tlabel);
        p1.add(cname);

        JPanel p2 = new JPanel(new GridLayout(2, 1));
        p2.add(ok);
        p2.add(quit);

        contentPane.add(p1, BorderLayout.WEST);
        contentPane.add(p2, BorderLayout.EAST);

        frame.pack();
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
//		Replaced by lambda expression version above
//		if (event.getSource() == quit) {
//			frame.dispose();
//			System.exit(0);
//		} else
        if (event.getSource() == ok) {
            // get all data and create course object
            String qual;
            String title;
            int length;

            if (babutton.isSelected())
                qual = "BA";
            else
                qual = "BSc";

            String years = (String) courseLength.getSelectedItem();
            length = Integer.parseInt(years);

            title = cname.getText();

//            Course c = new Course(qual, title, length);
//            // Output to console
//            c.printDetails();
//            // Output using a wee dialog
//            JOptionPane.showMessageDialog(frame, c.getDetails(), "Course Details", JOptionPane.PLAIN_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        GUIDemo g = new GUIDemo();
//    }

}
