package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.entity.Publisher;
import com.mike.librarydemo.repo.PublisherRepo;
import com.mike.librarydemo.service.PublisherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepo publisherRepo;

    @Override
    public PublisherDto getPublisher(Long publisherId) {
        Publisher publisher = publisherRepo.findById(publisherId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Publisher with Id %s not found", publisherId)));
        return null;
    }

    @Override
    public PublisherDto createPublisher(PublisherDto publisherDto) {
        return null;
    }

    @Override
    public PublisherDto updatePublisher(Long publisherId, PublisherDto publisherDto) {
        return null;
    }

    @Override
    public void deletePublisher(Long publisherId, PublisherDto publisherDto) {
    }
}
