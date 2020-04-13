package pro.sisit.unit9;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sisit.unit9.data.*;
import pro.sisit.unit9.entity.*;
import pro.sisit.unit9.service.BookService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataApplicationTests {
	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorOfBookRepository authorOfBookRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PurshedBookRepository purshedBookRepository;

	@Before
	public void init() {
		Book book = new Book();
		book.setDescription("Увлекательные приключения Тома Сойера");
		book.setTitle("Приключения Тома Сойера");
		book.setYear(1876);
		bookRepository.save(book);

		Book book2 = new Book();
		book2.setTitle("Михаил Строгов");
		book2.setYear(1876);
		bookRepository.save(book2);

		Book book3 = new Book();
		book3.setTitle("Приключения Гекльберри Финна");
		book3.setYear(1884);
		bookRepository.save(book3);

		Author author = new Author();
		author.setFirstname("Марк");
		author.setLastname("Твен");
		authorRepository.save(author);

		AuthorOfBook authorOfBook = new AuthorOfBook();
		authorOfBook.setAuthor(author);
		authorOfBook.setBook(book);
		authorOfBookRepository.save(authorOfBook);

		AuthorOfBook authorOfBook2 = new AuthorOfBook();
		authorOfBook2.setAuthor(author);
		authorOfBook2.setBook(book3);
		authorOfBookRepository.save(authorOfBook2);

		Author author2 = new Author();
		author2.setFirstname("Жюль");
		author2.setLastname("Верн");
		authorRepository.save(author2);

		AuthorOfBook authorOfBook3 = new AuthorOfBook();
		authorOfBook3.setAuthor(author2);
		authorOfBook3.setBook(book2);
		authorOfBookRepository.save(authorOfBook3);

		Book book4 = new Book();
		book4.setTitle("Буратино");
		book4.setYear(1936);
		bookRepository.save(book4);

		Author author3 = new Author();
		author3.setFirstname("Алексей");
		author3.setLastname("Толстой");
		authorRepository.save(author3);

		AuthorOfBook authorOfBook4 = new AuthorOfBook();
		authorOfBook4.setAuthor(author3);
		authorOfBook4.setBook(book4);
		authorOfBookRepository.save(authorOfBook4);

		Customer customer1=new Customer();
		customer1.setName("Алешка");
		customer1.setAddress("ул.Пушкина 23");
		customerRepository.save(customer1);

		Customer customer2=new Customer();
		customer2.setName("Иван");
		customer2.setAddress("ул.Мира 213");
		customerRepository.save(customer2);

		Customer customer3=new Customer();
		customer3.setName("Мария");
		customer3.setAddress("ул.Ленина 77");
		customerRepository.save(customer3);

		PurshedBook purshedBook1=new PurshedBook();
		purshedBook1.setBook(book2);
		purshedBook1.setCustomer(customer1);
		purshedBook1.setPrice(new BigDecimal(135));
		purshedBookRepository.save(purshedBook1);

		PurshedBook purshedBook2=new PurshedBook();
		purshedBook2.setBook(book2);
		purshedBook2.setCustomer(customer2);
		purshedBook2.setPrice(new BigDecimal(135));
		purshedBookRepository.save(purshedBook2);

		PurshedBook purshedBook3=new PurshedBook();
		purshedBook3.setBook(book);
		purshedBook3.setCustomer(customer3);
		purshedBook3.setPrice(new BigDecimal(220));
		purshedBookRepository.save(purshedBook3);

	}
	@Test
	public void testBookServicePayBook(){
		Book book4 = new Book();
		book4.setTitle("Буратино");
		book4.setYear(1936);
		bookRepository.save(book4);

		Customer customer1=new Customer();
		customer1.setName("Алешка");
		customer1.setAddress("ул.Пушкина 23");
		customerRepository.save(customer1);

		Customer customer2=new Customer();
		customer2.setName("Eшка");
		customer2.setAddress("ул.Пушкина 2");
		customerRepository.save(customer2);

		bookService.PayBook(book4,customer1,new BigDecimal(137));
		bookService.PayBook(book4,customer1,new BigDecimal(1000));
		bookService.PayBook(book4,customer2,new BigDecimal(200));
		assertEquals(2,purshedBookRepository.findByPrice(new BigDecimal(135)).size());
		assertEquals(1,purshedBookRepository.findByPrice(new BigDecimal(220)).size());
		assertEquals(0,purshedBookRepository.findByPrice(new BigDecimal(221)).size());
		assertEquals(1,purshedBookRepository.findByPrice(new BigDecimal(137)).size());
		System.out.println("**********************************");
		System.out.println(purshedBookRepository.getSumPrice("Буратино"));
		System.out.println("**********************************");
		System.out.println(bookService.SaleAmountBook("Буратино"));
		System.out.println(purshedBookRepository.getTotalPriceCustomer("Алешка"));
	}


	@Test
	public void testSaveCustomer(){
		boolean founded = false;
		for (Customer iteratedCustomer : customerRepository.findAll()) {
			if (iteratedCustomer.getName().equals("Алешка")
					&& iteratedCustomer.getId() > 0) {
				founded = true;
				break;
			}
		}
		assertTrue(founded);
	}

	@Test
	public void testSave() {
		boolean founded = false;
		for (Book iteratedBook : bookRepository.findAll()) {
			if (iteratedBook.getTitle().equals("Буратино")
					&& iteratedBook.getId() > 0) {
				founded = true;
				break;
			}
		}
		assertTrue(founded);
	}

	@Test
	public void testFindByYear() {
		assertEquals(2, bookRepository.findByYear(1876).size());
		assertEquals(1, bookRepository.findByYear(1884).size());
		assertEquals(0, bookRepository.findByYear(2000).size());

	}

	@Test
	public void testFindAtPage() {
		PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.ASC, "title");
		assertTrue(bookRepository.findAll(pageRequest)
				.get().allMatch(book -> book.getTitle().equals("Михаил Строгов")));
	}

	@Test
	public void testFindSame() {
		Book book = new Book();
		book.setYear(1876);

		assertEquals(2, bookRepository.findAll(Example.of(book)).size());
	}

	@Test
	public void testFindInRange() {
		assertEquals(3, bookRepository.findAll(
				BookSpecifications.byYearRange(1800, 1900)).size());
		assertEquals(0, bookRepository.findAll(
				BookSpecifications.byYearRange(2000, 2010)).size());
	}

	@Test
	public void testFindByAuthorLastname() {
		assertTrue(bookRepository.findByAuthor("Верн")
				.stream().allMatch(book -> book.getTitle().equals("Михаил Строгов")));
	}

	@Test
	public void testComplexQueryMethod() {

	assertEquals(4, bookRepository.complexQueryMethod().size());

	}

}
