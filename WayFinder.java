public class WayFinder {
    public static String RouteFinder(CountryMap map,String startCity,String finalCity) {
        int[][] table = map.getRouteTable();
        int numCities = table.length;
        String[] cityLabel = map.getLabel();
        boolean[] went = new boolean[numCities];

}
