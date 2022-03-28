import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{

    //Following ArrayList will store all the information about Driver's who will participated in the Season
    private ArrayList<Formula1Driver> participantsArr = new ArrayList<>();
    //Following ArrayList will Store all the dates and results of a race that have been held
    private ArrayList<CompletedRaces> completedRacesArr = new ArrayList<>();

    private Scanner inputUser = new Scanner(System.in);
    private Scanner inputUserInt = new Scanner(System.in);

    @Override
    public void createNewDriver() {
        inputUser = new Scanner(System.in);
        String nameDriver;

        do {
            System.out.println("Please enter the name of new Driver: ");
            nameDriver = inputUser.nextLine();
        }while(nameDriver.trim().equals(""));

        boolean loop = true; //To Break from the do-while Loop

        String teamDriver;
        /*
             one constructor team can have only one driver so,
             Following code lines will make sure the constructor team that will be insert by the user isn't occupied by a driver.
             if the constructor team already has a driver user will have to enter another team.
         */
        do{
            System.out.println("Please enter the name of constructor team: ");
            teamDriver = inputUser.nextLine();

            if( participantsArr.size() != 0 )
            for (int x=0; x < participantsArr.size(); x++){
                if (teamDriver.equalsIgnoreCase(participantsArr.get(x).getTeam())){
                    System.out.println(teamDriver + " Already has a Driver assigned to it");
                    loop = true;
                     break;
                }
                else {
                    loop = false;
                }
            }
            else{
                loop = false;
            }

        }while(loop || teamDriver.trim().equals(""));


        String location; //to Store the driver location temporarily
       do {
           System.out.print("Enter Location : ");
           location = inputUser.nextLine();
       } while(location.trim().equals(""));

       //After taking the necessary information from the user a new driver will be added to the arraylist
        participantsArr.add(new Formula1Driver(nameDriver,teamDriver,location));

        System.out.println("\n"+nameDriver+ " will be added to the championship \n");

    }



    @Override
    public void deleteDriver() {
        if(participantsArr.size() < 1){
            System.out.println("\nThere aren't any drivers \n");
        }
        else {
            driverShortDetails();
            inputUserInt = new Scanner(System.in);

            int deleteNo;
            try {
                do {

                    System.out.println("Please select the number of the driver you want to delete");
                    deleteNo = inputUserInt.nextInt();


                    if (deleteNo >= participantsArr.size() || deleteNo < 0) {
                        System.out.println("The number you enter isn't available\n \nPlease enter a valid number\n");
                    }

                } while (deleteNo >= participantsArr.size() || deleteNo < 0);

                participantsArr.remove(deleteNo);
                System.out.println("Successfully removed the Driver\n");

            } catch (InputMismatchException exception) {
                System.out.println("Please Enter A Valid Integer \n \n");
            }
        }
    }

    @Override
    public void changeTeam(){
        driverShortDetails();
        inputUserInt = new Scanner(System.in);
        if(participantsArr.size() <= 1){
            System.out.println("\nThere aren't enough Constructor teams to change \n");
        }
        else {
            try {
                int changeNo;

        /* Giving the option to the user to choose the driver that they wish to change team.
           do-while loop will make sure to get the correct input from the user for the following reasons,
           - if the integer that user enters is less than 0 (Zero).
           - if the integer that user enters is out of bounds.

         */

                do {
                    System.out.print("Please enter the number of the driver to change for another Constructor team : ");
                    integerValidator();
                    changeNo = inputUserInt.nextInt();

                    if (changeNo >= participantsArr.size() || changeNo < 0) {
                        System.out.println("The number you enter isn't available!!\n \nPlease enter a valid number\n");
                    }
                } while (changeNo >= participantsArr.size() || changeNo < 0);


                System.out.println(" ");

        /* Getting the name of the constructor team that user wish to change for the chosen driver
           While loop will continue to loop for the following reasons,
           - if the user enters the same team name that the driver already in
           - if the user didn't enter any name
           - if the user enters a name that isn't an existing constructor team
         */
                String changeTeams; // This variable will store user's input
                boolean changeName = true;


                while (changeName) {
                    System.out.print("Please enter the name of an constructor team you want change to : ");
                    changeTeams = inputUser.nextLine();

                    if (changeTeams.equalsIgnoreCase(participantsArr.get(changeNo).getTeam())) {
                        System.out.println("You have entered the name of the constructor team that the driver Already in!! \n");
                        continue;
                    }

                    for (int x = 0; x < participantsArr.size(); x++) {
                        if (participantsArr.get(x).getTeam().equalsIgnoreCase(changeTeams)) {
                            System.out.println("\n Successfully changed the team");
                            participantsArr.get(changeNo).changeTeam(changeTeams);
                            changeName = false;
                            break;
                        }
                    }
                    if (changeName) {
                        System.out.println("Name of the team you entered doesn't exist!! \n");
                    }
                }

            } catch (InputMismatchException exception) {
                System.out.println("Please Enter A Valid Integer!!");
            }
        }

    }



    public void getIndividualStats(){

        if(participantsArr.size() ==0){
            System.out.println("There aren't any players to show statistics \n");
        }
        else {
            driverShortDetails();
            inputUserInt = new Scanner(System.in);
            int statsNo;
            try {
                do {


                    System.out.print("Enter the number of the driver to view his Statistics : ");
                /*
                 following while loop will make sure to get an integer from the user
                 */
                    while (!inputUserInt.hasNextInt()) {
                        System.out.println("Please enter a valid Integer");
                        System.out.print("Enter again : ");
                        inputUserInt.next();
                    }

                    statsNo = inputUserInt.nextInt();

                    if (statsNo >= participantsArr.size() || statsNo < 0) {
                        System.out.println("The number you enter isn't available!!\n Please enter a valid number\n");
                    }

                } while (statsNo >= participantsArr.size() || statsNo < 0);


                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|Name             | Team       | Total    |Total     |____________________________________________Positions___________________________________________________________________|");
                System.out.println("|                 |            |   Races  |  Points  |First     |Second    |Third     |Fourth    |Fifth     |Sixth     |Seventh   |Eight     |Ninth     |Tenth     |Other     |");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                participantsArr.get(statsNo).getStats();
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            } catch (InputMismatchException exception) {
                System.out.println("Please Enter A Valid Integer!!");
            }
        }
    }



    public void getSortStatistics(){
        if(participantsArr.size() ==0){
            System.out.println("There aren't any players to show statistics \n");
        }
        else {
            ArrayList<Formula1Driver> accParticipantsArr;
            accParticipantsArr = participantsArr;
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|Name             | Team       | Total    |Total     |____________________________________________Positions___________________________________________________________________|");
            System.out.println("|                 |            |   Races  |  Points  |First     |Second    |Third     |Fourth    |Fifth     |Sixth     |Seventh   |Eight     |Ninth     |Tenth     |Other     |");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            //Sorting the participants According to their Total Points
            Formula1Driver temp;
            for (int x = 0; x < accParticipantsArr.size(); x++) {
                for (int y = x + 1; y < accParticipantsArr.size(); y++) {
                    temp = accParticipantsArr.get(x);
                    if (accParticipantsArr.get(y).getTotalPoint() > accParticipantsArr.get(x).getTotalPoint()) {

                        accParticipantsArr.set(x, accParticipantsArr.get(y));
                        accParticipantsArr.set(y, temp);
                    } else if (accParticipantsArr.get(y).getTotalPoint() == accParticipantsArr.get(x).getTotalPoint()) {
                        if (accParticipantsArr.get(y).getFirstPosition() > accParticipantsArr.get(x).getFirstPosition()) {
                            accParticipantsArr.set(x, accParticipantsArr.get(y));
                            accParticipantsArr.set(y, temp);
                        }
                    }
                }
            }
            //Displaying the sorted participants
            for (int x = 0; x < accParticipantsArr.size(); x++) {
                accParticipantsArr.get(x).getStats();
            }


            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        }
    }


    public void addRace() {
        //Following Array will store names of drivers who participated in the race temporarily and move to completedRace type variable
        ArrayList<String> tempNames = new ArrayList<>();
        //Following Array will store positions of the race achieved by the drivers temporarily and move to completedRace type variable
        ArrayList<Integer> tempPositionArr = new ArrayList<>();


        inputUserInt = new Scanner(System.in);
        if (participantsArr.size() < 10) {
            System.out.println("You need at least 10 Players to Start a Race \n");
        } else {
            System.out.println("Enter the Race Date");
            /*
            Prompting the user to enter the Year of the new Race.
            integerValidator() method will make sure that the user's input is a integer
             **/
            int year; //To store the current race's Year
            System.out.println("Year : ");
            integerValidator();
            do {
                year = inputUserInt.nextInt();
                if (year > 2021 || year < 2019){
                    System.out.println("Please enter a Year Between 2019 and 2021");
                    integerValidator();
                }
            }while (year > 2021 || year < 2019);

            /*
            Prompting the user to enter the Month of the new Race.
            integerValidator() method will make sure that the user's input is a integer
            DO-WHILE Loop will make sure that user will enter a integer between 1-12
             **/

            int month; //To store the current race's Month
            System.out.println("Month : ");
            integerValidator();
            do {
                    month = inputUserInt.nextInt();

                    if (month > 12 || month < 1) {
                        System.out.println("There are only 12 Months in a Year");
                        integerValidator();
                    }
            }while (month > 12 || month < 1);

            /*
            Prompting the user to enter the Date of the new Race.
            integerValidator() method will make sure that the user's input is a integer
            DO-WHILE Loop will make sure that user will enter a integer between 1-30
             **/

            int day; //To store the current race's Day
            System.out.println("Day : ");
            integerValidator();
            do {
                    day = inputUserInt.nextInt();

                    if (day > 30 || day < 1) {
                        System.out.println("There are only 30 Days in a Month");
                        integerValidator();
                    }
            }while (day > 30 || day < 1);


            System.out.println(" ");
            System.out.println("Enter the Positions of the following drivers have achieved ");
            System.out.println("(Note:- If the Driver didn't complete the Race please enter - 0) \n");
            System.out.println("Name         Position");


            int position; //Storing the position achieved
            ArrayList<Integer> positionAchArr = new ArrayList<>(); //Storing the achieved positions
            for (int x = 0; x < participantsArr.size(); x++) {
                    System.out.print(participantsArr.get(x).getName() + " : ");

                /*
                 following while loop will make sure to get an integer from the user
                 **/
                    while (!inputUserInt.hasNextInt()) {
                        System.out.println("Please enter a valid Integer");
                        System.out.print(participantsArr.get(x).getName() + " : ");
                        inputUserInt.next();
                    }
                    position = inputUserInt.nextInt();
                     /*
                if the position entered by user is more than total number of competitors program will ask the user to enter
                a valid position.
                if the position is already achieved by another driver, user will have to enter another position
                 **/
                    while (position < 0 || position > participantsArr.size() || positionAchArr.contains(position)){
                        if(position < 0 || position > participantsArr.size()) {
                            System.out.println("There's only " + participantsArr.size() + " Players, Please enter a valid Position");
                        }
                        else if(positionAchArr.contains(position)){
                            System.out.println("This Position has been already Achieved by Another Driver, Enter another Position");
                        }
                        System.out.print(participantsArr.get(x).getName() + " : ");
                        position = inputUserInt.nextInt();
                    }
                        participantsArr.get(x).updatePosition(position); //Updating Total number of position achieved by the particular driver
                        participantsArr.get(x).updateAllRaceDate(year,month,day,position); //Updating races that the particular driver has been participated
                        positionAchArr.add(position); //Position will be added to an ArrayList to make sure that a one position wont be achieved by many drivers
                        tempNames.add(participantsArr.get(x).getName()); //Adding the names to a temporarily arraylist to update completeRace variable later on
                        tempPositionArr.add(position);        //Adding the position to a temporarily arraylist to update completeRace variable later on
            }
            completedRacesArr.add(new CompletedRaces(year,month,day,tempNames,tempPositionArr)); //A New Completed Race will be created
        }
    }


    private void integerValidator(){
        while (!inputUserInt.hasNextInt()){
            System.out.print("Please enter a valid Integer :");
            inputUserInt.next();
        }
    }


   //A method to print the name and team of the Driver

    private void driverShortDetails(){
        String align = "| %-10d | %-15s | %-15s  |%n";  //This will help to align the driver's details in the table
        System.out.println("+-------------------------------------------------+");
        System.out.println("|Number      |Name             |Team              |");
        System.out.println("+-------------------------------------------------+");
        for(int x=0; x < participantsArr.size(); x++){
            System.out.format(align,x , participantsArr.get(x).getName(),participantsArr.get(x).getTeam());
        }
        System.out.println("+-------------------------------------------------+");
    }

    public void saveFile() throws IOException {
        File file = new File("CourseworkSave.txt");

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        objOut.writeObject(participantsArr);
        objOut.writeObject(completedRacesArr);

        objOut.close();
        fileOut.close();
        System.out.println("Program have saved successfully\n");
    }

    @SuppressWarnings("unchecked")
    public void loadFile() throws IOException{
        FileInputStream fileIn= new FileInputStream("CourseworkSave.txt");
        ObjectInputStream objIn= new ObjectInputStream(fileIn);

        while (true) {
            try {
                participantsArr = (ArrayList<Formula1Driver>) objIn.readObject();
                completedRacesArr = (ArrayList<CompletedRaces>) objIn.readObject();

            } catch (IOException|ClassNotFoundException e) {
                break;
            }
        }
        System.out.println("Program have loaded successfully\n");
    }






}
