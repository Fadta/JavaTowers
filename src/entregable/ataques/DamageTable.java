package entregable.ataques;
import java.util.EnumMap;

import game.types.Type;

public class DamageTable {
	private EnumMap<Type, EnumMap<Type, Double>> damageTable; // matrix structure
	private static DamageTable instance;
	
	private DamageTable() {
		damageTable = new EnumMap<Type, EnumMap<Type, Double>>(Type.class); // Prepare EnumMap to receive keys of type: Type
		
		initializeMap();
		
		// adds the strength and weakness multiplier
		// for each (strong, weak) Type pair]
		
		associateStrength(Type.FIRE, Type.COLD);
		associateStrength(Type.FIRE, Type.METAL);
		associateStrength(Type.FIRE, Type.WOOD);
		
		associateStrength(Type.COLD, Type.WATER);
		
		associateStrength(Type.WOOD, Type.COLD);
		associateStrength(Type.WOOD, Type.WATER);
		
		associateStrength(Type.DEMON, Type.SWORD);
		
		associateStrength(Type.ANGEL, Type.DEMON);
		associateStrength(Type.ANGEL, Type.BEAST);
		
		associateStrength(Type.BEAST, Type.BEAST);

		associateStrength(Type.SWORD, Type.BEAST);
		
		associateStrength(Type.ROCK, Type.SWORD);
		
		associateStrength(Type.WATER, Type.FIRE);
		associateStrength(Type.WATER, Type.ROCK);
		
		associateStrength(Type.METAL, Type.ROCK);
		associateStrength(Type.METAL, Type.WOOD);
	}
	
	
	private EnumMap<Type, Double> createRow() {
		EnumMap<Type, Double> row = new EnumMap<Type, Double>(Type.class);
		
		for(Type type : Type.values()) {
			// these are multipliers, need to be initialised at 1.0
			row.put(type, 1.0);
		}
		
		return row;
	}
	
	private void initializeMap() {
		for(Type type : Type.values()){
			damageTable.put(type, createRow());
		}
	}

	private void associateStrength(Type strong, Type weak) {
		addMultiplier(strong, weak, 2.0);
		addMultiplier(weak, strong, 0.5);
	}
	
	private void addMultiplier(Type attacking, Type defending, Double multiplier) {
		damageTable.get(attacking).put(defending, multiplier);
	}

	
	public double getMultiplier(Type attacking, Type defending) {
		return damageTable.get(attacking).get(defending);
	}
	
	
	public static DamageTable getInstance() {
		if(instance == null) {
			instance = new DamageTable();
		}
		
		return instance;
	}

}
