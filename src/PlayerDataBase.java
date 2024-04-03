//import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.util.*;

/**
 * Created by Pearse Lehmann on 7/5/2017.
 */
public class PlayerDataBase
{
    public List< Player > players_list;

    public static String[] positions_list = {"c","cb","dl","edge","lb","og","ot","qb","rb","s","te","wr"};

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

    public void print_rankings(String year)
    {
        //read_in_all_players(year);
        players_list.sort(new Comparator<Player>()
        {
            @Override
            public int compare(Player player1, Player player2)
            {
                return player1.compareRatingTo(player2);
            }
        });
        int ranking = 1;
        for ( Player current_player : players_list )
        {
            System.out.println(ranking + ": " + current_player.getPosition() + " " +
                    current_player.getName() + " " + current_player.getCollege() + " " +
                    current_player.getRating());
            ranking++;
        }
    }

    public void make_file(String year)
    {
        read_in_all_players(year);
        String directory_path_string = "positions\\" + year;
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

    public void sort_files(String year)
    {
        String directory_path_string = "positions\\" + year + "\\";
        for ( String position : positions_list )
        {
            players_list.clear();
            add_file( "positions\\" + year + "\\" + position + year);
            try
            {
                String player_file_name = directory_path_string + position + year;
                File player_file = new File( player_file_name );
                if ( player_file.exists() && player_file.isFile() )
                {
                    player_file.delete();
                }
                player_file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter( player_file_name ));
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
                writer.close();
            }
            catch (IOException io)
            {
                System.out.println("Couldn't deal with the compiled player list");
            }
        }
    }

    public void read_in_all_players(String year)
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
    }

    public void create_lists(String year)
    {
        players_list.clear();
        String directory_path_string = "positions\\" + "MockDrafts\\";
        for (String position : positions_list)
        {
            add_file( "positions\\" + year + "\\" + position + year);
            try
            {
                String player_file_name = directory_path_string + position + year;
                File player_file = new File( player_file_name );
                if ( player_file.exists() && player_file.isFile() )
                {
                    player_file.delete();
                }
                player_file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter( player_file_name ));
                players_list.sort(new Comparator<Player>()
                {
                    @Override
                    public int compare(Player player1, Player player2)
                    {
                        return player1.compareRatingTo(player2);
                    }
                });
                int rank = 1;
                for ( Player current_player : players_list )
                {
                    writer.write( rank + ". " + current_player.getPosition() + " " +
                            current_player.getName() + " " + current_player.getCollege() + "\n" );
                    rank += 1;
                }
                writer.close();
            }
            catch (IOException io)
            {
                System.out.println("Couldn't deal with the compiled player list");
            }
            players_list.clear();
        }
        // Read in all players and make that file
        read_in_all_players(year);
        try
        {
            String player_file_name = directory_path_string + "players" + year;
            File player_file = new File( player_file_name );
            if ( player_file.exists() && player_file.isFile() )
            {
                player_file.delete();
            }
            player_file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter( player_file_name ));
            players_list.sort(new Comparator<Player>()
            {
                @Override
                public int compare(Player player1, Player player2)
                {
                    return player1.compareRatingTo(player2);
                }
            });
            int rank = 1;
            for ( Player current_player : players_list )
            {
                writer.write( rank + ". " + current_player.getPosition() + " " +
                        current_player.getName() + " " + current_player.getCollege() + "\n" );
                rank += 1;
            }
            writer.close();
        }
        catch (IOException io)
        {
            System.out.println("Couldn't deal with the compiled player list");
        }
    }
}