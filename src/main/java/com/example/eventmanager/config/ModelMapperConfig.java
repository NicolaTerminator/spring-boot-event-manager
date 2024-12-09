package com.example.eventmanager.config;

import com.example.eventmanager.dto.EventDto;
import com.example.eventmanager.entity.Event;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(eventMapping);


        modelMapper.addConverter(eventConverter);

        return modelMapper;
    }

    PropertyMap<Event, EventDto> eventMapping = new PropertyMap<Event, EventDto>() {
        protected void configure() {
        }
    };

    Converter<String, String> eventConverter = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> context) {
            return context.getSource() == null ? "" : context.getSource().trim();
        }
    };
}
