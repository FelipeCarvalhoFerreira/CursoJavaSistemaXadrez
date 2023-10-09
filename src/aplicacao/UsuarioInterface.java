package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
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

	public static final String ANSI_BLACK_CORFUNDO = "\u001B[40m";
	public static final String ANSI_RED_CORFUNDO = "\u001B[41m";
	public static final String ANSI_GREEN_CORFUNDO = "\u001B[42m";
	public static final String ANSI_YELLOW_CORFUNDO = "\u001B[43m";
	public static final String ANSI_BLUE_CORFUNDO = "\u001B[44m";
	public static final String ANSI_PURPLE_CORFUNDO = "\u001B[45m";
	public static final String ANSI_CYAN_CORFUNDO = "\u001B[46m";
	public static final String ANSI_WHITE_CORFUNDO = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String lerPeca = sc.nextLine();
			char coluna = lerPeca.charAt(0);
			Integer linha = Integer.parseInt(lerPeca.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException exececao) {
			throw new InputMismatchException("Erro ao ler a posicaoo do xadrez. Os valores validos sao de a1 a h8.");
		}
	}
	
	public static void printarPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> listaPecasCapturadas) {
		printarTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		imprimirPecasCapturadas(listaPecasCapturadas);
		System.out.println();
		System.out.println("Quantidade de Jogadas: " + partidaXadrez.getVezJogador());
		
		if(!partidaXadrez.getChequeMate()) {
			System.out.println("Esperando Jogador: " + partidaXadrez.getAtualJogador());
		
			if(partidaXadrez.getCheque()) {
				System.out.println("Seu rei esta em CHEQUE");
			}
		}
		else {
			System.out.println("CHEQUE MATE");
			System.out.println("Vencedor: " + partidaXadrez.getAtualJogador());
		}
	}

	public static void printarTabuleiro(PecaXadrez[][] pecas) {
		for (int l = 0; l < pecas.length; l++) {
			System.out.print((8 - l) + " ");
			for (int c = 0; c < pecas.length; c++) {
				pritarPeca(pecas[l][c], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printarTabuleiro(PecaXadrez [][] pecas, boolean[][] movimentosPossiveis) {

		for (int l = 0; l < pecas.length; l++) {
			System.out.print((8 - l) + " ");
			for (int c = 0; c < pecas.length; c++) {
				pritarPeca(pecas[l][c], movimentosPossiveis[l][c]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void pritarPeca(PecaXadrez peca, boolean corFundo) {
		if (corFundo) {	
			System.out.print(ANSI_BLUE_CORFUNDO);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void imprimirPecasCapturadas(List<PecaXadrez> listaPecaXadrezCapturadas) {
		List<PecaXadrez> listaPecaBrancas = listaPecaXadrezCapturadas.stream().filter(lista -> lista.getCor() == Cor.WHITE).collect(Collectors.toList());
		List<PecaXadrez> listaPecaPretas = listaPecaXadrezCapturadas.stream().filter(lista -> lista.getCor() == Cor.BLACK).collect(Collectors.toList());
		System.out.println("Pecas Capturadas:");
		System.out.print("Pecas das Cores Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(listaPecaBrancas.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Pecas das Cores Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(listaPecaPretas.toArray()));
		System.out.print(ANSI_RESET);
	}

}
