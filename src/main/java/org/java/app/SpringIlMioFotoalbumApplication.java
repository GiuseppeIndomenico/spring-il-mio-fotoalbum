package org.java.app;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Message;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.MessageServ;
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

	@Autowired
	private MessageServ messageServ;

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
				"https://img.freepik.com/free-photo/selective-focus-miniature-tourist-compass-map-with-plastic-toy-airplane-abstract-background-travel-concept_1423-180.jpg?size=626&ext=jpg&ga=GA1.1.1788068356.1707091200&semt=sph", true, admin1, c2);
		Photo p6 = new Photo("title64", "desc6", "https://cdn.skuola.net/news_foto/2018/bellezza-natura.jpg", true,
				user1, c1);
		Photo p7 = new Photo("title72", "desc7",
				"https://www.intermundial.it/blog/wp-content/uploads/2015/11/Viaggio-a-Cuba.jpg", false, bigBoss, c5,
				c2, c3);
		Photo p8 = new Photo("title81", "desc8",
				"https://i.ebayimg.com/images/g/KekAAOSw~u5h8clF/s-l1200.webp",
				true, user1, c6, c8);
		Photo p9 = new Photo("title035", "desc5",
				"https://gazzettadellaspezia.com/media/k2/items/cache/a9fef019aaef6fa92603820bb92d7cb1_L.jpg", true,
				user1, c2, c4);
		Photo p10 = new Photo("title335", "desc25",
				"https://us.123rf.com/450wm/last19/last191809/last19180900613/108287865-amante-donna-e-uomo-asiatico-viaggio-natura-viaggia-rilassati-parco-naturale-della-montagna.jpg?ver=6",
				true, admin1, c2, c1);
		Photo p11 = new Photo("title355", "desc53",
				"https://us.123rf.com/450wm/balinature/balinature2205/balinature220500019/186546576-la-donna-si-tuffa-nel-mare-blu-apnea-e-bella-signora-nell-oceano.jpg?ver=6",
				true, user1, c4, c8);
		Photo p12 = new Photo("title359", "desc45",
				"https://img.freepik.com/free-photo/traveller-explores-rugged-landscape-iceland_346278-307.jpg?size=626&ext=jpg&ga=GA1.1.1413502914.1697414400&semt=ais",
				true, bigBoss, c2, c5);

		photoServ.save(p1);
		photoServ.save(p2);
		photoServ.save(p3);
		photoServ.save(p4);
		photoServ.save(p5);
		photoServ.save(p6);
		photoServ.save(p7);
		photoServ.save(p8);
		photoServ.save(p9);
		photoServ.save(p10);
		photoServ.save(p11);
		photoServ.save(p12);
		System.out.println("Photo Ok");

		Message m1 = new Message(admin1, "email", "sei mitico!");
		Message m2 = new Message(bigBoss, "email", "wow!");
		Message m3 = new Message(bigBoss, "email", "belle foto!");
		Message m4 = new Message(admin1, "email", "ok boomer!");

		messageServ.save(m1);
		messageServ.save(m2);
		messageServ.save(m3);
		messageServ.save(m4);

		System.out.println("message ok!");
	}

}
