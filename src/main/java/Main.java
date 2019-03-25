import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String fileName = "input.txt";
        int maxWords = 100;
        if(args.length > 0)
            fileName = args[0];
        if(args.length > 1)
            maxWords = Integer.parseInt(args[1]);

        try {
            Scanner input = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.print("Could not find the input file");
            System.exit(1);
        }
    }
}
