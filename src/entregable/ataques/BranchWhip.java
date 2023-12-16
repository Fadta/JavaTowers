package entregable.ataques;

import java.util.List;

import game.types.*;

import game.components.Monster;


public class BranchWhip implements Wood {

    @Override
    public int damage(Monster monster) {
    	DamageTable tabla = DamageTable.getInstance();
    	
    	List<Type> tipos = monster.getTypes();
    	int size = tipos.size();
    	
    	int dmg = 250;
    	double dmgMult = 1;
    	
    	for (int i = 0 ; i<size ; i++) {
    		dmgMult *= (tabla.getMultiplier(Type.WOOD, tipos.get(i)));
    	}
    	dmg = (int)(dmg*dmgMult);
        return dmg;
    }
}