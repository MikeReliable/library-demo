package com.mike.librarydemo.controller;

import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.createPublisher(publisherDto), HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long publisherId,
                                                        @RequestBody PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.updatePublisher(publisherId, publisherDto), HttpStatus.OK);
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long publisherId,
                                                  @RequestBody PublisherDto publisherDto) {
        publisherService.deletePublisher(publisherId, publisherDto);
        return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
    }
}
