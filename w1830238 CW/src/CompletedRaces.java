import java.io.Serializable;
import java.util.ArrayList;

public class CompletedRaces implements Serializable {

    private int year;
    private int month;
    private int day;

    private final ArrayList<String> namesArr = new ArrayList<>();
    private final ArrayList<Integer> positionsArr = new ArrayList<>();


    public CompletedRaces(int year,int month,int day,ArrayList<String> tempNames,ArrayList<Integer> tempPosition){

        this.year = year;
        this.month = month;
        this.day = day;
        namesArr.addAll(tempNames);
        positionsArr.addAll(tempPosition);

    }
    public CompletedRaces(){

    }

    public int getNameArrSize(){
        return namesArr.size();
    }

    public String getDate(){
        return year + " / " + month + " / "+ day;
    }

    public String getName(int index){
        return namesArr.get(index);
    }
    public int getPosition(int index){
        return positionsArr.get(index);
    }

    public int getYear(){
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
