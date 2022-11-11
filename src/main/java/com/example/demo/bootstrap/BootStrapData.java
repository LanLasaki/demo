package com.example.demo.bootstrap;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher( "BigBooks", "123 Hope street", "Atlanta", "Georgia", 30133L);
        publisherRepository.save(publisher);

        Author eric =  new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2RR Development with EJB","478654398", publisher);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);


        System.out.println("The number of publishers is: " + publisherRepository.count());

        System.out.println("Publishers number of books: " + publisher.getBooks().size());



    }
}
