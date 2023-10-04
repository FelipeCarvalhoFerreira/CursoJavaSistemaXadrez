package xadrez;

import jogoTabuleiro.Posicao;

public class PosicaoXadrez {
	
	private char coluna;
	private Integer linha;
	
	public PosicaoXadrez(char coluna, Integer linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezExcecao("Erro ao instanciar a Posição do Xadrez. Os valores válidos são de a1 a h8");
		}
		this.linha = linha;
		this.coluna = coluna;
	}

	public Integer getLinha() {
		return linha;
	}

	public char getColuna() {
		return coluna;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez dePosicao(Posicao posicao){
		return new PosicaoXadrez ((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		return "" + linha + coluna;
	}
	
	
}
