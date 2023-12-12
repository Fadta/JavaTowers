package entregable.ataques;

import game.components.Monster;

public class BranchWhip implements Wood {

    @Override
    public int damage(Monster monster) {
        return 500;
    }
}