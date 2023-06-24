import java.util.LinkedList;


public class WordTree {

    // Classe interna
    public class CharNode {
        public char character;
        public String significado;
        public boolean isFinal;
        public CharNode father;
        public LinkedList<CharNode> children;
        char aux;

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
         * Adiciona um filho (caracter) no nó. Não pode aceitar caracteres repetidos.
         *
         * @param character   - caracter a ser adicionado
         * @param isFinal     - se é final da palavra ou não
         * @param significado - significado da palavra (opcional)
         * @return o novo nó adicionado
         */
        public CharNode addChild(char character, boolean isFinal, String significado) {
            CharNode aux = findChildChar(character);
            if (aux != null) {
                return null;
            }

            CharNode n = new CharNode(character, isFinal);
            if (n.isFinal) {
                n.significado = significado;
            }

            n.father = this;
            children.add(n);
            totalNodes++;
            return n;
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public CharNode getChild(int index) {
            if (index < 0 || index >= children.size()) {
                return null;
            }
            return children.get(index);
        }

        public String getWord(CharNode n) {
            StringBuilder wordReverse = new StringBuilder();
            if (!n.isFinal) {
                return null;
            }
            getWord(n, wordReverse);
            return wordReverse.reverse().toString();
        }

        private void getWord(CharNode n, StringBuilder word) {
            if (n != null && n != root) {
                word.append(n.character);
                getWord(n.father, word);
            }
        }

        

        /**
         * Encontra e retorna o nó que tem determinado caractere.
         *
         * @param character - caractere a ser encontrado.
         * @return o nó encontrado ou null caso não seja encontrado.
         */
        public CharNode findChildChar(char character) {
            for (CharNode aux : children) {
                if (aux.character == character) {
                    return aux;
                }
            }
            return null;
        }
    }

    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    public WordTree() {
        root = null;
        totalNodes = 0;
        totalWords = 0;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public void addRoot() {
        if (root == null) {
            CharNode n = new CharNode('*', false);
            root = n;
        }
    }


    public CharNode addWord(String word, String significado) {
        CharNode aux = root;
        for (int i = 0; i < word.length(); i++) {
            CharNode child = aux.findChildChar(word.charAt(i));
            if (child == null) {
                boolean isFinal = (i == word.length() - 1);
                aux = aux.addChild(word.charAt(i), isFinal, (isFinal ? significado : null));
            } else {
                aux = child;
            }
        }
        totalWords++;
        return aux;
    }
    
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra.
     * @param word - palavra a ser procurada
     * @return o nó final encontrado ou null caso a palavra não seja encontrada
     */
    public CharNode findCharNodeForWord(String word) {
        CharNode aux = root;
        for (int i = 0; i < word.length(); i++) {
            if (aux != null) {
                CharNode n = aux.findChildChar(word.charAt(i));
                if (n == null) {
                    return null;
                }
                aux = n;
            }
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
        CharNode startNode = findCharNodeForWord(prefix);
        if(startNode != null){
            caminhamento(startNode, lista);
        }
        return lista;
    }

    private void caminhamento(CharNode n, LinkedList<String> lista) {
         if (n != null) {
            // Visita a raiz
            if ((n.isFinal)) {
                Palavra p = new Palavra(n.getWord(n), n.significado);
                lista.add(p.toString());
            }
            // Visita os filhos
            for (CharNode child : n.children) {
                caminhamento(child, lista);
            }
         }
    }


}
