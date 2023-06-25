import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void buildTree(WordTree tree, FileInputStream stream) throws IOException {
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
        String opcao = "";
        LinkedList<String> palavras = new LinkedList<>();
        LinkedList<String> significados = new LinkedList<>();
        Scanner input = new Scanner(System.in);

        FileInputStream stream = new FileInputStream("dicionario.csv");
        WordTree tree = new WordTree();
        tree.addRoot();
        buildTree(tree, stream);
        do{
            System.out.println("--MENU--\nPara encerrar o programa, digite: 0\nPara realizar a busca por um prefixo de palavras digite: 1");
            opcao = input.nextLine();

            if(opcao.equals("1")){
                System.out.println("Digite o prefixo para a busca:");
                String prefix = input.nextLine();

                LinkedList<String> strings = tree.searchAll(prefix);

                if(strings.isEmpty()){
                    System.out.println("Nenhuma palavra encontrada");
                } else {

                    System.out.println("Lista das palavras encontradas com o prefixo: ");

                    for (String s : strings) {
                        String[] split = s.split(":");
                        palavras.add(split[0]);
                        significados.add(split[1]);
                    }
                    String s = "";
                    int cont = 1;
                    for (String p : palavras) {
                        s += "Palavra " + cont + ":" + p + "\n";
                        cont++;
                    }
                    System.out.println(s);
                    System.out.println("Qual palavra da lista você deseja o significado?");
                    int palavraIndex = input.nextInt();
                    input.nextLine();
                    System.out.println("O significado da palavra: " + palavras.get(palavraIndex - 1) + " é:" + significados.get(palavraIndex - 1));
                    s = "";
                    cont = 1;
                    palavras.clear();
                    significados.clear();
                }
            }
        }while (!opcao.equals("0"));
    }

}