package game.monsters;

import game.attacks.Slice;
import game.components.Monster;
import game.components.PathBox;
import game.types.Type;

import java.util.Arrays;

public class Spartan extends Monster {
	private boolean parry;
	private double parryMult;
    public Spartan(String name) {
    	this.parry = false;
    	this.parryMult = 0.5;
        this.life = 500;
        this.activeSkill = new Slice();
        this.monsterName = name;
        this.types = Arrays.asList(Type.SWORD);
    }
    
    @Override
    public void onDamageReceive(Integer damage, Monster monster) {
    	if (parry) {
    		damage = (int)(damage*this.parryMult);
    		parry=false;
    		System.out.println(this + " se defiende de la mitad del daño del ataque!");
    	}
        this.life = this.life - damage;
        if(this.life < 0) {
            this.life = 0;
        }
        System.out.println(this + " fue herido, queda con " + this.life + " puntos de vida");
    }
    
    @Override
    public void attack(Monster enemy) {
        int damage = this.activeSkill.damage(enemy);
        System.out.println("--     ["+ this +"] ataca a [" + enemy + "] haciendole " + damage + " de daño");
        enemy.onDamageReceive(damage, this);
    }
    
    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        if (parry==false) {
        	parry=true;
        	System.out.println(this + " se prepara para defender!");
        }
        
    }
}
