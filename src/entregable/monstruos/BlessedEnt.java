package entregable.monstruos;
import java.util.Arrays;
import java.util.List;

import game.attacks.Attack;
import entregable.ataques.BranchWhip;
import entregable.ataques.SkyBeam;
import game.components.*;
import game.types.Type;
public class BlessedEnt extends Monster{
	private List<Attack> skills = Arrays.asList(new BranchWhip(), new SkyBeam());
    public BlessedEnt(String name) {
        this.life = 700;
        this.monsterName = name;
        this.activeSkill = skills.get(0);
        this.types = Arrays.asList(Type.WOOD, Type.ANGEL);
    }

    @Override
    public void attack(Monster enemy) {
        
    	if (this.life > 0)
        	enemy.onDamageReceive(this.activeSkill.damage(enemy), this);
    }
    
    @Override
    public void onDamageReceive(Integer damage, Monster monster) {
        this.life = this.life - damage;
        if(this.life < 0) {
            this.life = 0;
        }
        System.out.println(this + " fue herido, queda con " + this.life + " puntos de vida");
    }

    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        this.life += 50; //Life gain per move
        System.out.println(this + " activa su regeneración! Se cura 50 puntos de vida");
        if(activeSkill instanceof BranchWhip) {
            this.activeSkill = skills.get(1);
        } else {
            this.activeSkill = skills.get(0);
        }
    }
}

