package aplicacao;

import xadrez.PartidaXadrez;

public class Aplicacao {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UsuarioInterface.printarTabuleiro(partidaXadrez.getPecas());
	}

}
