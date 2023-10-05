package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.peca.Rei;
import xadrez.peca.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inciarPartida();
	}

	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] pecaXadrez = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int l=0; l<tabuleiro.getLinhas(); l++) {
			for(int c=0; c<tabuleiro.getColunas(); c++) {
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
	
	public PecaXadrez executarMovimentoXadrez (PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao posicaoOrigemInicio = posicaoOrigem.paraPosicao();
		Posicao posicaoDestinoFim = posicaoDestino.paraPosicao();
		ValidarPosicaoOrigem(posicaoOrigemInicio);
		ValidarPosicaoDestino(posicaoOrigemInicio, posicaoDestinoFim);
		Peca pecaCapturada = fazerMoverPeca(posicaoOrigemInicio, posicaoDestinoFim);
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca fazerMoverPeca(Posicao posicaoOrigem, Posicao posicaoDestino) {
		Peca moverPecaOrigem = tabuleiro.removerPeca(posicaoOrigem);
		Peca capturarPecaRemovida = tabuleiro.removerPeca(posicaoDestino);
		tabuleiro.moverPecaLugar(moverPecaOrigem, posicaoDestino);
		return capturarPecaRemovida;
	}
	
	private void ValidarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.ExistePeca(posicao)) {
			throw new XadrezExcecao("Nao Existe Peca na Posicao de Origem");
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
	
	private void posicaoNovaPecaXadrez(char coluna, Integer linha, PecaXadrez peca ) {
		tabuleiro.moverPecaLugar(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		
	}
	private void inciarPartida() {
		posicaoNovaPecaXadrez('c', 1, new Torre(tabuleiro, Cor.WHITE));
		posicaoNovaPecaXadrez('c', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('d', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('e', 2, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('e', 1, new Torre(tabuleiro, Cor.WHITE));
        posicaoNovaPecaXadrez('d', 1, new Rei(tabuleiro, Cor.WHITE));

        posicaoNovaPecaXadrez('c', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('c', 8, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('d', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('e', 7, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('e', 8, new Torre(tabuleiro, Cor.BLACK));
        posicaoNovaPecaXadrez('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
