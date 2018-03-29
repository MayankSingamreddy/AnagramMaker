import java.util.ArrayList;

/**
 *	AnagramMaker - a program to find all possible anagrams from a user
 *  entered phrase, with the user choice of amount of words in the anagram,
 *  along with the amount of anagrams printed.
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and OpenFile classes
 *
 *	@author		Mayank Singamreddy
 *	@since		January 16th, 2018
 */
public class AnagramMaker
{
	private final String FILE_NAME = "randomWords.txt";	// file containing all words

	private WordUtilities wordUtil;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters

	// variables for constraining the print output of AnagramMaker
	private int numberOfWords;		// the number of words in a phrase to print
	private int maxAmtPhrases;		// the maximum number of phrases to print
	private int numberOfPhrases;		// the number of phrases that have been printed

	/*	Initialize the database inside of Word Utilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
			wordUtil = new WordUtilities();
			wordUtil.readWordsFromFile(FILE_NAME);
			wordUtil.sortWords();
	}

	public static void main(String[] args) {
			AnagramMaker am = new AnagramMaker();
			am.run();//run the anagram maker
	}

	/*
	 *	The routine that prints the introduction and runs the anagram-maker.
	 */
	public void run()
	{
			printIntroduction(); //print out introduction
			boolean quit = false; //boolean to break out of loop
			while(!quit) // while condition is not broken
			{
				String phrase = Prompt.getString("Word(s), name, or phrase (q to quit)");
				if(phrase.equals("q")) // if the user enters q
				{
					System.out.println("\nThanks for using AnagramMaker!");
					quit = true; //then quit is true
				}
				else
				{
					//what is the maxinmum amount of words you would like in the anagrams
					numberOfWords = Prompt.getInt("Number of words in anagram");
					//what is the maximum amount you want the program to create/pint
					maxAmtPhrases = Prompt.getInt("Maximum number of phrases to print");
					numberOfPhrases = 0; //reset
					runAnagramMaker(phrase); //create anagrams on phrase
				}
			}
	}

	/**
	 *	Print the introduction to AnagramMaker to the user
	 *  explain the many choices the user has
	 */
	public void printIntroduction() {
			System.out.println("\nWelcome to ANAGRAM MAKER");
			System.out.println
				("\nProvide a word, name, or phrase and out comes their anagrams.");
			System.out.println("You can choose the number of words in the anagram.");
			System.out.println("You can choose the number of anagrams shown.");
			System.out.println("\nLet's get started!");
	}

	/**
         *  Prompt the user for their phrase to make an anagram out of
				 *	then create those anagrams
         *
         *  @param phrase  the user's phrase
         */
	public void runAnagramMaker(String phrase){
			//run through the phrase
			for (int i = 0; i < phrase.length(); i++) 
			{
				//if it is not a letter
				if (!Character.isLetter(phrase.charAt(i))) 
				{
					phrase = phrase.substring(0, i) + 
								phrase.substring(i+1);//take out
					i--;
				}
			}

			ArrayList<String> anagram = new ArrayList<String>();
				//created to hold the beginning of each single anagram
			findAnagrams(phrase, anagram);
				//find the anagrams for the phrase
	}
	/**
	         *  This method Recursively finds the
			 *  various anagrams for a specific word, deleting characters
			 *  going lower and lower until none is left
	         *
	         *  @param phrase  user's phrase
	         *  @param anagram  arraylist of possible words
	         */
	public void findAnagrams(String phrase, ArrayList<String> anagram){
			if(numberOfPhrases == maxAmtPhrases)
				return;
			if(phrase.length() != 0) //if there are letters left
			{
				ArrayList<String> words = wordUtil.allWords(phrase);
				for(String s: words) //for each string in words
				{
					if(anagram.size() < numberOfWords) //if the size is 
						   //greater than the user specified max number
					{
						anagram.add(s);
						int[] chars = new int[26];
						char[] key = 
							"abcdefghijklmnopqrstuvwxyz".toCharArray();
						//run through phrase
						for(int i = 0; i < phrase.length(); i++) 
							chars[phrase.charAt(i) - 'a']++;
						//run through String s
						for(int i = 0; i < s.length(); i++)
							chars[s.charAt(i) - 'a']--;
						String newPhrase = ""; //create new string

						for(int i = 0; i < chars.length; i++)
							while(chars[i] != 0)
							{
								newPhrase += key[i];
								chars[i]--;
							}
						//find anagrams from newPhrase
						findAnagrams(newPhrase, anagram); 
						// remove from the end
						anagram.remove(anagram.size() - 1); 
					}
				}
			}
			//if there are characters left in the phrase
			if(phrase.length() == 0)
			{
				for(String s: anagram)
					System.out.print(s + " "); //print out every anagram
				System.out.println();
				numberOfPhrases++; //add to the counter
				if(numberOfPhrases == maxAmtPhrases)
					System.out.println("\nStopped at " +
					 maxAmtPhrases + " anagrams\n");
			}
		}
}