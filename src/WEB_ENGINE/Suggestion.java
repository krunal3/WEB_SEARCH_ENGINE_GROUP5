package WEB_ENGINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Suggestion {
public static void suggestion_of_word(String word_insert) throws FileNotFoundException, IOException {
		
		//Initialization of file for storing the file dictionary.txt.
		File init_file = new File("dictionary.txt");
		
		//Reading the stored file with the help of BufferReader.
		try (BufferedReader file_reader = new BufferedReader(new FileReader(init_file))) {
			
			//Here, for storing words dynamically, ArrayList with type String is created.
			ArrayList<String> dictionary_for_line = new ArrayList<String>();
			String read_line;
			
			//This loop will run till the file is not completely read.
			while((read_line = file_reader.readLine())!=null) {
				
				//for each line while is read in the file will be added to dictionary.
				dictionary_for_line.add(read_line);	
			}
			//as soon as the file is read completely the loop will break and the file will be closed.
			file_reader.close();
			
			//Initialization of variables for edit distance and both the words.
			int edit_distance;
			int mini_editDistance_1=10, mini_editDistance_2=10;
			String first_word=null, second_word=null;
			
			//this for each loop is used find edit distance in the dictionary.
			for(String local_word : dictionary_for_line){
				edit_distance = EditDistance.editDistance(local_word, word_insert);
				
				//This is a condition where it will check the edit distance and minimum edit distance.
				if(edit_distance < mini_editDistance_2) {
					if(edit_distance < mini_editDistance_1) {
						mini_editDistance_1 = edit_distance;
						first_word = local_word;
					}
					else {
						mini_editDistance_2 = edit_distance;
						second_word = local_word;
					}
				}
			}
			System.out.println("Are you searching for this word: "+ first_word +" or "+ second_word);
			
		}
	}
	

	

}
