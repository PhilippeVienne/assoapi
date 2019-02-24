package com.philippevienne.assoapi;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@EnableBatchProcessing
@SpringBootApplication
public class AssoAPIApplication {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public static void main(String[] args) {
        SpringApplication.run(AssoAPIApplication.class, args);
    }

    @Bean
    public FlatFileItemReader < Association > csvReader() {
        FlatFileItemReader< Association > reader = new FlatFileItemReader < Association > ();
        reader.setResource(new FileSystemResource("/home/philippe/Downloads/little.csv"));
        reader.setLineMapper(new DefaultLineMapper< Association >() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("id", "id_ex", "siret", "rup_mi", "gestion", "date_creat", "date_decla", "date_publi", "date_disso", "nature", "groupement", "titre", "titre_court", "objet", "objet_social1", "objet_social2", "adrs_complement", "adrs_numvoie", "adrs_repetition", "adrs_typevoie", "adrs_libvoie", "adrs_distrib", "adrs_codeinsee", "adrs_codepostal", "adrs_libcommune", "adrg_declarant", "adrg_complemid", "adrg_complemgeo", "adrg_libvoie", "adrg_distrib", "adrg_codepostal", "adrg_achemine", "adrg_pays", "dir_civilite", "siteweb", "publiweb", "observation", "position", "maj_time");
                        setDelimiter(";");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper< Association >() {
                    {
                        setConversionService(new ConfigurableConversionService() {
                            @Override
                            public boolean canConvert(Class<?> aClass, Class<?> aClass1) {
                                return false;
                            }

                            @Override
                            public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
                                return false;
                            }

                            @Override
                            public <T> T convert(Object o, Class<T> aClass) {
                                return null;
                            }

                            @Override
                            public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
                                return null;
                            }

                            @Override
                            public void addConverter(Converter<?, ?> converter) {

                            }

                            @Override
                            public <S, T> void addConverter(Class<S> aClass, Class<T> aClass1, Converter<? super S, ? extends T> converter) {

                            }

                            @Override
                            public void addConverter(GenericConverter genericConverter) {

                            }

                            @Override
                            public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {

                            }

                            @Override
                            public void removeConvertible(Class<?> aClass, Class<?> aClass1) {

                            }
                        });
                        setTargetType(Association.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    ItemProcessor<Association, Association> csvProcessor() {
        return new AssociationProcessor();
    }

    @Bean
    public ItemWriter<Association> csvWriter(AssociationRepository repository) {
        return repository::saveAll;
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(ItemWriter<Association> writer, ItemProcessor<Association, Association> csvProcessor, FlatFileItemReader < Association > csvReader) {
        return stepBuilderFactory.get("step1")
                .<Association, Association> chunk(10)
                .reader(csvReader)
                .processor(csvProcessor)
                .writer(writer)
                .build();
    }

}
