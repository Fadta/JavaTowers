package entregable.ataques;


import game.components.Monster;

public class GravelTremors implements Rock {

    @Override
    public int damage(Monster monster) {
        return 500;
    }
}