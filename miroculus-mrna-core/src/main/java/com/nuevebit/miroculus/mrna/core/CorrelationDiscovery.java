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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Defines a correlation discovery made by a specific author about a miRNA with
 * a disease.
 *
 * @author emerino
 */
@Entity
@Table(name = "CorrelationDiscovery")
@Access(AccessType.FIELD)
public class CorrelationDiscovery
        extends AbstractIdentificable<Long>
        implements Comparable<CorrelationDiscovery> {

    private static final long serialVersionUID = 7083261160634786361L;

    @Column(nullable = false)
    private Integer potentialBiomarker;

    @ManyToOne
    @JoinColumn(name = "mirnaId", referencedColumnName = "id")
    private MiRNA miRNA;

    @ManyToOne
    @JoinColumn(name = "diseaseId", referencedColumnName = "id")
    private Disease disease;

    /**
     * The method used for this discovery correlation.
     */
    @ManyToOne
    @JoinColumn(name = "methodId", referencedColumnName = "id", nullable = true)
    private DiscoveryMethod method;

    /**
     * The publication where the correlation was documented.
     */
    @ManyToOne
    @JoinColumn(name = "publicationId", referencedColumnName = "id", nullable = true)
    private Publication publication;

    protected CorrelationDiscovery() {
    }

    CorrelationDiscovery(Disease disease, int pb) {
        this(null, disease, pb);
    }

    public CorrelationDiscovery(MiRNA miRNA, Disease disease, int pb) {
        this.miRNA = miRNA;
        this.disease = disease;
        this.potentialBiomarker = pb;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-CorrelationDiscovery"
    )
    @TableGenerator(name = "SeqGen-CorrelationDiscovery", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "correlationDiscoveryId", initialValue = 1, allocationSize = 500
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

    public Integer getPotentialBiomarker() {
        return potentialBiomarker;
    }

    public MiRNA getMiRNA() {
        return miRNA;
    }

    public Disease getDisease() {
        return disease;
    }

    public DiscoveryMethod getMethod() {
        return method;
    }

    public CorrelationDiscovery setMethod(DiscoveryMethod method) {
        this.method = method;
        return this;
    }

    public Publication getPublication() {
        return publication;
    }

    public CorrelationDiscovery setPublication(Publication publication) {
        this.publication = publication;
        return this;
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
    public int compareTo(CorrelationDiscovery o) {
        // mortality rates are in ranges [0, 100], we should transform potential
        // biomarkers to accounts for this.
        int val1 = o.getPotentialBiomarker() * 100 
                + (int) Math.round(o.getDisease().getMortalityRate());
        
        int val2 = getPotentialBiomarker() * 100 
                + (int) Math.round(getDisease().getMortalityRate());
        
        return val1 - val2;
    }
    
}
