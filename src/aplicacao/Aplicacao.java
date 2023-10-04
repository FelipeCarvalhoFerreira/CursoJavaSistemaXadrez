package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Aplicacao {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();

		while (true) {
			UsuarioInterface.printarTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Posicao Origem: ");
			PosicaoXadrez posicaoOrigem = UsuarioInterface.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Posicao Destino: ");
			PosicaoXadrez posicaoDestino = UsuarioInterface.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(posicaoOrigem, posicaoDestino);
		}
	}

}
