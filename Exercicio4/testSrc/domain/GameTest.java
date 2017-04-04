package domain;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import domain.Puzzle.Direction;

public class GameTest {
	
	private PuzzleGame game;
	
	@Before
	public void criaGame(){
		game = new PuzzleGame(3, new ShufflePuzzleWithoutShuffle());
	}
	
	// Teste MoveEmptyCell em todas as direções
	@Test 
	public void testMoveEmptyCellWithInvalidPosition(){
		assertFalse(game.moveEmptyCell(null));
	}
	
	@Test
	public void testMoveEmptyCellDown() {		
		// Implicit Setup
		
		Position initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Tenta mover para baixo e não deve acontecer nada
		game.moveEmptyCell(Direction.DOWN);
		Position finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
		
		// Move para cima e depois para baixo e deve voltar a posição inicial
		game.moveEmptyCell(Direction.UP);
		game.moveEmptyCell(Direction.DOWN);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
	}
	
	@Test
	public void testMoveEmptyCellUp() {		
		// Implicit Setup
		
		Position initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Move para cima e deve subir uma linha
		game.moveEmptyCell(Direction.UP);
		Position finalEmptyCellPos = game.getEmptyCellPosition();
		assertNotEquals(initialEmptyCellPos, finalEmptyCellPos);
		assertEquals(initialEmptyCellPos.getLine()-1, finalEmptyCellPos.getLine());
		
		// Move para cima para chegar no topo do board
		game.moveEmptyCell(Direction.UP);
		initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Move para cima e não deve acontecer nada
		game.moveEmptyCell(Direction.UP);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
	}
	
	@Test
	public void testMoveEmptyCellRight() {		
		// Implicit Setup
		
		Position initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Tenta mover para direita e não deve acontecer nada
		game.moveEmptyCell(Direction.RIGHT);
		Position finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
		
		// Move para esquerda e depois para direita e deve voltar a posição inicial
		game.moveEmptyCell(Direction.LEFT);
		game.moveEmptyCell(Direction.RIGHT);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
	}
	
	@Test
	public void testMoveEmptyCellLeft() {		
		// Implicit Setup
		
		Position initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Move para esquerda e deve diminuir uma coluna
		game.moveEmptyCell(Direction.LEFT);
		Position finalEmptyCellPos = game.getEmptyCellPosition();
		assertNotEquals(initialEmptyCellPos, finalEmptyCellPos);
		assertEquals(initialEmptyCellPos.getColumn()-1, finalEmptyCellPos.getColumn());
		
		// Move para esquerda para chegar na borda do board
		game.moveEmptyCell(Direction.LEFT);
		initialEmptyCellPos = game.getEmptyCellPosition();
		
		// Move para esquerda e não deve acontecer nada
		game.moveEmptyCell(Direction.LEFT);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos, finalEmptyCellPos);
	}
	
	// Teste putTilesInTheBoard
	@Test
	public void testPutTilesInTheBoard(){
		// Método putTilesInTheBoard ja é chamado no construtor do PuzzleGame
		// que é criado no Implicit Setup
		
		assertEquals(game.getBoard().getTile(new Position(1, 1)), new Tile(1));
		assertEquals(game.getBoard().getTile(new Position(1, 2)), new Tile(2));
		assertEquals(game.getBoard().getTile(new Position(1, 3)), new Tile(3));
		assertEquals(game.getBoard().getTile(new Position(2, 1)), new Tile(4));
		assertEquals(game.getBoard().getTile(new Position(2, 2)), new Tile(5));
		assertEquals(game.getBoard().getTile(new Position(2, 3)), new Tile(6));
		assertEquals(game.getBoard().getTile(new Position(3, 1)), new Tile(7));
		assertEquals(game.getBoard().getTile(new Position(3, 2)), new Tile(8));
		assertNull(game.getBoard().getTile(new Position(3, 3)));		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testPutTilesInTheBoardWithEmptyList() {
		// Implicit Setup
		
		game.putTilesInTheBoard(game.getBoard(), new ArrayList<Tile>());
	}

}
