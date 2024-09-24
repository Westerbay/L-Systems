package turtle.space;


import java.util.LinkedList;


/**
 * Classe représentant une Pile
 * @author leriche213
 * @author peltier212
 */
public class Pile<E> extends LinkedList<E>{

    /**
     * Constructeur
     */
    public Pile(){
        super();
    }

    /**
     * Fonction qui ajoute un element à la Pile
     * Preconditions : La pile doit être correctement initialisé
     * Preconditions : valeur >= 0
     * @param valeur : Position que l'on veut ajouter à la Pile
     */
    public void empile(E valeur){
        add(valeur);
    }

    /**
     * Fonction qui dépile
     * Preconditions : la pile ne doit pas être vide
     * @return le dernier élément de la Pile et le supprime
     */
    public E depile(){
        return removeLast();
    }

    /**
     * Fonction qui regarde si la Pile est vide
     * @return vrai si la liste est vide et faux sinon
     */
    public boolean estVide(){
        return isEmpty();
    }

}

