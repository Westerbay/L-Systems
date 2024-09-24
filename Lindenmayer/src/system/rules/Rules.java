package system.rules;


/**
 * Interface permettant la représentation de règles de L-System
 * @author dubuiss212
 */
public interface Rules {

    /**
     * Méthode renvoyant la mutation d'un caractère en String
     * @param index position dans ch
     * @param ch la String actuelle
     * @return la nouvelle mutation
     */
    String get(int index, String ch);

    /**
     * Fonction permettant de savoir si un caractère à une règle affectée
     * @param c le caractère à tester
     * @return vrai si le caractère est dans les règles, faux sinon !
     */
    boolean containsKey(char c);
    
    /**
     * Fonction renvoyant une représentation graphique des règles
     * @return une représentation graphique des règles
     */
    String toString();

}
