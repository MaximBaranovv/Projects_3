package robot;

import com.project.ppp.Program;
import com.project.ppp.Robot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RobotWithRuleTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    private Robot robot;
    private Program program;

    @Before
    public void setUp() {
        robot = new Robot();
        program = new Program(robot);
    }

    @Test
    public void testCreatingFile() throws IOException {
        program.stepForward();
        String content = Files.readString(Path.of(robot.getGET_ABSOLUTE_PATH()));
        Assert.assertEquals("0.1\n" +
                "0.2\n" +
                "1.2\n" +
                "0.2\n" +
                "-1.2\n" +
                "-2.2\n", content);
        robot.getFILE().delete();
    }
}
