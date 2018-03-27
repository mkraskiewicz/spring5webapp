package com.maciej.spring5webapp.bootstrap;

import com.maciej.spring5webapp.model.Author;
import com.maciej.spring5webapp.model.Book;
import com.maciej.spring5webapp.model.Publisher;
import com.maciej.spring5webapp.repositories.AuthorRepository;
import com.maciej.spring5webapp.repositories.BookRepository;
import com.maciej.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        onInit();
    }

    private void onInit(){

        Publisher publisher = new Publisher();
        publisher.setName("Houghton Mifflin Harcourt");
        publisherRepository.save(publisher);

        Author tolkien = new Author("J. R. R.", "Tolkien");
        Book fellowship = new Book("Fellowship of The Ring","0618346252",publisher);
        tolkien.getBooks().add(fellowship);
        fellowship.getAuthors().add(tolkien);

        authorRepository.save(tolkien);
        bookRepository.save(fellowship);

        Author andrew = new Author("Andrzej.", "Sapkowski");
        Book witcher = new Book("Wiedzmin. Ostatnie zyczenie","978-83-7578-028-4", publisher);
        andrew.getBooks().add(witcher);
        witcher.getAuthors().add(andrew);

        authorRepository.save(andrew);
        bookRepository.save(witcher);

    }
}
