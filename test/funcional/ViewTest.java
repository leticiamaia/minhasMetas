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

    @Test
    public void shouldAddNewGoalToView() {
        Goal  goal = new Goal("My Goal", "Example Goal", 0, 1);
        Form<Goal> goalForm = Form.form(Goal.class);
        dao.persist(goal);
        List<Goal> goals  = dao.findAllByClassName(Goal.class.getName());
        Html html = index.render(goals, goalForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("My Goal");
    }

    @Test
    public void shouldRemoveGoalToView() {
        Goal  goal = new Goal("My Goal", "Example Goal", 0, 1);
        Form<Goal> goalForm = Form.form(Goal.class);
        dao.persist(goal);
        List<Goal> goals  = dao.findAllByClassName(Goal.class.getName());
        Html html = index.render(goals, goalForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("My Goal");

        long id = goal.getId();
        dao.removeById(Goal.class, id);
        goals  = dao.findAllByClassName(Goal.class.getName());
        html = index.render(goals, goalForm);
        assertThat(contentAsString(html)).doesNotContain("My Goal");

    }

    }
