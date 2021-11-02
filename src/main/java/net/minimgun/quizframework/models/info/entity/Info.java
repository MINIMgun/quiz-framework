package net.minimgun.quizframework.models.info.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This {@link Entity} models the basic information provided to the frontend at
 * start up. <br>
 * e.g. the title of the application or page.
 * 
 * @author MINIMgun
 *
 */
@Entity
public class Info {

    /**
     * The database id of this {@link Info} entity.
     */
    private @Id @GeneratedValue long id;
    /**
     * The title of the frontend page
     */
    private String title;

    /**
     * Creates a new {@link Info}.
     * 
     * @param title The {@link #title}
     */
    public Info(String title) {
        super();
        this.title = title;
    }

    /**
     * Creates a new {@link Info}.
     */
    public Info() {
        super();
    }

    /**
     * The {@link #id}.
     *
     * @return The {@link #id}.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the {@link #id}.
     *
     * @param id The {@link #id} to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The {@link #title}.
     *
     * @return The {@link #title}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the {@link #title}.
     *
     * @param title The {@link #title} to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Info other = (Info) obj;
        if (id != other.id)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Info [id=" + id + ", title=" + title + "]";
    }
}
