import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Unit test for the the TVShow Class
 */
public class TVShowTests{
    TVShow testShow = new TVShow("testShow", Language.ENGLISH, "testStudio");

    /**
     * -> Used White Box
     * Testing if the default Episode prototype has empty custom info.
     * So if aCast and aTags are empty.
     */
    @Test
    public void defaultPrototypeTest() {
        Field prototypeFeild = null;
        Episode defaultPrototype = null;

        try {
            prototypeFeild = TVShow.class.
                    getDeclaredField("episodePrototype");
            prototypeFeild.setAccessible(true);
            defaultPrototype = (Episode) prototypeFeild.get(testShow);
        } catch (NoSuchFieldException e) {
            fail();
        }
        catch (IllegalAccessException e){
            fail();
        }

        boolean emptyCast = defaultPrototype.getAllCharacters().isEmpty();
        boolean emptyInfo = defaultPrototype.getAllInfo().isEmpty();

        boolean[] customFieldOutput = {emptyCast, emptyInfo};
        boolean[] customFieldExpectation = {true, true};

        assertArrayEquals(customFieldOutput, customFieldExpectation);
    }

    /**
     * Testing if the Episode prototype gets correctly set.
     * So if aCast and aTags get set to the correct values.
     */
    @Test
    public void setPrototypeTest() {
        Map<String, String> casts = new HashMap<>();
        casts.put("Char 1", "Cast 1");
        casts.put("Char 2", "Cast 2");
        casts.put("Char 3", "Cast 3");
        casts.put("Char 4", "Cast 4");

        Map<String, String> tags = new HashMap<>();
        tags.put("Value 1", "Key 1");
        tags.put("Value 2", "Key 2");
        tags.put("Value 3", "Key 3");
        tags.put("Value 4", "Key 4");

        testShow.setPrototypeEpisode(casts, tags);

        // Accessing the private episodePrototype field
        Field prototypeFeild = null;
        Episode newPrototype = null;

        try {
            prototypeFeild = TVShow.class.
                    getDeclaredField("episodePrototype");
            prototypeFeild.setAccessible(true);
            newPrototype = (Episode) prototypeFeild.get(testShow);
        } catch (NoSuchFieldException e) {
            fail();
        }
        catch (IllegalAccessException e){
            fail();
        }

        assertEquals(newPrototype.getAllCharacters(), casts.keySet());
        assertEquals(newPrototype.getAllInfo(), tags.keySet());
    }

    /**
     * Testing if a new Episode is correctly created using the prototype.
     */
    @Test
    public void addPrototypeTest() {
        Map<String, String> casts = new HashMap<>();
        casts.put("Char 1", "Cast 1");
        casts.put("Char 2", "Cast 2");
        casts.put("Char 3", "Cast 3");
        casts.put("Char 4", "Cast 4");

        Map<String, String> tags = new HashMap<>();
        tags.put("Value 1", "Key 1");
        tags.put("Value 2", "Key 2");
        tags.put("Value 3", "Key 3");
        tags.put("Value 4", "Key 4");

        String title = "prototypeTest";

        testShow.setPrototypeEpisode(casts, tags);
        testShow.addEpisodeFromPrototype(new File("prototypeTest Path"), title);

        Episode newEpisode = testShow.getEpisode(testShow.getTotalCount());

        assertEquals(newEpisode.getAllCharacters(), casts.keySet());
        assertEquals(newEpisode.getAllInfo(), tags.keySet());
        assertEquals(newEpisode.getTitle(), title);
    }

    /**
     * -> Used Black Box
     * Tests the clone method.
     * Uses .equals() to check equality.
     */
    @Test
    public void cloneTest() {
        TVShow cloneShow = testShow.clone();
        assertTrue(testShow.equals(cloneShow));
    }
}