package org.java.app;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.service.RoleService;
import org.java.app.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	@Autowired
	PhotoServ photoServ;

	@Autowired
	CategoryServ categoryServ;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category c1 = new Category("Naturalistica");
		Category c2 = new Category("Viaggio");
		Category c3 = new Category("Documentaria");
		Category c4 = new Category("Subacquea");
		Category c5 = new Category("Paesaggistica");
		Category c6 = new Category("Sportiva");
		Category c7 = new Category("Macro");
		Category c8 = new Category("Erotica");

		categoryServ.save(c1);
		categoryServ.save(c2);
		categoryServ.save(c3);
		categoryServ.save(c4);
		categoryServ.save(c5);
		categoryServ.save(c6);
		categoryServ.save(c7);
		categoryServ.save(c8);

		System.out.println("Category Ok");

		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		Role god = new Role("SuperMegaDirettoreGalattico");

		roleService.save(admin);
		roleService.save(user);
		roleService.save(god);

		final String pwsAdmin = new BCryptPasswordEncoder().encode("pass");
		final String pwsUser = new BCryptPasswordEncoder().encode("pass");
		final String pwsGod = new BCryptPasswordEncoder().encode("123");

		User admin1 = new User("PeppeAdmin", pwsAdmin, admin);
		User user1 = new User("PeppeUser", pwsUser, user);
		User bigBoss = new User("PeppeMusk", pwsGod, god);

		userService.save(admin1);
		userService.save(user1);
		userService.save(bigBoss);

		System.out.println("User Ok");

		Photo p1 = new Photo("title1", "desc1", "url1", false, admin1, c2,c3);
		Photo p2 = new Photo("title2", "desc2", "url2", false, user1, c4,c5);
		Photo p3 = new Photo("title3", "desc3", "url3", true, bigBoss, c7,c1,c2);
		Photo p4 = new Photo("title4", "desc4", "url4", true, bigBoss, c5,c8);
		Photo p5 = new Photo("title5", "desc5", "url5", true, admin1, c2);
		Photo p6 = new Photo("title6", "desc6", "url7", true, user1, c1);
		Photo p7 = new Photo("title7", "desc7", "url8", true, bigBoss, c5,c2,c3);
		Photo p8 = new Photo("title8", "desc8", "url6", false, user1, c6,c8);

		photoServ.save(p1);
		photoServ.save(p2);
		photoServ.save(p3);
		photoServ.save(p4);
		photoServ.save(p5);
		photoServ.save(p6);
		photoServ.save(p7);
		photoServ.save(p8);

		System.out.println("Photo Ok");
	}

}
