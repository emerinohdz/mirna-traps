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
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents a miRNA.
 *
 * @author emerino
 */
@Entity
@Table(name = "MiRNA")
@Access(AccessType.FIELD)
public class MiRNA extends AbstractIdentificable<Long> {

    private static final long serialVersionUID = -670173639936311917L;

    public enum Type {

        CIRCULATING
    }

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    protected MiRNA() {
    }

    public MiRNA(String name) {
        this(name, Type.CIRCULATING);
    }

    public MiRNA(String name, Type type) {
        // TODO: Verify name syntax is correct, should have at least 3 parts
        // divided by dashes (-).
        this.name = normalizeName(name);
        this.type = type;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-MiRNA"
    )
    @TableGenerator(name = "SeqGen-MiRNA", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "mirnaId", initialValue = 1, allocationSize = 500
    )
    @Access(AccessType.PROPERTY)
    @Override
    public Long getId() {
        return super.getId();
    }

    /**
     * This leaves the last letter out (in case it contains a letter at the end)
     * or * the last dash (in case it contains a dash with a suffix).
     *
     * @return the short name for this miRNA
     */
    public String getShortName() {
        String[] parts = getName().split("-");

        // we should verify up to the 3rd part, because if there's a 4th that
        // is the suffix and should be ignored.
        char last = parts[2].charAt(parts[2].toLowerCase().length() - 1);

        // test ascii codes
        if (last >= 97 && last <= 122) {
            parts[2] = parts[2].substring(0, parts[2].length() - 1);
        }

        String shortName = StringUtils.join(
                new String[]{parts[0], parts[1], parts[2]}, "-");

        return shortName;
    }

    /**
     * Sometimes the name contains an asterisk at the end, before setting the
     * name for this miRNA we should remove it.
     *
     * @param name
     * @return the name without an asterisk at the end.
     */
    private String normalizeName(String name) {
        if (name.endsWith("*")) {
            name = name.substring(0, name.length() - 1);
        }

        return name;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.type);
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
        final MiRNA other = (MiRNA) obj;

        // test using short name instead of name for now
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return this.type == other.type;
    }

}
