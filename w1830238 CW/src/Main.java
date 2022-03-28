import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        Formula1ChampionshipManager worldChampionship = new Formula1ChampionshipManager();
        Scanner userInput;

        boolean loop = true;
        while(loop){

             optionMenu();
            try {
                userInput = new Scanner(System.in);
             System.out.println("Select : ");
             String userSelect = userInput.nextLine().toLowerCase();

             switch (userSelect) {

                 case "cnd":
                 case "1":
                     worldChampionship.createNewDriver();
                     break;

                 case "ddt":
                 case "2":
                     worldChampionship.deleteDriver();
                     break;

                 case "cdt":
                 case "3":
                     worldChampionship.changeTeam();
                     break;
                 case "aad":
                 case "4":
                     worldChampionship.addRace();
                     break;
                 case "dsd":
                 case "5":
                     worldChampionship.getIndividualStats();
                     break;
                 case "dst":
                 case "6":
                     worldChampionship.getSortStatistics();
                     break;
                 case "sdg":
                 case "7":
                     worldChampionship.saveFile();
                          break;
                 case "ldg":
                 case "8":
                     worldChampionship.loadFile();
                     break;
                 case "otg":
                 case "9":
                      GraphicalUserInterface Gui = new GraphicalUserInterface();
                     break;
                 case "ext":
                 case "999":
                     //Ending the program by Breaking the Loop
                     System.out.println("Program will end.");
                     loop = false;
                     break;
                 default:
                     System.out.println("Please enter a valid option. \n");

             }
         }catch (Exception e) {
                System.out.println("Please enter a valid input.... ");
            }
        }


    }



    private static void optionMenu(){
        System.out.println("\n--------------------------------------------MENU----------------------------------------------------------\n");
        System.out.println("Enter 1 or CND to Create a New Driver");
        System.out.println("Enter 2 or DDT to Delete a Driver and his Team");
        System.out.println("Enter 3 or CDT to Change the driver for an existing constructor team");
        System.out.println("Enter 4 or AAD to Add a Completed race");
        System.out.println("Enter 5 or DSD to Display the statistics of a certain existing driver.");
        System.out.println("Enter 6 or DST to Display all the statistics of Drivers sorted by their  Total Points in Descending order ");
        System.out.println("Enter 7 or SDG to Save all the gathered Data in a file");
        System.out.println("Enter 8 or LDG to Load all the gathered Data in a file");
        System.out.println("Enter 9 or OTG to Open the GUI");
        System.out.println("Enter 999 or EXT to Exit from the Program");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}
