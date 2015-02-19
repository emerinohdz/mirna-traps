# MicroRNA trap finder

This project was developed to content for the Senior Architect position at 
miroculus. Built using the Java 6 platform, JPA for persistence, Spring
Framework, Restlet Framework for the REST webservice.

The obtained mortality rates I could find are listed in the file:

    miroculus-mrna-cli/src/main/resources/disease_mortality_rates.txt

The created DB schema (MySQL) is located under:

    miroculus-mrna-rest/src/main/resources/db/migrations/V1__initial_state.sql

# Dependencies

Only one manual dependency is needed, all other dependencies can be obtained
directly from maven central repository.

    NueveBit Persistence Utilities -> https://github.com/NueveBit/persistence-utils

# Build and installation

You'll need maven to build this project, then run

    mvn install

After building the project, install the db schema to a mysql database and
make a JNDI resource (datasource) available in your favorite Application Server 
with the name "jdbc/mirna", pointing to the created db. Also, you'll need
to make a Properties object available through JNDI under the name
"resource/mirna_config", containing a single property: 
"eclipselink.databasePlatform=org.eclipse.persistence.platform.database.MySQLPlatform"

You'll need to add the following system property to the jvm instance that runs
your app server for the production configuration to take place:

    spring.profiles.active=production
    
Once the app server is configured, deploy the war found under:
    
    miroculus-mrna-rest/target/miroculus-mrna-rest-0.1.war
