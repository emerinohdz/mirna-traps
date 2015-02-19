/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.cli;

import com.nuevebit.miroculus.mrna.core.Author;
import com.nuevebit.miroculus.mrna.core.AuthorRepository;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscovery;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscoveryRepository;
import com.nuevebit.miroculus.mrna.core.DiscoveryMethod;
import com.nuevebit.miroculus.mrna.core.DiscoveryMethodRepository;
import com.nuevebit.miroculus.mrna.core.Disease;
import com.nuevebit.miroculus.mrna.core.DiseaseRepository;
import com.nuevebit.miroculus.mrna.core.MiRNA;
import com.nuevebit.miroculus.mrna.core.MiRNARepository;
import com.nuevebit.miroculus.mrna.core.Publication;
import com.nuevebit.miroculus.mrna.core.PublicationRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class handles persistence of the provided CSV and downloaded json with
 * mortality rates.
 *
 * @author emerino
 */
@Named
@Singleton
public class DatabasePopulator {

    @Inject
    private MiRNARepository miRNARepository;

    @Inject
    private DiseaseRepository diseaseRepository;

    @Inject
    private CorrelationDiscoveryRepository correlationDiscoveryRepository;

    @Inject
    private DiscoveryMethodRepository discoveryMethodRepository;

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private PublicationRepository publicationRepository;

    /**
     * Retrieve information from CSV and json files, and populate db with this
     * data.
     * 
     * @param csv the CSV file as a string
     * @param mortalityRates a list of mortality rates separated by lines
     */
    @Transactional(rollbackFor = Exception.class)
    public void populate(String csv, InputStream mortalityRates) 
            throws IOException {
        
        // populate the information in the CSV string
        parseCSV(csv);

        // now that we have all the diseases registered, lets try to find
        // their mortality rate
        parseMortalityRates(mortalityRates);
    }

    private void parseMortalityRates(InputStream mortalityRates) 
            throws IOException {
        
        List<String> lines = IOUtils.readLines(mortalityRates);

        for (String line : lines) {
            String[] parts = line.split("=");
            String diseaseName = parts[0];
            double rate = Double.valueOf(parts[1]);

            Disease disease = diseaseRepository.findByName(diseaseName);
            disease.setMortalityRate(rate);

            //diseaseRepository.save(disease);
        }
    }

    private void parseCSV(String csv) throws IOException {
        CSVParser csvParser = CSVParser.parse(csv, CSVFormat.EXCEL);

        Iterator<CSVRecord> records = csvParser.iterator();
        // ignore headers
        records.next();

        // read line by line
        while (records.hasNext()) {
            CSVRecord record = records.next();

            // normalize the name (remove *)
            String miRNAName = MiRNA.normalizeName(record.get(0));
            MiRNA miRNA = miRNARepository.findByName(miRNAName);

            if (miRNA == null) { // primera vez que se agrega
                miRNA = miRNARepository.save(new MiRNA(miRNAName));
            }

            String diseaseName = record.get(1).toLowerCase().trim();
            Disease disease = diseaseRepository.findByName(diseaseName);

            if (disease == null) {
                disease = diseaseRepository.save(new Disease(diseaseName));
                disease.setMortalityRate(0d);
            }

            String authorName = record.get(4).trim();
            Author author = authorRepository.findByName(authorName);

            if (author == null) {
                author = authorRepository.save(new Author(authorName));
            }

            String publicationTitle = record.get(6).trim();
            String publicationJournal = record.get(5).trim();

            Publication pub = publicationRepository
                    .findByNameAndJournal(publicationTitle, publicationJournal);

            if (pub == null) {
                pub = new Publication(publicationTitle, publicationJournal);
                pub.setAuthor(author);
                String year = record.get(7);
                pub.setYear(Integer.valueOf(year));
                pub.setDescription(record.get(9).trim());

                pub = publicationRepository.save(pub);

            }

            String methodName = record.get(8).trim();
            DiscoveryMethod method = discoveryMethodRepository
                    .findByName(methodName);

            if (method == null) {
                method = discoveryMethodRepository.save(
                        new DiscoveryMethod(methodName));
            }

            CorrelationDiscovery correlation = new CorrelationDiscovery(
                    miRNA, disease, Integer.valueOf(record.get(2)));

            correlation.setPublication(pub);
            correlation.setMethod(method);

            // save the found correlation
            correlationDiscoveryRepository.save(correlation);
        }
    }
}
