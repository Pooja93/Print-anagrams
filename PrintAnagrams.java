import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class PrintAnagrams {

	private static HashMap<String, String> anagramMap = new HashMap<>();
	private static final String FILE = "C://Users/test/Desktop/print_anagrams/sowpods.txt";
	
	private static final String OUTPUT_FILE = "C://Users/test/Desktop/print_anagrams/output.txt";
	private static final String CHARSET = "UTF-8";
	
	private static String getWordSortedByCharacter(String in) {
		char[] chars = in.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	
	private static void addtoAnagramMap(String sortedWord, String currentWord) {
		if ( anagramMap.containsKey(sortedWord) ) {
			anagramMap.put(sortedWord, anagramMap.get(sortedWord) + " " + currentWord);
		} 
		else {
			anagramMap.put(sortedWord, currentWord);
		}
	}
	
	private static boolean hasAnagrams(String value) {
		return value.contains(" ");
	}

	public static void main(String args[]) {
		BufferedReader fileReader = null;
		String nextWord = "";
		String sortedWord = "";
		try {
			fileReader = new BufferedReader(new FileReader(FILE));
			while ( (nextWord = fileReader.readLine()) != null ) {
				sortedWord = getWordSortedByCharacter(nextWord);
				addtoAnagramMap(sortedWord, nextWord);
			}

			PrintWriter writer = new PrintWriter(OUTPUT_FILE, CHARSET);
			for ( Map.Entry<String, String> entry : anagramMap.entrySet() ) {
				if ( hasAnagrams(entry.getValue()) ) {
					writer.println(entry.getValue());
				}
			}
			writer.close();
		} 
		catch (Exception exp) {
			System.err.println(exp.getMessage());
		}
	}
}
