package entregable.orden;

import java.util.Comparator;
import game.components.Monster;

public abstract class MonsterComparator implements Comparator<Monster> {

	@Override
	public abstract int compare(Monster m1, Monster m2);

	/*
	 * monstruo1 > monstruo2 => 1
	 * monstruo1 = monstruo2 => 0
	 * monstruo1 < monstruo2 => -1
	 */
}
