package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	
	private Cor cor;
	private Integer contagemMovimentos;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public Integer getContagemMovimentos() {
		return contagemMovimentos;
	}
	
	public void aumentarContagemMovimentos() {
		contagemMovimentos++;
	}
	
	public void diminuirContagemMovimentos() {
		contagemMovimentos--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.dePosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez pecaOponente = (PecaXadrez)getTabuleiro().peca(posicao);
		return pecaOponente != null && pecaOponente.getCor() != cor;
	}
}
