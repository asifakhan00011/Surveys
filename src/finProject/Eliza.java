package finProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.naming.ldap.SortControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class Eliza extends JFrame implements ActionListener, UserInterface{
	private JButton startBtn, nextBtn, finishBtn, viewQA, viewLW, viewLWA;
	private JTextField tf;
	private JLabel lblResults, ansLbl;
	private static int queNum =0;
	private JTextArea jTextArea;
	private JScrollPane jScroll;
	
	private String[] lAlpha = new String [queNum];
	
	private static final int GUI_WIDTH = 900;
	private static final int GUI_HEIGHT = 800;
	private static final int NUM_COLS_TEXT_AREA = GUI_WIDTH/10;
	private static final int NUM_ROWS_TEXT_AREA = GUI_HEIGHT/10;
	
	
	//private String mainPro = "Final Project/";
	private String completeLog =  "Complete Log.txt";
	private String longestWLog = "Longest Word Log.txt";
	private String longestWA=  "Longest Word Alphabetically Log.txt";
	
	private static int n = 1;
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private Font sfont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	
	private TextFileHandler tfHandler = new TextFileHandler();
	private QuestionBank qb;
	private JPanel tfPan;
	private JPanel btnPan;
	
public Eliza() {
	createNewFile();
	qb = new QuestionBank();
	JPanel mainJPanel = new JPanel();
	mainJPanel.setBackground(Color.CYAN);
	mainJPanel.setLayout(new GridLayout(0,1));
	lblResults = new JLabel("                                                           Press Start to start!");
	lblResults.setFont(font);
	ansLbl = new JLabel ("Answer: ");
	ansLbl.setFont(sfont);
	tfPan = new JPanel();
	btnPan = new JPanel();
	
	jTextArea = new JTextArea(NUM_ROWS_TEXT_AREA,NUM_COLS_TEXT_AREA);
	jScroll = new JScrollPane(jTextArea);

	jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	tfPan.setBackground(Color.WHITE);
	btnPan.setBackground(Color.WHITE);
	
	tf = new JTextField ();
	tf.setText("");
	tf.setFont(sfont);
	tf.setColumns(44);

	startBtn = new JButton("Start");
	startBtn.setBackground(Color.WHITE);
	btnPan.add(startBtn);
	startBtn.addActionListener(this);
	
	nextBtn = new JButton ("Next");
	nextBtn.setBackground(Color.WHITE);
	//btnPan.add(nextBtn);
	nextBtn.addActionListener(this);
	
	finishBtn = new JButton("Finish");
	finishBtn.setBackground(Color.WHITE);
	btnPan.add(finishBtn);
	finishBtn.addActionListener(this);
	 
	viewQA = new JButton("View Q&A");
	viewLW = new JButton("View Longest Word"); 
	viewLWA = new JButton("View Longest Word Alphabet");

	viewQA.setBackground(Color.WHITE);
	viewLW.setBackground(Color.WHITE);
	viewLWA.setBackground(Color.CYAN);
	
	//startBtn.setFont(font);
	
	viewQA.addActionListener(this);
	viewLW.addActionListener(this);
	viewLWA.addActionListener(this);
	
	mainJPanel.add(lblResults);
	mainJPanel.add(tfPan);
	mainJPanel.add(btnPan);
	mainJPanel.add (jScroll);
	add(mainJPanel);
	
	setSize(GUI_WIDTH, GUI_HEIGHT);
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

	@Override
	public void actionPerformed(ActionEvent e){
		
	JButton btnClicked = (JButton)e.getSource();
	if ( btnClicked.equals(startBtn)) {
		queNum++;
		if (queNum == 11) {
			btnClicked.setEnabled(false);
		} 
		if(btnClicked.isEnabled()==false ) {
			goAgain();
			QuestionBank.setCurrQuestionIndex(0);
			n++;
			queNum =1;
			btnClicked.setEnabled(true);
		}
	}
		if (btnClicked.equals(startBtn)) {
			tfPan.add(ansLbl);
			tfPan.add(tf);
			lblResults.setText("\n"+"Session# "+ n +" Question# "+(QuestionBank.getCurrQuestionIndex()+1) +": "+QuestionBank.getNextQuestion());	
			//jTextArea.append(tf.getText()+"\n"+lblResults.getText()+ "\n");

			tfHandler.appendToFile(completeLog, tf.getText()+"\n"+lblResults.getText()+ "\n");
			jTextArea.setFont(sfont);
			
			tf.setText("");
			startBtn.setText ("Next");
	}
		else if (btnClicked.equals(finishBtn)) {

			tfPan.add(ansLbl);

			tfPan.add(tf);
			btnPan.add(viewQA);
			btnPan.add(viewLW);
			btnPan.add(viewLWA);
			goAgain();

			startBtn.setText ("Start");
			tf.setText("");
			QuestionBank.setCurrQuestionIndex(0);
			n++;
			queNum =0;

			btnClicked.setEnabled(true);
	
		}	
		
			
		String btnFace = e.getActionCommand();
		
switch(btnFace){

	case "View Longest Word":
			
		

		jTextArea.setText(longestWLog);
			displayLongestWord();
			break;
			
			
	case "View Q&A" :
		
			//tfHandler.appendToFile(completeLog, tf.getText()+"\n"+lblResults.getText()+ "\n");
			jTextArea.setText(completeLog);
			displayEntireFile();
			break;
			
	case "View Longest Word Alphabet": 
		jTextArea.setText(longestWA);
		displayOrderedFile();
		break;
		 
		}
		}
	
private void longestWord() {
	

	tfHandler.appendToFile(longestWLog, "\n"+"Session# "+ n +" Question# "+(QuestionBank.getCurrQuestionIndex()+1) +": "+QuestionBank.getNextQuestion());	
	
	String [] lngWord = tf.getText().split((" "));
	
	String longest = lngWord[0];
	for (int i = 0; i<lngWord.length-1; i++) {
		if (lngWord [i].length()< lngWord [i+1].length()) {
			longest = lngWord [i+1];
		}
	}
	
	//lAlpha [queNum] = longest;

	tfHandler.appendToFile(longestWLog, longest);
	
}


	
private void displayEntireFile() {
    	String fileName = completeLog;
    	String fileContent = tfHandler.readFile(fileName);
		
    	jTextArea.append(fileContent);
    	tfHandler.appendToFile(fileName, tf.getText());
    	}
private void displayLongestWord() {
	String fileName = longestWLog;
	String fileContent = tfHandler.readFile(fileName);

	jTextArea.append(fileContent);
	//tfHandler.appendToFile(fileName, tf.getText());
	}

private void displayOrderedFile() {
	String fileName = longestWA;
	String fileContent = tfHandler.readFile(fileName);
	//Arrays.sort(queNum);
	
	jTextArea.append(fileContent);
	tfHandler.appendToFile(fileName, tf.getText());
	}


 private void createNewFile(){
    	tfHandler.createNewFile(completeLog);
    	tfHandler.createNewFile(longestWLog);
    	tfHandler.createNewFile(longestWA);
    	}
   	

public void goAgain() {
		int yesno= JOptionPane.showConfirmDialog(null, "wanna start the session over?");
		if(yesno== JOptionPane.NO_OPTION) {
		System.exit(0);
		}
}	
}
