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
		posicaoNovaPecaXadrez('c', 1, new Torre(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('c', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('d', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('e', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('e', 1, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('d', 1, new Rei(tabuleiro, Cor.WHITE));

        posicaoNovaPecaXadrez('c', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('c', 8, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('d', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('e', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('e', 8, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
