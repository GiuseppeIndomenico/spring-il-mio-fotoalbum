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

		Photo p1 = new Photo("title1", "desc1",
				"https://i0.wp.com/clicmytrip.it/wp-content/uploads/2020/12/Spedizione-Felicita.jpg?resize=800%2C600&ssl=1",
				false, admin1, c2, c3);
		Photo p2 = new Photo("title2", "desc2",
				"https://static.vecteezy.com/ti/foto-gratuito/p2/22251267-subacqueo-mare-scogliera-paesaggio-con-tropicale-pesce-corallo-rosso-stelle-e-spugne-cartone-animato-sfondo-blu-laguna-mondo-o-acquario-con-colorato-animali-e-sole-travi-ai-foto.jpg",
				false, user1, c4, c5);
		Photo p3 = new Photo("title3", "desc3",
				"https://lh3.googleusercontent.com/YJs0_RwcN_ly7A_hELqDFUBOMs4vArGtiLpcdT0qdDvpm6gfoRsu_RbToAMyo8G6P_NS8DcICKB2AvfkuYxBdX12QxYJu3A4nkap0j8i-_yh=s0-c",
				true, bigBoss, c7, c1, c2);
		Photo p4 = new Photo("title4", "desc4",
				"https://photobypawelp.files.wordpress.com/2017/11/ppp0834-copy-1200-72.jpg?w=584&h=390", true, bigBoss,
				c5, c8);
		Photo p5 = new Photo("title35", "desc5",
				"https://www.viaggioinegitto.com/images/packages/1686129768packages2.webp",
				true, admin1, c2);
		Photo p6 = new Photo("title64", "desc6", "https://cdn.skuola.net/news_foto/2018/bellezza-natura.jpg", true,
				user1, c1);
		Photo p7 = new Photo("title72", "desc7",
						"https://www.intermundial.it/blog/wp-content/uploads/2015/11/Viaggio-a-Cuba.jpg",
				false, bigBoss, c5, c2, c3);
		Photo p8 = new Photo("title81", "desc8",
				"https://ae01.alicdn.com/kf/S8fd201a865f34417884df82141a53bef1/Sexy-Soccer-Uniform-Sex-Underwear-Set-donna-Cosplay-Cheerleading-Schoolgirl-sport-porno-Lingerie-erotica-calcio-Baby.jpg",
				true, user1, c6, c8);

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
