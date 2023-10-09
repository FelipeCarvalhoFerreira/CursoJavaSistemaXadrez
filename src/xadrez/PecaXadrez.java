package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	
	private Cor cor;
	private Integer contagemMovimento;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public Integer getContagemMovimento() {
		return contagemMovimento;
	}
	
	public void aumentarContagemMovimentos() {
		contagemMovimento++;
	}
	
	public void diminuirContagemMovimentos() {
		contagemMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.dePosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez pecaOponente = (PecaXadrez)getTabuleiro().peca(posicao);
		return pecaOponente != null && pecaOponente.getCor() != cor;
	}
}
