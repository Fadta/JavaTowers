package game.attacks;
import java.util.List;

import entregable.ataques.DamageTable;
import game.types.*;

import game.components.Monster;
import game.random.RandomGenerator;

public class Slice implements Sword {

    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = RandomGenerator.getInstance().calculateDamage(300, 500);
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<=size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.SWORD, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}
