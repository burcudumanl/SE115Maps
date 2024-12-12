public class WayFinder {
    private CountryMap map;

    public WayFinder(CountryMap map){
        this.map=map;
    }



    public String FindShorthestWay(String cityStart,String cityEnd){

        int End=map.findPosition(cityEnd);
        int Start=map.findPosition(cityStart);


        int[][] routeTable=map.getRouteTable();
        String[] labels=map.getLabel();
        int CitNum=map.getLabel().length;


        boolean[] processed =new boolean[CitNum];
        int[] PrevCity =new int[CitNum];
        int[] distances =new int[CitNum];





    }

}
