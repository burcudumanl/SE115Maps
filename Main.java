import java.util.Scanner;
import java.io.*;
import java.util.regex.*;
public class Main {
    public static void check(String s){
        try{
            File file=new File(s);
            BufferedReader br=new BufferedReader(new FileReader(file));


        }
        catch(IOException e) {
            System.out.println("Error reading or writing files: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the file name: ");
        String s=sc.nextLine();

    }
}
