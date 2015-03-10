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



public class HigherLowerGame extends JFrame implements ActionListener{

	private int randomNumber;
	private JButton submitButton;
	private JLabel response, countdown;
	private JTextField userEntry;
	private Counter timer;
	
	public HigherLowerGame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		response = new JLabel();
		response.setText("Begin!");
		this.getContentPane().add(response);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setActionCommand("Submit");
		submitButton.setEnabled(true);
		this.getContentPane().add(submitButton);
		
		userEntry = new JTextField(20);
		userEntry.setText("");
		this.getContentPane().add(userEntry);
		
		countdown = new JLabel();
		countdown.setText("30");
		this.getContentPane().add(countdown);
		
		pack();
		this.setVisible(true);
		
		Random random = new Random();
		randomNumber = random.nextInt(50);
		
		(timer = new Counter()).execute();
		
	}
	
	public boolean checkGuess(){
		try{
			Integer guess = Integer.parseInt(userEntry.getText().trim());
			if (guess==randomNumber){
				response.setText("CORRECT!!");
				timer.cancel(true);
				timer=null;
				int timeRemaining = Integer.parseInt(countdown.getText().trim());
				int timeTaken = 30-timeRemaining;
				System.out.println ("Congratulations!! You took "+timeTaken+" secs!");
				return true;
			}
			else if(guess<randomNumber) {
				response.setText("LOWER!!");
				userEntry.setText("");
				return false;
			}
			else{ //guess>randomNumber
				response.setText("HIGHER!!");
				userEntry.setText("");
				return false;
			}
			
		}
		catch(NumberFormatException nfx){
			response.setText("Incorrect Format");
			userEntry.setText("");
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton){
			if (checkGuess()){
				System.exit(0);
			}
		}
		
	}
	
	protected class Counter extends SwingWorker<Void, Integer>{

		@Override
		protected Void doInBackground() throws Exception {
			try{
				Integer time = Integer.parseInt(countdown.getText().trim());
				boolean timeRemaining = true;
				while (timeRemaining){
					if (time!=0){
						time--;
						publish(time);
						Thread.sleep(1000);
					}
					else{
						System.out.println ("OUT OF TIME!!");
						this.cancel(true);
						System.exit(0);
					}
				}
			}
			catch (Exception e){}
			return null;
		}
		
		protected void process(List<Integer> time){
			countdown.setText(""+time.get((time.size()-1)));
		}
		
	}
	
	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new HigherLowerGame();
			}
		});
	}
}