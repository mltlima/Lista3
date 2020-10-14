package com.ufrj.lista3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *  Classe para gerar TreeMaps de acordo com a entrada 
 *  como cidade, estado e total
 * @author Miguel
 */
public class ManipulacaoDeDados {
    
    private String ultimaData;
    
    public void readFile() {
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"));
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
 
                String name = parts[0];
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Reading the file failed.");
        }   
    }
    /**
     * Gera a quantidade total de casos em cada estado
     * @return TreeMap com o total de cada estado
     */
    public TreeMap<String, ArrayList<Integer>> totalCasos(){
        
        
        TreeMap<String, ArrayList<Integer>> dir = new TreeMap<>();
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"));
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String tipo = parts[3];
                int casosConfirmados = Integer.valueOf(parts[4]);
                int mortes = Integer.valueOf(parts[5]);
                ArrayList<Integer> dados = new ArrayList<>();                
                
                if (tipo.equals("state")) {
                    if (dir.containsKey(data)) {
                        int num1 = dir.get(data).get(0) + casosConfirmados;
                        int num2 = dir.get(data).get(1) + mortes;
                        dados.add(num1);
                        dados.add(num2);
                        dir.replace(data, dados);
                    } else {
                        dados.add(casosConfirmados);
                        dados.add(mortes);
                        dir.put(data, dados);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        } 
        
        return dir;
    }
    /**
     * Gera a quantidade total de casos na cidade
     * @param cidadeProcurar cidade a procurar
     * @param estadoProcurar estado da cidade
     * @return TreeMap evolucao de casos por cidade
     */
    public TreeMap<String, ArrayList<Integer>> totalCidade(String cidadeProcurar, String estadoProcurar) {
        
        TreeMap<String, ArrayList<Integer>> dir = new TreeMap<>();
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"), StandardCharsets.UTF_8);
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String estado = parts[1];
                String cidade = parts[2];
                String tipo = parts[3];
                int casosConfirmados = Integer.valueOf(parts[4]);
                int mortes = Integer.valueOf(parts[5]);
                ArrayList<Integer> dados = new ArrayList<>();                
                
                if (tipo.equals("city") && cidade.equals(cidadeProcurar) && estado.equals(estadoProcurar)) {
                    if (dir.containsKey(data)) {
                        int num1 = dir.get(data).get(0) + casosConfirmados;
                        int num2 = dir.get(data).get(1) + mortes;
                        dados.add(num1);
                        dados.add(num2);
                        dir.replace(data, dados);
                    } else {
                        dados.add(casosConfirmados);
                        dados.add(mortes);
                        dir.put(data, dados);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        } 
        
        return dir;
    }
    /**
     * Gera a quantidade total de casos no estado
     * @param estadoProcurar procurar no estado
     * @return TreeMap evolucao dos casos no estado procurado
     */
    public TreeMap<String, ArrayList<Integer>> totalEstado (String estadoProcurar) {
        TreeMap<String, ArrayList<Integer>> dir = new TreeMap<>();
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"), StandardCharsets.UTF_8);
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String estado = parts[1];
                String tipo = parts[3];
                int casosConfirmados = Integer.valueOf(parts[4]);
                int mortes = Integer.valueOf(parts[5]);
                ArrayList<Integer> dados = new ArrayList<>();                
                
                if (tipo.equals("state") && estado.equals(estadoProcurar)) {
                    if (dir.containsKey(data)) {
                        int num1 = dir.get(data).get(0) + casosConfirmados;
                        int num2 = dir.get(data).get(1) + mortes;
                        dados.add(num1);
                        dados.add(num2);
                        dir.replace(data, dados);
                    } else {
                        dados.add(casosConfirmados);
                        dados.add(mortes);
                        dir.put(data, dados);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        } 
        
        return dir;
    }
    /**
     * Organiza em ordem de casos
     * @return TreeMap em ordem pelo numero de casos
     */
    public TreeMap<String, ArrayList<String>> maiorNumeroDeCasos(){
        TreeMap<String, ArrayList<String>> dir = new TreeMap<>();
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"));
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String tipo = parts[3];
                String cidade = parts[2];
                String pop = parts[11];  
                ArrayList<String> dados = new ArrayList<>(); 
                
                if (tipo.equals("city")&& Boolean.valueOf(parts[7])) {   
                        dados.add(data);
                        dados.add(cidade);
                        dir.put(pop, dados);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        } 
        
        return dir;
    }
    /**
     * Organiza em ordem de mortes
     * @return TreeMap em ordem pelo numero de mortes
     */
    public TreeMap<String, ArrayList<String>> maiorNumeroDeMortes(){
        TreeMap<String, ArrayList<String>> dir = new TreeMap<>();
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"));
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String tipo = parts[3];
                String cidade = parts[2];
                String deathRate = parts[12];  
                ArrayList<String> dados = new ArrayList<>();
                
                if (tipo.equals("city")&& Boolean.valueOf(parts[7])) {
                        dados.add(data);
                        dados.add(cidade);
                        dir.put(deathRate, dados);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        } 
        
        return dir;
    }
    /**
     * Organiza em ordem de taxa de crescimento no ultimo mes
     * @return TreeMap em ordem pela taxa de mortalidade
     */
    public TreeMap<Double, String> taxaCasosMes(){
        
        TreeMap<Double, String> dir = new TreeMap<>();
        boolean check = false;
        int count = 0;
        
        try  {
            Scanner fileReader = new Scanner(Paths.get("caso.csv"));
            String header = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                
                String data = parts[0];
                data = "'"+ data + "'";
                String tipo = parts[3];
                String cidade = parts[2];
                String deathRate = parts[12];  
                ArrayList<String> dados = new ArrayList<>();
                String [] dataQuebrada = data.split("-");
                int dia = 0;
                
                if (dataQuebrada[2] == null) {
                    
                }else {
                    dia = Integer.valueOf(dataQuebrada[2].replace("'", ""));
                }
                    
                if (tipo.equals("city")&& Boolean.valueOf(parts[7])) {
                    check = true;
                }
                
                if (check && dia == 1) {
                    ArrayList<Integer> prov = new ArrayList<>();
                    boolean pular = true;
                    do {
                        String[] parts2 = line.split(",");
                        String data2 = parts2[0];
                        //String cidade2 = parts[2];
                        String casos = parts2[4];
                        dataQuebrada = data2.split("-");
                        if (dataQuebrada[2] == null) {
                        }else {
                            dia = Integer.valueOf(dataQuebrada[2].replace("'", ""));
                        }

                        if (dia == 1) {
                            if (casos == null) {
                            }else {
                                int caso1 = Integer.valueOf(casos);
                                prov.add(caso1);
                                ultimaData = data;
                            }

                        }
                        line = fileReader.nextLine();
                        if (pular) {
                            dia = 0;
                        }
                        pular = false;
                    } while (dia != 1);
                    //System.out.println(prov.get(0)+ " " + prov.get(1));
                    count ++;
                    //System.out.println("\n");
                    check = false;
                    double taxa = (prov.get(0) - prov.get(1)) * 1.0 / prov.get(1);
                    if (prov.get(1) == 0) {
                        taxa = prov.get(0);
                    }
                    dir.put(taxa,cidade);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo.");
        }
        return dir;
    }
    
    /**
     * 
     * @return ultima data da analise
     */
    public String getData(){
        return this.ultimaData;
    }
    
}
