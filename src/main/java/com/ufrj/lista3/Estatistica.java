package com.ufrj.lista3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *  Classe para geracao de arquivos .tsv
 * @author Miguel
 */
public class Estatistica {
    
    private TreeMap<Double, ArrayList<String>> treeMap; 
    private TreeMap<String, ArrayList<String>> treeMapOriginal;
    private TreeMap<Double, String> treeMapThree;
    /**
     * Primeiro constructor para quantidade de casos e mortalidade
     * @param treeMap especifico para numero de casos e numero de mortos
     */
    public Estatistica (TreeMap<String, ArrayList<String>> treeMap) {
        this.treeMapOriginal = treeMap;
        this.treeMap = new TreeMap<>(); 
        mudarToDouble();
    }
    /**
     * Segundo constructor para taxa de mortalidade
     * @param treeMapThree
     * @param date do ultimo mes de medicao
     */
    public Estatistica (TreeMap<Double, String> treeMapThree, String date) {
        this.treeMapThree = treeMapThree;
        maior("maior_taxa_crestimento.tsv",date);
    }

    /**
     * Muda os valoeres do TreeMap para double
     */
    public void mudarToDouble() {
        //this.treeMapOriginal.remove(this.treeMapOriginal.firstKey());
        int counter = 0;
        
        for (String key : this.treeMapOriginal.keySet() ) {
            /*if (counter == 0) {
                counter ++;
                return;
            }*/
            if (key == null || !key.matches("-?\\d+(\\.\\d*)?")) {
                //System.out.println("Invalido");
            }else {
                Double newKey = Double.parseDouble(key);
                this.treeMap.put(newKey, this.treeMapOriginal.get(key));
            }
        }        
    }
    /**
     * Cria um arquivo tsv
     * @param nome nome do arquivo
     */
    public void criarArquivo(String nome) {
        File arq = new File(nome);
        try {
            arq.createNewFile();
        }
        catch (IOException e) {
            System.err.println("Erro ao criar arquivo.");
        }
    }
   /**
    * Escreve no arquivo tsv gerado
    * @param nome nome do arquivo
    * @param str StringBuilder para com linhas para serem inseridas no arquivo
    */  
   public void write(String nome,StringBuilder str) {
        criarArquivo(nome);
        
        try {
            FileWriter file = new FileWriter(nome);
            file.write(str.toString());
            file.close();
            
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo.");
        }
    }
    /**
     * ordena pelo menor item
     * @param nome nome do arquivo
     */
    public void menor (String nome) {
        StringBuilder str = new StringBuilder();
        str.append("cidade,data_ultima_medicao,valor\n");
        int counter = 0;
        
        //Casos
        for (Map.Entry<Double, ArrayList<String>> entry : this.treeMap.entrySet()) {
            if (counter == 10) {
                break;
            }
            str.append(entry.getValue().get(1) + "," + entry.getValue().get(0) + "," + entry.getKey() + "\n"); 
            counter ++;
        }
        write(nome,str);
    }
    /**
     * Ordena pelo maior item
     * @param nome nome do arquivo
     */
    public void maior (String nome) {
        StringBuilder str = new StringBuilder();
        str.append("cidade,data_ultima_medicao,valor\n");
        int counter = 0;
        
        //Casos
        for (Double key : this.treeMap.descendingKeySet() ) {
            if (counter == 10) {
                break;
            }
            str.append(this.treeMap.get(key).get(1) + "," + this.treeMap.get(key).get(0) + "," + key + "\n"); 
            counter ++;
        }
        write(nome,str);
    }
    /**
     * metodo para caso especifico de taxa de mortalidade
     * @param nome do arquivo
     * @param date do ultimo mes de medida
     */
    public void maior (String nome, String date ){
        StringBuilder str = new StringBuilder();
        str.append("cidade,data_ultima_medicao,valor\n");
        int counter = 0;
        
        //Casos
        for (Double key : this.treeMapThree.descendingKeySet() ) {
            if (counter == 10) {
                break;
            }
            str.append(this.treeMapThree.get(key) + "," + date + "," + key + "\n"); 
            counter ++;
        }
        write(nome,str);
    }
    
}