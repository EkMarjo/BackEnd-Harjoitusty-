package harjoitustyo.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.backend.model.AppUser;
import harjoitustyo.backend.model.AppUserRepository;
import harjoitustyo.backend.model.Genre;
import harjoitustyo.backend.model.GenreRepository;
import harjoitustyo.backend.model.Practice;
import harjoitustyo.backend.model.PracticeRepository;

//Käynnistää sovelluksen ja määrittelee esimerkki dataa sovellukselle
@SpringBootApplication
public class BackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner practiceDemo (PracticeRepository repository, GenreRepository gRepository, AppUserRepository aRepository){
		return(args) -> {
			log.info("Tallenna harjoitus");
			Genre g1 = new Genre("Pilates");
			Genre g2= new Genre("Jooga");
			Genre g3 = new Genre("Venyttely");

			gRepository.save(g1);
			gRepository.save(g2);
			gRepository.save(g3);

			repository.save(new Practice("Suoritus ei sujunut kevyesti", 45, 98, 14022024, g2 ));
			repository.save(new Practice("Tehokas harjoitus", 30, 150, 17022024,g1));

			AppUser user1= new AppUser("jumppari","$2a$10$fRkHI70Z15xfTSMQvkql1OJGyVLgKyxjtnxuEZuTUi8iNGVuzTRTq", "USER");
			AppUser user2= new AppUser("hallitsija", "$2a$10$4L7vOdc2hpfjUQgNtf9.rOd75k.W.Y9n04lEt5SD5jfIVQfx62O/u", "ADMIN");
			AppUser user3= new AppUser("joogaaja","$2a$10$r4ZXPWDi5Pc7ZGT/rRz8reGV5WOIU4IlQ9AlisnpiY2plG8OZtsre", "USER");

			aRepository.save(user1);
			aRepository.save(user2);
			aRepository.save(user3);

			log.info("Näytä kaikki treenit");
			for(Practice practice: repository.findAll()){
				log.info(practice.toString());
			}
		};
	}

}
