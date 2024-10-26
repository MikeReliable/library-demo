package com.mike.librarydemo.controller;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> getPublisher(@PathVariable Long publisherId) {
        return new ResponseEntity<>(publisherService.getPublisher(publisherId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody @Valid PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.createPublisher(publisherDto), HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long publisherId,
                                                        @RequestBody @Valid PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.updatePublisher(publisherId, publisherDto), HttpStatus.OK);
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long publisherId) {
        publisherService.deletePublisher(publisherId);
        return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
    }
}
