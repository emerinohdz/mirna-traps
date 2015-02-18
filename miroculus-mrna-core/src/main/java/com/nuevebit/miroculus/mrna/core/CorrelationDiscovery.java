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
 * Defines a correlation discovery made by a specific author about a miRNA
 * with a disease.
 *
 * @author emerino
 */
@Entity
@Table(name = "CorrelationDiscovery")
@Access(AccessType.FIELD)
public class CorrelationDiscovery extends AbstractIdentificable<Long> {
    private static final long serialVersionUID = 7083261160634786361L;

    @ManyToOne
    @JoinColumn(name = "mirnaId", referencedColumnName = "id")
    private MiRNA miRNA;

    @ManyToOne
    @JoinColumn(name = "diseaseId", referencedColumnName = "id")
    private Disease disease;

    /**
     * The publication where the correlation was documented.
     */
    @ManyToOne
    @JoinColumn(name = "publicationId")
    private Publication publication;
    
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
}
