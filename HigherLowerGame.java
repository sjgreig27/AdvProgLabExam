import java.awt.event.*;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.util.List;
import java.util.Random;

public class HigherLowerGame extends JFrame implements ActionListener {

	private JButton submitButton;
	private JTextField userEntry;
	private JLabel countdown, response;
	private int randomNumber;
	private Counter timer;

	public HigherLowerGame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		//Add Response label
		response = new JLabel();
		response.setText("BEGIN!!");
		getContentPane().add(response);

		//Add submit Button
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("Submit");
		submitButton.addActionListener(this);
		getContentPane().add(submitButton);
		submitButton.setEnabled(true);

		//Add userEntry field
		userEntry = new JTextField(10);
		userEntry.setText("0");
		getContentPane().add(userEntry);

		//Add the countdown
		countdown = new JLabel();
		countdown.setText("30");
		getContentPane().add(countdown);

		pack();
		setVisible(true);

		Random generator = new Random();
		randomNumber = generator.nextInt(50);
		
		System.out.println(randomNumber);
		
		(timer = new Counter()).execute();
	}

	public boolean checkEntry(){
		String entry = userEntry.getText();
		int guess = Integer.parseInt(entry.trim());
		if (guess==randomNumber){
			response.setText("CORRECT!!");
			timer.cancel(true);
			timer = null;
			int timeTaken = 30-(Integer.parseInt(countdown.getText()));
			JOptionPane.showMessageDialog(null, "Congratulations, you took "
					+timeTaken+" secs!!",
					"GAME OVER", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else if (guess>randomNumber){
			response.setText("LOWER!!");
		}
		else if (guess<randomNumber){
			response.setText("HIGHER!!");
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton){
			checkEntry();
		}
	}

	private class Counter extends SwingWorker<Void, Integer>{

		@Override
		public Void doInBackground() throws Exception {
			Integer count = Integer.parseInt(countdown.getText());
			boolean remainingTime = true;
			while(remainingTime){
				if (count!=0){
					count--;
					publish(count);
					Thread.sleep(1000);
				}
				else{
					JOptionPane.showMessageDialog(null, "Out of Time!!",
							"GAME OVER", JOptionPane.ERROR_MESSAGE);
					remainingTime= false;
					this.cancel(true);
					System.exit(0);
				}
			}
			return null;
		}

		public void process(List<Integer> time){
			countdown.setText(""+time.get(time.size()-1));
		}
	}

	public static void main (String[]args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new HigherLowerGame();
			}
		});
	}
}
