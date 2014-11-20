package controllers;

import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.*;
import play.db.jpa.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import play.data.*;
import static views.html.index.render;
import views.html.index;

public class Application extends Controller {
    static Form<Goal> goalForm = Form.form(Goal.class);
    private static GenericDAO dao = new GenericDAOImpl();

    @Transactional
    public static Result index() {
        List<Goal> goals = getDao().findAllByClassName("Goal");
        return ok( views.html.index.render(goals, goalForm));
    }

    @Transactional
    public static Result goals() {
        List<Goal> goals = getDao().findAllByClassName("Goal");
        return ok( views.html.index.render(goals, goalForm));
    }

    public static GenericDAO getDao() {
        return dao;
    }

    public static void setDao(GenericDAO dao) {
        Application.dao = dao;
    }
}
