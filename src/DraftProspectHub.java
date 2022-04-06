import java.util.*;

/**
 * Created by Pearse Lehmann on 7/5/2017.
 */
public class DraftProspectHub
{
    public static void main( String[] args )
    {
        // Uncomment if using the command line to start
        // String year = args[0];
        String year = "2022";

        // Uncomment if using the command line to start
        // String position = args[1];
        String position = "cb";

        PlayerDataBase player_data_base = new PlayerDataBase();
        player_data_base.add_file( "positions\\" + year + "\\" + position + year);

        while ( true )
        {
            Scanner reader = new Scanner( System.in );
            System.out.println("Enter team name to search for a player on the team \n" +
                    "\"print rating\" to print the list of player in order\n" +
                    "\"draft\" to start the draft\n" +
                    "\"compile\" to compile all files into one for the year\n" +
                    "\"ranking\" to print out the ranking of all the players\n" +
                    "\"quit\" to end: ");
            String input = reader.nextLine();

            if (input.equals("quit")) {
                break;
            }
            try {
                String[] split_input = input.split(" ");
                // Set up mock draft
                if (split_input[0].equals("draft"))
                {
                    System.out.println("Draft Not Implemented yet");
                    // Mockdraft mockdraft = new Mockdraft(playerDataBase);
                    // mockdraft.draft();
                }
                else if (split_input[0].equals("print"))
                {
                    if (split_input[1].equals("rating"))
                    {
                        player_data_base.players_list.sort(new Comparator<Player>()
                        {
                            @Override
                            public int compare(Player player1, Player player2)
                            {
                                return player1.compareRatingTo(player2);
                            }
                        });
                    }
                    for (Player current_player : player_data_base.players_list)
                    {
                        System.out.println( current_player.toString() );
                    }
                }
                else if (split_input[0].equals("compile"))
                {
                    player_data_base.make_file(year);
                }
                else if (split_input[0].equals("ranking"))
                {
                    player_data_base.print_rankings(year);
                }
                else
                {
                    player_data_base.print_college(input);
                }
            }
            catch (Exception e)
            {
                System.out.println("Something went wrong");
            }
            System.out.println("");
        }
    }
}
