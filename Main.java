import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
public class Main{
    public static void main(String[] args) {
        CountryMap countryMap= new CountryMap();
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

            int CityNumber = Integer.parseInt(info[0]); //this CityNumber object store the number of cities.
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
                        CountryMap route = new CountryMap();
                        route.setCity1(line.substring(0, 1).charAt(0));
                        RouteTable1[i]=route;
                        route.setCity2(line.substring(2, 3).charAt(0));
                        RouteTable2[i]=route;

                        Times[i]=Integer.parseInt(line.substring(4,line.length()));

                        line = reader.nextLine();
                    }

                    System.out.println(" ");

                }
                if(currentLine==totallines){
                    String StartLabel =line.substring(0,1);
                    String FinishLabel =line.substring(2,3);

                }

            }

            countryMap.setRouteTable1(RouteTable1);
            countryMap.setRouteTable2(RouteTable2);
            countryMap.setTimes(Times);


            countryMap.deneme();

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