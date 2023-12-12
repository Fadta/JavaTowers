package entregable.ataques;

import game.components.Monster;

public class Riptide implements Water {

    @Override
    public int damage(Monster monster) {
        return 500;
    }
}
