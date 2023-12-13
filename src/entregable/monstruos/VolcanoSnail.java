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
	private int dmgRed; //Initial value of flat damage reduction
    public VolcanoSnail(String name) {
    	this.dmgRed = 50;
        this.life = 600;
        this.monsterName = name;
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
        this.types = Arrays.asList(Type.METAL, Type.WATER, Type.ROCK);
    }

    @Override
    public void attack(Monster enemy) {
        enemy.onDamageReceive(this.activeSkill.damage(enemy), this);
    }
    
    @Override
    public void onDamageReceive(Integer damage, Monster monster) {
    	damage = damage - this.dmgRed; //Flat damage reduction
    	if (damage < 0) damage = 0; //It's a Volcano snail, not masoquist snail
        this.life = this.life - damage;
        if(this.life < 0) {
            this.life = 0;
        }
        System.out.println(this + " fue herido, queda con " + this.life + " puntos de vida");
    }
    
    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        this.dmgRed += 50; //Increments the reduction every time it moves
        System.out.println(this + " absorbe restos del campo y endurece su caparazón! " + this.dmgRed + " de resistencia a ataques!");
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
    }
}
