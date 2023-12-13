package entregable.orden;

import game.components.Monster;

public class LifeComparator extends MonsterComparator {

	@Override
	public int compare(Monster m1, Monster m2) {
		int res;
		
		int m1Life = m1.getLife();
		int m2Life = m2.getLife();
				
		if(m1Life > m2Life) 
			res = 1;
		else if(m1Life == m2Life)
			res = 0;
		else
			res = -1;
		
		return res;
	}

}
