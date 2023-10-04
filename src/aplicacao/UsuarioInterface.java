package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UsuarioInterface {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String lerPeca = sc.nextLine();
			char coluna = lerPeca.charAt(0);
			Integer linha = Integer.parseInt(lerPeca.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException exececao) {
			throw new InputMismatchException("Erro ao ler a posição do xadrez. Os valores válidos são de a1 a h8.");
		}
	}

	public static void printarTabuleiro(PecaXadrez[][] pecas) {
		for (int l = 0; l < pecas.length; l++) {
			System.out.print((8 - l) + " ");

			for (int c = 0; c < pecas.length; c++) {
				pritarPeca(pecas[l][c]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	private static void pritarPeca(PecaXadrez peca) {
		if (peca == null) {
			System.out.print("-");
		} else {
			if (peca.getCor() == Cor.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

}
