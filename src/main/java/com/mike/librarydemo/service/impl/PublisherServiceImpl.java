package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.service.PublisherService;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Override
    public PublisherDto getPublisher(Long publisherId, PublisherDto publisherDto) {
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
