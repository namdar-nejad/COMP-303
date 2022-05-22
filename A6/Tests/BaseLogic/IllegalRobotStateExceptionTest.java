package BaseLogic;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IllegalRobotStateExceptionTest {

    IllegalRobotStateException testException = new IllegalRobotStateException("New Message");

    @Test
    public void  IllegalRobotStateExceptionTest(){

        try {
            throw testException;
        } catch (IllegalRobotStateException e) {
            assertThat(e.getMessage(), is("New Message"));
        }
    }
}