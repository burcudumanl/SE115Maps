public class CountryMap {
    private int NumRoutes;
    private char city1;
    private char city2;
    private int[] times;
    private CountryMap[] RouteTable1;
    private CountryMap[] RouteTable2;
    private String StartLabel;
    private String FinishLabel;
    public void setStartLabel(String StartLabel){
        this.StartLabel=StartLabel;
    }
    public String getStartLabel(){
        return StartLabel;
    }
    public void setFinishLabel(String FinishLabel){
        this.FinishLabel=FinishLabel;
    }
    public String getFinishLabel(){
        return FinishLabel;
    }


    public int[] getTimes() {
        return times;
    }

    public void setTimes(int[] times) {
        this.times = times;
    }


    public CountryMap[] getRouteTable1() {
        return RouteTable1;
    }

    public void setRouteTable1(CountryMap[] RouteTable1) {
        this.RouteTable1 = RouteTable1;
    }

    public CountryMap[] getRouteTable2() {
        return RouteTable2;
    }

    public void setRouteTable2(CountryMap[] RouteTable2) {
        this.RouteTable2 = RouteTable2;
    }

    public CountryMap(int numCities){
        if(numCities<=0){
            throw new IllegalArgumentException("Error:The number of cities or routes must be greater than zero!");
        }
        this.city1=city1;
        this.city2=city2;
        this.NumRoutes=NumRoutes;
        this.times=new int[NumRoutes];
        this.RouteTable1=new CountryMap[NumRoutes];
        this.RouteTable2=new CountryMap[NumRoutes];
        this.StartLabel=StartLabel;
        this.FinishLabel=FinishLabel;


    }



    public int getNumRoutes(){
        return NumRoutes;
    }
    public void setNumRoutes(int NumRoutes){
        this.NumRoutes=NumRoutes;
    }


    public void setCity1(char city1) {
        this.city1 = city1;
    }

    public char getCity1() {
        return city1;
    }


    public char getCity2() {
        return city2;
    }

    public void setCity2(char city2) {
        this.city2 = city2;
    }

    public CountryMap(){

    }

   /*public void deneme(){
        for (int i =0;i<getRouteTable1().length;i++){
            System.out.println(getRouteTable1()[i].getCity1());
            System.out.println(getRouteTable2()[i].getCity2());
            System.out.println(getTimes()[i]);

        }
        System.out.println(getStartLabel());
        System.out.println(getFinishLabel());
    }*/

}
