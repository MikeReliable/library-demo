package com.mike.librarydemo.mapper;


import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.PublisherDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {

    AuthorDto toAuthorDto(Author author);

    BookDto toBookDto(Book book);

    PublisherDto toPublisherDto(Publisher publisher);

    Author toAuthor(AuthorDto authorDto);

    Book toBook(BookDto bookDto);

    Publisher toPublisher(PublisherDto publisherDto);
}
