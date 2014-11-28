import play.*;
import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.db.jpa.JPA;
public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAOImpl();

    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Goal goal0 = new Goal("Aprender Git", "Para Lab1", 0, 1);
                dao.persist(goal0);

                Goal goal1 = new Goal("Aprender HTML", "Para Lab1", 0, 1);
                dao.persist(goal1);

                Goal goal2 = new Goal("Aprender CSS", "Para Lab1", 0, 1);
                dao.persist(goal2);

                Goal goal3 = new Goal("Aprender BOOTSTRAP", "Para Lab1", 1, 3);
                dao.persist(goal3);

                Goal goal4 = new Goal("Aprender JAVASCRIPT", "Para Lab1", 1, 1);
                dao.persist(goal4);

                Goal goal5 = new Goal("Aprender JQUERY", "Para Lab1", 1, 1);
                dao.persist(goal5);

                Goal goal6 = new Goal("Aprender Play", "Para Lab2", 2, 1);
                dao.persist(goal6);

                Goal goal7 = new Goal("Aprender BD", "Para Lab2", 2, 3);
                dao.persist(goal7);

                Goal goal8 = new Goal("Aprender BDD", "Para Lab2", 2, 3);
                dao.persist(goal8);

                Goal goal9 = new Goal("Terminar LAB2", "Para Lab2", 3, 3);
                dao.persist(goal9);

                dao.flush();
            }
        });
    }

    public void onStop(Application app) {
        Logger.info("Aplicação desligada...");
    }
}
