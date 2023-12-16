import game.components.Monster;
import game.components.RumbleGame;
import game.monsters.*;
import entregable.monstruos.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RumbleGame rumbleGame = RumbleGame.getInstance();
        rumbleGame.init();

        List<Monster> monstersOne = Arrays.asList(new Spartan("Spartan 1"),
                new EvilBeast("Evil beast 1"),
                new BlessedEnt("Blessed Ent"),
                new IceBeast("Ice Beast"),
                new VolcanoSnail("Volcano Snail"),
                new Spartan("Spartan 1"),
                new EvilBeast("Evil beast 1"),
                new BlessedEnt("Blessed Ent"),
                new IceBeast("Ice Beast"),
                new VolcanoSnail("Volcano Snail"));

        //TODO ordenar el listado de monstruos que recibe el jugador uno
        
        
        rumbleGame.getPlayerOne().setMonsters(monstersOne);

        List<Monster> monstersTwo = Arrays.asList(new EvilBeast("Evil beast 1"),
        		new Spartan("Spartan A"),
        		new IceBeast("Ice Beast"),
        		new VolcanoSnail("Volcano Snail"),
                new BlessedEnt("Blessed Ent"));

        //TODO ordenar el listado de monstruos que recibe el jugador dos
        rumbleGame.getPlayerTwo().setMonsters(monstersTwo);

        rumbleGame.startGame();
    }
}