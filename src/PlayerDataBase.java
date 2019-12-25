import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pearse Lehmann on 7/5/2017.
 */
public class PlayerDataBase {
    public HashMap<String,String> proscons;
    public List<String> players;
    public PlayerDataBase(){
        proscons = new HashMap<String,String>();
        players = new LinkedList<>();
    }
    public void addfile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line = bufferedReader.readLine();
            String playername = "";
            while (line != null){
                if (line.contains("+") ){
                    String desc = line +"\n";
                    line = bufferedReader.readLine();
                    desc += line;
                    proscons.put(playername,desc);
                }
                else{
                    playername = line;
                    players.add(playername);
                }
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            System.out.println("IO exeption");
        }
    }
    public void college(String[] namecol){
        for (String player : players) {
            int i = 0;
            boolean yes = true;
            String[] playersplit = player.split(" ");
            if (playersplit.length - 4 == namecol.length) {
                for (String s : namecol) {
                    if (!s.equals(playersplit[i + 4])) {
                        yes = false;
                    }
                    i = i + 1;
                }
                if (yes) {
                    System.out.println(player);
                }
            }
        }
    }
}
