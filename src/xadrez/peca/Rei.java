package xadrez.peca;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez rei = (PecaXadrez) getTabuleiro().peca(posicao);
		return rei == null || rei.getCor() != getCor();
	}

	private boolean testarJogadaEspecialRoque(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez) getTabuleiro().peca(posicao);
		return peca != null && peca instanceof Torre && peca.getCor() == getCor() && peca.getContagemMovimento() == 0;
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

		// MovimentoEspecialRoque
		if (getContagemMovimento() == 0 && partidaXadrez.getCheque()) {
			// movimentoEspecialRoqueDoLadoDoReiTorre_RoquePequeno
			Posicao posicaoTorreLadoReiRoquePequeno = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testarJogadaEspecialRoque(posicaoTorreLadoReiRoquePequeno)) {
				Posicao pos1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao pos2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(pos1) == null && getTabuleiro().peca(pos2) == null) {
					movimentosPossiveis[posicaoTorreLadoReiRoquePequeno.getLinha()][posicaoTorreLadoReiRoquePequeno.getColuna() + 2] = true;
				}
			}

			// movimentoEspecialRoqueDoLadoDaRainhaTorre_RoqueGrande
			Posicao posicaoTorreLadoRainhaRoqueGrande = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testarJogadaEspecialRoque(posicaoTorreLadoRainhaRoqueGrande)) {
				Posicao pos1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao pos2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao pos3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(pos1) == null && getTabuleiro().peca(pos2) == null && getTabuleiro().peca(pos3) == null) {
					movimentosPossiveis[posicaoTorreLadoRainhaRoqueGrande.getLinha()][posicaoTorreLadoRainhaRoqueGrande.getColuna() - 2] = true;
				}
			}
		}

		return movimentosPossiveis;
	}
}
