import java.util.*;

import base.AbstractTest;
import com.fasterxml.jackson.databind.JsonNode;
import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import org.junit.*;

import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import scala.Option;

import javax.persistence.EntityManager;

import static java.util.Collection.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest extends AbstractTest{

    @Test
    public void shouldOrderGoalsByWeekAndByRelevance() {
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new Goal("goal1", "description", 1, 1));
        goals.add(new Goal("goal2", "description", 1, 2));
        goals.add(new Goal("goal3", "description", 0, 1));
        goals.add(new Goal("goal4", "description", 0, 3));
        goals.add(new Goal("goal5", "description", 2, 1));
        Collections.sort(goals);
        assertThat(goals.get(0).getName()).isEqualTo("goal4");
        assertThat(goals.get(1).getName()).isEqualTo("goal3");
        assertThat(goals.get(2).getName()).isEqualTo("goal2");
        assertThat(goals.get(3).getName()).isEqualTo("goal1");
        assertThat(goals.get(4).getName()).isEqualTo("goal5");
    }

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void shouldStartDatabaseWithNoGoals() {
        List<Goal> goals = dao.findAllByClassName("Goal");
        assertThat(goals.size()).isEqualTo(0);
    }

    @Test
    public void shouldSaveGoalInDatabase() {
        Goal goal = new Goal("Goal","Description",0, 1);
        dao.persist(goal);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(1);
        assertThat(goals.get(0).getName()).isEqualTo("Goal");
    }

    @Test
    public void shouldRemoveGoalFromDatabase() {
        Goal goal = new Goal("Goal","Description", 0, 1);
        dao.persist(goal);
        long id = goal.getId();
        dao.removeById(Goal.class, id);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(0);
    }

    @Test
    public void shouldChangeGoalStatusInDatabase() {
        Goal goal = new Goal("Goal","Description", 0, 1);
        dao.persist(goal);
        goal.setAchieved(true);
        dao.merge(goal);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(1);
        assertThat(goals.get(0).getAchieved()).isEqualTo(true);
    }
}
