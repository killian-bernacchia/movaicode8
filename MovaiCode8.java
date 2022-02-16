import java.util.*;

public class MovaiCode8{

    public static <E> E[] mange_une_crepe(E[] pile_de_crepes) throws Exception {


        /***=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=***/
        // les classes de la fonction

        /***
         *  Nos fameuse Crepes Exceptionnelles
         */
        class CrepeExeptionnelle extends Exception {
            public final E crepe_ratee = null; //c'était pour optimiser et faire un truc propre/sympas mais comme c'est une inner class dans une fonction static avec un type paramétré bah c'était pas possible, mais j'ai laissé parce que c'est moche et on aime quand c'est moche ici

            /***
             *  La fameuse crêpe d'Exception qu'on pourra récupéré après avoir throw une CrepeExceptionnelle
             */
            public final E crepe;

            public CrepeExeptionnelle() {//le constructeur par défaut -> dans le cas d'un plantage avec une crepe raté
                super();
                crepe = crepe_ratee;
            }

            public CrepeExeptionnelle(E crepeExceptionnelle) {//le constructeur pour les crêpes exceptionnelle
                super();
                this.crepe = crepeExceptionnelle;//le this juste pour la lisibilité, trop de crêpe tue les crêpes (non c'est faux on en mange même quand ya plus la place.. je vous ai vu 👀)
            }
        }

        /***
         *  notre fameux itérateur d'Exception
         */
        class Iterateur {
            final E[] pile_de_crepe;//nos fameuses crêpes
            final int nombre_de_crepe;//pour se rappeler du nombre de crêpe au début au cas où un gourmant passerait par là..
            int index = -1;//notre avancé dans les crêpes de la pile

            public Iterateur(E[] pile_de_crepe) {
                this.pile_de_crepe = pile_de_crepe.clone();//on copie la pile de crêpe dans l'itérateur au cas où il se permettrait un petit croc le gourmand..
                this.nombre_de_crepe = pile_de_crepe.length;//comme dit plus, on note dans un coin de la tête le nombre de crêpe au cas où une souris passerai par là
            }

            /***
             *  nous permet de savoir si la crêpe actuelle a une crêpe au dessus
             */
            public void aUnSuivant() throws CrepeExeptionnelle {
                if (index >= nombre_de_crepe - 1)
                    throw new CrepeExeptionnelle();//ça throw une crepe raté si jamais il y en a plus, sinon tout va bien
            }

            /***
             *  ça nous donne la crêpe suivante et on avance donc d'une crepe dans la pile
             */
            public void suivant() throws CrepeExeptionnelle {
                aUnSuivant();//on vérifie bien qu'il y a une suivante avant de la manger
                throw new CrepeExeptionnelle(pile_de_crepe[++index]);//this ça n'a pas throw la ligne d'avant alors on peut prendre la crêpe d'Exception suivante
            }
        }


        /***=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=***/
        // le corp de la fonction

        Stack<E> stack = null;
        try {//un try parce qu'ici on fait avec des CrepesExceptionnelles
            stack = new Stack<>();
            E crepe = null;//la crepe précédente que l'on ajoutera à notre pile (null car il y a pas de précédent pour l'instant, ça permettra de ne pas ajouter la dernière crêpe qui sera mangé)
            Iterateur iterateur = new Iterateur(pile_de_crepes); //on créer notre itérateur

            //ici ça devient sérieux
            while (true) {//ici on n'en sort qu'Exceptionnellement (via le catch de l'Exception qui sera retourné par aUnSuivant();
                iterateur.aUnSuivant();//laisse passé s'il y a une crêpe suivante sinon on sort
                try {
                    iterateur.suivant();
                }//va chercher la crêpe suivante
                catch (CrepeExeptionnelle crepeExeptionnelle) {//récupère la crêpe suivante
                    if (crepeExeptionnelle.crepe == null)//vérifie qu'il ne s'agit pas d'une mauvaise crêpe
                        throw new NoSuchElementException();//s'il s'agit d'une mauvaise crêpe c'est qu'il y en a plus, donc on throw l'erreur qui correspond
                    if (crepe != null)//si ya une crepe précédente
                        stack.add(crepe);//on l'ajoute alors à la pile
                    crepe = crepeExeptionnelle.crepe;//la crepe précédente passe à la suivante
                }
            }
        } catch (CrepeExeptionnelle crepeExeptionnelle) {//ici c'est pour sortir du while(true); grâce à l'Exception aUnSuivant();

        }

        pile_de_crepes = stack.toArray(pile_de_crepes);//on met notre nouvelle pile de crepe à la place de l'ancienne (il s'agit du même tableau, de la même façon qu'on garderait la pile dans la même assiette après en avoir mangé une)
        return pile_de_crepes;//on renvoie la pile même si ce n'est pas nécessaire car le tableau est directement modifié (ça plait toujours à certains..)
    }

    public static void main(String[] args) throws Exception {
        //ici j'ai mis des Integer mais par définition vous pouvez mettre n'importe quel type de crêpes, des Strings, des Boolean, des CrepesALaFraise, CrepesAuNutella voir même encore des CrepesExceptionnelle  mais là ça devient l'Inception des Exceptions.. une autre fois peut-être (dans les faits c'est pas possible puisqu'il s'agit d'une inner class de la fonction, mais écrite dans un fichier à part ça marcherait tout bien)
        String[] array = new String[]{"Sucre citron","banane chocolat et chantilly","confiture abricot","crème de maron et chantilly"};
        System.out.println(array);
        //array = {0,1,2,3}

        String[] array2 = mange_une_crepe(array);//(array2==array), on réutilise la même assiette, gare à la vaisselle
        System.out.println(array2);
        //array2 = {0,1,2}
    }
}
