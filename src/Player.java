import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Player {
    String position;
    String name;
    String college;
    int rating;
    Map<String, String> profile;

    public static final String[] PROFILE_ATTRIBUTES = new String[] { "info:", "pros:", "cons:", "take:" };

    Player( String player_data )
    {
        // Add all first line information to the player
        try {
            String[] split_player_data = player_data.split(" ");
            int player_data_length = split_player_data.length;
            position = split_player_data[0];
            name = split_player_data[1] + " " + split_player_data[2];
            String[] college_array = Arrays.copyOfRange(split_player_data, 3, player_data_length - 1);
            college = String.join(" ", college_array);
            rating = Integer.valueOf(split_player_data[player_data_length - 1]);
            // Add Player Profiles later
            profile = new HashMap<String, String>();
        }
        catch (Exception e)
        {
            System.out.println("Failed to create player: " + player_data);
        }
    }

    public void add_to_profile(String profile_data)
    {
        String[] split_profile_data = profile_data.split(" ");
        // Need to get all elements after the 1st one to be stored as a string
        String[] split_profile_data_value = Arrays.copyOfRange(split_profile_data, 1, split_profile_data.length);
        String profile_data_value = String.join(" ",split_profile_data_value);
        profile.put( split_profile_data[0], profile_data_value );
    }

    public String getCollege()
    {
        return college;
    }

    public int getRating()
    {
        return rating;
    }

    public String getName()
    {
        return name;
    }

    public String getPosition() {
        return this.position;
    }

    @Override
    public String toString()
    {
        String profile_string = "";
        for ( String key : PROFILE_ATTRIBUTES )
        {
            String value = profile.get(key);
            profile_string = profile_string + "\n" + key + " " + value;
        }
        return (position + " " + name + " " + college + " " + rating + profile_string);
    }

    public int compareRatingTo(Player player2)
    {
        return player2.getRating() - this.rating;
    }

}
