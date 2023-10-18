package org.java.app;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	@Autowired
	PhotoServ photoServ;

	@Autowired
	CategoryServ categoryServ;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category c1= new Category("Naturalistica");
		Category c2= new Category("Viaggio");
		Category c3= new Category("Documentaria");
		Category c4= new Category("Subacquea");
		Category c5= new Category("Paesaggistica");
		Category c6= new Category("Sportiva");
		Category c7= new Category("Macro");
		Category c8= new Category("Erotica");
		
		categoryServ.save(c1);
		categoryServ.save(c2);
		categoryServ.save(c3);
		categoryServ.save(c4);
		categoryServ.save(c5);
		categoryServ.save(c6);
		categoryServ.save(c7);
		categoryServ.save(c8);

		System.out.println("Category Ok");
		
		Photo p1= new Photo("title1", "desc1", "url1", true, c2);
		Photo p2= new Photo("title2", "desc2", "url2", false,c4);
		Photo p3= new Photo("title3", "desc3", "url3", false, c7);
		Photo p4= new Photo("title4", "desc4", "url4", true,c3);
		
		photoServ.save(p1);
		photoServ.save(p2);
		photoServ.save(p3);
		photoServ.save(p4);
		
		System.out.println("Photo Ok");
		
		
	}

}
