package models;

import java.util.Date;

/**
 * Created by Leticia on 11/18/2014.
 */
public class Goal {
    private Date date;
    private String name;
    private String description;
    //De 1 a 5
    private int relevance;

    public Date getDate() {
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
