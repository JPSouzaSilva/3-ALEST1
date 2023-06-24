import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void fazLista(WordTree tree, FileInputStream stream) throws IOException {
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while (linha != null) {
            String[] dados = linha.split(";");
            String palavra = dados[0];
            String significado = dados[1];
            tree.addWord(palavra, significado);
            linha = br.readLine();
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        FileInputStream stream = new FileInputStream("dicionario.csv");
        WordTree tree = new WordTree();
        tree.addRoot();
        fazLista(tree, stream);
        System.out.println(tree.searchAll("J"));


    }
}