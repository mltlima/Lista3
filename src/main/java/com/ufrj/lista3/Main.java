package com.ufrj.lista3;

import java.util.Scanner;

/**
 *  Programa para analisar os casos de coronavirus pelos
 *  estados e cidades brasileiras
 * @author Miguel
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Digite um comando: ");
            String comando = scanner.nextLine();
            
            if (comando.equals("")) {
                ManipulacaoDeDados nada = new ManipulacaoDeDados();
                CriarHtml tes = new CriarHtml(nada.totalCasos());
                getTsv(nada);
                break;
            } else if (comando.contains("//")) {
                String[] str = comando.split("//");
                try {
                    String cidade = str[0];
                    String estado = str[1];
                    ManipulacaoDeDados cidEst = new ManipulacaoDeDados();
                    CriarHtml tes = new CriarHtml(cidEst.totalCidade(cidade, estado));
                    getTsv(cidEst);
                    break;
                }catch (Exception e){
                    System.out.println("Cidade ou estado nao existem");
                    break;
                }
                
            } else {
                try {
                    ManipulacaoDeDados estado = new ManipulacaoDeDados();
                    CriarHtml tes = new CriarHtml(estado.totalEstado(comando));
                    getTsv(estado);
                    break;
                }catch (Exception e){
                    System.out.println("Cidade ou estado nao existem");
                    break;
                }
            }
        }

    }
    /**
     * Cria os arquivos .tsv de analise dos dados
     * @param dados HashTree com os dados para analise
     */
    public static void getTsv(ManipulacaoDeDados dados) {
        Estatistica casos = new Estatistica(dados.maiorNumeroDeCasos());
        casos.maior("maior_casos_100k.tsv");
        casos.menor("menor_casos_100k.tsv");
        Estatistica morte = new Estatistica(dados.maiorNumeroDeMortes());
        morte.maior("maior_mortalidade.tsv");
        morte.menor("menor_mortalidade.tsv");
        Estatistica taxa = new Estatistica(dados.taxaCasosMes(),dados.getData());
    }
}
