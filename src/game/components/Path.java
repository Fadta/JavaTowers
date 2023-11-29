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

    public void nextRound(Long playerId, Castle castle) {
        Optional<PathBox> occupiedPathBox =
        		pathBoxes.stream().filter(
        				pathBox -> // filter: Monster exists & Monster is owned by player($playerID)
        				pathBox.getMonster() != null && pathBox.getMonster().getPlayer().getId().equals(playerId)
        		).findFirst();
        
        if(occupiedPathBox.isPresent()) {
        	// if PathBox is occupied
            if(playerId.equals(1L)) {
            	// working with player 1
                if(occupiedPathBox.get().getNorthBox() == null) {
                	// north box not occupied (arrived @Castle) => damage player & dispose monster
                    castle.setLife(castle.getLife() - 1);
                    occupiedPathBox.get().setMonster(null);
                } else {
                	// there exists north box (not arrived @Castle)
                    if(occupiedPathBox.get().getNorthBox().getMonster() == null) {
                    	// there is no monster, advance
                        occupiedPathBox.get().getMonster().move(occupiedPathBox.get(), occupiedPathBox.get().getNorthBox());
                    } else {
                    	// there is a monster => battle
                        System.out.println("Casilla " + occupiedPathBox.get().getNorthBox().getName() + " Esta ocupada!!!");
                        System.out.println("Monstruo " + occupiedPathBox.get().getMonster() + " Ataca a " + occupiedPathBox.get().getNorthBox().getMonster());
                        occupiedPathBox.get().getMonster().attack(occupiedPathBox.get().getNorthBox().getMonster());
                        occupiedPathBox.get().getNorthBox().getMonster().attack(occupiedPathBox.get().getMonster());
                        if(occupiedPathBox.get().getNorthBox().getMonster().getLife() <= 0) {
                        	// enemy died
                            occupiedPathBox.get().getNorthBox().setMonster(null);
                        }
                    }
                }
            } else {
            	// working with player 2
                if(occupiedPathBox.get().getSouthBox() == null) {
                	// south box not occupied (arrived @Castle) => damage player & dispose monster
                    castle.setLife(castle.getLife() - 1);
                    occupiedPathBox.get().setMonster(null);
                } else {
                	// there exists south box (not arrived @Castle)
                    if (occupiedPathBox.get().getSouthBox().getMonster() == null) {
                    	// there is no monster, advance
                        occupiedPathBox.get().getMonster().move(occupiedPathBox.get(), occupiedPathBox.get().getSouthBox());
                    } else {
                    	// there is a monster => battle
                        System.out.println("Casilla " + occupiedPathBox.get().getSouthBox().getName() + " Esta ocupada!!!");
                        System.out.println("Monstruo " + occupiedPathBox.get().getMonster() + " Ataca a " + occupiedPathBox.get().getSouthBox().getMonster());
                        occupiedPathBox.get().getMonster().attack(occupiedPathBox.get().getSouthBox().getMonster());
                        occupiedPathBox.get().getSouthBox().getMonster().attack(occupiedPathBox.get().getMonster());
                        if (occupiedPathBox.get().getSouthBox().getMonster().getLife() <= 0) {
                        	// enemy died
                            occupiedPathBox.get().getSouthBox().setMonster(null);
                        }
                    }
                }
            }
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
