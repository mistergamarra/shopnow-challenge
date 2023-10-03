package com.mistergamarra.shopnow.warehousesvc.conf;

import org.dozer.DozerBeanMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableFeignClients(basePackages = {"com.mistergamarra.shopnow.warehousesvc.client"})
public class Config {

    @Bean
    public DozerBeanMapper dozerBeanMapper(){

        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
