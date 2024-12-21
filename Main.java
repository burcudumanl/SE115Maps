import java.io.IOException;
import java.io.File;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        // Check if a file name is provided as a command line argument
        if (args.length == 0) {
            System.out.println("Error: No file name provided.");
            System.out.println("Usage: java Main <filename>");
            return;
        }
        // Initialize necessary variables and objects
        CountryMap countryMap= new CountryMap();
        Scanner s=new Scanner(System.in);
        String filename=args[0];
        Scanner reader=null;
        //---------------------------------------------------------READ THE FILE------------------------------------------------
        try {
            reader = new Scanner(new File(filename));
            int counter = 0;
            int totallines=0;
            String[] info = new String[100]; //this info array stores the lines of text file.
            while (reader.hasNextLine()) {
                info[counter] = reader.nextLine();
                counter++;
                totallines++;
            }
            int CityNumber = Integer.parseInt(info[0]); //this CityNumber variable parse and store the number of cities.
            String[] Labels = info[1].toUpperCase().split(" ");/* this Labels array stores city labels.
                                                                     Prevents confusion if lowercase letters are entered*/

            //This array store cities labels and isVisited information as city object.
            City [] cityArray = new City[Labels.length];
            for (int i = 0; i < Labels.length; i++) {
                City city=new City();
                city.setCitylabel(Labels[i].charAt(0));
                city.setVisited(false);
                cityArray[i] = city;
                cityArray[i].getCitylabel();
            }
            // Validate the number of city labels matches the declared number of cities
            int size= Labels.length;
            try{
                if(CityNumber>size){
                    throw new IOException("<Error line:" + 2 + ">" + "<Missed number of city labels, please control your routes.>");
                }
                if(CityNumber<size){
                    throw new IOException("<Error line:" + 2 + ">" + "<Extra number of city labels, please control your routes.>");
                }

            }
            catch (IOException e){
                System.err.println(e.getMessage());
                return;
            }

            int NumRoutes=Integer.parseInt(info[2]);//parse and contain the number of routes
            // Initialize arrays for routes and times
            int[] Times=new int[NumRoutes];
            CountryMap [] RouteTable1 = new CountryMap[NumRoutes];
            CountryMap [] RouteTable2 = new CountryMap[NumRoutes];
            String StartLabel=new String();
            String FinishLabel=new String();

            //---------------------------------------------------------REREAD THE FILE------------------------------------------------
            reader = new Scanner(new File(filename));
            int currentLine = 0;
            int startLine = 4;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                currentLine++;
                //fill the arrays with parsing the route data and validate the route format
                if (currentLine >= startLine && currentLine < totallines) {
                    for (int i = 0; i < NumRoutes; i++) {
                        String[] RouteParts=line.split(" ");
                        try {
                            if (RouteParts[0].length() != 1|| RouteParts[1].length()!=1) {
                                throw new IOException("<Error line: " + (currentLine+i) + ">"+ "<Invalid format! Expected: <City1> <City2> <Time>>");
                            }
                        }catch(IOException e){
                            System.err.println(e.getMessage());
                            return;
                        }
                        CountryMap route = new CountryMap();
                        route.setCity1(line.substring(0, 1).toUpperCase().charAt(0)); //Prevents confusion if lowercase letters are entered.
                        RouteTable1[i] = route;
                        route.setCity2(line.substring(2, 3).toUpperCase().charAt(0)); //Prevents confusion if lowercase letters are entered
                        RouteTable2[i] = route;

                        Times[i] = Integer.parseInt(line.substring(4, line.length()));

                        line = reader.nextLine();
                    }
                }
                //Validate the number of routes
                try {
                    if ((totallines - startLine) < NumRoutes) {
                        throw new IOException("<Error line:" + (totallines) + ">" + "<Missed route, please control your routes.>");
                    }
                    if ((totallines - startLine) > NumRoutes) {
                        throw new IOException("<Error line:" + (totallines - 1) + ">" + "<Extra route,please check your routes.> ");
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    return;
                }
                if (currentLine == 4) {
                    currentLine = totallines;
                }
                // take start and finish labels
                if(currentLine==totallines){
                    StartLabel =line.substring(0,1).toUpperCase(); /*Prevents confusion if lowercase letters are entered
                                                                    It takes the start city from th text file.*/

                    FinishLabel =line.substring(2,3).toUpperCase(); /*Prevents confusion if lowercase letters are entered
                                                                     It takes the finish city from the text file. */
                }

            }
            //set the data to CountryMap
            countryMap.setRouteTable1(RouteTable1);
            countryMap.setRouteTable2(RouteTable2);
            countryMap.setTimes(Times);
            countryMap.setStartLabel(StartLabel);
            countryMap.setFinishLabel(FinishLabel);
            countryMap.setNumRoutes(NumRoutes);
            WayFinder wayFinder = new WayFinder(countryMap);
            try {
                for (int i = 0; i < NumRoutes; i++) {
                    boolean Cit1IsValid = false;
                    boolean City2IsValid = false;

                    char city1 = countryMap.getRouteTable1()[i].getCity1();
                    char city2 = countryMap.getRouteTable2()[i].getCity2();

                    for (String cityLabel : Labels) {
                        if (cityLabel.equals(String.valueOf(city1))) {
                            Cit1IsValid = true;
                            break;
                        }
                    }
                    for (String cityLabel : Labels) {
                        if (cityLabel.equals(String.valueOf(city2))) {
                            City2IsValid = true;
                            break;
                        }
                    }
                    // It gives error if any city label in route is invalid.
                    if (!Cit1IsValid) {
                        throw new IOException("<Error line: " + (4 + i) + ">"+ "<Invalid start label: >" + city1+" >");
                    }
                    if (!City2IsValid) {
                        throw new IOException("<Error line: " + (4 + i) + ">"+ "<Invalid finish label: " + city2+" >");
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }
            // Find and print the fastest way
            String result = wayFinder.findFastestRoute(StartLabel,FinishLabel); //It takes the result from WayFinder class.
            System.out.println("File read is successful! ");
            System.out.println(result);
            System.out.println("--------------------------------");

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