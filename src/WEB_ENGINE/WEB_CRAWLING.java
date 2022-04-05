package WEB_ENGINE;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;



public class WEB_CRAWLING {
	
	// Defining the paths of the folder where the web files and the text files will be stored

	public static String HtmlFiles_folder_windows = System.getProperty("user.dir") + "\\webFiles\\";
	public static String TxtFiles_folder_windows = System.getProperty("user.dir") + "\\textfiles\\";
	public static String HtmlFiles_folder_mac = System.getProperty("user.dir") + "/webFiles/";
	public static String TxtFiles_folder_mac = System.getProperty("user.dir") + "/textfiles/";
	// Defing the depth limit of the web crawler
	public static int Depth_value = 2;
	
	// Creating Hash set for all the visited url's 
	public static Set<String> Links_Crawled = new HashSet<String>();
	
	// Taking the URL as input and using the web crawler to get all the web links
	public static void Website_Crawler(String URL, int Depth_crawl) throws Exception{
		
		
		// Initializing the depth of the web crawler and the regular expression to find the links similar to the URL mentioned
		String Regex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
		
		// Creating an object for pattern using the regex defined above
		Pattern p = Pattern.compile(Regex);
		
		// Defining the condition for if statement
		boolean check = Depth_crawl <= Depth_value;
		if (check) {
	
			// Try anf catch for exception handling
			try {
				
				// creating the folder to store web files and calling the function used to do that from the mentioned below
				Document Doc = Jsoup.connect(URL).get();
				CreatingFolder(Doc, URL);
				// Incrementing the depth of crawler until the depth reaches the maximum value defined
				Depth_crawl = Depth_crawl + 1;
				
				// Defining the condition for if statement
				boolean condition = Depth_crawl < Depth_value;
				if (condition) {
					
					String str = "a[href]";
					Elements ele = Doc.select(str);
					
					
					for (Element pg : ele) {
						
						// Defining the conditions for if statement
						String s1 = "abs:href";
						String s2 = "href";
						boolean conditon_1 = Confirmation(pg.attr(s1));
						boolean condition_2 = p.matcher(pg.attr(s2)).find();
						
						
						if (conditon_1 && condition_2) {
							
							// Priting the link crawled for reference
							String temp = "abs:href";
						//	System.out.println("* LINK : "+pg.attr(temp));
							// reaclling the method in a recursive manner
							Website_Crawler(pg.attr(temp), Depth_crawl);
							
							//  Adding the links crawled to the HashSet for future reference
							Links_Crawled.add(pg.attr(temp));
							
						}
					}
				}

				}
			catch(Exception e){
				
				}
		}
		
		
			

	}
	
	
	// The method is used to create the folder to store web files and start converting them ino text format.
	public static void CreatingFolder(Document doc, String URL) throws Exception{
		try {
		// Intializing the part for folder to store links after completion of crawling and storing the html pages in the mentioned folder
			 if(Web_Search_Engine.os.indexOf("wind") >= 0){
					FileWriter File = new FileWriter(HtmlFiles_folder_windows + doc.title().replace('/', '_') + ".html");
					PrintWriter HTML = new PrintWriter(File);
					
					HTML.write(doc.toString());
					
					HTML.close();
					// Calling the function to convert all the HTML files stored in web folder into txt format
					HTMLtoTXT_Converter(HtmlFiles_folder_windows + doc.title().replace('/', '_') + ".html", URL,doc.title().replace('/', '_') + ".txt");
			 }else 
			 {
					FileWriter File = new FileWriter(HtmlFiles_folder_mac + doc.title().replace('/', '_') + ".html");
					PrintWriter HTML = new PrintWriter(File);
					
					HTML.write(doc.toString());
					
					HTML.close();
					// Calling the function to convert all the HTML files stored in web folder into txt format
					HTMLtoTXT_Converter(HtmlFiles_folder_mac + doc.title().replace('/', '_') + ".html", URL,doc.title().replace('/', '_') + ".txt");
					 }
	
	
		} catch(Exception e) {
		//	System.out.println(e);
		}

	}

	
	// The method is used to convert the HTML files to Txt format in the mentioned folder.
	public static void HTMLtoTXT_Converter(String HTMLfile, String URL, String NameofFile) throws Exception {
		
		// Storing the details of the web folder using the File object
		File FV = new File(HTMLfile);
		
		// Representing the entire set of HTML files
		String s = "UTF-8";
		Document DT = Jsoup.parse(FV, s);
		
		// Storing the data obtained from the abobe parameter into string
		String SD = DT.text().toLowerCase();
		String temp = "::";
		SD = URL + temp + SD;
		
		// Storing the text files into the folder metnioned
		 if(Web_Search_Engine.os.indexOf("wind") >= 0){
				PrintWriter W = new PrintWriter(TxtFiles_folder_windows + NameofFile);
				W.println(SD);
				W.close();
		 }else {
				PrintWriter W = new PrintWriter(TxtFiles_folder_mac + NameofFile);
				W.println(SD);
				W.close();
		 }

	}

	// The method is used to confirm wether the URL is already crawled or not
	private static boolean Confirmation(String SuccesiveURL) {
		
		// Intializing the strings
		String A = ".jpeg";
		String B = ".jpg";
		String C = ".png";
		String D = ".pdf";
		String E = "#";
		String F = "?";
		String G = "mailto";
		String H = "javascript:";
		String I = ".gif";
		String J = ".xml";
		
		// Confirming the presence of link already crawled in the HashSet created
		if (Links_Crawled.contains(SuccesiveURL)) {
			return false;
		}
		
		// Defining conditions
		boolean Condtion1 = SuccesiveURL.endsWith(A);
		boolean Condtion2 = SuccesiveURL.endsWith(B);
		boolean Condtion3 = SuccesiveURL.endsWith(C);
		boolean Condtion4 = SuccesiveURL.endsWith(D);
		boolean Condtion5 = SuccesiveURL.contains(E);
		boolean Condtion6 = SuccesiveURL.contains(F);
		boolean Condtion7 = SuccesiveURL.contains(G);
		boolean Condtion8 = SuccesiveURL.startsWith(H);
		boolean Condtion9 = SuccesiveURL.endsWith(I);
		boolean Condtion10 = SuccesiveURL.endsWith(J);
		boolean FinalCondition = (  Condtion1 || Condtion2 || Condtion3 || Condtion4 || Condtion5 || Condtion6|| Condtion7 || Condtion8 || Condtion9 || Condtion10 ); 
		
		// Confirming the presence of various conditions to make the seach engine effecient
		if ( FinalCondition ) {
			return false;
		}
		
		
		return true;
	}

	



	public static void main(String[] args) throws Exception{
		System.out.println("Saving website");
		Website_Crawler("https://en.wikipedia.org/",0);
	

	}
}
