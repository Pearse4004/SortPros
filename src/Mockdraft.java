/**
 * Created by Pearse Lehmann on 11/20/2017.
 */
public class Mockdraft {
    private PlayerDataBase players;
    public Mockdraft(PlayerDataBase playerDataBase){
        players = playerDataBase;
    }
    public void draft(){
        int i = 1;
        while (i<225){
            System.out.print(i+" Pearse Lehmann");
            i++;
        }
    }
}
