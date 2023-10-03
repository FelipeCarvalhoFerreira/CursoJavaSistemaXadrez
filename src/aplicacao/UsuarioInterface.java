package aplicacao;

import xadrez.PecaXadrez;

public class UsuarioInterface {
	
	public static void printarTabuleiro(PecaXadrez[][] pecas) {
		for (int l=0; l<pecas.length; l++) {
			System.out.print((8 - l) + " ");
				
			for(int c=0; c<pecas.length; c++) {
				pritarPeca(pecas[l][c]);
			} 
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}
	
	private static void pritarPeca(PecaXadrez peca) {
		if (peca == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}

}
