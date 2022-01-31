//import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.util.*;

/**
 * Created by Pearse Lehmann on 7/5/2017.
 */
public class PlayerDataBase
{
    public List< Player > players_list;

    public PlayerDataBase()
    {
        players_list = new LinkedList<>();
    }

    public void add_file( String file_name )
    {
        Player current_player = null;
        try ( BufferedReader bufferedReader = new BufferedReader( new FileReader( file_name ) ) )
        {
            String line = bufferedReader.readLine();
            while ( line != null )
            {
                String[] split_line = line.split(" ");
                if ( Arrays.asList(Player.PROFILE_ATTRIBUTES).contains(split_line[0]) )
                {
                    current_player.add_to_profile(line);
                }
                else
                {
                    current_player = new Player(line);
                    players_list.add(current_player);
                }
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("IO exeption in reading in the players from " + file_name);
        }
    }

    public void print_college(String college_name){
        for ( Player current_player : players_list ) {
            if ( current_player.getCollege().equals(college_name) )
            {
                System.out.println(current_player);
            }
        }
    }

    public void make_file(String year)
    {
        String directory_path_string = "positions\\" + year;
        players_list.clear();
        File directory_path = new File( directory_path_string );
        File[] directory_files = directory_path.listFiles();
        for (File current_file : directory_files)
        {
            String file_name = current_file.getName();
            if ( !file_name.contains( "players" ) )
            {
                this.add_file( directory_path_string + "\\" + file_name );
            }
        }
        try
        {
            String all_player_file_name = directory_path_string + "\\" + "players" + year;
            File all_player_file = new File( all_player_file_name );
            if ( all_player_file.exists() && all_player_file.isFile() )
            {
                all_player_file.delete();
            }
            all_player_file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter( all_player_file_name ));
            players_list.sort(new Comparator<Player>()
            {
                @Override
                public int compare(Player player1, Player player2)
                {
                    return player1.compareRatingTo(player2);
                }
            });
            for ( Player current_player : players_list )
            {
                writer.write( current_player.toString() + "\n" );
            }
            System.out.println(players_list.size() + " players in the file");
            writer.close();
        }
        catch (IOException io)
        {
            System.out.println("Couldn't deal with the compiled player list");
        }
    }
}
