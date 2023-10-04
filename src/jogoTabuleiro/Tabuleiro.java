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
			throw new TabuleiroExcecao("Não Existe a posição no Tabuleiro");
		}
		return pecas [linha][coluna];
	}
	
	public Peca peca (Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new TabuleiroExcecao("Não Existe a posição no Tabuleiro");
		}
		return pecas [posicao.getLinha()][posicao.getColuna()];
	}
	
	public void lugarPeca(Peca peca, Posicao posicao) {
		if (ExistePeca(posicao)) {
			throw new TabuleiroExcecao("Já existe uma peça na posição " + posicao);
			
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean ExistePosicao (Integer linha, Integer coluna) {
		return linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	
	public boolean ExistePosicao (Posicao posicao) {
		return ExistePosicao(posicao.getLinha(), posicao.getColuna());
	} 
	
	public boolean ExistePeca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new TabuleiroExcecao("Não Existe a posição no Tabuleiro");
		}
		return peca(posicao) != null;
	}
}
