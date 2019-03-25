import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";
        int maxWords = 100;

        if (args.length > 0)
            fileName = args[0];
        if (args.length > 1)
            maxWords = Integer.parseInt(args[1]);

        String[] words = new String[maxWords];
        int n = 0;

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
                        if (!isInList(words, word, n)) {
                            words[n] = word;
                            n++;
                        }
                    }
                    i++;
                }
            }
            printString(words, maxWords);
            input.close();

        } catch (FileNotFoundException e) {
            System.err.print("Could not find the input file");
            System.exit(1);
        }
    }
    public static void printString(String[] list, int n) {
        System.out.printf("---- --- %d items in the list -------\n", n);
        for (int i=0; i<n; i++) {
            System.out.printf("[%d] %s\n", n, list[i]);
        }
    }

    public static boolean isInList(String[] haystack, String needle, int n) {
        boolean found = false;

        int i = 0;
        while (!found && i < n) {
            found = needle.equals(haystack[i]);
            i++;
        }
        return found;
    }
}
