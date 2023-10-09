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
	public boolean[][] movimentosPossiveis() {
		boolean[][] movimentosPossiveis = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		if (getCor() == Cor.WHITE) {

			pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() - 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos) && getTabuleiro().ExistePosicao(pos2) && !getTabuleiro().ExistePeca(pos2) && getContagemMovimento() == 0) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}
		}

		else {
			pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() + 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)&& getTabuleiro().ExistePosicao(pos2) && !getTabuleiro().ExistePeca(pos2) && getContagemMovimento() == 0) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}
			
			pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().ExistePosicao(pos) && existePecaOponente(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}
		}
		return movimentosPossiveis;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
