package models;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Leticia on 11/18/2014.
 */
@Entity(name = "Goal")
public class Goal implements Comparable{
    @Id
    @SequenceGenerator(name = "GOAL_SEQUENCE", sequenceName = "GOAL_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    // Usar Id sempre Long
    private Long id;

    private int date;
    private String name;
    private String description;
    //1 to 3
    private int relevance;
    private boolean achieved;

    public Goal(String name,String description, int date, int relevance) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.relevance = relevance;
        achieved = false;
    }

    public Goal() {

    }

    public int getDate() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAchieved() { return achieved; }

    public void setAchieved(boolean achieved) { this.achieved = achieved; }

    @Override
    public int compareTo(Object o) {
        Goal goal2 = (Goal)o;
        if(date == goal2.getDate()) {
            if(relevance > goal2.getRelevance()) {
                return -1;
            } else if (relevance < goal2.getRelevance()) {
                return 1;
            } else {
                return 0;
            }
         }
        return ((Integer) date).compareTo((Integer)goal2.getDate());
    }
}
