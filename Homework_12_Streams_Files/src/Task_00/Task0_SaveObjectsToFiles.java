
package Task_00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Arrays;

public class Task0_SaveObjectsToFiles {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String[] actors = {"Brad Pitt", "Edward Norton", "Halena Bonham Carter "};

        Movie movie = new Movie("Fight club", "David Fincher",
                actors, LocalDate.of(1999, 9, 21));

        String fileName = "fightclub";

        try (FileOutputStream fs = new FileOutputStream(fileName)) {
            try (ObjectOutputStream os = new ObjectOutputStream(fs)) {
                os.writeObject(movie);
            }
        }

        Movie parsed;

        try (FileInputStream fs = new FileInputStream(fileName)) {
            try (ObjectInputStream is = new ObjectInputStream(fs)) {
                parsed = (Movie) is.readObject();
            }
        }

        if (movie.director.equals(parsed.director)
                && movie.releaseDate.equals(parsed.releaseDate)
                && movie.title.equals(parsed.title)
                && Arrays.deepEquals(movie.actors, parsed.actors)) {
            System.out.println("Match! ");
        }
    }
}
