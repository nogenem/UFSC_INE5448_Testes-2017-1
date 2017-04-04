package domain;

public class Position {
	
	int line;
	int column;
	
	public Position(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public int getLine() {
		return this.line;
	}

	public int getColumn() {
		return this.column;
	}
	
	// Métodos criados para fazer os testes
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Position p = (Position) obj;
		return this.line == p.line &&
				this.column == p.column;
	}
	
	@Override
	public String toString() {
		return "<" +this.line+ ", " +this.column+ ">";
	}
	// End
}
