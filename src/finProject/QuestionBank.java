package finProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

	public final static int NUM_QUESTIONS = 10;
	private static String [] questions =new String [NUM_QUESTIONS];
	private static int currQuestionIndex =0; 
	
	public QuestionBank(){
		questions = new String [NUM_QUESTIONS]; //increase array size if you will add more questions
		
	}
	
	private static void populateQuestionArray(){
		questions[0]= "Which three words describe you best?";
		questions[1]= "Which is your best feature?";
		questions[2]= "Which common saying or phrase describes you?";
		questions[3]= "What’s the best thing that’s happened to you this week?";
		questions[4]= "Who was your role model when you were a child?";
		questions[5]= "Who was your favorite teacher and why?";
		questions[6]= "What was your favorite subject at school?";
		questions[7]= "What did you want to be when you grew up?";
		questions[8]= "If you could have one wish come true what would it be?";
		questions[9]= "Which would you prefer — three wishes over five years or one wish right now?";
		/*add more questions if you like so the experience seems more realistic when randomly selecting
		remember the number of questions cannot exceed the length of the array*/
}
	public static String  getNextQuestion() {
		 int x = 0;
		 String q = null;
		 
		// ArrayList<Integer> list = new ArrayList<Integer>();
	    //  for (int i = 0; i < NUM_QUESTIONS; i++) {
	    //        list.add(new Integer(i));
	    //  }
	    //   Collections.shuffle(list);
	    //  for (int i = 0; i <10; i++) {
	    //  x=list.get(i); 
	        x =(int )(Math. random() *10);
	     setCurrQuestionIndex(getCurrQuestionIndex() + 1);
	      populateQuestionArray();
	      q=questions [x];
	       
	     //}
		return q;     
}
	

	public String getQuest (int x) {
	return questions [x];
	}

	public static int getCurrQuestionIndex() {
		return currQuestionIndex;
	}

	public static void setCurrQuestionIndex(int currQuestionIndex) {
		QuestionBank.currQuestionIndex = currQuestionIndex;
	}
	
	
	
	
}

