package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.EmptySprite;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCollisionsTest {
    PacManSprites sprite = new PacManSprites();
    GhostFactory ghostFactory = new GhostFactory(sprite);
    PlayerFactory playerFactory = new PlayerFactory(sprite);
    BoardFactory boardFactory = new BoardFactory(sprite);

    Ghost ghost;
    Player pacman;
    PlayerCollisions playerCollisions;

    @BeforeEach
    public void init() {
        ghost = ghostFactory.createClyde();
        pacman = playerFactory.createPacMan();
        playerCollisions = new PlayerCollisions();
    }

    @Test
    public void PlayerCollideOnGhost() {
        assertTrue(pacman.isAlive());
        playerCollisions.collide(pacman,ghost);
        assertFalse(pacman.isAlive());
    }

    @Test
    public void PlayerCollideOnPellet() {
        Pellet pellet = new Pellet(10,new EmptySprite());

        Square ground = boardFactory.createGround();
        pellet.occupy(ground);

        assertTrue(pellet.hasSquare());
        playerCollisions.collide(pacman,pellet);
        assertEquals(10, pacman.getScore());
        assertFalse(pellet.hasSquare());
    }

    @Test
    public void PlayerCollideOnNull() {
        Square ground = boardFactory.createGround();
        pacman.occupy(ground);

        assertTrue(pacman.hasSquare());
        playerCollisions.collide(pacman,null);
        assertTrue(pacman.hasSquare());
    }

    @Test
    public void GhostCollideOnPellet() {
        Pellet pellet = new Pellet(10,new EmptySprite());

        Square ground = boardFactory.createGround();
        pellet.occupy(ground);

        assertTrue(pellet.hasSquare());
        playerCollisions.collide(ghost,pellet);
        assertTrue(pellet.hasSquare());
    }

    @Test
    public void GhostCollideOnGhost() {
        Ghost ghost2 = ghostFactory.createBlinky();
        Square ground1 = boardFactory.createGround();
        Square ground2 = boardFactory.createGround();

        ghost.occupy(ground1);
        ghost2.occupy(ground2);

        playerCollisions.collide(ghost,ghost2);

        assertTrue(ghost.hasSquare());
        assertTrue(ghost2.hasSquare());
    }

}
