package mspr.GoSecuri;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;


public class AppWeb {
	
	public static ListIterator<String> listAgent;

	public static void main(String args[]) {
	
	}
	
	public static ArrayList<String> newCollection(){
		ArrayList<String> staffList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/files/staff.txt"));
			try {
				String ln = br.readLine();
				while (ln != null) {
					staffList.add(ln);
					ln = br.readLine();
				}
			}
			finally {
				br.close();
			}
		}
		catch(IOException e) {
			// catch IOExceptions
				System.out.println("General I/O exception: " + e.getMessage());
				e.printStackTrace();
			}
		return staffList;
	}

}
