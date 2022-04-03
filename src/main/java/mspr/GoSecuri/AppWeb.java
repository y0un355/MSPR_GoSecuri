package mspr.GoSecuri;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;


public class AppWeb {

	public static String header = "<!DOCTYPE html>\r\n"
			+ "<html lang=\"en\">\r\n"
			+ "<FONT face=\"roboto\">\r\n"
			+ "    <head>\r\n"
			+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
			+ "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n"
			+ "        <meta name=\"description\" content=\"\" />\r\n"
			+ "        <meta name=\"author\" content=\"\" />\r\n"
			+ "        <title>Full Width Pics - Start Bootstrap Template</title>\r\n"
			+ "        <!-- Favicon-->\r\n"
			+ "        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\r\n"
			+ "        <!-- Core theme CSS (includes Bootstrap)-->\r\n"
			+ "        <link href=\"css/styles.css\" rel=\"stylesheet\" />\r\n"
			+ "    </head>\r\n";
	public static String footer = "<!-- Footer-->\r\n"
			+ "        <footer class=\"py-5 bg-dark\">\r\n"
			+ "            <div class=\"container\"><p class=\"m-0 text-center text-white\">Copyright &copy; Go Securi</p></div>\r\n"
			+ "        </footer>\r\n"
			+ "        <!-- Bootstrap core JS-->\r\n"
			+ "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
			+ "        <!-- Core theme JS-->\r\n"
			+ "        <script src=\"js/scripts.js\"></script>\r\n"
			+ "    </body>\r\n"
			+ "</html>";
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

	public static void writeFiles() {
		ArrayList<String> list = newCollection();
		listAgent = list.listIterator();

		for(int i = 0;i < list.size();i++){
			new Thread(() -> {
				String name = listAgent.next();
				writeFile("https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/src/files/" + name + ".txt", name + ".txt");
				writeAgent(name);

			}).start();
		}
	}

}
