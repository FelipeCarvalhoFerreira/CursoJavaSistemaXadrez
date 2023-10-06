package jogoTabuleiro;

public class Tabuleiro {
	
	private Integer linhas;
	private Integer colunas;
	private Peca [][]  pecas;
	
	public Tabuleiro(Integer linha, Integer coluna) {
		if(linha < 1 || coluna < 1) {
			throw new TabuleiroExcecao("Erro ao criar o quadro: deve haver pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linha;
		this.colunas = coluna;
		pecas = new Peca [linha][coluna];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public Peca peca(Integer linha, Integer coluna) {
		if (!ExistePosicao(linha, coluna)) {
			throw new TabuleiroExcecao("Nao Existe a posicao no Tabuleiro");
		}
		return pecas [linha][coluna];
	}
	
	public Peca peca (Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new TabuleiroExcecao("Nao Existe a posicao no Tabuleiro");
		}
		return pecas [posicao.getLinha()][posicao.getColuna()];
	}
	
	public void moverPecaLugar(Peca peca, Posicao posicao) {
		if (ExistePeca(posicao)) {
			throw new TabuleiroExcecao("Ja existe uma peca na posicao " + posicao);
			
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;	
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new TabuleiroExcecao("Nao Existe a posicao no Tabuleiro");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca sobreporPeca = peca(posicao);
		sobreporPeca.posicao = null;
		pecas [posicao.getLinha()] [posicao.getColuna()] = null;
		return sobreporPeca;
	}
	
	private boolean ExistePosicao (Integer linha, Integer coluna) {
		return linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	
	public boolean ExistePosicao (Posicao posicao) {
		return ExistePosicao(posicao.getLinha(), posicao.getColuna());
	} 
	
	public boolean ExistePeca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new TabuleiroExcecao("Nao Existe a posicao no Tabuleiro");
		}
		return peca(posicao) != null;
	}
}
