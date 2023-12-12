package entregable.ataques;


import game.components.Monster;

public class MetalClaw implements Metal {

    @Override
    public int damage(Monster monster) {
        return 500;
    }
}