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
        this.life = 850;
        this.monsterName = name;
        this.activeSkill = skills.get(0);
        this.types = Arrays.asList(Type.WOOD, Type.ANGEL);
    }

    @Override
    public void attack(Monster enemy) {
        enemy.onDamageReceive(this.activeSkill.damage(enemy), this);
    }
    @Override
    public void move(PathBox oldPathBox, PathBox newPathBox) {
        super.move(oldPathBox, newPathBox);
        if(activeSkill instanceof BranchWhip) {
            this.activeSkill = skills.get(1);
        } else {
            this.activeSkill = skills.get(0);
        }
    }
}

