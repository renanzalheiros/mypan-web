package br.com.bonbini.mypan.app.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoDBConfig {


    @Bean
    public MongoDbFactory mongo() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost:27017");
        return new SimpleMongoDbFactory(mongoClient, "mypan");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoMappingContext context) throws Exception {
        MongoDbFactory mongo = mongo();
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongo), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongo, converter);

        return mongoTemplate;

    }
}
