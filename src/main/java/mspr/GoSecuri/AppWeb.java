package mspr.GoSecuri;
import java.util.*;
import java.io.*;


public class AppWeb {

	public static ListIterator<String> listAgent;
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

	public static void main(String args[]) {
		writeFile("https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/files/staff.txt", "staff.txt");
		writeFile("https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/files/liste.txt", "liste.txt");
		writeIndex();
		writeFiles();
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
				writeFile("https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/files/" + name + ".txt", name + ".txt");
				writeAgent(name);

			}).start();
		}
	}

	public static void writeFile(String url, String name) {
		Okhttp client = new Okhttp();
		try {
			String response = client.run(url);
			PrintWriter writer = new PrintWriter("src/files/"+name, "UTF-8");
			writer.print(response);
			writer.close();
		}
		catch(IOException e) {
			// catch IOExceptions
			System.out.println("General I/O exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void writeAgent(String name) {
		boolean find = false;
		try {
			InputStream fileInputStream = new FileInputStream("src/files/liste.txt");
			Reader charsetReader = new InputStreamReader(fileInputStream,"UTF-8");
			InputStream fileInputStream1 = new FileInputStream("src/files/"+ name +".txt");
			Reader charsetReader1 = new InputStreamReader(fileInputStream1,"UTF-8");

			BufferedReader br = new BufferedReader(charsetReader);
			BufferedReader br1 = new BufferedReader(charsetReader1);
			PrintWriter writer = new PrintWriter("src/files/" + name +".html", "UTF-8");
			writer.print(header);
			writer.print("<body style=\"Light\">\r\n"
					+ "        <!-- Navbar-->\r\n"
					+ "        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
					+ "            <div class=\"container\">\r\n"
					+ "                <a class=\"navbar-brand\" href=\"index.html\">GoSecuri</a>\r\n"
					+ "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "        </nav>\r\n"
					+ "        \r\n"
					+ "        <div class=\"name\">\r\n"
					+ "           <p>" + br1.readLine() + " " + br1.readLine());
			br1.readLine();
			br1.readLine();
			br1.readLine();
			writer.print("</div>\r\n"
					+ "\r\n"
					+ "        <div class=\"photo\">" + "<img src=\"https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/img/" + name + ".jpg\"height=\"100\" weith=\"100\">" + "</div>\r\n"
					+ "        <!-- Content section-->\r\n"
					+ "        <section class=\"py-5\">\r\n"
					+ "            <div class=\"container my-5\">\r\n"
					+ "                <div class=\"row justify-content-center\">\r\n"
					+ "                    <div class=\"col-lg-6\">");
			br1.close();
			try {
				String linel = br.readLine();
				while (linel != null) {
					String[] s = linel.split(" ");
					br1 = new BufferedReader(new FileReader("src/files/"+ name +".txt"));
					String line = br1.readLine();
					while(line != null) {
						if(line.compareTo(s[0]) == 0) {
							find = true;
						}
						line = br1.readLine();
					}
					br1.close();
					if(find) {
						find = false;
						writer.print("<img src=\"https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/img/ok.jpg\">");
						for(int i = 1; i < s.length;i++) {
							writer.print(s[i] + " ");

						}
						writer.print("<br>");
					}
					else {
						writer.print("<img src=\"https://raw.githubusercontent.com/y0un355/MSPR_GoSecuri/main/src/img/pasok.png\">");
						for(int i = 1; i < s.length;i++) {
							writer.print(s[i] + " ");
						}
						writer.print("<br>");
					}
					linel = br.readLine();
				}
			}
			finally {
				br.close();
			}
			writer.print("</div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </section>\r\n");

			writer.print(footer);
			writer.close();
		}
		catch(IOException e) {
		// catch IOExceptions
					System.out.println("General I/O exception: " + e.getMessage());
					e.printStackTrace();
		}
	}

	public static void writeIndex() {
		try {
			PrintWriter writer = new PrintWriter("src/files/index.html", "UTF-8");
			writer.print("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "    <head>\r\n"
					+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
					+ "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n"
					+ "        <meta name=\"description\" content=\"\" />\r\n"
					+ "        <meta name=\"author\" content=\"\" />\r\n"
					+ "        <title>Go Securi</title>\r\n"
					+ "        <!-- Favicon-->\r\n"
					+ "        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\r\n"
					+ "        <!-- Core theme CSS (includes Bootstrap)-->\r\n"
					+ "        <link href=\"../css/styles.css\" rel=\"stylesheet\" />\r\n"
					+ "    </head>\r\n"
					+ "    <body style=\"Light\">\r\n"
					+ "        <!-- Responsive navbar-->\r\n"
					+ "        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
					+ "            <div class=\"container\">\r\n"
					+ "                <a class=\"navbar-brand\" href=\"index.html\">GoSecuri</a>\r\n"
					+ "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "        </nav>\r\n"
					+ "        <!-- Header - set the background image for the header in the line below-->\r\n"
					+ "        <header class=\"py-5 bg-image-full\">\r\n"
					+ "            <div class=\"text-center my-5\">\r\n"
					+ "                <img class=\"img-fluid rounded-circle mb-4\" src=\"../img/logo.png\" />\r\n"
					+ "                <h1 class=\"text-white fs-3 fw-bolder\">GO Securi</h1>\r\n"
					+ "                <p class=\"text-white-50 mb-0\">The best of security</p>\r\n"
					+ "            </div>\r\n"
					+ "        </header>\r\n"
					+ "        \r\n"
					+ "        <!-- Content section-->\r\n"
					+ "        <section class=\"py-5\">\r\n"
					+ "            <div class=\"container my-5\">\r\n"
					+ "                <div class=\"row justify-content-center\">\r\n"
					+ "                    <div class=\"col-lg-6\">");
			BufferedReader br = new BufferedReader(new FileReader("src/files/staff.txt"));
			try {
				String line = br.readLine();

				while (line != null) {
					writer.print("<a href=\"" + line + ".html\">" + line + "</a><br>");
					line = br.readLine();
				}
			} finally {
				br.close();
			}
			writer.print("                    </div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </section>\r\n"
					+ "        <!-- Footer-->\r\n"
					+ "        <footer class=\"py-5 bg-dark\">\r\n"
					+ "            <div class=\"container\"><p class=\"m-0 text-center text-white\">Copyright &copy; Go Securi</p></div>\r\n"
					+ "        </footer>\r\n"
					+ "        <!-- Bootstrap core JS-->\r\n"
					+ "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "        <!-- Core theme JS-->\r\n"
					+ "        <script src=\"src/js/scripts.js\"></script>\r\n"
					+ "    </body>\r\n"
					+ "</FONT>\r\n"
					+ "</html>");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
