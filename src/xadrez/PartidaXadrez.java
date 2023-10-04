package xadrez;

import jogoTabuleiro.Tabuleiro;
import xadrez.peca.Rei;
import xadrez.peca.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inciarPartida();
	}

	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] pecaXadrez = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int l=0; l<tabuleiro.getLinhas(); l++) {
			for(int c=0; c<tabuleiro.getColunas(); c++) {
				pecaXadrez[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return pecaXadrez;
	}
	
	private void posicaoNovaPecaXadrez(char coluna, Integer linha, PecaXadrez peca ) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		
	}
	private void inciarPartida() {
		posicaoNovaPecaXadrez('a', 6, new Torre(tabuleiro, Cor.WRITE));
		posicaoNovaPecaXadrez('e', 1, new Rei(tabuleiro, Cor.WRITE));
		posicaoNovaPecaXadrez('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
