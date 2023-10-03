package xadrez;

import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
	}

	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] pecaXadrez = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for (int l=0; l<tabuleiro.getLinha(); l++) {
			for(int c=0; c<tabuleiro.getColuna(); c++) {
				pecaXadrez[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return pecaXadrez;
	}
}
