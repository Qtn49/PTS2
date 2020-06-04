package application;

import model.Exercice;
import model.Section;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("test.o"));
        Exercice exercice = (Exercice) stream.readObject();

//        Section section = exercice.getSection(0);
//        System.out.println(section.getSolution());
//        System.out.println(section.getTexteOccultee());
//
//        System.out.println(exercice.getConsigne());

        System.out.println(exercice);

    }

}
