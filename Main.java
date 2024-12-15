import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Scanner s=new Scanner(System.in);
        Scanner reader=null;
        System.out.println("Please enter the file name: ");
        String filename=s.nextLine();
        try {
            reader = new Scanner(Paths.get(filename));
            int counter = 0;
            int totallines=0;
            String[] info = new String[50];
            while (reader.hasNextLine()) {
                info[counter] = reader.nextLine();
                counter++;
                totallines++;
            }
            CountryMap CityNumber = new CountryMap(Integer.parseInt(info[0])); //this CityNumber object store the number of cities.
            String[] Labels = info[1].split(" ");// this Labels array stores city labels.

            //This array store cities labels and isVisited information as city object.
            City [] cityArray = new City[Labels.length];
            for (int i = 0; i < Labels.length; i++) {
                City city=new City();
                city.setCitylabel(Labels[i].charAt(0));
                city.setVisited(false);
                cityArray[i] = city;
                cityArray[i].getCitylabel();
            }

            int NumRoutes=Integer.parseInt(info[2]);


            int[] Times=new int[NumRoutes];
            CountryMap [] RouteTable1 = new CountryMap[NumRoutes];
            CountryMap [] RouteTable2 = new CountryMap[NumRoutes];

            reader = new Scanner(Paths.get(filename));
            int currentLine = 0;
            int startLine = 4;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                currentLine++;

                if (currentLine >= startLine && currentLine < totallines) {

                    for(int i=0;i<NumRoutes;i++) {
                            CountryMap route1 = new CountryMap();
                            route1.setCity1(line.substring(0, 1).charAt(0));
                            RouteTable1[i]=route1;
                            System.out.println(RouteTable1[i].getCity1());


                    }

                    for(int j=0;j<NumRoutes;j++) {
                        CountryMap route2 = new CountryMap();
                        route2.setCity2(line.substring(2, 3).charAt(0));
                        RouteTable2[j]=route2;

                        System.out.println(RouteTable2[j].getCity2());
                    }

                    for(int i=0;i<NumRoutes;i++){
                        Times[i]=Integer.parseInt(line.substring(4,line.length()));
                        System.out.println(Times[i]);
                    }

                    System.out.println(" ");

                    String StartLabel =line.substring(0,1);
                    String FinishLabel =line.substring(2,3);
                    System.out.println(StartLabel);
                    System.out.println(FinishLabel);



                }

            }






        }
        catch(IOException e){
            e.getMessage();
            System.out.println("Invalid txt name");
        }
        finally{
            if(reader !=null){
                reader.close();
            }
        }
    }
}