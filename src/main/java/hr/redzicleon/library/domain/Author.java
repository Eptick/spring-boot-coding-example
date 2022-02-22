package hr.redzicleon.library.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hr.redzicleon.library.domain.audit.AuditableEntity;


@Entity()
@Table(name = "authors")
public class Author extends AuditableEntity {

    @Id
    @Column(name = "author_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID authorId;

    @Column(name = "name", updatable = true, nullable = false)
    private String name;

    public UUID getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}