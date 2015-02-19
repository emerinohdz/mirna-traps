/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.resource;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.Author;
import com.nuevebit.miroculus.mrna.core.AuthorRepository;
import java.util.List;
import javax.inject.Inject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author emerino
 */
public class AuthorResource extends ServerResource {
    
    @Inject
    private AuthorRepository authorRepository;

    @Get("json")
    public List<Author> getAuthors() {
        return Lists.newArrayList(authorRepository.findAll());
    }
}
