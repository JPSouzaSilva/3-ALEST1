import java.util.LinkedList;


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
         * @param significado2
        */
        public CharNode addChild (char character, boolean isfinal, String significado) {
            CharNode aux = findChildChar(character);
            if (aux != null) {
                return null;
            }
            
            CharNode n = new CharNode(character, isfinal);
            if (n.isFinal == true) {
                n.significado = significado;
            }

            n.father = this;
            children.add(n);
            totalNodes++;
            return n;
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
        public CharNode findChildChar (char character) {
            for (CharNode aux : children) {
                if (aux.character == character) {
                    return aux;
                }
            }
            return null;
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
    public void addWord(String word, String significado) {
        CharNode aux = root;
        for (int i = 0; i < word.length(); i++) {
            if (aux.getChild(i).character == word.charAt(i)) {
                aux = aux.getChild(i);
            } else {
                if (aux.getChild(i).character == word.charAt(word.length()-1)) {
                    aux.addChild(word.charAt(i), true, significado);
                } else {
                    aux.addChild(word.charAt(i), false, null);
                }
            }
        }
        totalWords++;        
    }

    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        CharNode aux = root;
        for (int i = 0; i < aux.getNumberOfChildren(); i++) {
            if (aux.getChild(i).character == word.charAt(i)) {
                aux = aux.getChild(i);
            }
            return null;
        }
        if (aux.isFinal == false) {
            return null;
        }
        return aux;
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public LinkedList<String> searchAll(String prefix) {
        LinkedList<String> lista = new LinkedList<>();
        caminhamento(root, lista);
        return lista;
        // Não ta pronto
        
    }
    
    private void caminhamento(CharNode n, LinkedList<String> lista) {
        if (n != null) {
            if (n.isFinal == true) {
                addWord(n.getWord(n), n.significado);
            }
            for (int i = 0; i < n.getNumberOfChildren(); i++) {
                caminhamento(n.getChild(i), lista);
            }
        }
    }

}
