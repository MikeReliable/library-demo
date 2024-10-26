package com.mike.librarydemo.repo.controller;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
@Tag(name = "Publisher", description = "Publisher CRUD methods")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/{publisherId}")
    @Operation(summary = "Get publisher by Id")
    @Cacheable(value = "publisher", key = "#publisherId")
    public ResponseEntity<PublisherDto> getPublisher(@PathVariable Long publisherId) {
        return new ResponseEntity<>(publisherService.getPublisher(publisherId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create publisher")
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody @Valid PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.createPublisher(publisherDto), HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    @Operation(summary = "Update publisher by Id")
    @CachePut(value = "publisher", key = "#publisherId")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long publisherId,
                                                        @RequestBody @Valid PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.updatePublisher(publisherId, publisherDto), HttpStatus.OK);
    }

    @DeleteMapping("/{publisherId}")
    @Operation(summary = "Delete publisher by Id")
    @CacheEvict(value = "publisher", key = "#publisherId")
    public ResponseEntity<String> deletePublisher(@PathVariable Long publisherId) {
        publisherService.deletePublisher(publisherId);
        return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
    }
}
