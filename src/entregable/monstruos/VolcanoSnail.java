package entregable.monstruos;

import java.util.Arrays;
import java.util.List;

import game.random.*;
import entregable.ataques.*;
import game.attacks.Attack;
import game.components.Monster;
import game.components.PathBox;
import game.types.Type;

public class VolcanoSnail extends Monster {
	private List<Attack> skills = Arrays.asList(new MetalClaw(), new Riptide(), new GravelTremors());
    public VolcanoSnail(String name) {
        this.life = 850;
        this.monsterName = name;
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
        this.types = Arrays.asList(Type.METAL, Type.WATER, Type.ROCK);
    }

    @Override
    public void attack(Monster enemy) {
        enemy.onDamageReceive(this.activeSkill.damage(enemy), this);
    }
    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
    }
}
