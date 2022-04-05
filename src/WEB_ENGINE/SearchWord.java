package WEB_ENGINE;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;



public class SearchWord {
	private static Scanner sc = new Scanner(System.in);
	//This function will find all the words from the documents and print the name of the file in which word is present	
	public static int wordSearch(String txt, String word, String FName)throws Exception {
		
		
		
		//Calculate the number of time word is repeating in the file
		int occurences = 0;
		//Offset initiazation
		int initial = 0;
		//Initializing BoyerMoore Algorithm
		BooyerMoore boyerMoore = new BooyerMoore(word);
		
		for (int i = 0; i <= txt.length(); i += initial + word.length()) {
		//Searching the word in a substring of data
			initial = boyerMoore.search(word, txt.substring(i));
			if ((initial + i) < txt.length()) {
				//Incrementing occurences if word found in doc
				occurences++;
			}
		}
		if (occurences != 0) {
			//Printing FIle name and its count
			System.out.println("File --> " + FName);
			System.out.println("occurences -----> "+occurences);
		}
	return occurences;
		
		
	}
	
	public static String search1(String url) throws Exception{
		
		Hashtable<String, Integer> pages = new Hashtable<String, Integer>();
		String choose = "yes";
		System.out.println("===> Saving Html Files....");
		WEB_CRAWLING.Website_Crawler(url,0);
		System.out.println("===> Task Completed");
		System.out.println("===> Dictionary has been created");
		Dictionary.create_Dictionary();
		do {
			System.out.println("");
			System.out.println("Please input the word to search : ");
			String wordToSearch = sc.next();
			System.out.println("");
		// Path for the text File
		File Path = new File("textfiles");
		//Get the list of all the files
		File[] allfile_list = Path.listFiles();
		//Number of occurences counter
		int occurences = 0;
		int total_file = 0;
		
		for(int i =0;i<allfile_list.length;i++ ) 
		{
			//System.out.println(str);
			//System.out.println(allfile_list[i]);
		
			In data = new In(allfile_list[i].getAbsolutePath());
			//REad all the data from the sing file
			String txt = data.readAll();
			data.close();
	
			//File name
			String filename = allfile_list[i].getName();
			//Calling wordSearch FUnction to search the given word from file
			occurences = wordSearch(txt,wordToSearch,filename);
		
			if (occurences != 0) {
				pages.put(filename, occurences);
			//Total number of files in which word is present
				total_file++;
			}

		}
	if(total_file== 0) {
		System.out.println("No file found");
		System.out.println("Suggested word");
		Suggestion.suggestion_of_word(wordToSearch);
		}else {	
		}
		System.out.println("\n Another Search? Type yes or no)?");
		choose = sc.next();
		}while(choose.equals("yes"));
		System.out.println("Main Menu? Type Yes or no ");
		return sc.next();
	}


}
