public class CountryMap {
    private int numCities;
    private City[] city;
    private int[][] RouteTable;

    public CountryMap(int numCities){
        if(numCities<=0){
            throw new IllegalArgumentException("Error:The number of cities must be greater than zero!");
        }

        this.numCities=numCities;
        this.city=new City[numCities];
        this.RouteTable=new int[numCities][numCities];

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                RouteTable[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
            }
        }
    }


    public void addCity(String citylabel){
        city[numCities]= new City(citylabel);
    }


    public int findPosition(String citylabel){
        for(int i=0;i<numCities;i++){
            if(city[i].getCitylabel().equals(citylabel));
            {
                return i;
            }
        }
        return 0;
    }


    public void addRoute(String city1,String city2,int time){
        int position1=findPosition(city1);
        int position2=findPosition(city2);
        if(position1==0 || position2==0){
            throw new IllegalArgumentException("Error:The city couldn't find!");
        }
        RouteTable[position1][position2]=time;
        RouteTable[position2][position1]=time;
    }



    public int[][] getRouteTable(){
        return RouteTable;
    }



}
