package jogoTabuleiro;

public abstract class  Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentoPossivel (Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao .getColuna()];
	}

	public boolean ExisteAlgumMovimentoPossivel() {
		boolean[][] movimentoPossivel = movimentosPossiveis();
		for (int l=0; l<movimentoPossivel.length; l++) {
			for (int c=0; c<movimentoPossivel.length; c++) {
				if (movimentoPossivel[l][c]) {
					return true;
				}
			}
		}
		return false;
	}
}
