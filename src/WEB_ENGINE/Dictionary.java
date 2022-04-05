package WEB_ENGINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	public static void create_Dictionary() {
		//System.out.println("Creating dictionary");
		//Variable initialization
		String current_line, next_line;
		//This is used to store the files located inside the folder cleanedFile.
		File text_folder = new File("textfiles/");
		File[] files = text_folder.listFiles();
		//Here set and array list is made for adding words inside the dictionary.
		Set<String> set = new HashSet<>();
		ArrayList<String> new_dictionary = new ArrayList<String>();
		
		//This loop runs for each file present inside the folder cleanedFile.
		for (File file : files)
		{
			try {
				//BUfferReader is used to read the file.
				BufferedReader file_reader = new BufferedReader(new FileReader(file));
				//This loop will run till the end of the file.
				while((current_line = file_reader.readLine()) != null) {
					@SuppressWarnings("resource")
					//This is the scanner object for the current line.
					Scanner search = new Scanner(current_line);
					//This loop will run utill the last line of of file.
						while (search.hasNext()) {
							
							//This will make all the content into lowercase and update that line into HashSet.
							next_line = search.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
							//System.out.println(next_line);
							set.add(next_line);
						}
					}
				file_reader.close();
				//System.out.println("Dictionary created");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Here, all the lines will store into our ArrayList of new_dictionary and also will sort the fictionary.
		new_dictionary.addAll(set);
		Collections.sort(new_dictionary);
		
		try {
			//FileWriter will write the words inside the file dictionary.txt.
			FileWriter file_writer = new FileWriter("dictionary.txt"); 
			//This loop will find each word and separate every word into new line.
			for(String new_word: new_dictionary) 
				file_writer.write(new_word + System.lineSeparator());	
			file_writer.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Dictionary.create_Dictionary();
		System.out.println("Dictionary has been created successfully!");
	}

}
