import java.util.*;

public class MovaiCode8{

    public static <E> E[] mange_une_crepe(E[] pile_de_crepes) throws Exception{

        class CrepeExeptionnelle extends Exception{
            public final E imprevu = null;
            public final E crepe;

            public CrepeExeptionnelle(){
                super();
                crepe = imprevu;
            }

            public CrepeExeptionnelle(E crepe){
                super();
                this.crepe = crepe;
            }

        }

        class Iterateur {
            final E[] pile_de_crepe;
            final int nombre_de_crepe;
            int index = -1;

            public Iterateur(E[] pile_de_crepe){
                this.pile_de_crepe = pile_de_crepe.clone();
                this.nombre_de_crepe = pile_de_crepe.length;
            }

            public void aUnSuivant() throws CrepeExeptionnelle {
                if(index>= nombre_de_crepe -1) throw new CrepeExeptionnelle();
            }

            public void suivant() throws  CrepeExeptionnelle {
                aUnSuivant();
                throw new CrepeExeptionnelle(pile_de_crepe[++index]);
            }

            public boolean hasNext() {
                try{
                    aUnSuivant();
                }catch (CrepeExeptionnelle crepeExeptionnelle){
                    return false;
                }
                return true;
            }

            public E nnext() {
                try {suivant();}
                catch (CrepeExeptionnelle crepeExeptionnelle) {
                    if (crepeExeptionnelle.crepe != null)
                    return crepeExeptionnelle.crepe;
                }
                throw new NoSuchElementException();
            }
        }



        Stack<E> stack = new Stack<>();
        Iterateur iterateur = new Iterateur(pile_de_crepes);

        try{
            while (true){
                iterateur.aUnSuivant();
                try{ iterateur.suivant(); }
                catch( CrepeExeptionnelle crepeExeptionnelle){
                    if(crepeExeptionnelle.crepe==null)
                        throw new NoSuchElementException();
                    stack.add(crepeExeptionnelle.crepe);
                }
            }
        }catch (CrepeExeptionnelle crepeExeptionnelle){

        }
        stack.pop();

        pile_de_crepes = stack.toArray(pile_de_crepes);
        return pile_de_crepes;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{0,1,2,3};
        Integer[] array2 = MovaiCode8.<Integer>mange_une_crepe(array);
    }
}
