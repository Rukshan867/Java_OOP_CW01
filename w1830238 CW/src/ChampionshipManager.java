import java.io.IOException;

public interface ChampionshipManager {

    void createNewDriver();
    void deleteDriver();
    void changeTeam();
    void getIndividualStats();
    void getSortStatistics();
    void addRace();
    void saveFile()throws IOException;
    void loadFile() throws IOException;
}
