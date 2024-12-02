import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc=new Scanner(System.in);
        try{
            System.out.println("Please enter the file name: ");
            File file=new File(sc.nextLine());
            BufferedReader br=new BufferedReader(new FileReader(file));


        }
    }
}
