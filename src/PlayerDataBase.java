//import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.util.Comparator;
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
    public void reset(String asYear) {
        String lsDirPath = "positions\\"+asYear;
        players.clear();
        proscons.clear();
        File lcDir = new File(lsDirPath);
        File[] lcFiles = lcDir.listFiles();
        for (File lcFile : lcFiles)
        {
            String lsFileName = lcFile.getName();
            if (!lsFileName.contains("players"))
            {
                this.addfile(lsDirPath+"\\"+lsFileName);
            }
        }
        try {
            File file = new File(lsDirPath+"\\"+asYear+"players");
            if (file.exists() && file.isFile())
            {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(lsDirPath+"\\"+asYear+"players"));
            players.sort(new Comparator<String>() {
                public int compare(String o1, String o2) {
                    String[] ovr1 = o1.split(" ");
                    String[] ovr2 = o2.split(" ");
                    return ovr2[3].compareTo(ovr1[3]);
                }
            });
            for (String player : players) {
                writer.write(player+"\n");
                writer.write(proscons.get(player)+"\n");
            }
            System.out.println(players.size() + " players");
            writer.close();
        }
        catch (IOException io)
        {
            System.out.println("NOPE");
        }
    }
}
