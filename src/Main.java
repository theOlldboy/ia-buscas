import espacoDeEstados.*;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.*;

import java.util.List;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		//char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
//		char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		char[] cfgFim = {'1','2','3','4','5','6','7','8',' '};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory

		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
			
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado(cfgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);
						
/*		BuscaInformada busca = new AStar();
		busca.setInicio(puzzleInicial);
		busca.setObjetivo(puzzleFinal);
		busca.buscar();
		for(Estado e : busca.getCaminhoSolucao()) {
			System.out.println(e);
		}
*/
//		BuscaCega buscaC = new BuscaCega() {
//                    @Override
//                    public void buscar() {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//                };
//		buscaC.setInicio(puzzleInicial);
//		buscaC.setObjetivo(puzzleFinal);
//		buscaC.buscar();
//		for(Estado e : buscaC.getCaminhoSolucao()) {
//			System.out.println(e);
//		}

//                BuscaEmLargura buscaL = new BuscaEmLargura(puzzleInicial, puzzleFinal);
//		buscaL.setInicio(puzzleInicial);
//		buscaL.setObjetivo(puzzleFinal);
//		buscaL.buscar();
//		for(Estado e : buscaL.getCaminhoSolucao()) {
//			System.out.println(e);
//		}

//                BuscaEmProfundidade buscaP = new BuscaEmProfundidade(puzzleInicial, puzzleFinal);
//		buscaP.setInicio(puzzleInicial);
//		buscaP.setObjetivo(puzzleFinal);
//		buscaP.buscar();
//		for(Estado e : buscaP.getCaminhoSolucao()) {
//			System.out.println(e);
//		}

			BuscaEmProfundidadeLimitada buscaPL = new BuscaEmProfundidadeLimitada(puzzleInicial, puzzleFinal, 0);

		int upper_limit = 20;


		buscaPL.setInicio(puzzleInicial);
		buscaPL.setObjetivo(puzzleFinal);
		buscaPL.setLimite(0);
		List<Estado<?>> check = null;

		while(check == null){
				try{
					buscaPL.buscar();
					check = buscaPL.getCaminhoSolucao();
				}catch (Exception e){

				}
				buscaPL.setLimite(buscaPL.getLimite() + 1);

		}
		for(Estado e : buscaPL.getCaminhoSolucao()) {
			System.out.println(e);
		}


		BuscaInformada buscaBme = new BestFirst();

		buscaBme.setInicio(puzzleInicial);
		buscaBme.setObjetivo(puzzleFinal);
		buscaBme.buscar();

		for(Estado e : buscaBme.getCaminhoSolucao()){
			System.out.println(e);
		}

		/*		BuscaInformada buscaAS = new AStar();
		buscaAS.setInicio(puzzleInicial);
		buscaAs.setObjetivo(puzzleFinal);
		buscaAs.buscar();
		for(Estado e : busca.getCaminhoSolucao()) {
			System.out.println(e);
		}
*/




		System.exit(0);
	}

}
