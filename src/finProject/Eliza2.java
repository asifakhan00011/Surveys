package finProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

public class Eliza2 extends JFrame implements ActionListener{
	private JPanel mainJP;
	private JPanel textPanel;
	private JPanel btns;
	private JButton viewAll, viewLongest, viewLongAlpha;
	private JButton jbStNxt; 
	private JLabel jlText;
	private JLabel leftAns;
	private JTextField jField;
	private JTextArea textArea;
	
	public static final int NUM_QUESTIONS = 10;
	private String [] lAlpha = new String[NUM_QUESTIONS];
	
	private QuestionBank qNumber = new QuestionBank();
	private TextFileHandler tfHand = new TextFileHandler();
	
	private int qIncr = 1;
	private int sessNum = 1; 
	
	private static final int GUI_WIDTH = 900;
	private static final int GUI_HEIGHT = 800;
	private static final int NUM_COLS_TEXT_AREA = GUI_WIDTH/10;
	private static final int NUM_ROWS_TEXT_AREA = GUI_HEIGHT/10;
	
	//private String mainP = "finProject/LogFolder/";
	private String complLog = "Complete Log.txt";
	private String lWordsLog = "Longest Word Log.txt";
	private String LWordsAlpha = "Longest Words Alphabetically";
	
	private static final String ACTION_COMMAND_START = "Start";
	private static final String ACTION_COMMAND_NEXT = "Next";
	private static final String ACTION_COMMAND_FINISH = "Finish";
	private static final String ACTION_COMMAND_VIEW_ALL = "View All";
	private static final String ACTION_COMMAND_VIEW_LONGEST = "View Longest Word";
	private static final String ACTION_COMMAND_VIEW_LONGEST_ALPHABETICALLY = "View Longest Words Alphabetically";


	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private Font font2 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private JScrollPane jScroll;
	
	public Eliza2(){
		createFiles();
		mainJP = new JPanel(new GridLayout(0,1));
		textPanel = new JPanel(new GridLayout(0,2));
		btns = new JPanel(new GridLayout(0,3));
		

		
		viewAll = new JButton("View All");
		viewLongest = new JButton("View Longest Word");
		viewLongAlpha = new JButton("View Longest Words Alphabetically");
		jbStNxt = new JButton(ACTION_COMMAND_START);
		
		jbStNxt.addActionListener(this);
		viewAll.addActionListener(this);
		viewLongest.addActionListener(this);
		viewLongAlpha.addActionListener(this);
		
		textArea = new JTextArea();
		jField = new JTextField();
		jlText = new JLabel("Welcome! Press Start to begin session!");
		jField.setBackground(Color.WHITE);
		leftAns= new JLabel("Answer:");

		jScroll = new JScrollPane(textArea);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//jScroll.add(textArea);
		
		jField.setFont(font);
		leftAns.setFont(font);
		jbStNxt.setFont(font);
		jlText.setFont(font);
		leftAns.setHorizontalAlignment(JLabel.RIGHT);
		viewAll.setFont(font);
		viewLongest.setFont(font);
		viewLongAlpha.setFont(font2);
		textArea.setFont(font2);
		
		mainJP.setBackground(Color.CYAN);
		textPanel.setBackground(Color.WHITE);
		jlText.setBackground(Color.WHITE);
		textArea.setBackground(Color.WHITE);
		leftAns.setBackground(Color.WHITE);
		btns.setBackground(Color.WHITE);
		
		jField.setVisible(false);
		leftAns.setVisible(false);
		viewAll.setVisible(false);
		viewLongAlpha.setVisible(false);
		viewLongest.setVisible(false);
		textArea.setVisible(false);

		textPanel.add(leftAns);
		textPanel.add(jField);
		btns.add(viewAll);
		btns.add(viewLongAlpha);
		btns.add(viewLongest);
		mainJP.add(jlText);
		mainJP.add(textPanel);
		mainJP.add(jbStNxt);
		mainJP.add(btns);
		//mainJP.add(textArea);
		mainJP.add(jScroll);
		add(mainJP);
		
		setSize(GUI_WIDTH,GUI_HEIGHT);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

		@Override
		public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()){
				case ACTION_COMMAND_START:
					jbStNxt.setText(ACTION_COMMAND_NEXT);
					jlText.setText("Session: " + sessNum + ", Question " + qIncr +": " + qNumber.getNextQuestion());
					leftAns.setVisible(true);
					jField.setVisible(true);
					break;
				case ACTION_COMMAND_NEXT:
					while(jField.getText().isEmpty()){
						jField.setText(" ");
					}
					appendLog();
					++qIncr;
					jField.setText("");
					jlText.setText("Session: " + sessNum + ", Question " + qIncr +": " + qNumber.getNextQuestion());
					
					if(qIncr == NUM_QUESTIONS){
						jbStNxt.setText(ACTION_COMMAND_FINISH);
					}
					break;
				case ACTION_COMMAND_FINISH:
					viewAll.setVisible(true);
					viewLongAlpha.setVisible(true);
					viewLongest.setVisible(true);
					textArea.setVisible(true);
					appendLog();
					Arrays.sort(lAlpha);
					for(int i=0 ; i < lAlpha.length; i++){
						tfHand.appendToFile(LWordsAlpha, lAlpha[i]);
					}
					jbStNxt.setText(ACTION_COMMAND_START);
					++sessNum;
				case ACTION_COMMAND_VIEW_ALL:
					textArea.setText(tfHand.readFile(complLog));
					break;
				case ACTION_COMMAND_VIEW_LONGEST:
					textArea.setText(tfHand.readFile(lWordsLog));
					break;
				case ACTION_COMMAND_VIEW_LONGEST_ALPHABETICALLY:
					textArea.setText(tfHand.readFile(LWordsAlpha));
					break;
			}	
		}
		
		public void createFiles(){
			tfHand.createNewFile(complLog);
			tfHand.createNewFile(lWordsLog);
			tfHand.createNewFile(LWordsAlpha);
		}
		
		public void appendLog(){
			String app = "Session: " + sessNum + ", Question " + qIncr + ": " + qNumber.getQuest(qNumber.getCurrQuestionIndex())+ "\n Answer: " + jField.getText();
			tfHand.appendToFile(complLog, app);
			String [] lWord = jField.getText().split(" ");
			String longest = lWord[0];
			for(int i = 0; i < lWord.length-1; i++){
				if(lWord[i].length() < lWord[i+1].length()){
					longest = lWord[i+1];
				}
			}
			lAlpha[qNumber.getCurrQuestionIndex()] = longest;
			tfHand.appendToFile(lWordsLog, longest);
		}
		
}
