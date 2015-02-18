/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import com.nuevebit.persistence.AbstractIdentificable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author emerino
 */
@Entity
@Table(name = "Publication")
@Access(AccessType.FIELD)
public class Publication extends AbstractIdentificable<Long> {
    private static final long serialVersionUID = -5963682330976914167L;

    @Lob
    private String name;

    @Lob
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id", nullable = false)
    private Author author;

    @Lob
    @Column(nullable = false)
    private String journal;

    @Column(nullable = false)
    private Integer year;

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-Publication"
    )
    @TableGenerator(name = "SeqGen-Publication", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "publicationId", initialValue = 1, allocationSize = 500
    )
    @Access(AccessType.PROPERTY)
    @Override
    public Long getId() {
        return super.getId(); 
    }

    @Override
    public void setId(Long id) {
        super.setId(id); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + Objects.hashCode(this.journal);
        hash = 17 * hash + Objects.hashCode(this.year);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publication other = (Publication) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.journal, other.journal)) {
            return false;
        }
        return Objects.equals(this.year, other.year);
    }
    
}
