package me.elordenador.parejabaile;

/**
 * @version 1.0
 * @author Daniel Sánchez Úbeda
 */
public class Parejas {
    private static final String[] heroesStarWars = {"Luke Skywalker", "Leia Organa", "Han Solo", "Obi-Wan Kenobi", "Yoda", "Ahsoka Tano", "Rey", "Poe Dameron", "Din Djarin", "Bo-Katan Kryze"};
    private static final String[] villanosStarWars = {"Darth Vader", "Darth Sidious", "General Grievous", "Conde Dooku", "Kylo Ren", "Darth Maul", "Boba Fett", "Gran Moff Tarkin", "Moff Gideon", "Jabba el Hutt"};
    private static final String[] heroesMarvel = {"Spider-Man", "Tony Stark", "Capitán América", "Thor", "Hulk", "Lobezno", "Viuda Negra", "Charles Xavier", "Star Lord", "Doctor Strange"};
    private static final String[] villanosMarvel = {"Doctor Doom", "Thanos", "Magneto", "Loki", "Red Skull", "Venom", "Green Goblin", "Ultron", "Kang", "Doctor Octopus"};
    private static final String[] heroesTolkien = {"Frodo Bolsón", "Aragorn", "Gandalf", "Legolas", "Gimli", "Arwen", "Galadriel", "Elrond", "Bilbo Bolsón", "San Gamyi"};
    private static final String[] villanosTolkien = {"Sauron", "Saruman", "Gollum", "Bill Helechal", "Ella Laraña", "Gran Trasgo", "Smaug", "Bolg", "Azog", "Lengua de Serpiente"};

    public static void main(String[] args) {

        int[] emparejamientos = {0,0,0,0,0,0};


        Personaje[] heroes = generateHero();
        Personaje[] villanos = generateVillano();
        Personaje[] outputVillanos = new Personaje[heroesStarWars.length+heroesMarvel.length+heroesTolkien.length];
        Personaje[] outputHeroes = new Personaje[heroesStarWars.length+heroesMarvel.length+heroesTolkien.length];
        ScrUtils.clearScreen();
        boolean salir = false;
        int contador = 0;
        Personaje hero;
        Personaje villano;
        while (!salir) {

            // Sacamos a un heroe
            hero = heroes[genRandomNumber(heroes.length)];

            if (hero.getUniverso() == Universo.STAR_WARS) {
                // Nuestro heroe es del Universo de Star Wars. Procederemos a generar un personaje dependiendo del universo
                if (emparejamientos[0] > emparejamientos[1]) {
                    // Hay mas personajes de Marvel Emparejados con personajes de Tolkien. Emparejaremos uno de Tolkien
                    villano = getRandomVillain(villanos, Universo.TOLKIEN);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[1]++;
                    }
                } else {
                    // Puede que haya mas de Tolkien emparejados o son identicos, emparejaremos con un villano de Marvel.
                    villano = getRandomVillain(villanos, Universo.MARVEL);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[0]++;
                    }
                }
            } else if (hero.getUniverso() == Universo.MARVEL) {
                // No es de Star Wars pero es de Marvel, Se emparejará con Star Wars o Tolkien
                if (emparejamientos[2] > emparejamientos[3]) {
                    // Hay mas personajes de Star Wars emparejados con personajes de Marvel. Emparejaremos uno de Tolkien
                    villano = getRandomVillain(villanos, Universo.TOLKIEN);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[3]++;
                    }
                } else {
                    // Emparejaremos aqui uno de Marvel
                    villano = getRandomVillain(villanos, Universo.STAR_WARS);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[2]++;

                    }
                }

            } else {
                // Es de tolkien.
                if (emparejamientos[4] > emparejamientos[5]) {
                    // Hay mas emparejamientos de Star Wars que de Marvel, emparejaremos con Marvel
                    villano = getRandomVillain(villanos, Universo.MARVEL);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[5]++;

                    }

                } else {
                    // Emparejamos con Star Wars
                    villano = getRandomVillain(villanos, Universo.STAR_WARS);
                    if (!found(outputHeroes, hero) && !found(outputVillanos, villano)) {
                        outputHeroes[contador] = hero;
                        outputVillanos[contador] = villano;
                        contador++;
                        emparejamientos[4]++;

                    }
                }
            }
            if (contador > 29) {
                salir = true;
            }

        }
        for (int i = 0; i < outputHeroes.length; i++) {
            System.out.println(outputHeroes[i].getNombre() + " -> " + outputVillanos[i].getNombre());
        }
    }

    /**
     * Esta función convertira la lista de un String[] a un Personaje[] para los heroes
     */
    private static Personaje[] generateHero() {
        Personaje[] heroes = new Personaje[heroesStarWars.length+heroesMarvel.length+heroesTolkien.length];
        for (int i = 0; i < heroesStarWars.length; i++) {
            heroes[i] = new Personaje(heroesStarWars[i], Universo.STAR_WARS);
        }
        for (int i = 0; i < heroesMarvel.length; i++) {
            heroes[i+heroesStarWars.length] = new Personaje(heroesMarvel[i], Universo.MARVEL);
        }
        for (int i = 0; i < heroesTolkien.length; i++) {
            heroes[i+ heroesStarWars.length+ heroesMarvel.length] = new Personaje(heroesTolkien[i], Universo.TOLKIEN);
        }

        return heroes;
    }

    private static Personaje getRandomVillain(Personaje[] VILLAIN_LIST, Universo universo) {
        boolean salida = false;
        Personaje villano = null;
        while (!salida) {
            villano = VILLAIN_LIST[genRandomNumber(VILLAIN_LIST.length)];
            if (villano.getUniverso() == universo) {
                salida = true;
            }

        }

        return villano;
    }

    /**
     * Esta función convertira la lista de un String[] a un Personaje[] para los villanos.
     */
    private static Personaje[] generateVillano() {
        Personaje[] villanos = new Personaje[villanosStarWars.length+villanosMarvel.length+villanosTolkien.length];
        for (int i = 0; i < villanosStarWars.length; i++) {
            villanos[i] = new Personaje(villanosStarWars[i], Universo.STAR_WARS);
        }
        for (int i = 0; i < villanosMarvel.length; i++) {
            villanos[i+villanosStarWars.length] = new Personaje(villanosMarvel[i], Universo.MARVEL);
        }
        for (int i = 0; i < villanosTolkien.length; i++) {
            villanos[i+ villanosStarWars.length+ villanosMarvel.length] = new Personaje(villanosTolkien[i], Universo.TOLKIEN);
        }

        return villanos;
    }

    /**
     * Esta función toma un numero maximo y genera un numero entre el 0 y el (maximo-1)
     * @param max Numero maximo (1 mas del real)
     * @return El numero aleatorio
     */
    private static int genRandomNumber(int max) {
        return (int) Math.floor(Math.random() * (max) + 0);
    }

    /**
     * Esta función checkea en una lista si hay un elemento
     * @param lista Lista a suministrar
     * @param personaje Objeto personaje
     * @return True si existe en la lista o False si no.
     */
    private static boolean found(Personaje[] lista, Personaje personaje) {

        boolean salida = false;
        for (Personaje value : lista) {
            try {
                if (value.getNombre().equals(personaje.getNombre())) {
                    salida = true;
                }
            } catch (NullPointerException ignored) {
            }

        }

        /*
        System.out.println();
        System.out.println(personaje.getNombre());
        System.out.println(salida);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */
        return salida;
    }
}
