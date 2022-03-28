import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GraphicalUserInterface {

    //ArrayList will store all the information about Driver's who will participated in the Season
    ArrayList<Formula1Driver> participantsArr = new ArrayList<>();
    //Following ArrayList will Store all the dates and results of a race that have been held
    ArrayList<CompletedRaces> completedRacesArr = new ArrayList<>();


    //Columns for the firstFrame, secondFrame and thirdFrame
    String[] fullColumn = {"Name", "Team","Total Races","Total Points","1st Positions","2nd Positions","3rd Positions",
            "4th Positions","5th Positions","6th Positions","7th Positions","8th Positions","9th Positions","10th Positions",
            "Other Positions"};


    @SuppressWarnings("unchecked")
    public void loadFile() throws IOException {
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
    }

    //The following method will sort participantsArr(ArrayList) in descending order according to Total Points
    public void sortDescending(){
        Formula1Driver temp;
        for(int x = 0; x < participantsArr.size(); x++){
            for(int y = x+1; y < participantsArr.size(); y++){
                temp = participantsArr.get(x);
                if (participantsArr.get(y).getTotalPoint() > participantsArr.get(x).getTotalPoint() ){

                    participantsArr.set(x, participantsArr.get(y));
                    participantsArr.set(y,temp);
                }

                else if(participantsArr.get(y).getTotalPoint() == participantsArr.get(x).getTotalPoint() ){
                    if(participantsArr.get(y).getFirstPosition() > participantsArr.get(x).getFirstPosition()){
                        participantsArr.set(x, participantsArr.get(y));
                        participantsArr.set(y,temp);
                    }
                }
            }
        }
    }

    //The following method will sort participantsArr(ArrayList) in ascending order according to Total Points
    public void sortAscending(){
        Formula1Driver temp;
        for(int x = 0; x < participantsArr.size(); x++){
            for(int y = x+1; y < participantsArr.size(); y++){
                temp = participantsArr.get(x);
                if (participantsArr.get(y).getTotalPoint() < participantsArr.get(x).getTotalPoint() ){

                    participantsArr.set(x, participantsArr.get(y));
                    participantsArr.set(y,temp);
                }

                else if(participantsArr.get(y).getTotalPoint() == participantsArr.get(x).getTotalPoint() ){
                    if(participantsArr.get(y).getFirstPosition() < participantsArr.get(x).getFirstPosition()){
                        participantsArr.set(x, participantsArr.get(y));
                        participantsArr.set(y,temp);
                    }
                }
            }
        }
    }
    //The following method will sort participantsArr(ArrayList) in descending order according to First Position
    public void sortFirstPA(){
        Formula1Driver temp;
        for(int x = 0; x < participantsArr.size(); x++){
            for(int y = x+1; y < participantsArr.size(); y++){
                temp = participantsArr.get(x);
                if (participantsArr.get(y).getFirstPosition() > participantsArr.get(x).getFirstPosition() ){

                    participantsArr.set(x, participantsArr.get(y));
                    participantsArr.set(y,temp);
                }
                else if(participantsArr.get(y).getFirstPosition() == participantsArr.get(x).getFirstPosition() ) {
                    if (participantsArr.get(y).getTotalPoint() > participantsArr.get(x).getTotalPoint()) {
                        participantsArr.set(x, participantsArr.get(y));
                        participantsArr.set(y, temp);
                    }


                }

                }
        }
    }

    int viewYear; //Temporarily to hold the latest race's year(This will be used to display the date in fifthFrame and sixthFrame)
    int viewMonth; //Temporarily to hold the latest race's month (This will be used to display the date in fifthFrame and sixthFrame)
    int viewDay; //Temporarily to hold the latest race's day(This will be used to display the date in fifthFrame and sixthFrame)

    public void randomAddRace(Object[][] rows){

        //Following Array will store names of drivers who participated in the race temporarily and move to Completed race class
        ArrayList<String> completedNamesArr = new ArrayList<>();
        //Following Array will store positions of the race achieved by the drivers temporarily and move to Completed race class
        ArrayList<Integer> completedPositionsArr = new ArrayList<>();


        int year  = 2021;
        int month = (int) (Math.random() * (13-1)) +1;
        int day   = (int) (Math.random() * (31-1)) +1;
        this.viewYear = year;
        this.viewMonth = month;
        this.viewDay = day;

        ArrayList<Integer> positionsArr = new ArrayList<>();
        for(int x = 0; x < participantsArr.size(); x++){
            positionsArr.add(x+1);
        }

        for (int x = 0; x < participantsArr.size(); x++){
            int random = (int) (Math.random() * (positionsArr.size()))  ;
            System.out.println(participantsArr.get(x).getName() + " got " + positionsArr.get(random));
            participantsArr.get(x).updatePosition(positionsArr.get(random)); //participants Individual positions will be updated
            participantsArr.get(x).updateAllRaceDate(year,month,day,positionsArr.get(random)); //Participants individual profile wll be updated(Race Date)
            //updatePositionsAchievedArr(x,positionsArr.get(random));
            completedNamesArr.add(participantsArr.get(x).getName()); //adding the names to a temporarily arraylist to update completeRace variable later on
            completedPositionsArr.add(positionsArr.get(random)); //adding the position to a temporarily arraylist to update completeRace variable later on
            positionsArr.remove(random);
        }
        completedRacesArr.add(new CompletedRaces(year,month,day,completedNamesArr,completedPositionsArr)); //A New completed Race will be added

        //Fillings the rows of the table according to columns (fourthFrame)
        for (int x = 0; x < participantsArr.size(); x++){
            rows[x][0] = participantsArr.get(x).getName();
            rows[x][1] = participantsArr.get(x).getTeam();
            rows[x][2] = completedPositionsArr.get(x);
        }

    }

    //Filling the rows of the table according to Columns (firstFrame, secondFrame and thirdFrame)
    public void addRows(Object[][] rows){

        for(int x = 0; x < participantsArr.size(); x++) {
            rows[x][0] = participantsArr.get(x).getName();
            rows[x][1] = participantsArr.get(x).getTeam();
            rows[x][2] = participantsArr.get(x).getRaces();
            rows[x][3] = participantsArr.get(x).getTotalPoint();
            rows[x][4] = participantsArr.get(x).getFirstPosition();
            rows[x][5] = participantsArr.get(x).getSecondPosition();
            rows[x][6] = participantsArr.get(x).getThirdPosition();
            rows[x][7] = participantsArr.get(x).getFourthPosition();
            rows[x][8] = participantsArr.get(x).getFifthPosition();
            rows[x][9] = participantsArr.get(x).getSixthPosition();
            rows[x][10] = participantsArr.get(x).getSeventhPosition();
            rows[x][11] = participantsArr.get(x).getEightPosition();
            rows[x][12] = participantsArr.get(x).getNinthPosition();
            rows[x][13] = participantsArr.get(x).getTenthPosition();
            rows[x][14] = participantsArr.get(x).getOtherPositions();
        }
    }

    //Filling the rows of the table according to Columns (seventhFrame)
    private void fillSearchRows(Object[][] rows, int playerIndex){
        for(int x = 0; x < participantsArr.get(playerIndex).getAllRaceDateArrSize(); x++){
            String date = participantsArr.get(playerIndex).getYear(x) + " / "+ participantsArr.get(playerIndex).getMonth(x) + " / " + participantsArr.get(playerIndex).getDate(x);
            rows[x][0] = date;
            rows[x][1] = participantsArr.get(playerIndex).getPosition(x);
        }
    }


    public ArrayList<Integer> probabilityRace() {
        //Following for-loop will create a temporarily starting positions array to help with probability matter, later on drivers will be assigned to starting positions
        ArrayList<Integer> startingPositionArr = new ArrayList<>();
        for (int x = 0; x < participantsArr.size(); x++) {
            startingPositionArr.add(x+1);
        }
        //This ArrayList will help to select other achieved positions for drivers who didn't get the chance to win
        ArrayList<Integer> winPositionsArr = new ArrayList<>();
        for (int x = 2; x <= participantsArr.size(); x++) {
            winPositionsArr.add(x);
        }

        ArrayList<Integer> wonPositionsArr = new ArrayList<>(); //This ArrayList will store the achieved positions by drivers according to the participantsArr Order

        int oneWinChance = (int) (Math.random() * (100 -1)) +1; //Creating a random between 1 to 100 to count the chances of wining the race according to their starting position

        for (int x = 0; x < participantsArr.size(); x++) {
            if (startingPositionArr.get(x) == 1) {

                if (oneWinChance <= 40) {
                    wonPositionsArr.add(1);

                } else {
                    int otherPosition = (int) (Math.random() * (winPositionsArr.size())) ;
                    wonPositionsArr.add(winPositionsArr.get(otherPosition));
                    winPositionsArr.remove(otherPosition);

                }
            }
            if (startingPositionArr.get(x) == 2){
                if(!wonPositionsArr.contains(1) && oneWinChance <= 70){

                    wonPositionsArr.add(1);
                } else{
                    int otherPosition = (int) (Math.random() * (winPositionsArr.size())) ;
                    wonPositionsArr.add(winPositionsArr.get(otherPosition));
                    winPositionsArr.remove(otherPosition);

                }
            }
            if (startingPositionArr.get(x) == 3 || startingPositionArr.get(x) == 4 ){

                if((!wonPositionsArr.contains(1) && oneWinChance <= 80) || (!wonPositionsArr.contains(1) && oneWinChance <= 90)){
                    wonPositionsArr.add(1);

                } else{
                    int otherPosition = (int) (Math.random() * (winPositionsArr.size())) ;
                    wonPositionsArr.add(winPositionsArr.get(otherPosition));
                    winPositionsArr.remove(otherPosition);

                }
            }
            if (startingPositionArr.get(x) == 5 || startingPositionArr.get(x) == 6 || startingPositionArr.get(x) == 7 || startingPositionArr.get(x) == 8 || startingPositionArr.get(x) == 9 ){

                if((!wonPositionsArr.contains(1) && oneWinChance <= 92) || (!wonPositionsArr.contains(1) && oneWinChance <= 94)
                        || (!wonPositionsArr.contains(1) && oneWinChance <= 96) || (!wonPositionsArr.contains(1) && oneWinChance <= 98)
                        || (!wonPositionsArr.contains(1) && oneWinChance <= 100)){

                    wonPositionsArr.add(1);

                } else{
                    int otherPosition = (int) (Math.random() * (winPositionsArr.size())) ;
                    wonPositionsArr.add(winPositionsArr.get(otherPosition));
                    winPositionsArr.remove(otherPosition);

                }
            }
            if (startingPositionArr.get(x) >= 10){
                int otherPosition = (int) (Math.random() * (winPositionsArr.size())) ;
                wonPositionsArr.add(winPositionsArr.get(otherPosition));
                winPositionsArr.remove(otherPosition);
            }
        }

        //Following Array will store names of drivers who participated in the race temporarily and move to Completed race class
        ArrayList<String> completedNamesArr = new ArrayList<>();
        //Following Array will store positions of the race achieved by the drivers temporarily and move to Completed race class
        ArrayList<Integer> completedPositionsArr = new ArrayList<>();


        int year  = 2021;
        int month = (int) (Math.random() * (13-1)) +1;
        int day   = (int) (Math.random() * (31-1)) +1;

        this.viewYear = year;
        this.viewMonth = month;
        this.viewDay = day;

        //Following for-loop will assign drivers their Starting positions randomly
        for(int x = 0; x < participantsArr.size(); x++) {
            int randomPosition = (int) (Math.random() * (startingPositionArr.size()));
            participantsArr.get(x).setStartingPosition(startingPositionArr.get(randomPosition));
            System.out.println(participantsArr.get(x).getName() + " will Start in " + startingPositionArr.get(randomPosition));
            startingPositionArr.remove(randomPosition);
        }

        sortStartingPAsc(); //This will sort the drivers in ascending order according to their Starting Positions
        //Following for-loop will update the positions achieved by drivers
        for (int x = 0; x < participantsArr.size(); x++){
            System.out.println(participantsArr.get(x).getName() + " Started on " + participantsArr.get(x).getStartingPosition() + " Position have finished race in " + wonPositionsArr.get(x));
            participantsArr.get(x).updatePosition(wonPositionsArr.get(x));
            participantsArr.get(x).updateAllRaceDate(year,month,day,wonPositionsArr.get(x));
            completedNamesArr.add(participantsArr.get(x).getName());      //Adding the names to a temporarily arraylist to update completeRace variable later on
            completedPositionsArr.add(wonPositionsArr.get(x)); //Adding the position to a temporarily arraylist to update completeRace variable later on
        }

        completedRacesArr.add(new CompletedRaces(year,month,day,completedNamesArr,completedPositionsArr));

        return wonPositionsArr;
    }
    /*
      The following method will sort participantsArr(ArrayList) in ascending order according to Starting Position
      This method will be used when the user choose to add a race according to Probability
      **/
    private void sortStartingPAsc(){
        Formula1Driver temp;
        for(int x = 0; x < participantsArr.size(); x++){
            for(int y = x+1; y < participantsArr.size(); y++){
                temp = participantsArr.get(x);
                if (participantsArr.get(y).getStartingPosition() < participantsArr.get(x).getStartingPosition() ){

                    participantsArr.set(x, participantsArr.get(y));
                    participantsArr.set(y,temp);
                }

            }
        }
    }

    //The following method will fill all the rows in sixthFrame Table
    public void fillDateRows(Object[][] rows){
        sortDateAsc(); //Dates will be sorted in ascending order
        int increment =0; //This variable will help to display the date in the correct Row on table
        for (int x=0; x < completedRacesArr.size(); x++){
            rows[increment][0] = completedRacesArr.get(x).getDate();
            for (int y=0;y < completedRacesArr.get(x).getNameArrSize(); y++){
                rows[increment][1] = completedRacesArr.get(x).getName(y);
                rows[increment][2] = completedRacesArr.get(x).getPosition(y);
                increment++;
            }
        }
    }

    //The following method will sort the dates in ascending order
    private void sortDateAsc(){
        for(int x=0; x < completedRacesArr.size(); x++) {


            for (int i = x + 1; i < completedRacesArr.size(); i++) {
                CompletedRaces temp = completedRacesArr.get(x);

                if (completedRacesArr.get(x).getYear() > completedRacesArr.get(i).getYear()) {
                    completedRacesArr.set(x, completedRacesArr.get(i)); //Changing the position of the Dates
                    completedRacesArr.set(i, temp);

                } else if (completedRacesArr.get(x).getYear() == completedRacesArr.get(i).getYear()) {
                    if (completedRacesArr.get(x).getMonth() > completedRacesArr.get(i).getMonth()) {
                        completedRacesArr.set(x, completedRacesArr.get(i)); //Changing the position of the Dates
                        completedRacesArr.set(i, temp);

                    } else if (completedRacesArr.get(x).getMonth() == completedRacesArr.get(i).getMonth()) {
                        if (completedRacesArr.get(x).getDay() > completedRacesArr.get(i).getDay()) {
                            completedRacesArr.set(x, completedRacesArr.get(i)); //Changing the position of the Dates
                            completedRacesArr.set(i, temp);

                        }
                    }
                }
            }
        }
    }


    public GraphicalUserInterface() throws IOException {
        //Loading the Saved File
        try {
            loadFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        JFrame homePage = new JFrame("Main Page");

        ImageIcon pointsDIcon = new ImageIcon("Icon\\Details.png");
        JButton viewDescBtn = new JButton("VIEW TABLE (POINTS - DESC)");
        viewDescBtn.setBounds(80,150,270,50);
        viewDescBtn.setBackground(Color.LIGHT_GRAY);
        viewDescBtn.setIcon(pointsDIcon);

        ImageIcon pointsAIcon = new ImageIcon("Icon\\sortAsc.png");
        JButton viewAscBtn = new JButton("VIEW TABLE (POINTS - ASC)");
        viewAscBtn.setBounds(480,150,250,50);
        viewAscBtn.setBackground(Color.LIGHT_GRAY);
        viewAscBtn.setIcon(pointsAIcon);

        ImageIcon sortFirstIcon = new ImageIcon("Icon\\Fp.png");
        JButton sortFirstA = new JButton("VIEW TABLE (FIRST PLACE - DESC)");
        sortFirstA.setBounds(260,250,310,50);
        sortFirstA.setBackground(Color.lightGray);
        sortFirstA.setIcon(sortFirstIcon);

        ImageIcon addRaceIcon = new ImageIcon("Icon\\addRace.png");
        JButton addRaceBtn = new JButton("ADD A RACE");
        addRaceBtn.setBounds(180,350,180,50);
        addRaceBtn.setBackground(Color.LIGHT_GRAY);
        addRaceBtn.setIcon(addRaceIcon);

        ImageIcon winProbIcon = new ImageIcon("Icon\\prob.png");
        JButton winProbBtn = new JButton("ADD A RACE (STARTING POSITIONS & PROBABILITY)");
        winProbBtn.setBounds(480,350,430,50);
        winProbBtn.setBackground(Color.LIGHT_GRAY);
        winProbBtn.setIcon(winProbIcon);

        ImageIcon dateIcon = new ImageIcon("Icon\\date.png");
        JButton raceDates = new JButton("ALL RACE DATES");
        raceDates.setBounds(310,450,200,50);
        raceDates.setBackground(Color.LIGHT_GRAY);
        raceDates.setIcon(dateIcon);

        JTextField search = new JTextField("Enter Player's Name");
        search.setBounds(310,560,180,30);

        ImageIcon searchIcon = new ImageIcon("Icon\\search.png");
        JButton searchBtn = new JButton("SEARCH");
        searchBtn.setBounds(530,550,150,50);
        searchBtn.setBackground(Color.LIGHT_GRAY);
        searchBtn.setIcon(searchIcon);

        JLabel searchLabel = new JLabel();
        searchLabel.setBounds(310,600,380,30);
        searchLabel.setForeground(Color.RED);
        searchLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        homePage.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Icon\\Background.jpg")))));
        homePage.add(viewDescBtn);
        homePage.add(viewAscBtn);
        homePage.add(sortFirstA);
        homePage.add(addRaceBtn);
        homePage.add(winProbBtn);
        homePage.add(raceDates);
        homePage.add(search);
        homePage.add(searchBtn);
        homePage.add(searchLabel);
        homePage.setLayout(null);
        homePage.setSize(1000,700);
        homePage.setVisible(true);
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        viewDescBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                JFrame firstFrame = new JFrame("VIEW TABLE (POINTS - DESC)");


                JLabel title = new JLabel("VIEW TABLE (POINTS - DESC)");
                title.setBounds(580,20,400,50);
                title.setForeground(Color.YELLOW);
                title.setFont(new Font("Times New Roman", Font.BOLD, 20));

                Object[][] rows = new Object[participantsArr.size()][15];
                sortDescending();
                addRows(rows);
                JTable jTable = new JTable(rows, fullColumn);

                JScrollPane scrollPane=new JScrollPane(jTable);
                scrollPane.setBounds(50,100,1400,500);

                firstFrame.add(scrollPane);
                firstFrame.add(title);
                firstFrame.setSize(1500,400);
                firstFrame.setLayout(null);
                firstFrame.getContentPane().setBackground(Color.BLACK);
                firstFrame.setVisible(true);
            }
        });

        viewAscBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame secondFrame = new JFrame("VIEW TABLE (POINTS - ASC)");

                JLabel title = new JLabel("VIEW TABLE (POINTS - ASC)");
                title.setBounds(580,20,400,50);
                title.setForeground(Color.PINK);
                title.setFont(new Font("Times New Roman", Font.BOLD, 20));

                Object[][] rows = new Object[participantsArr.size()][15];
                sortAscending();
                addRows(rows);
                JTable jTable = new JTable(rows, fullColumn);

                JScrollPane scrollPane=new JScrollPane(jTable);
                scrollPane.setBounds(50,100,1400,500);

                secondFrame.add(scrollPane);
                secondFrame.add(title);
                secondFrame.setSize(1500,400);
                secondFrame.getContentPane().setBackground(Color.BLACK);
                secondFrame.setLayout(null);
                secondFrame.setVisible(true);

            }
        });

        sortFirstA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame thirdFrame = new JFrame("VIEW TABLE (FIRST PLACE - DESC)");

                JLabel title = new JLabel("VIEW TABLE (FIRST PLACE - DESC)");
                title.setBounds(580,20,400,50);
                title.setForeground(Color.ORANGE);
                title.setFont(new Font("Times New Roman", Font.BOLD, 20));

                Object[][] rows = new Object[participantsArr.size()][15];
                sortFirstPA();
                addRows(rows);
                JTable jTable = new JTable(rows, fullColumn);
                //jTable.setBounds(50,50,300,300);

                JScrollPane scrollPane=new JScrollPane(jTable);
                scrollPane.setBounds(50,100,1400,500);

                thirdFrame.add(scrollPane);
                thirdFrame.add(title);
                thirdFrame.setSize(1500,400);
                thirdFrame.getContentPane().setBackground(Color.BLACK);
                thirdFrame.setLayout(null);
                thirdFrame.setVisible(true);
            }
        });

        addRaceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel title = new JLabel("NEW RACE");
                title.setBounds(280,20,200,50);
                title.setForeground(Color.CYAN);
                title.setFont(new Font("Times New Roman", Font.BOLD, 20));

                JFrame fourthFrame = new JFrame("ADD A RACE");
                String[] column = {"Name","Team","Position Achieved"};
                Object[][] rows = new Object[participantsArr.size()][3];

                randomAddRace(rows);
                JTable jTable = new JTable(rows,column);


                JLabel raceDate = new JLabel();
                String date = "Race date : " + viewYear + "/" + viewMonth + "/" + viewDay;
                raceDate.setText(date);
                raceDate.setForeground(Color.WHITE);
                raceDate.setBounds(100,80,150,50);

                JScrollPane scrollPane=new JScrollPane(jTable);
                scrollPane.setBounds(50,150,600,200);

                fourthFrame.add(title);
                fourthFrame.add(raceDate);
                fourthFrame.add(scrollPane);
                fourthFrame.setSize(700,500);
                fourthFrame.getContentPane().setBackground(Color.BLACK);
                fourthFrame.setLayout(null);
                fourthFrame.setVisible(true);
            }
        });

        winProbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fifthFrame = new JFrame("ADD A RACE (STARTING POSITIONS & PROBABILITY)");

                //Adding a background Image
                try {
                    fifthFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Icon\\Starting Position.jpg")))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                ArrayList<Integer> wonPositionsArr;
                wonPositionsArr = probabilityRace();

                JLabel title = new JLabel("Started Positions & Achieved Places");
                title.setForeground(Color.lightGray);
                title.setFont(new Font("Times New Roman", Font.BOLD, 30));
                title.setBounds(100,100,500,50);
                fifthFrame.add(title);

                int height = 170; // this variable will help to change the y position of labels

                for (int x = 0; x < participantsArr.size(); x++){
                    JLabel line = new JLabel();
                    line.setText(participantsArr.get(x).getName() + " Started in " + participantsArr.get(x).getStartingPosition() + " Position " +
                            "have finished the Race in " + wonPositionsArr.get(x));
                    line.setForeground(Color.WHITE);
                    line.setFont(new Font("Times New Roman", Font.PLAIN, 16));

                    line.setBounds(60,height,800,25);
                    height += 40;
                    fifthFrame.add(line);
                }

                JLabel raceDate = new JLabel();
                String date = "Race date : " + viewYear + "/" + viewMonth + "/" + viewDay;
                raceDate.setText(date);
                raceDate.setForeground(Color.WHITE);
                raceDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
                raceDate.setBounds(50,50,200,50);
                fifthFrame.add(raceDate);


                fifthFrame.setSize(700,800);
                fifthFrame.getContentPane().setBackground(Color.lightGray);
                fifthFrame.setLayout(null);
                fifthFrame.setVisible(true);
            }
        });

        raceDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sixthFrame = new JFrame("ALL RACE DATES");

                JLabel title = new JLabel("Dates Of All the Races held so far");
                title.setBounds(250,50,200,50);
                title.setForeground(Color.GREEN);

                int rowSize =0; //This variable will store amount of the rows needed for the Table
                for(int x=0; x < completedRacesArr.size();x++){     //for-Loop will calculate will get the each amount of rows needed to display in the table
                   rowSize += completedRacesArr.get(x).getNameArrSize();
                }

                String[] column = {"Date","Driver's Name","Position Achieved"};
                Object[][] rows = new Object[rowSize][3];
                fillDateRows(rows);

                JTable jTable = new JTable(rows,column);
                JScrollPane scrollPane=new JScrollPane(jTable);
                scrollPane.setBounds(50,100,600,200);

                sixthFrame.add(title);
                sixthFrame.add(scrollPane);
                sixthFrame.getContentPane().setBackground(Color.BLACK);
                sixthFrame.setSize(700,500);
                sixthFrame.setLayout(null);
                sixthFrame.setVisible(true);
            }

        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userSearch = search.getText();
                int count = 0;
                for (int x = 0; x < participantsArr.size(); x++) {
                    if (participantsArr.get(x).getName().equalsIgnoreCase(userSearch)) {

                        JFrame seventhFrame = new JFrame("SEARCH");

                        JLabel title = new JLabel("DRIVER DETAILS");
                        title.setBounds(300,50,100,50);
                        title.setForeground(Color.RED);

                        String[] column = {"Date","Position Achieved"};
                        Object[][] rows = new Object[participantsArr.get(x).getAllRaceDateArrSize()][2];


                        fillSearchRows(rows,x);

                        JTable jTable = new JTable(rows,column);
                        JScrollPane scrollPane=new JScrollPane(jTable);
                        scrollPane.setBounds(50,150,600,200);

                        seventhFrame.add(title);
                        seventhFrame.add(scrollPane);
                        seventhFrame.setSize(800,600);
                        seventhFrame.getContentPane().setBackground(Color.BLACK);
                        seventhFrame.setLayout(null);
                        seventhFrame.setVisible(true);
                        break;
                    }
                    count++;
                }
                if(count == participantsArr.size()) {
                    searchLabel.setText("Not Found! Please Enter A Proper Name!");
                }
            }
        });


    }




    public static void main(String[] args) throws IOException {
      GraphicalUserInterface d =new GraphicalUserInterface();


    }
}
