import java.util.LinkedList;
import java.util.List;

public class WordTree {
    
    // Classe interna
    private class CharNode {
        private char character;
	    private String significado;
        private boolean isFinal;
        private CharNode father;
        private LinkedList<CharNode> children;

        public CharNode(char character) {
            this.character = character;
            father = null;
            children = new LinkedList<>();
            isFinal = false;
            significado = null;
        }
        
        public CharNode(char character, boolean isFinal) {
            this.character = character;
            father = null;
            children = new LinkedList<>();
            this.isFinal = isFinal;
            significado = "";
        }

        /**
        * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
        * @param character - caracter a ser adicionado
        * @param isfinal - se é final da palavra ou não
        */
        public CharNode addChild (char character, boolean isfinal) {
            CharNode nodeCaracterRep = findChildChar(character, root);
            if (nodeCaracterRep != null) {
                return null;
            }
            
        }
        
        public int getNumberOfChildren () {
            return children.size();
        }
        
        public CharNode getChild (int index) {
            if ((index < 0) || (index >= children.size())) {
                throw new IndexOutOfBoundsException();
            }
            return children.get(index);
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
         */
        private String getWord(CharNode n) {
            String wordReverse = "";
            if (n.isFinal != true) {
                return null;
            }
            getWord(n, wordReverse);
            String word = "";
            for (int i = wordReverse.length()-1; i >= 0; i++) {
                word += wordReverse.charAt(i);
            }
            return word;
        }

        private void getWord(CharNode n, String word) {
            if (n != null) {
                word += n.character;
                getWord(n.father, word);
            }
        }
        
        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character, CharNode n) {
            if (n == null) {
                return null;
            }

            if (n.character == character) {
                return n;
            }

            CharNode aux = null;
            for (int i = 0; i < n.getNumberOfChildren(); i++) {
                aux = findChildChar(character, n.getChild(i));
                if (aux != null) {
                    return aux;
                }
            }
            return aux;
        }
    }
    
    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    // Construtor
    public WordTree() {
        root = null;
        totalNodes = 0;
        totalWords = 0;
    }

    // Metodos
    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }
    
    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */
    public void addWord(String word) {
        ...
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        ...
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<String> searchAll(String prefix) {
        ...
    }   

}
