public class Game { 
    public String game_id; 
    public String home_team; 
    public String away_team; 
    public ZonedDateTime date_time; 
}

public class PlayerData { 
    public String league; 
    public String bookmaker; 
    public String player; 
    public String shot_type; 
    public double odds; 
    public double shots; 
}


//Constructors 

public playerData(String league, String bookmaker, String shot_type, double odds, double shots){ 
    this.league = league; 
    this.bookmaker = bookmaker; 
    this.player = player; 
    this.shot_type = shot_type; 
    this.odds = odds; 
    this.shots = shots; 
}

public Game(String game_id, String home_team, String away_team, ZonedDateTime date_time){ 
    this.game_id = game_id; 
    this.home_team = home_team; 
    this.away_team = away_team; 
    this.date_time = date_time; 
}

//Getters  

//playerData
public String getLeague() { return league; } 
public String getBookmaker() { return bookmaker; } 
public String getPlayer() { return player; } 
public String getShotType() { return shot_type; } 
public double getOdds() { return odds; }
public double getShots() { return shots; }

//Game 
public String getGameID() { return game_id; } 
public String getHomeTeam() { return home_team; } 
public String getAwayTeam() { return away_team; }
public ZonedDateTime getDate() { return date_time; } 


//Setters 


