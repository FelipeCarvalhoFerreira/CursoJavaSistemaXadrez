package xadrez;

import jogoTabuleiro.Posicao;
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
		PecaXadrez[][] pecaXadrez = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for (int l=0; l<tabuleiro.getLinha(); l++) {
			for(int c=0; c<tabuleiro.getColuna(); c++) {
				pecaXadrez[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return pecaXadrez;
	}
	
	private void inciarPartida() {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cor.WRITE), new Posicao(0, 0));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.WRITE), new Posicao(0, 3));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(7, 4));
	}
}
