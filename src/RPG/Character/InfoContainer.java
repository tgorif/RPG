package RPG.Character;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoContainer {
    private String name;
    private Character character;
    private RPG.Main.Level.Tile tile;
    private boolean isBlueTeam;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public RPG.Main.Level.Tile getTile() {
        return tile;
    }

    public void setTile(RPG.Main.Level.Tile tile) {
        this.tile = tile;
    }

    public boolean isBlueTeam() {
        return isBlueTeam;
    }

    public void setBlueTeam(boolean blueTeam) {
        isBlueTeam = blueTeam;
    }

    public InfoContainer(String name, Character character, RPG.Main.Level.Tile tile, boolean isBlueTeam){
        this.name = name;
        this.character = character;
        this.tile = tile;
        this.isBlueTeam = isBlueTeam;
        final Logger LOGGER = Logger.getLogger(InfoContainer.class.getName());
        if(name==null || name.length()==0) LOGGER.log(Level.SEVERE,"Created Character without name");
        if(tile==null) LOGGER.log(Level.SEVERE,"Created Character with null Position");
        if(character==null) LOGGER.log(Level.SEVERE,"Created Character with null Character");
    }
    private InfoContainer(InfoContainer original){
        this.name = original.name;
        this.character = original.character;
        this.tile = original.tile;
        this.isBlueTeam = original.isBlueTeam;
        final Logger LOGGER = Logger.getLogger(InfoContainer.class.getName());
        if(name==null || name.length()==0) LOGGER.log(Level.SEVERE,"Created Character without name");
        if(tile ==null) LOGGER.log(Level.SEVERE,"Created Character with null Position");
        if(character==null) LOGGER.log(Level.SEVERE,"Created Character with null Character");
    }
    public InfoContainer clone(){
        return new InfoContainer(this);
    }

}
