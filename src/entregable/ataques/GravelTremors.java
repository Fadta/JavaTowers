package entregable.ataques;

import java.util.List;

import game.types.*;

import game.components.Monster;
import game.random.RandomGenerator;

public class GravelTremors implements Rock {

    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = RandomGenerator.getInstance().calculateDamage(200, 400);
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.ROCK, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}