package controllers;

import models.Goal;
import play.*;
import play.db.jpa.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {
    private static GenericDAO dao = new GenericDAOImpl();
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    @Transactional
    public static result goals() {
        List<Goal> result = getDao().findAllByClassName("Goal");
        return ok(views.html.index.render(result, bookForm));
    }

    public static GenericDAO getDao() {
        return dao;
    }

    public static void setDao(GenericDAO dao) {
        Application.dao = dao;
}
