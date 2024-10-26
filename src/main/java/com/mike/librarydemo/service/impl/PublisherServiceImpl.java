package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.entity.Publisher;
import com.mike.librarydemo.mapper.EntityMapper;
import com.mike.librarydemo.repo.PublisherRepo;
import com.mike.librarydemo.service.PublisherService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepo publisherRepo;
    private EntityMapper mapper;

    @Override
    public PublisherDto getPublisher(Long publisherId) {
        Publisher publisher = publisherRepo.findById(publisherId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Publisher with Id %s not found", publisherId)));
        return mapper.toPublisherDto(publisher);
    }

    @Override
    public PublisherDto createPublisher(PublisherDto publisherDto) {
        Publisher publisher = mapper.toPublisher(publisherDto);
        if (publisherRepo.findByCountryAndAndCityAndAndEmailAndAndTelephone(publisherDto.getCountry(), publisherDto.getCity(), publisherDto.getEmail(), publisherDto.getTelephone()).isEmpty()) {
            publisherRepo.save(publisher);
        } else throw new EntityExistsException("This publisher already exists");
        return publisherDto;
    }

    @Override
    public PublisherDto updatePublisher(Long publisherId, PublisherDto publisherDto) {
        Publisher publisher;
        Publisher publisherDb = publisherRepo.findById(publisherId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Publisher with Id %s not found", publisherId)));
        if (publisherRepo.findByCountryAndAndCityAndAndEmailAndAndTelephone(publisherDto.getCountry(), publisherDto.getCity(), publisherDto.getEmail(), publisherDto.getTelephone()).isPresent()) {
            publisher = publisherRepo.findByCountryAndAndCityAndAndEmailAndAndTelephone(publisherDto.getCountry(), publisherDto.getCity(), publisherDto.getEmail(), publisherDto.getTelephone()).get();
            if (!publisherId.equals(publisher.getPublisherId())) {
                throw new EntityExistsException("This publisher already exists");
            }
        }
        publisherDb.setCountry(publisherDto.getCountry());
        publisherDb.setCity(publisherDto.getCity());
        publisherDb.setEmail(publisherDto.getEmail());
        publisherDb.setTelephone(publisherDto.getTelephone());
        publisherRepo.save(publisherDb);
        return publisherDto;
    }

    @Override
    public void deletePublisher(Long publisherId) {
        Publisher publisher = publisherRepo.findById(publisherId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Publisher with Id %s not found", publisherId)));
        if(publisher.getBooks().isEmpty()){
            publisherRepo.delete(publisher);
        }else {
            throw new EntityExistsException("This publisher is still in use");
        }

    }
}
