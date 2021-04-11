import java.util.*;

/**
 * Created by Pearse Lehmann on 7/5/2017.
 */
public class Sortpros {
    public static void main(String[] args) {
        //System.out.println(args[0] + "\n");
        PlayerDataBase playerDataBase = new PlayerDataBase();
        //playerDataBase.addfile(args[0]);
        playerDataBase.addfile("positions\\2021\\edge2021");
        String input = "";
        while (!input.equals("quit")) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter team name to search for a player on the team \nor print name/rating to print the list\nor draft to start the draft\nor quit to end: ");
            input = reader.nextLine();
            if (input.equals("quit")) {
                break;
            }
            String[] sortby = null;
            try {
                sortby = input.split(" ");
                if (sortby[0].equals("draft")) {
                    Mockdraft mockdraft = new Mockdraft(playerDataBase);
                    mockdraft.draft();
                }
                if (sortby[0].equals("print")) {
                    if (sortby[1].equals("name")) {
                        playerDataBase.players.sort(new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return (o1.compareTo(o2));
                            }
                        });
                    }
                    if (sortby[1].equals("rating")) {
                        playerDataBase.players.sort(new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                String[] ovr1 = o1.split(" ");
                                String[] ovr2 = o2.split(" ");
                                return ovr2[3].compareTo(ovr1[3]);
                            }
                        });
                    }
//                    if(sortby[1].equals("name")){
//                        playerDataBase.players.sort(new Comparator<String>() {
//                            @Override
//                            public int compare(String o1, String o2) {
//                                String[] ovr1 = o1.split(" ");
//                                String[] ovr2 = o2.split(" ");
//                                return ovr2[3].compareTo(ovr1[3]);
//                            }
//                        });
//                    }
                    for (String s : playerDataBase.players) {
                        if (sortby.length == 3 && sortby[2].equals(s.split(" ")[0])) {
                            System.out.println(s);
                            //System.out.println(playerDataBase.proscons.get(s));
                        }
                        if (sortby.length == 2) {
                            String[] s2 = s.split(" ");
                            String[] coll = (String[]) Arrays.copyOfRange(s2, 4, s2.length);
                            //System.out.print(s2[0]+" "+s2[1]+" "+s2[2]);
                            for (String c : coll) {
                                //System.out.print(" ");
                                //System.out.print(c);
                            }
                            //System.out.println();
                            System.out.println(s);
                            System.out.println(playerDataBase.proscons.get(s));
                        }
                    }
                } else {
                    String[] college = input.split(" ");
                    int i = 1;
                    for (String s : args) {
                        if (i != 1) {
                            college[i - 2] = s;
                        }
                        i++;
                    }
                    playerDataBase.college(college);
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
            System.out.println("");
        }
        playerDataBase.reset("2021");
        List<String> list = Createplayers.main("hi");
        List<String> players = new ArrayList<String>();
        for (String player : playerDataBase.players){
            String[] hold = player.split(" ");
            String hol = hold[1] + " " + hold[2];
            players.add(hol);
        }
        for (String s : list){
            if (!players.contains(s)){
                System.out.println(s);
            }
        }
    }
}
