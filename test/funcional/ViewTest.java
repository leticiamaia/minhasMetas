package funcional;

import base.AbstractTest;
import models.Goal;
import org.junit.Before;
import org.junit.Test;
import play.api.mvc.Result;
import play.data.Form;
import play.twirl.api.Html;
import scala.tools.cmd.Meta;
import views.html.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Created by Leticia on 12/3/2014.
 */
public class ViewTest extends AbstractTest {
    @Before
    public void setUp() {
        Goal goal = new Goal("Goal", "Example Goal", 0, 1);
    }

    @Test
    public void shouldAddNewGoalToView() {
        Form<Goal> goalForm = Form.form(Goal.class);
        List<Goal> goals  = dao.findAllByClassName(Goal.class.getName());
        Html html = index.render(goals, goalForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Goal");
    }

    /*@Test
    public void shouldDeleteGoalFromView() {
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("name", "goal1");
        formData.put("date", "3");
        formData.put("relevance", "2");
        formData.put("description", "1");
        Result result1 = callAction(controllers.routes.ref.Application.newGoal(), fakeRequest()
                .withFormUrlEncodedBody(formData));
        dao.flush();
        List<Goal> goals = dao.findAllByClassName(Meta.class.getName());
        assertThat(goals.size()).isEqualTo(1);

        formData = new HashMap<String, String>();
        formData.put("id", "1");
        Result result = callAction(controllers.routes.ref.Application.deleteGoal(), fakeRequest()
                .withFormUrlEncodedBody(formData));
        dao.flush();
        goals = dao.findAllByClassName(Meta.class.getName());
        assertThat(goals.size()).isEqualTo(0);
        }*/
    }
