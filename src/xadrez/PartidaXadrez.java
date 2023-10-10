package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.peca.Bispo;
import xadrez.peca.Cavalo;
import xadrez.peca.Peao;
import xadrez.peca.Rainha;
import xadrez.peca.Rei;
import xadrez.peca.Torre;

public class PartidaXadrez {

	private Integer vezJogador;
	private Cor atualJogador;
	private Tabuleiro tabuleiro;
	private boolean cheque;
	private boolean chequeMate;
	private PecaXadrez enPassantPeçaVulneravel;

	private List<Peca> pecasDoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		vezJogador = 1;
		atualJogador = Cor.WHITE;
		inciarPartida();
	}

	public Integer getVezJogador() {
		return vezJogador;
	}

	public Cor getAtualJogador() {
		return atualJogador;
	}

	public boolean getCheque() {
		return cheque;
	}

	public boolean getChequeMate() {
		return chequeMate;
	}

	public PecaXadrez getEnPassantPeçaVulneravel() {
		return enPassantPeçaVulneravel;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] pecaXadrez = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int l = 0; l < tabuleiro.getLinhas(); l++) {
			for (int c = 0; c < tabuleiro.getColunas(); c++) {
				pecaXadrez[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return pecaXadrez;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.paraPosicao();
		ValidarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez executarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		ValidarPosicaoOrigem(origem);
		ValidarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMoverPeca(origem, destino);

		if (testarCheque(atualJogador)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezExcecao("Voce nao pode fazer esse movimento. Seu rei ficou em cheque");
		}

		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

		cheque = (testarCheque(adversario(atualJogador))) ? true : false;

		if (testarChequeMate(adversario(atualJogador))) {
			chequeMate = true;
		} else {
			vezProximoJogador();
		}

		// MovimentoEspecialEnPassant
		if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantPeçaVulneravel = pecaMovida;
		} 
		else {
			enPassantPeçaVulneravel = null;
		}

		return (PecaXadrez) pecaCapturada;
	}

	private Peca fazerMoverPeca(Posicao posicaoOrigem, Posicao posicaoDestino) {
		PecaXadrez peca = (PecaXadrez) tabuleiro.removerPeca(posicaoOrigem);
		peca.aumentarContagemMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(posicaoDestino);
		tabuleiro.moverPecaLugar(peca, posicaoDestino);

		if (pecaCapturada != null) {
			pecasDoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// movimentoEspecialRoqueDoLadoDoReiTorre_RoquePequeno
		if (peca instanceof Rei && posicaoDestino.getColuna() == posicaoOrigem.getColuna() + 2) {
			Posicao posicaoOrigemTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() + 3);
			Posicao posicaoDestinoTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(posicaoOrigemTorre);
			tabuleiro.moverPecaLugar(torre, posicaoDestinoTorre);
			torre.aumentarContagemMovimentos();
		}

		// movimentoEspecialRoqueDoLadoDaRainhaTorre_RoqueGrande
		if (peca instanceof Rei && posicaoDestino.getColuna() == posicaoOrigem.getColuna() - 2) {
			Posicao posicaoOrigemTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() - 4);
			Posicao posicaoDestinoTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(posicaoOrigemTorre);
			tabuleiro.moverPecaLugar(torre, posicaoDestinoTorre);
			torre.aumentarContagemMovimentos();
		}

		// MovimentoEspecialEnPassant
		if (peca instanceof Peao) {
			if (posicaoOrigem.getColuna() != posicaoDestino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (peca.getCor() == Cor.WHITE) {
					posicaoPeao = new Posicao(posicaoDestino.getLinha() + 1, posicaoDestino.getColuna());
				} else {
					posicaoPeao = new Posicao(posicaoDestino.getLinha() - 1, posicaoDestino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasDoTabuleiro.remove(pecaCapturada);
			}

		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao posicaoOrigem, Posicao posicaoDestino, Peca pecaCapturada) {
		PecaXadrez peca = (PecaXadrez) tabuleiro.removerPeca(posicaoDestino);
		peca.diminuirContagemMovimentos();
		tabuleiro.moverPecaLugar(peca, posicaoOrigem);

		if (pecaCapturada != null) {
			tabuleiro.moverPecaLugar(pecaCapturada, posicaoDestino);
			pecasCapturadas.remove(pecaCapturada);
			pecasDoTabuleiro.add(pecaCapturada);
		}

		// movimentoEspecialRoqueDoLadoDoReiTorre_RoquePequeno
		if (peca instanceof Rei && posicaoDestino.getColuna() == posicaoOrigem.getColuna() + 2) {
			Posicao posicaoOrigemTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() + 3);
			Posicao posicaoDestinoTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(posicaoDestinoTorre);
			tabuleiro.moverPecaLugar(torre, posicaoOrigemTorre);
			torre.diminuirContagemMovimentos();
		}

		// movimentoEspecialRoqueDoLadoDaRainhaTorre_RoqueGrande
		if (peca instanceof Rei && posicaoDestino.getColuna() == posicaoOrigem.getColuna() - 2) {
			Posicao posicaoOrigemTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() - 4);
			Posicao posicaoDestinoTorre = new Posicao(posicaoOrigem.getLinha(), posicaoOrigem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(posicaoDestinoTorre);
			tabuleiro.moverPecaLugar(torre, posicaoOrigemTorre);
			torre.diminuirContagemMovimentos();
		}

		// MovimentoEspecialEnPassant
		if (peca instanceof Peao) {
			if (posicaoOrigem.getColuna() != posicaoDestino.getColuna() && pecaCapturada == enPassantPeçaVulneravel) {
				PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(posicaoDestino);
				Posicao posicaoPeao;
				if (peca.getCor() == Cor.WHITE) {
					posicaoPeao = new Posicao(3, posicaoDestino.getColuna());
				} else {
					posicaoPeao = new Posicao(4, posicaoDestino.getColuna());
				}
				tabuleiro.moverPecaLugar(peao, posicaoPeao);
			}
		}
	}

	private void ValidarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.ExistePeca(posicao)) {
			throw new XadrezExcecao("Nao Existe Peca na Posicao de Origem");
		}

		if (atualJogador != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}

		if (!tabuleiro.peca(posicao).ExisteAlgumMovimentoPossivel()) {
			throw new XadrezExcecao("Nao ha movimentos possiveis para a peca escolhida");
		}
	}

	private void ValidarPosicaoDestino(Posicao posicaoOrigemInicio, Posicao posicaoDestinoFim) {
		if (!tabuleiro.peca(posicaoOrigemInicio).movimentoPossivel(posicaoDestinoFim)) {
			throw new XadrezExcecao("A peca escolhida nao pode se mover para a posicao alvo");
		}
	}

	private void vezProximoJogador() {
		vezJogador++;
		atualJogador = (atualJogador == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private Cor adversario(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> listaRei = pecasDoTabuleiro.stream().filter(lista -> ((PecaXadrez) lista).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca pecaRei : listaRei) {
			if (pecaRei instanceof Rei) {
				return (PecaXadrez) pecaRei;
			}
		}
		throw new IllegalStateException("Nao ha rei " + cor + " no tabuleiro");
	}

	private boolean testarCheque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecasOponente = pecasDoTabuleiro.stream()
				.filter(lista -> ((PecaXadrez) lista).getCor() == adversario(cor)).collect(Collectors.toList());
		for (Peca peca : pecasOponente) {
			boolean[][] mat = peca.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarChequeMate(Cor cor) {
		if (!testarCheque(cor)) {
			return false;
		}
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : list) {
			boolean[][] mat = peca.movimentosPossiveis();
			for (int l = 0; l < tabuleiro.getLinhas(); l++) {
				for (int c = 0; c < tabuleiro.getColunas(); c++) {
					if (mat[l][c]) {
						Posicao posicaoOrigem = ((PecaXadrez) peca).getPosicaoXadrez().paraPosicao();
						Posicao posicaoDestino = new Posicao(l, c);
						Peca pecaCapturada = fazerMoverPeca(posicaoOrigem, posicaoDestino);
						boolean testarCheque = testarCheque(cor);
						desfazerMovimento(posicaoOrigem, posicaoDestino, pecaCapturada);
						if (!testarCheque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void posicaoNovaPecaXadrez(char coluna, Integer linha, PecaXadrez peca) {
		tabuleiro.moverPecaLugar(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasDoTabuleiro.add(peca);
	}

	private void inciarPartida() {

		posicaoNovaPecaXadrez('a', 1, new Torre(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('h', 1, new Torre(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('e', 1, new Rei(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('d', 1, new Rainha(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('a', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('b', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('c', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('d', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('e', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('f', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('g', 2, new Peao(tabuleiro, Cor.WHITE, this));
		posicaoNovaPecaXadrez('h', 2, new Peao(tabuleiro, Cor.WHITE, this));

		posicaoNovaPecaXadrez('a', 8, new Torre(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('e', 8, new Rei(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('d', 8, new Rainha(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('h', 8, new Torre(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('c', 8, new Bispo(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('f', 8, new Bispo(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
		posicaoNovaPecaXadrez('a', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('b', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('c', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('d', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('e', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('f', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('g', 7, new Peao(tabuleiro, Cor.BLACK, this));
		posicaoNovaPecaXadrez('h', 7, new Peao(tabuleiro, Cor.BLACK, this));
	}
}
