package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
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
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)
					&& getTabuleiro().ExistePosicao(pos2) && !getTabuleiro().ExistePeca(pos2)
					&& getContagemMovimento() == 0) {
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

			// MovimentoEspecialEnPassantPecasBrancas
			if (posicao.getLinha() == 3) {
				Posicao posicaoEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().ExistePosicao(posicaoEsquerda) && existePecaOponente(posicaoEsquerda)
						&& getTabuleiro().peca(posicaoEsquerda) == partidaXadrez.getEnPassantPeçaVulneravel()) {
					movimentosPossiveis[posicaoEsquerda.getLinha() - 1][posicaoEsquerda.getColuna()] = true;
				}

				Posicao posicaoDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().ExistePosicao(posicaoDireita) && existePecaOponente(posicaoDireita)
						&& getTabuleiro().peca(posicaoDireita) == partidaXadrez.getEnPassantPeçaVulneravel()) {
					movimentosPossiveis[posicaoDireita.getLinha() - 1][posicaoDireita.getColuna()] = true;
				}
			}

		} else {
			pos.setNovaPosicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)) {
				movimentosPossiveis[pos.getLinha()][pos.getColuna()] = true;
			}

			pos.setNovaPosicao(posicao.getLinha() + 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().ExistePosicao(pos) && !getTabuleiro().ExistePeca(pos)
					&& getTabuleiro().ExistePosicao(pos2) && !getTabuleiro().ExistePeca(pos2)
					&& getContagemMovimento() == 0) {
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

			// MovimentoEspecialEnPassantPecasPretas
			if (posicao.getLinha() == 4) {
				Posicao posicaoEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().ExistePosicao(posicaoEsquerda) && existePecaOponente(posicaoEsquerda)
						&& getTabuleiro().peca(posicaoEsquerda) == partidaXadrez.getEnPassantPeçaVulneravel()) {
					movimentosPossiveis[posicaoEsquerda.getLinha() + 1][posicaoEsquerda.getColuna()] = true;
				}

				Posicao posicaoDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().ExistePosicao(posicaoDireita) && existePecaOponente(posicaoDireita)
						&& getTabuleiro().peca(posicaoDireita) == partidaXadrez.getEnPassantPeçaVulneravel()) {
					movimentosPossiveis[posicaoDireita.getLinha() + 1][posicaoDireita.getColuna()] = true;
				}
			}
		}
		return movimentosPossiveis;
	}

	@Override
	public String toString() {
		return "P";
	}
}
