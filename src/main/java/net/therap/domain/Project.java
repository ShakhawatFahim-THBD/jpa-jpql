package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shakhawat.hossain
 * @since 11/29/16
 */
@Entity
@Table(name = "table_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpProjectSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpProjectSeq", sequenceName = "tmp_project_seq", allocationSize = 1)
    private long id;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private ProjectType projectType;

    public Project() {
    }

    public Project(String title, ProjectType projectType) {
        this.title = title;
        this.projectType = projectType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", projectType=" + projectType +
                '}';
    }
}
