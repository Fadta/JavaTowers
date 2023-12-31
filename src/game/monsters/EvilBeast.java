package game.monsters;

import game.attacks.*;
import game.attacks.Curse;
import game.attacks.FireBreath;
import game.attacks.Pounce;
import game.components.Monster;
import game.components.PathBox;
import game.random.RandomGenerator;
import game.types.Type;

import java.util.Arrays;
import java.util.List;

public class EvilBeast extends Monster {
	private boolean cursed;
	private boolean ambush;
	private double cursedMult;
	private double ambushMult;
	private List<Attack> skills = Arrays.asList(new Curse(), new Pounce(), new FireBreath());
    public EvilBeast(String name) {
    	this.ambush = false;
    	this.cursed = false;
    	this.ambushMult = 1.25;
    	this.cursedMult = 0.85;
        this.life = 700;
        this.monsterName = name;
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
        this.types = Arrays.asList(Type.BEAST, Type.DEMON, Type.FIRE);
    }

    @Override
    public void attack(Monster enemy) {
    	if (this.life > 0) {
        	if (ambush) {
        		int dmg = (int) ((this.activeSkill.damage(enemy))*(this.ambushMult));
        		ambush = false;
        		enemy.onDamageReceive(dmg, this);
        	}
        	else {
        		enemy.onDamageReceive(this.activeSkill.damage(enemy), this);
        	}
    	}
    }
    
    @Override
    public void onDamageReceive(Integer damage, Monster monster) {
        damage = (int)(damage*this.cursedMult);
    	this.life = this.life - damage;
        if(this.life < 0) {
            this.life = 0;
        }
        System.out.println(this + " fue herido, queda con " + this.life + " puntos de vida");
    }
    
    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        oldPathBox.setMonster(null);
        newPathBox.setMonster(this);
        this.activeSkill = skills.get(RandomGenerator.getInstance().calculateDamage(0, 2));
        if (cursed==false) {
        	cursed = true;
        	System.out.println(this + " maldice a sus oponentes! Sus ataques le harán x0.85 de daño");
        }
        if (ambush==false) {
        	ambush = true;
        	System.out.println(this + " se prepara para atacar! Su próximo ataque hará x1.25 de daño");
        }
    }
}
