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

		Posicao movimentoRei = new Posicao(0, 0);

		// MoverParaCima
		movimentoRei.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaBaixo
		movimentoRei.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaEsquerda
		movimentoRei.setNovaPosicao(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaDireita
		movimentoRei.setNovaPosicao(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoNoroeste
		movimentoRei.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoNordeste
		movimentoRei.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoSuldoeste
		movimentoRei.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		// MoverParaDiagonalSentidoSuldeste
		movimentoRei.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(movimentoRei) && podeMover(movimentoRei)) {
			movimentosPossiveis[movimentoRei.getLinha()][movimentoRei.getColuna()] = true;
		}

		return movimentosPossiveis;
	}
}
