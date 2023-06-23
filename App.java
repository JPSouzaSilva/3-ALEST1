import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;


public class App {
    public static void main(String[] args) {
        LinkedList<Palavra> lista = new LinkedList<>();
        String aux[];
                
        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {// Charset.defaultCharset())
            String line = reader.readLine();
            while (line != null) {
                aux = line.split(";");
                if(lista.size()==0) {
                    aux[0] = aux[0].substring(1);
                    System.out.println(aux[0]);
                }
                Palavra p = new Palavra(aux[0],aux[1]);
                lista.add(p);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }  
        System.out.println("Lista de palavras e seus significados" + lista);
        
    }
 
}
