import game.components.Monster;
import game.components.RumbleGame;
import game.monsters.*;
import entregable.monstruos.*;
import entregable.orden.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RumbleGame rumbleGame = RumbleGame.getInstance();
        rumbleGame.init();
        
        Scanner s = new Scanner(System.in);
        
        List<Monster> monstersOne = Arrays.asList(new Spartan("Spartan 1"),
                new EvilBeast("Evil beast 1"),
                new BlessedEnt("Blessed Ent 1"),
                new IceBeast("Ice Beast 1"),
                new VolcanoSnail("Volcano Snail 1"),
                new Spartan("Spartan 2"),
                new EvilBeast("Evil beast 2"),
                new BlessedEnt("Blessed Ent 2"),
                new IceBeast("Ice Beast 2"),
                new VolcanoSnail("Volcano Snail 2"));

        //TODO ordenar el listado de monstruos que recibe el jugador uno
        System.out.println("Jugador 1: Como quiere ordenar sus monstruos? '1':por puntos de vida - '2':por tipo - '[otro valor]':dejarlo como está");
        int option = s.nextInt();
        if (option == 1) 
        	monstersOne.sort(new LifeComparator());
        else if (option == 2)
        	monstersOne.sort(new TypeComparator());
        
        rumbleGame.getPlayerOne().setMonsters(monstersOne);

        List<Monster> monstersTwo = Arrays.asList(new EvilBeast("Evil beast 3"),
        		new Spartan("Spartan B"),
        		new IceBeast("Ice Beast 3"),
        		new VolcanoSnail("Volcano Snail 3"),
                new BlessedEnt("Blessed Ent 3"));

        //TODO ordenar el listado de monstruos que recibe el jugador dos
        System.out.println("Jugador 2: Como quiere ordenar sus monstruos? '1':por puntos de vida - '2':por tipo - '[otro valor]':dejarlo como está");
        option = s.nextInt();
        if (option == 1) 
        	monstersTwo.sort(new LifeComparator());
        else if (option == 2)
        	monstersTwo.sort(new TypeComparator());
        
        rumbleGame.getPlayerTwo().setMonsters(monstersTwo);
        s.close();
        rumbleGame.startGame();
    }
}