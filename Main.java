import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Formatter;
import java.io.FileWriter;
public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Scanner reader=null;
        System.out.println("Please enter the file name: ");
        String filename=sc.nextLine();
        try{
            reader=new Scanner(Paths.get("filename.txt"));
        }
        catch(IOException e){

        }
    }
}