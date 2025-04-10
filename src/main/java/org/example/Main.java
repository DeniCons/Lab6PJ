package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            File file=new File("src/main/resources/angajat.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Angajat> citire() {
        try {
            File file=new File("src/main/resources/angajat.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> persoane = mapper
                    .readValue(file, new TypeReference<List<Angajat>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
      List<Angajat>lista=new ArrayList<>();
      lista.add(new Angajat("Alin","Sef", LocalDate.of(2025,9,8),(float)9000.8));
        lista.add(new Angajat("Adelin","Muncitor", LocalDate.of(2007,9,7),(float)2400.0));
        lista.add(new Angajat("Maria","Muncitor", LocalDate.of(2021,5,6),(float)2000.8));
        lista.add(new Angajat("Adelina","Director", LocalDate.of(2020,12,8),(float)9100.8));
        lista.add(new Angajat("Madalin","Paznic", LocalDate.of(2025,11,10),(float)1000.8));
        scriere(lista);
        lista=citire();

        //1.Afișarea listei de angajați folosind referințe la metode.
            lista.forEach(System.out::println);

        //2.Afișarea angajaților care au salariul peste 2500 RON. Rezolvarea va utiliza stream-uri.
        //Interfața funcțională Predicate, care este parametrul metodei de filtrare va fi
        //implementată printr-o expresie Lambda.

        System.out.println("Angajatii care au salarul peste 2500:");
        Predicate<Angajat> salariuPeste2500= angajat->angajat.getSalar()>2500;
        List<Angajat>anagajatiFiltrati=lista.stream()
                .filter(salariuPeste2500)
                .collect(Collectors.toList());

        anagajatiFiltrati.forEach(System.out::println);

      //3.Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de
        //conducere (postul conține unul din cuvintele sef sau director). Pentru crearea unei liste
        //dintr-un stream se va utiliza operația terminală collect (Collectors.toList()). Se vor
        //utiliza steram-uri şi expresii lambda. Noua listă va fi apoi afișată. Anul curent se va citi
        //din sistem.

        int anulCurent=LocalDate.now().getYear();
        List<Angajat> angajatiFiltrati = lista.stream()
                .filter(angajat ->
                        angajat.getData_angajari().getMonthValue() == 4 &&
                                angajat.getData_angajari().getYear() == (anulCurent - 1) &&
                                angajat.getPostul().toLowerCase().contains("sef") ||
                                angajat.getPostul().toLowerCase().contains("director")
                )
                .collect(Collectors.toList());

        System.out.println("Angajatii din aprilie anul trecut cu functie de conducere:");
        angajatiFiltrati.forEach(System.out::println);

        //4.. Afișarea angajaților care nu au funcție de conducere (postul lor nu conține cuvintele
        //director sau șef), în ordine descrescătoare a salariilor, folosind stream-uri şi expresii
        //lambda.

        List<Angajat> angajatiFaraConducere = lista.stream()
                .filter(angajat -> {
                    String post = angajat.getPostul().toLowerCase();
                    return !post.contains("sef") && !post.contains("director");
                })
                .sorted((a1, a2) -> Float.compare(a2.getSalar(), a1.getSalar())) // ordonare descrescătoare
                .collect(Collectors.toList());

        System.out.println("Angajatii care NU au functie de conducere, ordonati descrescator dupa salariu:");
        angajatiFaraConducere.forEach(System.out::println);
//5.. Extragerea din lista de angajați a unei liste de String-uri care conține numele angajaților
//scrise cu majuscule. Rezolvarea va utiliza steram-uri, metoda map() şi operația
//terminală collect(). Lista de String-uri va fi afișată.

        List<String> numeCuMajuscule = lista.stream()
                .map(angajat -> angajat.getNume().toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Numele angajatilor scrise cu majuscule:");
        numeCuMajuscule.forEach(System.out::println);

        //6 Afișarea salariilor mai mici de 3000 de RON (doar salariile, fără alte informații)
        //folosind stream-uri, expresii lambda, referințe la metode şi metoda map().
        lista.stream()
                .map(Angajat::getSalar)
                .filter(salariu->salariu<3000)
                .forEach(System.out::println);

        //7 primul angajat al firmei
        lista.stream()
                .min((a1, a2) -> a1.getData_angajari().compareTo(a2.getData_angajari()))
                .ifPresentOrElse(
                        angajat -> System.out.println("Primul angajat al firmei: " + angajat),
                        () -> System.out.println("Lista de angajati este goala.")
                );








    }
    }
