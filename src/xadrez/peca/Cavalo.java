package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez rei = (PecaXadrez) getTabuleiro().peca(posicao);
		return rei == null || rei.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().ExistePosicao(pos) && podeMover(pos)) {
			movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
		}

		return movimentosPossiveis;
	}
}