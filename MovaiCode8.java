import java.util.*;
import java.util.function.Consumer;

public class MovaiCode8{

    private class CrepeExeptionnelle extends Exception{
        private final Object imprevu = null;
        Object objet;

        public CrepeExeptionnelle(){
            super();
            objet = imprevu;
        }

        public CrepeExeptionnelle(Object o){
            super();
            objet = o;
        }

        public Object getObjet(){
            return objet;
        }

    }

    private class Iterateur<E> implements Iterator<E> {
        List<E> liste;
        int index = -1;

        public Iterateur(List<E> liste){
            this.liste = liste;
        }

        public void aUnSuivant() throws CrepeExeptionnelle {
            if(index>=liste.size()-1) throw new CrepeExeptionnelle();
        }

        public void suivant() throws  CrepeExeptionnelle {
            aUnSuivant();
            throw new CrepeExeptionnelle(liste.get(++index));
        }

        @Override
        public boolean hasNext() {
            try{
                aUnSuivant();
            }catch (CrepeExeptionnelle crepeExeptionnelle){
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            E objet = null;
            try {
                suivant();
            }catch (CrepeExeptionnelle crepeExeptionnelle){
                if(crepeExeptionnelle.objet==null) throw new NoSuchElementException();
                objet = (E) crepeExeptionnelle.getObjet();
            }
            return objet;
        }

        @Override
        public void remove() {
            liste.remove(index);
        }

        @Override
        public void forEachRemaining(Consumer action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    public List<Object> mange_une_crepe(List<Object> pile_de_crepes){
        Stack<Object> stack = new Stack<>();
        Iterateur<Object> iterateur = new Iterateur<Object>(pile_de_crepes);
        while(iterateur.hasNext()){
            stack.add(iterateur.next());
        }
        stack.pop();
        pile_de_crepes = stack.subList(0, stack.size());
        return pile_de_crepes;
    }

    public static void main(String[] args) {
        MovaiCode8 movaiCode8 = new MovaiCode8();
        ArrayList<Integer> array = new ArrayList<>();
        array.add(0);array.add(1);array.add(2);array.add(3);array.add(4);array.add(5);
        List<Object> li = movaiCode8.mange_une_crepe(Arrays.asList(array.toArray()));
        Object[] ob = li.toArray();
    }
}
