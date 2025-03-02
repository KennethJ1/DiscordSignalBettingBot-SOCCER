import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;


public class APIconnect {
    public static void main(String[] args) throws IOException {
        String apiKey = "35c9689d1e67fcad802be426947910c0";

        HashMap<String, List<Game>> Games = new HashMap<>();
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String[] GameKeys = new String[]{"soccer_epl", "soccer_france_ligue_one", "soccer_germany_bundesliga", "soccer_italy_serie_a", "soccer_netherlands_eredivisie", "soccer_spain_la_liga"};
        String[] Markets = new String[]{"player_shots_on_goal", "player_shots_on_target"};

        String sport; // Replace with desired sport (e.g., "basketball_nba", "tennis_atp")
        String market = "h2h"; // Replace with desired market (e.g., "spreads", "totals")
        String baseUrl = "https://api.the-odds-api.com/v4/sports/";
        String regions = "us";
        String dateFormat = "iso";
        String oddsFormat = "decimal";

        ZonedDateTime todayUtc = ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        ZonedDateTime futureUtc = ZonedDateTime.of(LocalDate.now().plusDays(10), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        // Format it to "yyyy-MM-dd'T'HH:mm:ss'Z'"
        DateTimeFormatter formatter_to_string = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter formatter_from_string = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        // Convert to string
        String commenceTimeFrom = todayUtc.format(formatter_to_string);

        String commenceTimeTo = futureUtc.format(formatter_to_string);

        for (int i = 0; i < GameKeys.length; i++) {
            sport = GameKeys[i];
            String url = String.format(
                    "%s%s/odds?apiKey=%s&regions=%s&markets=%s&dateFormat=%s&oddsFormat=%s&commenceTimeFrom=%s&commenceTimeTo=%s&includeLinks=true",
                    baseUrl, sport, apiKey, regions, market, dateFormat, oddsFormat, commenceTimeFrom, commenceTimeTo
            );

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                    List<Map<String, Object>> data = gson.fromJson(response.body().string(), listType);
                    List<Game> curr_games = new ArrayList<>();
                    for (Map<String, Object> game : data) {

                        Game curr_game = new Game();
                        curr_game.game_id  = (String) game.get("id");
                        curr_game.home_team  = (String) game.get("home_team");
                        curr_game.away_team  = (String) game.get("away_team");
                        curr_game.date_time  = ZonedDateTime.parse((String) game.get("commence_time"),formatter_from_string.withZone(ZoneId.of("UTC")) );
                        curr_games.add(curr_game);
                    }
                    Games.put(sport, curr_games);

//                    String responseBody = response.body().string();
//                    System.out.println("API Response: " + responseBody);
                } else {
                    System.out.println("Request failed with status code: " + response.code());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        System.out.println(Games.get("soccer_epl").get(0).date_time);
    }

    public static class Game{
        String game_id = "";
        String home_team = "";
        String away_team = "";
        ZonedDateTime date_time;
    }
}
//https://api.the-odds-api.com/v4/sports/soccer_epl/events/1ee2fa57e3e5993877afb667347a7d85/odds?apiKey=35c9689d1e67fcad802be426947910c0&regions=us&markets=player_shots_on_target&dateFormat=iso&oddsFormat=decimal
//https://api.the-odds-api.com/v4/sports/soccer_italy_serie_a/events/49db5797338c6b4e07fdc61f182fc09d/odds?apiKey=35c9689d1e67fcad802be426947910c0&dateFormat=iso&oddsFormat=decimal&markets=player_shots_on_target&regions=us
//https://api.the-odds-api.com/v4/sports/{sport}/odds?apiKey={APIKey}&regions=us&markets={market}&dateFormat=iso&oddsFormat=decimal&commenceTimeFrom={2025-03-01T00%3A00%3A00Z}&commenceTimeTo={2025-03-10T00%3A00%3A00Z}&includeLinks=true

