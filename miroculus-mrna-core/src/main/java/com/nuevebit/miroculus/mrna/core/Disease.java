/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import com.nuevebit.persistence.AbstractIdentificable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Represents a known disease.
 *
 * @author emerino
 */
@Entity
@Table(name = "Disease")
@Access(AccessType.FIELD)
public class Disease
        extends AbstractIdentificable<Long>
        implements Comparable<Disease> {

    private static final long serialVersionUID = 3643529063898534558L;

    @Lob
    private String name;

    @Column(nullable = false)
    private Integer potentialBiomarker;

    @Column(nullable = true)
    private Double mortalityRate;

    protected Disease() {
    }

    public Disease(int potentialBiomarker, double mortalityRate) {
        this(null, potentialBiomarker, mortalityRate);
    }

    public Disease(String name, Integer potentialBiomarker, Double mortalityRate) {
        this.name = name;
        this.potentialBiomarker = potentialBiomarker;
        this.mortalityRate = mortalityRate;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-Disease"
    )
    @TableGenerator(name = "SeqGen-Disease", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "diseaseId", initialValue = 1, allocationSize = 500
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

    /**
     * Compare according to potential biomarker and mortality rate, in ascending
     * order. It gives more weight to potential biomarker than to mortality
     * rate.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Disease o) {
        // mortality rates are in ranges [0, 100], we should transform potential
        // biomarkers to accounts for this.
        int val1 = o.getPotentialBiomarker() * 100 
                + (int) Math.round(o.getMortalityRate());
        
        int val2 = getPotentialBiomarker() * 100 
                + (int) Math.round(getMortalityRate());
        
        return val1 - val2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPotentialBiomarker() {
        return potentialBiomarker;
    }

    public void setMortalityRate(Double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public Double getMortalityRate() {
        return mortalityRate;
    }

}
