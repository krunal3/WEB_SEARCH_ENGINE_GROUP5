package WEB_ENGINE;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Web_Search_Engine {
	private static Scanner sc = new Scanner(System.in);	
	public static String os =  System.getProperty("os.name").toLowerCase();
	

	public static void main(String[] args) throws Exception {
	   
		
		
		String choose = "yes";
		do {
		System.out.println("");
		System.out.println("********************************************************************");
		System.out.println("        WELCOME TO THE WEB SERACH ENGINE OF GROUP 5");
		System.out.println("********************************************************************");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("********************************************************************");
		System.out.println("                           FINAL PROJECT                            ");
		System.out.println("DETAILS : ");
		System.out.println("SUBJECT : COMP 8547-R-2-2022W");
		System.out.println("TEAM MEMBERS :");
		System.out.println("1. Jaineel Fenal Dharia");
		System.out.println("2. Krunal Parikh");
		System.out.println("********************************************************************");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("********************************************************************");
		System.out.println("                       WEB SERACH ENGINE                       ");
		System.out.println("********************************************************************");
		System.out.println("");
		System.out.println("Please choose one of the following options to opertae the web search engine : ");
		System.out.println("");
		System.out.println(" 1) Please enter 1 for the Web search in URL (The defualt URL)\n");
		System.out.println(" 2) Please enter 2 for searching the word from the web link enetered by the user\n");
		System.out.println(" 3) Please enter 3 to exit the web search enigine\n");
		String option = sc.next();


		switch (option) {
		case "1":
			 if(os.indexOf("wind") >= 0){
			FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_windows)); 
			FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_windows)); 
			 }else {
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_mac)); 
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_mac)); 
			 }
			String url = "http://geeksforgeeks.org/";
			choose = SearchWord.search1(url);
			break;
		case "2":
			 if(os.indexOf("wind") >= 0){
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_windows)); 
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_windows)); 
					 }else {
							FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_mac)); 
							FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_mac)); 
					 }
			System.out.println("Enter the Web Url");
			String link = sc.next();
			choose = SearchWord.search1(link);
			break;
		case "3":
			choose = "no";
			break;
	default:
		System.out.println("\nPlease enter correct option\n");
		choose = "yes";

		}
	}while(choose.equals("yes"));
    System.out.println("\n*************************************************************\n");
	System.out.println("\n       Thank you for choosing the web engine\n");
	System.out.println("");
	System.out.println("\n         Hope you had a wonderful experience \n");
	System.out.println("\n*************************************************************\n");
	//Clean webFiles and Text Files
	 if(os.indexOf("wind") >= 0){
			FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_windows)); 
			FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_windows)); 
			 }else {
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.HtmlFiles_folder_mac)); 
					FileUtils.cleanDirectory(new File(WEB_CRAWLING.TxtFiles_folder_mac)); 
			 }
 
	}

}	
	

