package com.yoratyo.endOfDay.config;

import com.yoratyo.endOfDay.model.Eod;
import com.yoratyo.endOfDay.model.EodResult;
import com.yoratyo.endOfDay.processor.EodProcess1;
import com.yoratyo.endOfDay.processor.EodProcess2a;
import com.yoratyo.endOfDay.processor.EodProcess2b;
import com.yoratyo.endOfDay.processor.EodProcess3;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableBatchProcessing
public class EndOfDayBatchConfiguration {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    private Resource beforeEodResource = new FileSystemResource("data/Before_Eod.csv");
    private Resource afterEodResource = new FileSystemResource("data/After_Eod.csv");

    @Bean
    public FlatFileItemReader<Eod> readCsvBeforeEod(){
        FlatFileItemReader<Eod> reader = new FlatFileItemReader<>();
        reader.setResource(beforeEodResource);
        reader.setLineMapper(new DefaultLineMapper<Eod>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(Eod.fields());
                        setDelimiter(";");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Eod>() {
                    {
                        setTargetType(Eod.class);
                    }
                });
            }
        });
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public FlatFileItemReader<EodResult> readCsvAfterEod(){
        FlatFileItemReader<EodResult> reader = new FlatFileItemReader<>();
        reader.setResource(afterEodResource);
        reader.setLineMapper(new DefaultLineMapper<EodResult>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(EodResult.fields());
                        setDelimiter(";");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<EodResult>() {
                    {
                        setTargetType(EodResult.class);
                    }
                });
            }
        });
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public EodProcess1 processAverageBalance() {
        return new EodProcess1();
    }

    @Bean
    public EodProcess2a processBenefit1() {
        return new EodProcess2a();
    }

    @Bean
    public EodProcess2b processBenefit2() {
        return new EodProcess2b();
    }

    @Bean
    public EodProcess3 processBonus() {
        return new EodProcess3();
    }

    @Bean
    public FlatFileItemWriter<EodResult> writerResultEod(){
        FlatFileItemWriter<EodResult> writer = new FlatFileItemWriter<>();
        writer.setResource(afterEodResource);
        DelimitedLineAggregator<EodResult> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<EodResult> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(EodResult.fields());
        aggregator.setDelimiter(";");
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);
        writer.setHeaderCallback(writer1 -> writer1.write("id;Nama;Age;Balanced;No 2b Thread-No;No 3 Thread-No;Previous Balanced;Average Balanced;No 1 Thread-No;Free Transfer;No 2a Thread-No"));
        return writer;
    }

    @Bean
    public Step stepCalcAverageBalance() {
        return stepBuilderFactory.get("stepCalcAverageBalance").<Eod, EodResult>chunk(20)
                .reader(readCsvBeforeEod())
                .processor(processAverageBalance())
                .writer(writerResultEod())
                .taskExecutor(taskExecutor(5))
                .build();
    }

    @Bean
    public Step stepCalcBenefit1() {
        return stepBuilderFactory.get("stepCalcBenefit1").<EodResult, EodResult>chunk(20)
                .reader(readCsvAfterEod())
                .processor(processBenefit1())
                .writer(writerResultEod())
                .taskExecutor(taskExecutor(5))
                .build();
    }

    @Bean
    public Step stepCalcBenefit2() {
        return stepBuilderFactory.get("stepCalcBenefit2").<EodResult, EodResult>chunk(20)
                .reader(readCsvAfterEod())
                .processor(processBenefit2())
                .writer(writerResultEod())
                .taskExecutor(taskExecutor(5))
                .build();
    }

    @Bean
    public Step stepCalcBonus() {
        return stepBuilderFactory.get("stepCalcBonus").<EodResult, EodResult>chunk(25)
                .reader(readCsvAfterEod())
                .processor(processBonus())
                .writer(writerResultEod())
                .taskExecutor(taskExecutor(8))
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(int numOfThread){
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(numOfThread);
        executor.setMaxPoolSize(numOfThread);
        return executor;
    }

    @Bean
    public Job processEodJob() {
        return jobBuilderFactory.get("processEodJob").
                incrementer(new RunIdIncrementer()).
                start(stepCalcAverageBalance()).
                next(stepCalcBenefit1()).
                next(stepCalcBenefit2()).
                next(stepCalcBonus()).
                build();
    }
}
