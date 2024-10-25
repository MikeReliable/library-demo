package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.PublisherDto;

public interface PublisherService {

    PublisherDto getPublisher(Long publisherId);

    PublisherDto createPublisher(PublisherDto publisherDto);

    PublisherDto updatePublisher(Long publisherId, PublisherDto publisherDto);

    void deletePublisher(Long publisherId, PublisherDto publisherDto);
}
