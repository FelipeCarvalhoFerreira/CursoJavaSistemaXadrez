package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// MoverParaCima
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setLinha((pos.getLinha() - 1));
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaEsquerda
		pos.setNovaPosicao(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setColuna((pos.getColuna() - 1));
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDireita
		pos.setNovaPosicao(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setColuna((pos.getColuna() + 1));
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaBaixo
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			pos.setLinha((pos.getLinha() + 1));
		}
		if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

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