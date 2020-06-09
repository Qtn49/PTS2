package application;

import model.Exercice;
import model.Section;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws IOException {
        ArrayList<Section> sections = new ArrayList<>();
        Section section = new Section(10, "blablablabla hahaha.\njsp quoi dire ! bruh mais je teste la ponctuation ?");
        LinkedList<String> aide = new LinkedList<>();

        aide.add("Un petit mot");

        sections.add(section);
        sections.add(new Section(20, "yo yo une deuxième section (d'assault) ! haha lol"));

        Exercice exercice = new Exercice(sections, aide, "Tu dois trouver tout seul haha !", false, true);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("test.o"));
        outputStream.writeObject(exercice);
        outputStream.close();

    }

}
