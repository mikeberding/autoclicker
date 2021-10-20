import javax.swing.*;
import java.awt.event.*;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;

public class KeyboardPresser extends JFrame{
	private JComboBox keySelected;
	private JTextField txtDelay;
	private JButton btnStop;
	private JButton btnStart;
	String[] keyStrings = {"Space","Click"};  //If you want more keys in the JComboBox you can add them to this array.
	private String entKey = "";
	private JLabel errMessage;
	private String txtError = "";
	private String strDelay = "";
	private int intDelay;
	
	public KeyboardPresser() {
		setAutoRequestFocus(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Mike's Auto Presser");
		getContentPane().setLayout(null);

		JLabel lblWhatDoYou = new JLabel("What do you want to press?");
		lblWhatDoYou.setBounds(10, 11, 141, 14);
		getContentPane().add(lblWhatDoYou);

		keySelected = new JComboBox(keyStrings);
		keySelected.setBounds(161, 7, 60, 22);
		getContentPane().add(keySelected);

		JLabel lblDelay = new JLabel("How much delay? (ms)");
		lblDelay.setBounds(10, 36, 141, 22);
		getContentPane().add(lblDelay);

		txtDelay = new JTextField();
		txtDelay.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDelay.setToolTipText("Enter delay in miliseconds.");
		txtDelay.setBounds(161, 37, 60, 20);
		getContentPane().add(txtDelay);
		txtDelay.setColumns(10);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

					btnStart.setVisible(false);
					btnStop.setVisible(true);
					setup();
			}
		});
		
		btnStart.setBounds(35, 69, 89, 23);
		getContentPane().add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setup();
				btnStop.setVisible(false);
				btnStart.setVisible(true);
				timer.stop();
			}
		});
		
		btnStop.setBounds(35, 99, 89, 23);
		getContentPane().add(btnStop);

		errMessage = new JLabel("Error Messages show up here.");
		errMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errMessage.setBounds(35, 152, 377, 14);
		getContentPane().add(errMessage);
	}

	
// add else if if you added additional keys	
	ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent ae){
			
			if (entKey == "Space")
				try {
					space();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				try {
					click();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}; 
	
	Timer timer = new Timer(1000,al);
	
	
	private void setup() {
		try {
			strDelay = txtDelay.getText(); 
			intDelay = Integer.parseInt(strDelay);
			entKey = keySelected.getSelectedItem().toString();
			timer.setDelay(intDelay);
			timer.start();
		} catch (Exception e){
			txtError = "Please enter a valid integer";
			errMessage.setText(txtError);
			btnStart.setVisible(true);
			btnStop.setVisible(false);
		}
	}
	
// copy this method for additional keys	
	private void space() throws AWTException{
		Robot robot = new Robot();
		//robot.delay(intDelay);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}

	private void click() throws AWTException{
		Robot robot = new Robot();
		//robot.delay(intDelay);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public static void main(String[] args){

		// TODO Auto-generated method stub
		KeyboardPresser thePresser = new KeyboardPresser();
		thePresser.setSize(new Dimension(450,300));
		thePresser.setVisible(true);

	}
}
