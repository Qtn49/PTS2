package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Exercice;
import model.Section;

public class Test {

    public static void main(String[] args) throws IOException {
        ArrayList<Section> sections = new ArrayList<>();
        Section section = new Section(10, "blablablabla hahaha.\njsp quoi dire ! bruh mais je teste la ponctuation ?");
        LinkedList<String> aide = new LinkedList<>();

        aide.add("Un petit mot");

        sections.add(section);

        Exercice exercice = new Exercice(sections, "blc", aide, "Tu dois trouver tout seul haha !", false, true);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("test.o"));
        outputStream.writeObject(exercice);
        outputStream.close();

    }

}
