package entregable.ataques;

import game.components.Monster;

public class SkyBeam implements Angel {

    @Override
    public int damage(Monster monster) {
        return 500;
    }
}
