import base.AbstractTest;
import controllers.Application;
import models.Goal;
import models.dao.GenericDAOImpl;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
   @Test
    public void shouldStartPage() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Minhas Metas");
            }
        });
    }

    @Test
    public void shouldAddGoal() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");

                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("name", "Goal");
                parameters.put("description", "Description");
                parameters.put("date", "1");
                parameters.put("relevance", "0");

                FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                Result result = callAction(controllers.routes.ref.Application.newGoal(), fakeRequest);

                int responseCode = status(result);
                assertThat(responseCode).isEqualTo(OK);
            }
        });
    }

    @Test
    public void shouldRemoveGoal() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");

                GenericDAOImpl dao = new GenericDAOImpl();
                Goal goal = new Goal("goal", "description", 0, 0);
                dao.persist(goal);
                long id = goal.getId();

                FakeRequest fakeRequest = new FakeRequest();
                Result result = callAction(controllers.routes.ref.Application.deleteGoal(id), fakeRequest);

                int responseCode = status(result);
                assertThat(responseCode).isEqualTo(OK);
            }
        });
    }

/*    @Test
    public void shouldUpdateGoal() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");

                GenericDAOImpl dao = new GenericDAOImpl();
                Goal goal = new Goal("goal", "description", 0, 0);
                dao.persist(goal);
                long id = goal.getId();

                FakeRequest fakeRequest = new FakeRequest();
                Result result = callAction(controllers.routes.ref.Application.updateGoal(id), fakeRequest);

                int responseCode = status(result);
                assertThat(responseCode).isEqualTo(OK);
            }
        });
    }*/
}
