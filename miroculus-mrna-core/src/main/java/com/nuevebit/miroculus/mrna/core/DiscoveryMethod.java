/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import com.nuevebit.persistence.AbstractIdentificable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author emerino
 */
@Entity
@Table
@Access(AccessType.FIELD)
public class DiscoveryMethod extends AbstractIdentificable<Integer> {

    @Lob
    private String name;
    
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "SeqGen-DiscoveryMethod"
    )
    @TableGenerator(name = "SeqGen-DiscoveryMethod", table = "ID_GEN",
            pkColumnName = "ID_NAME", valueColumnName = "ID_VAL",
            pkColumnValue = "discoveryMethodId", initialValue = 1, allocationSize = 500
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
}
