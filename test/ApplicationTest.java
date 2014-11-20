import java.util.*;
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

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
    private GenericDAO dao = new GenericDAOImpl();

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void shouldStartDatabaseWithNoGoals() {
        dao = new GenericDAOImpl();
        List<Goal> goals = dao.findAllByClassName("Goal");
        assertThat(goals.size()).isEqualTo(0);
    }

    @Test
    public void shouldSaveGoalInDatabase() {
        Goal goal = new Goal("Goal", new GregorianCalendar(1,1,2014), 1);
        dao.persist(goal);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(1);
        assertThat(goals.get(0).getName()).isEqualTo("GOAL");
    }

    public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }
}
