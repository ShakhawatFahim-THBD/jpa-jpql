package net.therap.domain;

import net.therap.command.Status;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author safat
 * @since 12/2/16.
 */
@Entity
@Table(name = "table_release_note")
public class ReleaseNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpReleaseNoteSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpReleaseNoteSeq", sequenceName = "tmp_release_note_seq", allocationSize = 1)
    private long id;

    private String description;
    private Status status;

    public ReleaseNote(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReleaseNote{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
