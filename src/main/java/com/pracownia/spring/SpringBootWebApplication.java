package com.pracownia.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import javax.persistence.Query;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pracownia.spring.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
import java.util.*;


@SpringBootApplication
@EnableJpaRepositories("com.pracownia.spring.repositories")
@EnableSwagger2
public class SpringBootWebApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.pracownia.spring.controllers"))
                .build();
    }

    public static void main(String[] args) { SpringApplication.run(SpringBootWebApplication.class, args); }


   /* static void createEntities()
    {
        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;


        try{
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            //utworz entityManagera
            entityManager = entityManagerFactory.createEntityManager();


            Films film1 = new Films(UUID.randomUUID().toString(),"Mroczny Rycerz",2008,152);
            Films film2 = new Films(UUID.randomUUID().toString(),"Batman: Początek",2005,140);


            Studios studio1 = new Studios();
            studio1.setName(" Warner Bros. Pictures");
            studio1.setAddress("Burbank");

            Studios studio2 = new Studios();
            studio2.setName("The Walt Disney Company");
            studio2.setAddress("Burbank");

            Directors rez1 = new Directors();
            rez1.setName("Christopher");
            rez1.setSurname("Nolan");
            rez1.setSalary(100000);
            rez1.setDate(new Date(80,0,2));
            //rez1.setId(1);

            Directors rez2 = new Directors();
            rez2.setName("Stanley");
            rez2.setSurname("Kubrick");
            rez2.setSalary(200000);
            rez2.setDate(new Date(80,0,1));
            //rez2.setId(2);

            StudioOwners ws1 = new StudioOwners();
            ws1.setName("Właściciel1");
            ws1.setSurname("A");

            StudioOwners ws2 = new StudioOwners();
            ws2.setName("Właściciel2");
            ws2.setSurname("B");

            Ceos ceo1 = new Ceos();
            ceo1.setName("ceo1");
            ceo1.setSurname("c1");
            ceo1.setSalary(10000);

            Ceos ceo2 = new Ceos();
            ceo2.setName("ceo2");
            ceo2.setSurname("c2");
            ceo2.setSalary(20000);

            Set<Films> filmy = new HashSet<Films>();
            filmy.add(film1);
            filmy.add(film2);
            rez1.setFilmy(filmy);

            studio1.setFilmy(filmy);

            studio1.setStudioOwners(ws1);
            studio2.setStudioOwners(ws2);

            studio1.setCeo(ceo1);
            studio2.setCeo(ceo2);

            //rozpocznij transakcje

            entityManager.getTransaction().begin();
            entityManager.persist(film1);
            entityManager.persist(film2);
            entityManager.persist(studio1);
            entityManager.persist(studio2);
            entityManager.persist(rez1);
            entityManager.persist(rez2);
            entityManager.persist(ws1);
            entityManager.persist(ws2);
            entityManager.persist(ceo1);
            entityManager.persist(ceo2);


            List<Ceos> filmy2 = new ArrayList<Ceos>();
            filmy2.add(ceo1);
            filmy2.add(ceo2);

            //Zapisywanie do xml
            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JodaModule())
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/META-INF/toXml.xml"), filmy2);

            //Zapisanie do json
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule())
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/META-INF/toJson.json"), filmy2);

            //Kwerendy
            queryToJSON(entityManager);

            entityManager.getTransaction().commit();
            System.out.println("Done");
            entityManager.close();


        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }
    }



    static List<Films> getAllFilms(EntityManager entityManager)
    {
        Query query = entityManager.createQuery("Select k from Film k");
        List<Films> result = query.getResultList();
        return result;
    }
    static List<Films> getFilmsFrom2008(EntityManager entityManager){
        Query query = entityManager.createQuery("SELECT k FROM Film k" +" WHERE k.year=2008");
        List<Films> result = query.getResultList();
        return result;
    }
    static List<Ceos> getCeoSalaryMoreThan10000(EntityManager entityManager) {

        Query query = entityManager.createQuery("SELECT k FROM Ceo k WHERE k.salary>10000");
        List<Ceos> result = query.getResultList();
        return result;
    }
    static List<Directors> getRezyserByName(EntityManager entityManager) {

        Query query = entityManager.createQuery("SELECT k FROM Rezyser k WHERE k.surname = 'Nolan'");
        List<Directors> result = query.getResultList();
        return result;
    }


    static void queryToJSON(EntityManager entityManager) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        List<Films> qr1 = getAllFilms(entityManager);
        mapper.registerModule(new JodaModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/query/allFilms.json"), qr1);

        List<Films> qr2 = getFilmsFrom2008(entityManager);
        mapper.registerModule(new JodaModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/query/filmsFrom2008.json"), qr2);

        List<Ceos> qr3 = getCeoSalaryMoreThan10000(entityManager);
        mapper.registerModule(new JodaModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/query/CeoSalaryMoreThan10000.json"), qr3);

        List<Directors> qr4 = getRezyserByName(entityManager);
        mapper.registerModule(new JodaModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writeValue(new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/query/RezyserByName.json"), qr4);

        //piąte strniecowane

    }

    /*static void zPlikuDoBazy() throws IOException
    {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("From XML [a] From JSON [b]");
        Scanner loader2 = new Scanner(System.in);
        String file = loader2.nextLine();

        if(file.equals("a"))
        {
            ObjectMapper xmlMapper = new XmlMapper();
            List<com.pracownia.spring.entities.Film> deserializedFilm = xmlMapper.registerModule(new JodaModule()).enable(SerializationFeature.INDENT_OUTPUT).readValue(
                    new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/META-INF/toXml.xml"
                    ), new TypeReference<List<com.pracownia.spring.entities.Film>>() { } );


            for(com.pracownia.spring.entities.Film emp: deserializedFilm)
            {
                entityManager.persist(emp);
            }
        }
        else if(file.equals("b"))
        {
            ObjectMapper mapper = new ObjectMapper();
            List<com.pracownia.spring.entities.Film> deserializedFilm = mapper.registerModule(new JodaModule()).enable(SerializationFeature.INDENT_OUTPUT).readValue(
                    new File("C:/Users/Piotr/IdeaProjects/ProjektPracDrugi/src/main/resources/META-INF/toJson.json"
                    ), new TypeReference<List<com.pracownia.spring.entities.Film>>() { } );

            for (com.pracownia.spring.entities.Film emp : deserializedFilm) {
                entityManager.persist(emp);
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }*/
}



