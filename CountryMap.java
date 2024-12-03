public class CountryMap {
    private int numCities;
    private City[] city;
    private int[][] time;

    public CountryMap(int numCities){
        this.numCities=numCities;
        this.city=new City[numCities];
        this.time=new int[numCities][numCities];

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                time[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
            }
        }
    }



}
