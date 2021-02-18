import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class p2 {

    static class Human{
        private int age;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private int weight;

        public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
            Random random = new Random();
            this.age = age;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = LocalDate.of(2020-age,random.nextInt(11)+1,random.nextInt(20)+1);
            this.weight = weight;
        }

        public int getAge() {
            return age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Human{" +
                    "age=" + age +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", birthDate=" + birthDate +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        Human human[] = new Human[10];
        human[0] = new Human(15, "Alex", "Luser", LocalDate.of(2000,11,14), 54);
        human[1] = new Human(21, "Imigrator", "Rum", LocalDate.of(1987,11,14), 65);
        human[2] = new Human(22, "Clone", "Samuel", LocalDate.of(2000,11,14), 67);
        human[3] = new Human(45, "Imaginator", "Saber", LocalDate.of(2000,11,14), 98);
        human[4] = new Human(56, "Crowed", "Lerk", LocalDate.of(2000,11,14), 67);
        human[5] = new Human(87, "Flex", "Klork", LocalDate.of(2000,11,14), 78);
        human[6] = new Human(41, "Looser", "Grotk", LocalDate.of(2000,11,14), 65);
        human[7] = new Human(33, "Test", "Peterstang", LocalDate.of(2000,11,14), 90);
        human[8] = new Human(11, "Krinj", "Uglovn", LocalDate.of(2000,11,14), 98);
        human[9] = new Human(10, "Down", "Iondrum", LocalDate.of(2000,11,14), 76);
        Stream<Human> stream = Arrays.stream(human.clone());
        stream.sorted(Comparator.comparingInt(hum -> hum.getFirstName().length())).forEach(human1 -> System.out.println(human1));
        System.out.println("-----------------------------------------------");

        Stream<Human> stream2 = Arrays.stream(human.clone());
        stream2.filter(hum1 -> hum1.getBirthDate().toEpochDay() > LocalDate.of(2000, 6,24).toEpochDay()).forEach(human2 -> System.out.println(human2));
        System.out.println("-----------------------------------------------");

        Stream<Human> stream3 = Arrays.stream(human.clone());
        stream3.sorted(Comparator.comparingInt(hum2 -> hum2.getLastName().length())).forEach(human3 -> System.out.println(human3));
        System.out.println("-----------------------------------------------");

        Stream<Human> stream4 = Arrays.stream(human.clone());
        Integer sum = stream4.reduce(0,
                (x,y)-> {
                        return x + y.getAge();
                },
                (x, y)->x+y);
        System.out.println(sum);
    }
}
