package models;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Leticia on 11/18/2014.
 */
@Entity(name = "Goal")
public class Goal {
    /*Todo Id tem que ter o GeneratedValue a não ser que ele seja setado*/
	@Id
	@SequenceGenerator(name = "GOAL_SEQUENCE", sequenceName = "GOAL_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	// Usar Id sempre Long
	//private Long id;

    private GregorianCalendar date;
    private String name;
    private String description;
    //De 1 a 5
    private int relevance;

    public Goal(String name, GregorianCalendar date, int relevance) {
        this.date = date;
       // this.id = id;
        this.name = name;
        this.relevance = relevance;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRelevance() {
        return relevance;
    }

}
