import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.File;

/**
 * Unit test for the the Episode Class
 */
public class EpisodeTests{
    Episode testEpisode;

    /**
     * Initializes the testEpisode
     */
    @BeforeEach
    public void setup(){
        TVShow testShow = new TVShow("testShow", Language.ENGLISH, "testStudio");
        testEpisode = new Episode(new File("testPath"), testShow,"testEpisode", 1);
    }

    /**
     * Tests the Episode constructor that take a Prototype and
     * creates a new Episode based on the Custom info of that.
     */
    @Test
    public void prototypeConstructorTest(){

        //Episode(Episode protoEpisode, File pPath, String pTitle, int pEpisodeNumber)
        Episode prototypeEpisode = testEpisode.clone();
        prototypeEpisode.setCast("Char 1", "Cast 1");
        prototypeEpisode.setCast("Char 2", "Cast 2");
        prototypeEpisode.setCast("Char 3", "Cast 3");

        prototypeEpisode.setInfo("Value 1", "Key 1");
        prototypeEpisode.setInfo("Value 2", "Key 2");
        prototypeEpisode.setInfo("Value 3", "Key 3");

        int eNumber = 2;
        String eTitle = "newEpisode";

        Episode newEpisode = new Episode(prototypeEpisode, new File("newPath"), eTitle, eNumber);

        assertEquals(newEpisode.getAllCharacters(), prototypeEpisode.getAllCharacters());
        assertEquals(newEpisode.getAllInfo(), prototypeEpisode.getAllInfo());
        assertEquals(newEpisode.getEpisodeNumber(), eNumber);
        assertEquals(newEpisode.getTitle(), eTitle);
    }

    /**
     * -> Used Black Box
     * Tests the clone method.
     * Uses .equals() to check equality.
     */
    @Test
    public void cloneTest() {
        Episode cloneEpisode = testEpisode.clone();
        assertTrue(testEpisode.equals(cloneEpisode));
    }
}

