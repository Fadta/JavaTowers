package entregable.ataques;

import java.util.List;

import game.types.*;

import game.components.Monster;
import game.random.RandomGenerator;

public class MetalClaw implements Metal {

    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = RandomGenerator.getInstance().calculateDamage(200, 325);
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.METAL, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}