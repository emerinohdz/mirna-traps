/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import com.google.common.collect.Lists;
import com.nuevebit.persistence.AbstractIdentificable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author emerino
 */
@Entity
@Table(name = "Author")
@Access(AccessType.FIELD)
public class Author extends AbstractIdentificable<Integer> {
    private static final long serialVersionUID = -6328242816786276763L;

    @Lob
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "author"
    )
    private Set<Publication> publications;

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-Author"
    )
    @TableGenerator(name = "SeqGen-Author", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "authorId", initialValue = 1, allocationSize = 500
    )
    @Access(AccessType.PROPERTY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public void addPublication(Publication publication) {
        publication.setAuthor(this);
        this.publications.add(publication);
    }

    public List<Publication> getPublications() {
        return Lists.newArrayList(publications);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Author other = (Author) obj;
        return Objects.equals(this.name, other.name);
    }
    
}
