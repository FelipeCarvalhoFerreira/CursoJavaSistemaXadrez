package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// MoverParaDiagonalNoroeste
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setNovaPosicao(pos.getLinha() - 1, pos.getColuna() - 1);
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalNordeste
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setNovaPosicao(pos.getLinha() - 1, pos.getColuna() + 1);
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSuldeste
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setNovaPosicao(pos.getLinha() + 1, pos.getColuna() + 1);
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSuldoeste
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setNovaPosicao(pos.getLinha() + 1, pos.getColuna() - 1);
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}
		return movimentosPossiveis;

	}

}