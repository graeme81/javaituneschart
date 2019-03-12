import java.awt.Desktop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class ItunesChart {

	protected Shell shell;
	public static String date;
	public static List<String> songs;
	public static List<String> artists;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		int  number = 40;
		try {
			ItunesChart window = new ItunesChart();
			Chart now = new Chart(number);
			songs = now.getSong();
			artists = now.getArtist();
			date = now.getDate();
			//Desktop d = Desktop.getDesktop();
			
			System.out.println(date);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(600, 400);
		shell.setText("I-Music Chart");
		
		Label lblTitle = new Label(shell, SWT.NONE);
		lblTitle.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBounds(10, 10, 564, 30);
		lblTitle.setText("I-Charts For Day " + date );
		
		ListViewer listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List list = listViewer.getList();
		list.setBounds(10, 53, 278, 298);
		
		Label lblOut = new Label(shell, SWT.SHADOW_IN | SWT.CENTER);
		lblOut.setFont(SWTResourceManager.getFont("Corbel", 10, SWT.NORMAL));
		lblOut.setBounds(312, 98, 201, 124);
		lblOut.setText("Label here");
		
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(328, 53, 185, 21);
		combo.addSelectionListener(new SelectionAdapter(){
			public void widgetDefaultSelected(SelectionEvent e) {
				int x = songs.indexOf(combo.getText());
				String find = artists.get(x)+ " "+songs.get(x) ;
				String click = "http://www.youtube.com/results?search_query="+find;
		        lblOut.setText(click);
		        
		      }
		});
	
		List<String> charty = new ArrayList<String>();
		
		for(int i = 0; i < songs.size(); i++){
			int x = i+1;
			String here  = songs.get(i) + " by " + artists.get(i);
			charty.add(here);
			String in = "No."+ x + " - " + here;
			list.add(in);
			combo.add(songs.get(i));
		}
		
	}
}
