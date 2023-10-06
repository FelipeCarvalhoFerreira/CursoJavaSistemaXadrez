package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao movimentoPeao = new Posicao(0, 0);

		if (getCor() == Cor.WHITE) {

			movimentoPeao.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(movimentoPeao) && !getTabuleiro().ExistePeca(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}

			movimentoPeao.setNovaPosicao(posicao.getLinha() - 2, posicao.getColuna());
			Posicao movimentoPeao2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(movimentoPeao) && !getTabuleiro().ExistePeca(movimentoPeao) && getTabuleiro().ExistePosicao(movimentoPeao2) && !getTabuleiro().ExistePeca(movimentoPeao2) && getContagemMovimentos() == 0) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}

			movimentoPeao.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().ExistePosicao(movimentoPeao) && existePecaOponente(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}

			movimentoPeao.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().ExistePosicao(movimentoPeao) && existePecaOponente(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}
		}

		else {
			movimentoPeao.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(movimentoPeao) && !getTabuleiro().ExistePeca(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}

			movimentoPeao.setNovaPosicao(posicao.getLinha() + 2, posicao.getColuna());
			Posicao movimentoPeao2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(movimentoPeao) && !getTabuleiro().ExistePeca(movimentoPeao)&& getTabuleiro().ExistePosicao(movimentoPeao2) && !getTabuleiro().ExistePeca(movimentoPeao2) && getContagemMovimentos() == 0) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}

			movimentoPeao.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().ExistePosicao(movimentoPeao) && existePecaOponente(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}
			
			movimentoPeao.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().ExistePosicao(movimentoPeao) && existePecaOponente(movimentoPeao)) {
				movimentosPossiveis[movimentoPeao.getLinha()][movimentoPeao.getColuna()] = true;
			}
		}
		return movimentosPossiveis;
	}
}
