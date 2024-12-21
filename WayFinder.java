import java.io.FileWriter;
import java.io.IOException;
public class WayFinder {
    private CountryMap countryMap;
    private int[] distances;
    private boolean[] visited;
    private int[] previous;

    public WayFinder(CountryMap countryMap) {
        this.countryMap = countryMap;
    }

    //---------------------------------------------------------FİND FASTEST WAY------------------------------------------------
    public String findFastestRoute(String startLabel, String endLabel) {
        int numRoutes = countryMap.getNumRoutes();
        char[] cityLabels = getCityLabels();
        int cityCount = cityLabels.length;
        distances = new int[cityCount];
        visited = new boolean[cityCount];
        previous = new int[cityCount];
        // Set initial distances to infinity and mark all cities as unvisited
        for (int i = 0; i < cityCount; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;
        }
        int StartPosition = getCityPosition(startLabel.charAt(0), cityLabels);
        distances[StartPosition] = 0;
        //Find the shortest way to all cities
        for (int i = 0; i < cityCount - 1; i++) {
            int current = getMinDistance(cityCount);
            if (current == -1) break;
            visited[current] = true;
            // Update distances for neighbors of the current city
            for (int j = 0; j < numRoutes; j++) {
                char city1 = countryMap.getRouteTable1()[j].getCity1();
                char city2 = countryMap.getRouteTable2()[j].getCity2();
                int time = countryMap.getTimes()[j];
                int neighborPosition = -1;
                if (city1 == cityLabels[current]) {
                    neighborPosition = getCityPosition(city2, cityLabels);
                } else if (city2 == cityLabels[current]) {
                    neighborPosition = getCityPosition(city1, cityLabels);
                } else {
                    continue;
                }
                if (!visited[neighborPosition] && distances[current] + time < distances[neighborPosition]) {
                    distances[neighborPosition] = distances[current] + time;
                    previous[neighborPosition] = current;
                }
            }
        }
        String result = PathBuilder(StartPosition, getCityPosition(endLabel.charAt(0), cityLabels), cityLabels);
        FileWriter(result, "output.txt");
        return result;
    }

    // Checks if a character exists in an array
    private boolean ArrayControl(char c, char[] array, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }

    // Retrieves all unique city labels from the CountryMap
    private char[] getCityLabels() {
        int numRoutes = countryMap.getNumRoutes();
        char[] tempLabels = new char[numRoutes * 2];
        int position = 0;
        // Add city labels
        for (int i = 0; i < numRoutes; i++) {
            char city1 = countryMap.getRouteTable1()[i].getCity1();
            char city2 = countryMap.getRouteTable2()[i].getCity2();

            if (!ArrayControl(city1, tempLabels, position)) {
                tempLabels[position++] = city1;
            }
            if (!ArrayControl(city2, tempLabels, position)) {
                tempLabels[position++] = city2;
            }
        }

        // A final array which contains only unique labels
        char[] cityLabels = new char[position];
        for (int i = 0; i < position; i++) {
            cityLabels[i] = tempLabels[i];
        }

        return cityLabels;
    }

    // Retrieves the position of a city in the cityLabels array
    private int getCityPosition(char label, char[] cityLabels) {
        for (int i = 0; i < cityLabels.length; i++) {
            if (cityLabels[i] == label) {
                return i;
            }
        }
        return -1;
    }

    // Retrieves the index of the unvisited city with the smallest distance
    private int getMinDistance(int cityCount) {
        int MinPosition = -1, minValue = Integer.MAX_VALUE;
        for (int i = 0; i < cityCount; i++) {
            if (!visited[i] && distances[i] < minValue) {
                minValue = distances[i];
                MinPosition = i;
            }
        }
        return MinPosition;
    }

    // Constructs the path from start to end position
    private String PathBuilder(int StartPosition, int EndPosition, char[] cityLabels) {
        if (distances[EndPosition] == Integer.MAX_VALUE) {
            return "No route found!";
        }

        StringBuilder path = new StringBuilder();
        int current = EndPosition;
        while (current != -1) {
            path.insert(0, cityLabels[current]);
            current = previous[current];
            if (current != -1) path.insert(0, " -> ");
        }

        return "Fastest Way: " + path + "\nTotal Time: " + distances[EndPosition] + " min";
    }

    // Writes the result to an output file
    private void FileWriter(String result, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(result);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
