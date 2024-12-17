public class WayFinder {
    private CountryMap map;
    private int[] distances;
    private boolean[] visited;
    private int[] predecessors;

    public WayFinder(CountryMap map) {
        this.map = map;
    }

    public String findFastestRoute(String startLabel, String endLabel) {
        int numRoutes = map.getNumRoutes();
        char[] cityLabels = getCityLabels();
        int cityCount = cityLabels.length;

        distances = new int[cityCount];
        visited = new boolean[cityCount];
        predecessors = new int[cityCount];


        for (int i = 0; i < cityCount; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            predecessors[i] = -1;
        }


        int startIndex = getCityIndex(startLabel.charAt(0), cityLabels);
        distances[startIndex] = 0;

        for (int i = 0; i < cityCount - 1; i++) {
            int current = getMinDistanceIndex(cityCount);
            if (current == -1) break;
            visited[current] = true;


            for (int j = 0; j < numRoutes; j++) {
                char city1 = map.getRouteTable1()[j].getCity1();
                char city2 = map.getRouteTable2()[j].getCity2();
                int time = map.getTimes()[j];

                int neighborIndex = -1;
                if (city1 == cityLabels[current]) {
                    neighborIndex = getCityIndex(city2, cityLabels);
                } else if (city2 == cityLabels[current]) {
                    neighborIndex = getCityIndex(city1, cityLabels);
                } else {
                    continue;
                }

                if (!visited[neighborIndex] && distances[current] + time < distances[neighborIndex]) {
                    distances[neighborIndex] = distances[current] + time;
                    predecessors[neighborIndex] = current;
                }
            }
        }

        return buildPath(startIndex, getCityIndex(endLabel.charAt(0), cityLabels), cityLabels);
    }

    private char[] getCityLabels() {

        int numRoutes = map.getNumRoutes();
        char[] tempLabels = new char[numRoutes * 2];
        int index = 0;

        for (int i = 0; i < numRoutes; i++) {
            char city1 = map.getRouteTable1()[i].getCity1();
            char city2 = map.getRouteTable2()[i].getCity2();

            if (!isInArray(city1, tempLabels, index)) {
                tempLabels[index++] = city1;
            }
            if (!isInArray(city2, tempLabels, index)) {
                tempLabels[index++] = city2;
            }
        }


        char[] cityLabels = new char[index];
        for (int i = 0; i < index; i++) {
            cityLabels[i] = tempLabels[i];
        }

        return cityLabels;
    }


    private boolean isInArray(char c, char[] array, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }

    private int getCityIndex(char label, char[] cityLabels) {
        for (int i = 0; i < cityLabels.length; i++) {
            if (cityLabels[i] == label) {
                return i;
            }
        }
        return -1;
    }

    private int getMinDistanceIndex(int cityCount) {
        int minIndex = -1, minValue = Integer.MAX_VALUE;
        for (int i = 0; i < cityCount; i++) {
            if (!visited[i] && distances[i] < minValue) {
                minValue = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private String buildPath(int startIndex, int endIndex, char[] cityLabels) {
        if (distances[endIndex] == Integer.MAX_VALUE) {
            return "No route found!";
        }

        StringBuilder path = new StringBuilder();
        int current = endIndex;
        while (current != -1) {
            path.insert(0, cityLabels[current]);
            current = predecessors[current];
            if (current != -1) path.insert(0, " -> ");
        }

        return "Fastest Way: " + path + "\nTotal Time: " + distances[endIndex] + " min";
    }
}
