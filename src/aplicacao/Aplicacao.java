package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExcecao;

public class Aplicacao {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> listaPecasCapturadas = new ArrayList<>();

		while (!partidaXadrez.getChequeMate()) {
			try {
				UsuarioInterface.limparTela();
				UsuarioInterface.printarPartida(partidaXadrez, listaPecasCapturadas);
				System.out.println();
				System.out.print("Posicao Origem: ");
				PosicaoXadrez posicaoOrigem = UsuarioInterface.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(posicaoOrigem);
				UsuarioInterface.limparTela();
				UsuarioInterface.printarTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				System.out.print("Posicao Destino: ");
				PosicaoXadrez posicaoDestino = UsuarioInterface.lerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(posicaoOrigem, posicaoDestino);
				
				if (pecaCapturada != null) {
					listaPecasCapturadas.add(pecaCapturada);
				}
				
				if (partidaXadrez.getPecaPromovida() != null) {
					System.out.print("Digite peca para promocao (Q(Rainha)/B/C/T): ");
					String tipo = sc.nextLine();
					partidaXadrez.substituirPecaPromovida(tipo);
				}
			}
			catch (XadrezExcecao execao) {
				System.out.println(execao.getMessage());
				sc.nextLine();
			}
			
			catch (InputMismatchException execao) {
				System.out.println(execao.getMessage());
				sc.nextLine();
			}
		}
		
		UsuarioInterface.limparTela();
		UsuarioInterface.printarPartida(partidaXadrez, listaPecasCapturadas);
	}

}
