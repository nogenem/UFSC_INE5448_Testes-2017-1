package domain;

import domain.Puzzle.Direction;

public class ShufflePuzzleEmptyCellInMiddle implements StrategyShufflePuzzle {

	public void shuffle(Puzzle game) {
		game.moveEmptyCell(Direction.UP);
		game.moveEmptyCell(Direction.LEFT);
	}

}
