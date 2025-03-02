import java.time.format.DateTimeFormatter;
import java.time.*;

public class dataConfig {

    public static class Game {
        public String game_id;
        public String home_team;
        public String away_team;
        public ZonedDateTime date_time;

        public Game() {
        }

        public Game(String game_id, String home_team, String away_team, ZonedDateTime date_time) {
            this.game_id = game_id;
            this.home_team = home_team;
            this.away_team = away_team;
            this.date_time = date_time;
        }

        public String getGameID() {
            return game_id;
        }

        public String getHomeTeam() {
            return home_team;
        }

        public String getAwayTeam() {
            return away_team;
        }

        public ZonedDateTime getDate() {
            return date_time;
        }

        public void setHome_team(String home_team) {
            this.home_team = home_team;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public void setAway_team(String away_team) {
            this.away_team = away_team;
        }

        public void setDate_time(ZonedDateTime date_time) {
            this.date_time = date_time;
        }
    }

    public static class PlayerData {
        public String league;
        public String bookmaker;
        public String player;
        public String shot_type;
        public double odds;
        public double shots;

        public PlayerData(String league, String bookmaker, String player, String shot_type, double odds, double shots) {
            this.league = league;
            this.bookmaker = bookmaker;
            this.player = player;
            this.shot_type = shot_type;
            this.odds = odds;
            this.shots = shots;
        }

        public void setLeague(String league) {
            this.league = league;
        }

        public void setBookmaker(String bookmaker) {
            this.bookmaker = bookmaker;
        }

        public void setPlayer(String player) {
            this.player = player;
        }

        public void setShot_type(String shot_type) {
            this.shot_type = shot_type;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }

        public void setShots(double shots) {
            this.shots = shots;
        }

        //playerData
        public String getLeague() {
            return league;
        }

        public String getBookmaker() {
            return bookmaker;
        }

        public String getPlayer() {
            return player;
        }

        public String getShotType() {
            return shot_type;
        }

        public double getOdds() {
            return odds;
        }

        public double getShots() {
            return shots;
        }

    }
}