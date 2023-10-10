package com.example.aaposBookStore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.aaposBookStore.web.BookController;
import com.example.aaposBookStore.domain.Book;
import com.example.aaposBookStore.domain.Category;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AaposBookStoreApplicationTests {

	@Autowired
	private BookController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	};

	

}
