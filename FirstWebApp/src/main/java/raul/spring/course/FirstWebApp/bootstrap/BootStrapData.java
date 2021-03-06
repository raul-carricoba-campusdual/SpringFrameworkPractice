package raul.spring.course.FirstWebApp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import raul.spring.course.FirstWebApp.domain.Author;
import raul.spring.course.FirstWebApp.domain.Book;
import raul.spring.course.FirstWebApp.domain.Publisher;
import raul.spring.course.FirstWebApp.repositories.AuthorRepository;
import raul.spring.course.FirstWebApp.repositories.BookRepository;
import raul.spring.course.FirstWebApp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository	authorRepository;
	private final BookRepository	bookRepository;
	private final PublisherRepository	publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "2342425");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		Publisher publisher = new Publisher("PublisherName", "PublisherAddressLine1", "PulisherCity", "PublisherState", "PublisherZip");
		this.publisherRepository.save(publisher);

		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);

		this.authorRepository.save(eric);
		this.bookRepository.save(ddd);
		this.publisherRepository.save(publisher);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "235364275");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);

		this.authorRepository.save(rod);
		this.bookRepository.save(noEJB);
		this.publisherRepository.save(publisher);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of books: " + this.bookRepository.count());
		System.out.println("Number of publishers: " + this.publisherRepository.count());
		System.out.println("Publisher's number of books: " + publisher.getBooks().size());

	}

}
