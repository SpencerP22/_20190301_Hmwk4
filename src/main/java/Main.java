import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";
        int maxWords = 100;
        //checks args for file name and maximum word list length
        //if neither are found, the program defaults to input.txt and a limit of 100
        if (args.length > 0)
            fileName = args[0];
        if (args.length > 1)
            maxWords = Integer.parseInt(args[1]);

        String[] words = new String[maxWords];
        //n is used to keep track of the total number of unique words read
        int n = 0;
        String longestWord = "";
        double totalChars = 0.0;

        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                line = line.toLowerCase();
                String[] parts = line.split("[^a-z]+");
                int i = 0;

                while (i < parts.length && n < maxWords) {
                    String word = parts[i];
                    if (word.length() > 0) {
                        //checks if word is in list of words
                        if (!isInList(words, word, n)) {
                            words[n] = word;
                            //checks current word against longest word
                            if(word.length() > longestWord.length())
                                longestWord = word;
                            totalChars += word.length();
                            n++;
                        }
                    }
                    i++;
                }
            }
            //adds single character words to its own array of strings
            String[] singleCharWords = new String[26];
            int j=0;
            for(int i=0; i<n; i++) {
                if(words[i].length() == 1){
                    singleCharWords[j] = words[i];
                    j++;
                }
            }
            //adds the longest words to its own array of strings
            String[] longestWords = new String[n];
            int k=0;
            for(int i=0; i<n; i++) {
                if(words[i].length() == longestWord.length()) {
                    longestWords[k] = words[i];
                    k++;
                }
            }

            sortArray(words, n);
            print(words, n);
            print(singleCharWords,j);
            print(longestWords, k);
            printSummary(n, totalChars, singleCharWords, j, longestWords, k);

            input.close();
        } catch (FileNotFoundException e) {
            System.err.print("Could not find the input file");
            System.exit(1);
        }
    }
    //sorts the list alphabetically using an insertion sort
    public static void sortArray(String[] list, int n) {
        for (int i=1; i<n; i++) {
            String nextValueToInsert = list[i];
            int j = i-1;

            while (j>=0 && nextValueToInsert.compareToIgnoreCase(list[j]) < 0) {
                list[j+1] = list[j];
                j--;
            }
            list[j+1] = nextValueToInsert;
        }
    }
    //takes an array and prints it out line by line for each string in array
    public static void print(String[] list, int n) {
        System.out.printf("-------- %d items in the list -------\n", n);
        for (int i=0; i<n; i++) {
            System.out.printf("[%d] %s\n", i, list[i]);
        }
    }
    //checks if word(needle) is in the array of words(haystack)
    public static boolean isInList(String[] haystack, String needle, int n) {
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            found = needle.equals(haystack[i]);
            i++;
        }
        return found;
    }
    //converts an array of strings to one string separated by commas
    public static String listToString(String[] words, int n) {
        String result = "";
        for(int i=0; i<n; i++) {
            result += (words[i] + ", ");
        }
        return result;
    }
    //prints out a summary of the list of unique words.
    public static void printSummary(int listLength, double totalChars, String[] singleCharWords, int j, String[] longestWords, int k) {
        System.out.println("---------- Input Summary ----------");
        System.out.printf("Total Unique Words: %d\nAverage Word Length: %2.1f\nSingle Character words: %s\nLongest Word Length: %d\nLongest Words: %s",
                listLength, totalChars/listLength, listToString(singleCharWords, j), longestWords[0].length(), listToString(longestWords, k));
    }
}
