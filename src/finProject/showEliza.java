package finProject;


public class showEliza {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() {
		@Override
		public void run() {
			Eliza eQue = new Eliza();
		}
		});
		}

}
