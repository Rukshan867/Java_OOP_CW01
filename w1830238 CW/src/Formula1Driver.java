import java.util.ArrayList;

public class Formula1Driver extends Driver{

    public Formula1Driver(String name, String team, String location){
        setName(name);
        setTeam(team);
        setLocation(location);

    }


    private int firstP   = 0;
    private int secondP  = 0;
    private int thirdP   = 0;
    private int fourthP  = 0;
    private int fifthP   = 0;
    private int sixthP   = 0;
    private int seventhP = 0;
    private int eightP   = 0;
    private int ninthP   = 0;
    private int tenthP   = 0;
    private int otherP   = 0;

    private int totalPoint = 0;
    private int totalRaces = 0;
    private int unFinishRace = 0;
    private int startingPosition = 0;

    /*
    This 2D arraylist will store all dates of the races driver participated and the positions
    he achieved according to the race date
     **/
    private final ArrayList<ArrayList<Integer>> allRaceDateArr = new ArrayList<>();

    public void updateAllRaceDate(int year, int month, int day, int position){
        ArrayList<Integer> dateDetailsArr = new ArrayList<>();
        dateDetailsArr.add(year);
        dateDetailsArr.add(month);
        dateDetailsArr.add(day);
        dateDetailsArr.add(position);
        allRaceDateArr.add(dateDetailsArr);

        if(allRaceDateArr.size() > 1){
            sortAllRaceDateArr();
        }

    }
    public int getAllRaceDateArrSize(){
        return allRaceDateArr.size();
    }
    public int getYear(int x){
        return allRaceDateArr.get(x).get(0);
    }
    public int getMonth(int x) {
        return allRaceDateArr.get(x).get(1);
    }
    public int getDate(int x) {
        return allRaceDateArr.get(x).get(2);
    }
    public int getPosition(int x) {
        return allRaceDateArr.get(x).get(3);
    }

    //Sorting Dates of Races in Ascending
    public void sortAllRaceDateArr(){
        ArrayList<Integer> temp;
        for(int x=0 ; x < allRaceDateArr.size();  x++){
            for(int y=x+1; y < allRaceDateArr.size();y++){
                temp = allRaceDateArr.get(x);
                if (allRaceDateArr.get(x).get(0) > allRaceDateArr.get(y).get(0) ){

                    allRaceDateArr.set(x, allRaceDateArr.get(y));
                    allRaceDateArr.set(y,temp);
                }

                else if(allRaceDateArr.get(x).get(0).equals(allRaceDateArr.get(y).get(0)) ){
                    if(allRaceDateArr.get(x).get(1) > allRaceDateArr.get(y).get(1)){
                        allRaceDateArr.set(x, allRaceDateArr.get(y));
                        allRaceDateArr.set(y,temp);
                    }
                }
            }
        }
    }


        public void setTotalPoint(int points){
        totalPoint += points;
    }

    public int getTotalPoint(){
        return totalPoint;
    }

    public int getFirstPosition(){return firstP;}
    public int getSecondPosition(){return secondP;}
    public int getThirdPosition(){return thirdP;}
    public int getFourthPosition(){return fourthP;}
    public int getFifthPosition(){return fifthP;}
    public int getSixthPosition(){return sixthP;}
    public int getSeventhPosition(){return seventhP;}
    public int getEightPosition(){return eightP;}
    public int getNinthPosition(){return ninthP;}
    public int getTenthPosition(){return tenthP;}
    public int getOtherPositions(){return otherP;}

    public void getStats(){
        updateTotalPoint(); //Total points will be automatically updated when the user needs stats about a driver
        //This will help to align the driver's details in the table
        String align = "| %-15s | %-10s | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %n";
        System.out.format(align,getName(),getTeam(),totalRaces,totalPoint,firstP,secondP,thirdP,fourthP,fifthP,sixthP,seventhP,eightP,ninthP,tenthP,otherP);
    }

    public void updatePosition(int position){

        if (position == 1){
            firstP ++;
        }
        else if (position ==2){
            secondP++;
        }
        else if (position == 3){
            thirdP++;
        }
        else if (position == 4){
            fourthP++;
        }
        else if (position == 5){
            fifthP++;
        }
        else if (position == 6){
            sixthP++;
        }
        else if (position == 7){
            seventhP++;
        }
        else if (position == 8){
            eightP++;
        }
        else if (position == 9){
            ninthP++;
        }
        else if (position == 10){
            tenthP++;
        }
        else if (position > 10){
            otherP++;
        }
        else {
            unFinishRace++;
        }
        updateTotalPoint();
        updateTotalRaces();
    }

    private void updateTotalPoint(){
        totalPoint = (firstP*25) + (secondP*18) + (thirdP*15) + (fourthP*12) + (fifthP*10) + (sixthP*8) + (seventhP*6) + (eightP*4) + (ninthP*2) + (tenthP);
    }

    public void updateTotalRaces(){
        totalRaces++;
    }

    public int getRaces(){return totalRaces;}

    public void setStartingPosition(int position){
        startingPosition = position;
    }
    public int getStartingPosition(){
        return startingPosition;
    }
}
