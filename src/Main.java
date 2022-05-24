import espacoDeEstados.*;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] cfgIni = {'1',' ','6','4','5','8','7','3','2'};
        char[] cfgIni2 = {'8','4','6','5','7','2','1',' ','3'};

        char[] cgFim = {'1','6','4','5',' ','8','7','3','2'};
        char[] cgFim2 = {' ', '1', '2', '3', '4', '5', '6', '7', '8'};

        Puzzle8 puzzleInicial = new Puzzle8();
        puzzleInicial.setEstado(cfgIni);
        puzzleInicial.setCusto(0);
        puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );

        Puzzle8 puzzleFinal = new Puzzle8();
        puzzleFinal.setEstado(cgFim);
        puzzleFinal.setCusto(0);
        puzzleFinal.setAvaliacao(0);



        System.out.println("Selecione o metodo de busca desejado:");

        System.out.println("1 - Busca em profundidade limitada manual");
        System.out.println("2 - Busca profundidade limitada interativa");
        System.out.println("3 - Busca AStar");
        System.out.println("4 - Busca BestFirst");

        Integer selecionaBusca = scanner.nextInt();
        scanner.nextLine();

        switch(selecionaBusca) {
            case 1:
                System.out.println("Estabeleca um limite para a primeira execucao:");
                BuscaEmProfundidadeLimitada buscaL = new BuscaEmProfundidadeLimitada();

                buscaL.setInicio(puzzleInicial);
                buscaL.setObjetivo(puzzleFinal);
                buscaL.setLimite(scanner.nextInt());
                buscaL.buscar();
                scanner.nextLine();

                mostrarCaminho(buscaL.getCaminhoSolucao());
                System.out.println("Fim da primeira execucao.");

                System.out.println("Estabeleca um limite para a segunda execucao:");

                puzzleInicial.setEstado(cfgIni2);
                puzzleFinal.setEstado(cgFim2);

                buscaL.setLimite(scanner.nextInt());
                buscaL.buscar();
                scanner.nextLine();

                mostrarCaminho(buscaL.getCaminhoSolucao());
                System.out.println("Fim da segunda execucao.");

                break;
            case 2:
                BuscaEmProfundidadeLimitada buscaInterativa = new BuscaEmProfundidadeLimitada();

                buscaInterativa.setInicio(puzzleInicial);
                buscaInterativa.setObjetivo(puzzleFinal);

                buscaInterativa.setLimite(0);

                List<Estado<?>> caminhoSolucao = null;

                while(caminhoSolucao == null) {
                    try {
                        buscaInterativa.buscar();
                        caminhoSolucao = buscaInterativa.getCaminhoSolucao();
                    } catch (Exception e) {
                        System.out.println("Buscando limite... " + buscaInterativa.getLimite());
                    }
                    buscaInterativa.setLimite(buscaInterativa.getLimite() + 1);
                }

                mostrarCaminho(buscaInterativa.getCaminhoSolucao());
                System.out.println("Fim da primeira execucao");

                puzzleInicial.setEstado(cfgIni2);
                puzzleFinal.setEstado(cgFim2);
                buscaInterativa.setLimite(0);
                caminhoSolucao = null;

                while(caminhoSolucao == null) {
                    try {
                        buscaInterativa.buscar();
                        caminhoSolucao = buscaInterativa.getCaminhoSolucao();
                    } catch (Exception e) {
                        System.out.println("Buscando limite... " + buscaInterativa.getLimite());
                    }
                    buscaInterativa.setLimite(buscaInterativa.getLimite() + 1);
                }

                mostrarCaminho(buscaInterativa.getCaminhoSolucao());
                System.out.println("Fim da segunda execucao");

                break;

            case 3:
                AStar buscaAstar = new AStar();
                buscaAstar.setInicio(puzzleInicial);
                buscaAstar.setObjetivo(puzzleFinal);

                buscaAstar.buscar();

                mostrarCaminho(buscaAstar.getCaminhoSolucao());
                System.out.println("Fim da primeira execucao.");

                puzzleInicial.setEstado(cfgIni2);
                puzzleFinal.setEstado(cgFim2);

                buscaAstar.buscar();

                mostrarCaminho(buscaAstar.getCaminhoSolucao());
                System.out.println("Fim da segunda execucao.");

                break;

            case 4:
                BestFirst bF = new BestFirst();
                bF.setInicio(puzzleInicial);
                bF.setObjetivo(puzzleFinal);

                bF.buscar();

                mostrarCaminho(bF.getCaminhoSolucao());
                System.out.println("Fim da primeira execucao.");


                puzzleInicial.setEstado(cfgIni2);
                puzzleFinal.setEstado(cgFim2);

                bF.buscar();

                mostrarCaminho(bF.getCaminhoSolucao());
                System.out.println("Fim da segunda execucao.");

        }
        System.exit(0);
    }

    public static void mostrarCaminho(List<Estado<?>> caminhoDaSolucao) {
        for(Estado e : caminhoDaSolucao) {
            System.out.println(e);
            System.out.println(" ");
        }
    }
}