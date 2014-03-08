
//GoodBye.java
import java.awt.event.*;

class ExitButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("I am exiting...Goodbye!");
        System.exit(0);
    }
}
