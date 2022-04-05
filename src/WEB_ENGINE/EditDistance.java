package WEB_ENGINE;

public class EditDistance {
	public static int editDistance(String first_word, String second_word) {
		//Variables for storing length of words by using length() method.
		int length_word_1 = first_word.length();
		int length_word_2 = second_word.length();
	 
		//We are initialization 2D array for storing each char of first_word and second_word.
		int init_arr [][] = new int[length_word_1 + 1][length_word_2 + 1];
	 
		//This loop is for reading all the char for the first_word.
		for (int i = 0; i <= length_word_1; i++) {
			init_arr [i][0] = i;
		}	
		
		//This loop is for reading all the char for the second_word.
		for (int j = 0; j <= length_word_2; j++) {
			init_arr [0][j] = j;
		}
		
		//This loop is used for checking and matching the char position for both the first_word and second_word.
		for (int i = 0; i < length_word_1; i++) 
			for (int j = 0; j < length_word_2; j++) 
				
				//Condition for checking whether both the char are at same position or not!
				if(first_word.charAt(i) == second_word.charAt(j)) 
					init_arr [i + 1][j + 1] = init_arr [i][j];
		
				//This condition is used for replacement, insertion and deletion of words.
				else {
					int word_replace = init_arr [i][j] + 1;
					int word_push = init_arr [i][j + 1] + 1;
					int word_remove = init_arr [i + 1][j] + 1;
			
					init_arr [i + 1][j + 1] = Math.min(Math.min(word_replace, word_push), word_remove);
				}	
		return init_arr [length_word_1][length_word_2];
	}
	


}
