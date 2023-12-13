package entregable.orden;

import game.components.Monster;

public class TypeComparator extends MonsterComparator {

	@Override
	public int compare(Monster m1, Monster m2) {
		int res;
		int m1Ordinal = m1.getTypes().get(0).ordinal();
		int m2Ordinal = m2.getTypes().get(0).ordinal();

		if(m1Ordinal > m2Ordinal) 
			res = 1;
		else if(m1Ordinal == m2Ordinal)
			res = 0;
		else
			res = -1;
		
		return res;
	}

}
