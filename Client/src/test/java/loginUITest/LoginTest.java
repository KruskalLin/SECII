package loginUITest;

import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ui.mainui.Launcher;

import static org.testfx.api.FxAssert.verifyThat;

public class LoginTest extends ApplicationTest{

    @Before
    public void setUpClass() throws Exception{
        ApplicationTest.launch(Launcher.class);
    }
    @Override
    public void start(Stage stage) throws Exception {
          stage.show();
    }
    @Test
    public void test() {
        // given:

        clickOn("#account").write("admin");
        clickOn("#password").write("admin");
        clickOn("#login");
        // when:

        // then:
    }
}
