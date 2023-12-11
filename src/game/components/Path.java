package game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Path {
    private List<PathBox> pathBoxes = new ArrayList<>();

    public List<PathBox> getPathBoxes() {
        return pathBoxes;
    }

    public void setPathBoxes(List<PathBox> pathBoxes) {
        this.pathBoxes = pathBoxes;
    }

    public boolean haveMonster(Long playerId) {
    	// match criteria: Monster exists && Monster is owned by player($playerID)
        return pathBoxes.stream().anyMatch(
        			pathBox ->
        			pathBox.getMonster() != null && pathBox.getMonster().getPlayer().getId().equals(playerId)
        		);
    }

    private void informBattle(String slotName, Monster attacking, Monster defending) {
    	System.out.println("Casilla " + slotName + " Esta ocupada!!!");
        System.out.println("Monstruo " + attacking + " Ataca a " + defending);
    }
    
    private void moveOrBattle(PathBox currentBox, PathBox nextBox) {
    	if (nextBox.getMonster() == null) {
        	currentBox.getMonster().move(currentBox, nextBox);
        
        } else {
        	// there is a monster => battle
        	Monster ownedMonster = currentBox.getMonster();
        	Monster enemyMonster = nextBox.getMonster();
        	
            informBattle(nextBox.getName(), ownedMonster, enemyMonster);
            ownedMonster.attack(enemyMonster);
            enemyMonster.attack(ownedMonster);
            
            
            if (enemyMonster.getLife() <= 0) {
            	// enemy died
            	nextBox.setMonster(null);
            }
        }
    }
    
    private void marchForward(Castle enemyCastle, PathBox currentBox, PathBox nextBox) {
    	boolean arrivedAtCastle = (nextBox == null);
    	if (arrivedAtCastle) {
    		enemyCastle.setLife(enemyCastle.getLife() - 1);
            currentBox.setMonster(null);
    	} 
    	else {
    		moveOrBattle(currentBox, nextBox);
    	}
    		
    }
    
    public void nextRound(Long playerId, Castle enemyCastle) {
        Optional<PathBox> occupiedPathBox =
        		pathBoxes.stream().filter(
        				pathBox -> // filter: Monster exists & Monster is owned by player($playerID)
        				pathBox.getMonster() != null && pathBox.getMonster().getPlayer().getId().equals(playerId)
        		).findFirst();
        
        if(occupiedPathBox.isPresent()) {
        	// PathBox is occupied by a monster
        	PathBox currentBox = occupiedPathBox.get();
        	PathBox advanceDirection;
            if(playerId.equals(1L)) {
            	// player 1 advances north
            	advanceDirection = currentBox.getNorthBox();
            } else {
            	// player 2 advances south
            	advanceDirection = currentBox.getSouthBox();
            }
            
            marchForward(enemyCastle, currentBox, advanceDirection);
        }
    }

    public void update() {
        this.pathBoxes.forEach(PathBox::update);
    }

    public void releaseMonster(Long playerId, Monster monster) {
        if(playerId.equals(1L)) {
            if(pathBoxes.get(2).getMonster() == null) {
                pathBoxes.get(2).setMonster(monster);
            }
        } else {
            if(pathBoxes.get(0).getMonster() == null) {
                pathBoxes.get(0).setMonster(monster);
            }
        }
    }
}
