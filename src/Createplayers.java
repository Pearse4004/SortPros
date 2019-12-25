import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pearse Lehmann on 3/20/2018.
 */
public class Createplayers {
    /**
    public static void main(String[] args) {
        String file = "positions\\add";
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null){
                line = line.trim();
                String[] strings;
                strings = line.split(" ");
                //System.out.println("TE "+strings[1]+strings[2]);
                int i = 0;
                String retur = "";
                for (String s : strings){
                    if (!s.equals("") && s.charAt(0) > 58){
                        retur = retur + s + " ";
                    }
                }
                list.add("WR " + retur.trim());
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        list.sort(String::compareTo);
        for (String str : list){
            System.out.println(str);
        }
    }
     */
    public static List main(String args) {
        String file = "positions\\addE";
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null){
                list.add(line);
                bufferedReader.readLine();
                //list.add("WR " + line + " "+bufferedReader.readLine());
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        list.sort(String::compareTo);
        for (String str : list){
            //System.out.println(str);
        }
        return list;
    }
}
