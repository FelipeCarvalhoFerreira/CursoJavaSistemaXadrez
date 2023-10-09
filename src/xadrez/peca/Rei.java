	package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez rei = (PecaXadrez) getTabuleiro().peca(posicao);
		return rei == null || rei.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// MoverParaCima
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaBaixo
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaEsquerda
		pos.setNovaPosicao(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDireita
		pos.setNovaPosicao(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoNoroeste
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoNordeste
		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoSuldoeste
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoSuldeste
		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		return movimentosPossiveis;
	}
}
