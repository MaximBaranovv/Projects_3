package robot;

import com.project.ppp.Program;
import com.project.ppp.Robot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RobotWithMockitoTest {

    @InjectMocks
    private Robot robot;

    @InjectMocks
    private Program program;

    @Test
    public void testTurningLeft() {
        robot.turnLeft();
        Assertions.assertEquals("up", robot.getDirection());
    }

    @Test
    public void testTurningAround() {
        robot.turnLeft();
        Assertions.assertEquals("up", robot.getDirection());
        robot.turnLeft();
        Assertions.assertEquals("left", robot.getDirection());
        robot.turnLeft();
        Assertions.assertEquals("down", robot.getDirection());
        robot.turnLeft();
        Assertions.assertEquals("right", robot.getDirection());
    }

//    @Test
//    public void testCreatingFile() throws IOException {
//        program.stepForward();
//        String content = Files.readString(Path.of(robot.getGET_ABSOLUTE_PATH()));
//        Assert.assertEquals("0.1\n" +
//                "0.2\n" +
//                "1.2\n" +
//                "0.2\n" +
//                "-1.2\n" +
//                "-2.2\n", content);
//        robot.getFILE().delete();
//    }

}
