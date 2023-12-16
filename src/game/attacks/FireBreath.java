package game.attacks;

import java.util.List;

import entregable.ataques.DamageTable;
import game.components.Monster;
import game.random.RandomGenerator;
import game.types.Type;

public class FireBreath implements Fire{
    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = RandomGenerator.getInstance().calculateDamage(200, 300);
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.FIRE, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}
