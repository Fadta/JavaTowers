package game.monsters;

import game.attacks.ColdBreath;
import game.attacks.IceSpike;
import game.components.Monster;
import game.components.PathBox;
import game.attacks.Cold;
import game.types.Type;

import java.util.Arrays;
import java.util.List;

public class IceBeast extends Monster {

    private List<Cold> skills = Arrays.asList(new ColdBreath(), new IceSpike());
    private boolean ambush;
    private double ambushMult;
    public IceBeast(String name) {
        this.life = 400;
        this.ambush = false;
        this.ambushMult = 1.5;
        this.monsterName = name;
        this.activeSkill = skills.get(0);
        this.types = Arrays.asList(Type.COLD, Type.BEAST);
    }

    @Override
    public void attack(Monster monster) {
    	if (ambush) {
    		int dmg = (int) ((this.activeSkill.damage(monster))*(this.ambushMult));
    		ambush = false;
    		monster.onDamageReceive(dmg, this);
    	}
    	else {
        monster.onDamageReceive(this.activeSkill.damage(monster), this);
    	}
    }

    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        if (ambush==false) {
        	ambush = true;
        	System.out.println(this + " se prepara para atacar! Su próximo ataque hará x1.5 de daño");
        }
        if(activeSkill instanceof ColdBreath) {
            this.activeSkill = skills.get(1);
        } else {
            this.activeSkill = skills.get(0);
        }
    }
}
