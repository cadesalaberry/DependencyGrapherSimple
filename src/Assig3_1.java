import java.io.*;
import java.util.*;

// Framework for assignment 3, question 1c
public class Assig3_1 {
    public static Random r; // seeded or not 

    // Starting point. Accepts 1 or 2 command-line arguments,
    // the number of tasks (n) and an optional random seed may 
    // be passed in for repeatable results.
    public static void main(String[] args) {
        try {
            // arg 1 is n
            int n = Integer.parseInt(args[0]);
            if (args.length>1) {
                long seed = Long.parseLong(args[1]);
                r = new Random(seed);
            } else {
                r = new Random();
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
