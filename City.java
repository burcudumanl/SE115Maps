public class City {
    private char citylabel;
    private boolean visited;

    public City(char citylabel,boolean visited){
        this.citylabel=citylabel;
        this.visited=visited;
    }

    public City(){

    }
    //SET-GET0
    public void setVisited(boolean Visited){
        this.visited=Visited;
    }

    public boolean getVisited(){
        return visited;
    }
    //SET-GET1
    public void setCitylabel(char citylabel){
        this.citylabel=citylabel;

    }
    public char getCitylabel(){
        return citylabel;
    }

}
