package game.attacks;

import java.util.List;

import entregable.ataques.DamageTable;
import game.components.Monster;
import game.types.Type;

public class Curse implements Demon {

    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = 300;
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.DEMON, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}
