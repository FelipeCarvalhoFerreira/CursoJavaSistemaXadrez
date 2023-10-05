package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao movimentoTorre = new Posicao(0, 0);

		// MoverParaCima
		movimentoTorre.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(movimentoTorre) && !getTabuleiro().ExistePeca(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
			movimentoTorre.setLinha((movimentoTorre.getLinha() - 1));
		}
		if (getTabuleiro().ExistePosicao(movimentoTorre) && existePecaOponente(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
		}

		// MoverParaEsquerda
		movimentoTorre.setNovaPosicao(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(movimentoTorre) && !getTabuleiro().ExistePeca(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
			movimentoTorre.setColuna((movimentoTorre.getColuna() - 1));
		}
		if (getTabuleiro().ExistePosicao(movimentoTorre) && existePecaOponente(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
		}

		// MoverParaDireita
		movimentoTorre.setNovaPosicao(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(movimentoTorre) && !getTabuleiro().ExistePeca(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
			movimentoTorre.setColuna((movimentoTorre.getColuna() + 1));
		}
		if (getTabuleiro().ExistePosicao(movimentoTorre) && existePecaOponente(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
		}

		// MoverParaBaixo
		movimentoTorre.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(movimentoTorre) && !getTabuleiro().ExistePeca(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
			movimentoTorre.setLinha((movimentoTorre.getLinha() + 1));
		}
		if (getTabuleiro().ExistePosicao(movimentoTorre) && existePecaOponente(movimentoTorre)) {
			movimentosPossiveis[movimentoTorre.getLinha()][movimentoTorre.getColuna()] = true;
		}
		return movimentosPossiveis;

	}

}
