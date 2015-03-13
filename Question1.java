import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * Class representing a simple higher/lower game
 */
public class Question1 extends JFrame implements ActionListener{

	private int randomNumber; 
	//number to be guessed by the player
	private JLabel countdown, response; 
	// one label to indicate the time remaining and one to indicate 
	// whether the previous player guess was higher or lower
	private JButton submitButton;
	private JTextField userEntry;
	// Textfield for entry of the player's guess
	private Counter timer;
	// SwingWorker object to perform countdown in Thread safe manner
	
	/**
	 * Constructor for a Question1 object 
	 */
	public Question1(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		// Create a JLabel for responding to the player's guess
		response = new JLabel();
		response.setText("Begin!!");
		this.getContentPane().add(response);
		
		// Add a JButton for entry of the player's guesses
		submitButton = new JButton("Submit");
		// ActionListener required for responding to user presses
		submitButton.addActionListener(this);
		submitButton.setEnabled(true);
		this.getContentPane().add(submitButton);
		
		//Add userEntry TextField
		userEntry = new JTextField(20);
		userEntry.setText("");
		this.getContentPane().add(userEntry);
		
		// Add JLabel to show the time remaining to the player
		countdown = new JLabel();
		countdown.setText("30");
		this.getContentPane().add(countdown);
		
		// Add the components to the frame and display it
		pack();
		this.setVisible(true);
		//Determine a random for the player to guess
		Random random = new Random();
		randomNumber = random.nextInt(50);
		// Create a new Counter object and call execute to initiate the countdown
		(timer = new Counter()).execute();
	}
	
	/**
	 * Method to determine if the player's guess matches the random number generated
	 * by the game. In not, then feedback is provided to assist the player.
	 * @return boolean	indicates whether the player's guess matches the random number
	 * to be found.
	 */
	public boolean checkGuess(){
		try{
			// Retrieve the user's guess from the GUI
			Integer guess = Integer.parseInt(userEntry.getText());
			if (guess==randomNumber){
				// If the guess matches the random number
				// determine the time taken to complete the game
				Integer timeRemaining = Integer.parseInt(countdown.getText().trim());
				Integer timeTaken = 30 - timeRemaining;
				System.out.println("Congratulations, you took "+timeTaken+" secs!");
				return true;
			}
			else if(guess<randomNumber){
				response.setText("LOWER!");
				userEntry.setText("");
				return false;
			}
			else{ // guess>randomNumber
				response.setText("HIGHER!");
				userEntry.setText("");
				return false;
			}
		}
		catch(NumberFormatException nfx){
			// In the event that an integer is not entered by the player
			response.setText("INCORRECT FORMAT!");
			userEntry.setText("");
			return false;
		}
	}

	/**
	 * Method to respond to action events
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton){
			if (checkGuess()){
				// If the player's guess is correct, cancel the
				// Counter object which manages the countdown
				timer.cancel(true);
				timer = null;
			}
		}
	}
	
	/**
	 * Inner class to manage the countdown timer associated
	 * with the game. Void indicates the return type of doInBackground()
	 * and Integer is the type to be passed to publish().
	 */
	private class Counter extends SwingWorker<Void, Integer>{

		/**
		 * Method to perform the countdown.
		 */
		protected Void doInBackground() throws Exception {
			try{
				// the timer for the game begins at 30 secs
				int time = 30;
				boolean remainingTime = true;
				while(remainingTime){
					if (time>0){
						// if there is time remaining, decrement the time
						time--;
						// pass the value of time remaining to the publish method
						publish(time);
						// the thread is put to sleep for one second
						Thread.sleep(1000);
					}
					else{
						// no time remains so inform the player that their time has run out
						System.out.println ("GAME OVER!! TIME RAN OUT!!");
						remainingTime = false;
					}
				}
			}
			catch(InterruptedException e){}
			return null;
		}
		
		/**
		 * Method to update the countdown label of the user interface at intervals
		 */
		protected void process(List<Integer> timer){
			// Method receives a list of integer objects
			countdown.setText(""+timer.get(timer.size()-1));
			// The most recent value of the countdown is the final value in the list 
		}
		
		/**
		 * Method evoked upon completion of doInBackground() or timer.cancel(true).
		 * Simply used here to disable the submitButton.
		 */
		protected void done(){
			// Upon completion of the countdown or in the event of a
			// correct guess then disable the submitButton
			submitButton.setEnabled(false);
		}
	}

	/**
	 * Main method to instantiate a new JFrame game object
	 */
	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Question1();
			}
		});
	}
}