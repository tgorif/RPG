package RPG.Character;
import RPG.Main.Position;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoContainer {
    private String name;
    private Character character;
    private Position position;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isBlueTeam() {
        return isBlueTeam;
    }

    public void setBlueTeam(boolean blueTeam) {
        isBlueTeam = blueTeam;
    }

    public InfoContainer(String name, Character character, Position position, boolean isBlueTeam){
        this.name = name;
        this.character = character;
        this.position = position;
        this.isBlueTeam = isBlueTeam;
        final Logger LOGGER = Logger.getLogger(InfoContainer.class.getName());
        if(name==null || name.length()==0) LOGGER.log(Level.SEVERE,"Created Character without name");
        if(position==null) LOGGER.log(Level.SEVERE,"Created Character with null Position");
        if(character==null) LOGGER.log(Level.SEVERE,"Created Character with null Character");
    }
    private InfoContainer(InfoContainer original){
        this.name = original.name;
        this.character = original.character;
        this.position = original.position;
        this.isBlueTeam = original.isBlueTeam;
        final Logger LOGGER = Logger.getLogger(InfoContainer.class.getName());
        if(name==null || name.length()==0) LOGGER.log(Level.SEVERE,"Created Character without name");
        if(position==null) LOGGER.log(Level.SEVERE,"Created Character with null Position");
        if(character==null) LOGGER.log(Level.SEVERE,"Created Character with null Character");
    }
    public InfoContainer clone(){
        return new InfoContainer(this);
    }

}
