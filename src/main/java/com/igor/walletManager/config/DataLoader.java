package com.igor.walletManager.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.igor.walletManager.entity.Category;
import com.igor.walletManager.entity.Transaction;
import com.igor.walletManager.entity.User;
import com.igor.walletManager.entity.enums.TransactionType;
import com.igor.walletManager.entity.enums.UserRole;
import com.igor.walletManager.repositories.CategoryRepository;
import com.igor.walletManager.repositories.TransactionRepository;
import com.igor.walletManager.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final TransactionRepository transactionRepository;
	private final PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {

		 User u1 = new User(null, "User 1", "user1@mail.com", encoder.encode("123") , null, UserRole.ADMIN, null);
	        User u2 = new User(null, "User 2", "user2@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u3 = new User(null, "User 3", "user3@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u4 = new User(null, "User 4", "user4@mail.com", encoder.encode("123"), null, UserRole.ADMIN, null);
	        User u5 = new User(null, "User 5", "user5@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u6 = new User(null, "User 6", "user6@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u7 = new User(null, "User 7", "user7@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u8 = new User(null, "User 8", "user8@mail.com", encoder.encode("123"), null, UserRole.ADMIN, null);
	        User u9 = new User(null, "User 9", "user9@mail.com", encoder.encode("123"), null, UserRole.USER, null);
	        User u10 = new User(null, "User 10", "user10@mail.com", encoder.encode("123"), null, UserRole.ADMIN, null);

	        userRepository.saveAll(List.of(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10));
	        
	        Category c1 = new Category(null,"Food", u1);
	        Category c2 = new Category(null,"Transport", u2);
	        Category c3 = new Category(null,"Health", u3);
	        Category c4 = new Category(null,"Education", u4);
	        Category c5 = new Category(null,"Entertainment", u5);
	        Category c6 = new Category(null,"Bills", u6);
	        Category c7 = new Category(null,"Shopping", u7);
	        Category c8 = new Category(null,"Salary", u8);
	        Category c9 = new Category(null,"Investments", u9);
	        Category c10 = new Category(null,"Others", u10);


	       categoryRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));
	        
	       Transaction t1 = new Transaction(null, BigDecimal.valueOf(100), "Market U1", LocalDateTime.now(), TransactionType.EXPENSE, null, c1, u1);
	       Transaction t2 = new Transaction(null, BigDecimal.valueOf(500), "Salary U1", LocalDateTime.now(), TransactionType.INCOME, null, c1, u1);

	       Transaction t3 = new Transaction(null, BigDecimal.valueOf(200), "Uber U2", LocalDateTime.now(), TransactionType.EXPENSE, null, c2, u2);
	       Transaction t4 = new Transaction(null, BigDecimal.valueOf(600), "Salary U2", LocalDateTime.now(), TransactionType.INCOME, null, c2, u2);

	       Transaction t5 = new Transaction(null, BigDecimal.valueOf(300), "Doctor U3", LocalDateTime.now(), TransactionType.EXPENSE, null, c3, u3);
	       Transaction t6 = new Transaction(null, BigDecimal.valueOf(700), "Salary U3", LocalDateTime.now(), TransactionType.INCOME, null, c3, u3);

	       Transaction t7 = new Transaction(null, BigDecimal.valueOf(400), "Course U4", LocalDateTime.now(), TransactionType.EXPENSE, null, c4, u4);
	       Transaction t8 = new Transaction(null, BigDecimal.valueOf(800), "Salary U4", LocalDateTime.now(), TransactionType.INCOME, null, c4, u4);

	       Transaction t9 = new Transaction(null, BigDecimal.valueOf(500), "Cinema U5", LocalDateTime.now(), TransactionType.EXPENSE, null, c5, u5);
	       Transaction t10 = new Transaction(null, BigDecimal.valueOf(900), "Salary U5", LocalDateTime.now(), TransactionType.INCOME, null, c5, u5);

	       Transaction t11 = new Transaction(null, BigDecimal.valueOf(600), "Rent U6", LocalDateTime.now(), TransactionType.EXPENSE, null, c6, u6);
	       Transaction t12 = new Transaction(null, BigDecimal.valueOf(1000), "Salary U6", LocalDateTime.now(), TransactionType.INCOME, null, c6, u6);

	       Transaction t13 = new Transaction(null, BigDecimal.valueOf(700), "Clothes U7", LocalDateTime.now(), TransactionType.EXPENSE, null, c7, u7);
	       Transaction t14 = new Transaction(null, BigDecimal.valueOf(1100), "Salary U7", LocalDateTime.now(), TransactionType.INCOME, null, c7, u7);

	       Transaction t15 = new Transaction(null, BigDecimal.valueOf(800), "Food U8", LocalDateTime.now(), TransactionType.EXPENSE, null, c8, u8);
	       Transaction t16 = new Transaction(null, BigDecimal.valueOf(1200), "Salary U8", LocalDateTime.now(), TransactionType.INCOME, null, c8, u8);

	       Transaction t17 = new Transaction(null, BigDecimal.valueOf(900), "Uber U9", LocalDateTime.now(), TransactionType.EXPENSE, null, c9, u9);
	       Transaction t18 = new Transaction(null, BigDecimal.valueOf(1300), "Salary U9", LocalDateTime.now(), TransactionType.INCOME, null, c9, u9);

	       Transaction t19 = new Transaction(null, BigDecimal.valueOf(1000), "Market U10", LocalDateTime.now(), TransactionType.EXPENSE, null, c10, u10);
	       Transaction t20 = new Transaction(null, BigDecimal.valueOf(1400), "Salary U10", LocalDateTime.now(), TransactionType.INCOME, null, c10, u10);

	        transactionRepository.saveAll(List.of(
	                t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,
	                t11,t12,t13,t14,t15,t16,t17,t18,t19,t20
	        ));
	       

	}

}
