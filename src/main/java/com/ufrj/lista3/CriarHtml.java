package com.ufrj.lista3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe para criacao da pagina html
 * @author Miguel
 */
public class CriarHtml {
    
    private TreeMap<String, ArrayList<Integer>> treeMap;
    /**
     * Cria pagina a partir do TreeMap
     * @param treeMap total, estado, cidade
     */
    public CriarHtml (TreeMap<String, ArrayList<Integer>> treeMap) {
        this.treeMap = treeMap;
        criarPagina("resultado.html");
    }
    
    /**
     * Cria a pagina html
     * @param nome nome do arquivo
     */
    public void criarPagina(String nome) {
        
        criarArquivo(nome);
        copiarArquivo(nome);
        StringBuilder str = new StringBuilder();
        //Casos
        this.treeMap.entrySet().forEach(entry->{
            str.append("{x: " + entry.getKey() + ", y: " + entry.getValue().get(0) + ", group: \"Casos\"},\n");  
        });
        //Mortes
        this.treeMap.entrySet().forEach(entry->{
            if (entry.getKey().equals(this.treeMap.lastKey())) {
                str.append("{x: " + entry.getKey() + ", y: " + entry.getValue().get(1) + ", group: \"Mortes\"}\n");
            } else {
                str.append("{x: " + entry.getKey() + ", y: " + entry.getValue().get(1) + ", group: \"Mortes\"},\n");
            }
        });
        
        try {
            Path path = Paths.get(nome);
            Charset charset = StandardCharsets.UTF_8;

            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(":items:", str.toString());
            content = content.replaceAll(":data_inicio:",this.treeMap.firstKey());
            content = content.replaceAll(":data_fim:",this.treeMap.lastKey());
            Files.write(path, content.getBytes(charset));
        }
        catch (IOException e) {
            System.err.println("Erro ao criar arquivo.");
        }
    }
    /**
     * Cria um aqrivo html
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
     * Copia arquivo templete para substituir os dados
     * @param nome nome do arquivo
     */
    public void copiarArquivo(String nome) {
    
        var source = Paths.get("template.html");
        var dest = Paths.get(nome);

        try (var fis = Files.newInputStream(source);
             var fos = Files.newOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo.");
        }
    }
}
