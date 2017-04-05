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
		game = new PuzzleGame(3, new ShufflePuzzleEmptyCellInMiddle());
	}
	
	// Teste MoveEmptyCell em todas as direções
	@Test 
	public void testMoveEmptyCellWithInvalidPosition(){
		assertFalse(game.moveEmptyCell(null));
	}
	
	@Test
	public void testMoveEmptyCellDown() {		
		// Implicit Setup [EmptyCell no meio do board]
		
		Position initialEmptyCellPos = game.getEmptyCellPosition(),
				finalEmptyCellPos = null;
		
		// Move para baixo e a emptyCell deve ficar na mesma coluna
		// e descer uma linha (neste caso, descer uma linha significa
		// aumentar em 1 o valor da linha)
		game.moveEmptyCell(Direction.DOWN);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos.getColumn(), finalEmptyCellPos.getColumn());
		assertEquals(initialEmptyCellPos.getLine()+1, finalEmptyCellPos.getLine());
		
		// Move para baixo de novo e a posição não deve mudar
		initialEmptyCellPos = game.getEmptyCellPosition();
		game.moveEmptyCell(Direction.DOWN);
		finalEmptyCellPos = game.getEmptyCellPosition();
	}
	
	@Test
	public void testMoveEmptyCellUp() {		
		// Implicit Setup [EmptyCell no meio do board]
		
		Position initialEmptyCellPos = game.getEmptyCellPosition(),
				finalEmptyCellPos = null;
		
		// Move para cima e a emptyCell deve ficar na mesma coluna
		// e subir uma linha (neste caso, subir uma linha significa
		// diminuir em 1 o valor da linha)
		game.moveEmptyCell(Direction.UP);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos.getColumn(), finalEmptyCellPos.getColumn());
		assertEquals(initialEmptyCellPos.getLine()-1, finalEmptyCellPos.getLine());
		
		// Move para cima de novo e a posição não deve mudar
		initialEmptyCellPos = game.getEmptyCellPosition();
		game.moveEmptyCell(Direction.UP);
		finalEmptyCellPos = game.getEmptyCellPosition();
	}
	
	@Test
	public void testMoveEmptyCellLeft() {		
		// Implicit Setup [EmptyCell no meio do board]
		
		Position initialEmptyCellPos = game.getEmptyCellPosition(),
				finalEmptyCellPos = null;
		
		// Move para esquerda e a emptyCell deve ficar na mesma linha
		// e voltar uma coluna
		game.moveEmptyCell(Direction.LEFT);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos.getColumn()-1, finalEmptyCellPos.getColumn());
		assertEquals(initialEmptyCellPos.getLine(), finalEmptyCellPos.getLine());
		
		// Move para esquerda de novo e a posição não deve mudar
		initialEmptyCellPos = game.getEmptyCellPosition();
		game.moveEmptyCell(Direction.LEFT);
		finalEmptyCellPos = game.getEmptyCellPosition();
	}
	
	@Test
	public void testMoveEmptyCellRight() {		
		// Implicit Setup [EmptyCell no meio do board]
		
		Position initialEmptyCellPos = game.getEmptyCellPosition(),
				finalEmptyCellPos = null;
		
		// Move para direita e a emptyCell deve ficar na mesma linha
		// e aumentar uma coluna
		game.moveEmptyCell(Direction.RIGHT);
		finalEmptyCellPos = game.getEmptyCellPosition();
		assertEquals(initialEmptyCellPos.getColumn()+1, finalEmptyCellPos.getColumn());
		assertEquals(initialEmptyCellPos.getLine(), finalEmptyCellPos.getLine());
		
		// Move para direita de novo e a posição não deve mudar
		initialEmptyCellPos = game.getEmptyCellPosition();
		game.moveEmptyCell(Direction.RIGHT);
		finalEmptyCellPos = game.getEmptyCellPosition();
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
		assertNull(game.getBoard().getTile(new Position(2, 2)));		
		assertEquals(game.getBoard().getTile(new Position(2, 3)), new Tile(5));
		assertEquals(game.getBoard().getTile(new Position(3, 1)), new Tile(7));
		assertEquals(game.getBoard().getTile(new Position(3, 2)), new Tile(8));
		assertEquals(game.getBoard().getTile(new Position(3, 3)), new Tile(6));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testPutTilesInTheBoardWithEmptyList() {
		// Implicit Setup
		
		game.putTilesInTheBoard(game.getBoard(), new ArrayList<Tile>());
	}

}
