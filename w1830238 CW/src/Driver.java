import java.io.Serializable;

public abstract class Driver implements Serializable {

    private String driverName;
    private String driverLocation;
    private String driverTeam;

    public void setName (String name){
        driverName = name;
    }
    public String getName(){
        return driverName;
    }


    public void setTeam(String team){
        driverTeam = team;
    }

    public String getTeam(){
        return driverTeam;
    }

    public void changeTeam(String team){
        System.out.println("Driver  has been changed from " + driverTeam +" to " + team +"\n");
        driverTeam = team;
    }
    public void setLocation(String location){
        driverLocation = location;
    }
    public String getLocation(){
        return driverLocation;
    }

}
