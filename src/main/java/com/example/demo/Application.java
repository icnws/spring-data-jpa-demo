package com.example.demo;

import com.example.demo.dto.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

//	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			repository.save(new Customer("Bauer", "Dessler"));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Customer customer : repository.findAll()) {
				System.out.println(customer.toString());
			}
			System.out.println("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			System.out.println("Customer found with findOne(1L):");
			System.out.println("--------------------------------");
			System.out.println(customer.toString());
			System.out.println("");

			// fetch customers by last name
			System.out.println("Customer found with findByLastName('Bauer'):");
			System.out.println("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				System.out.println(bauer.toString());
			}
			System.out.println("");
            // fetch customers by  like %FirstName%
			System.out.println("Customer found with findByFirstNameLike('Bau'):");
			System.out.println("--------------------------------------------");
			for (Customer bauer : repository.findByFirstNameContaining("Bau") ){
				System.out.println(bauer.toString());
			}
			System.out.println("");
            // fetch customers by  like %LastName% order by id desc
			System.out.println("Customer found with findByLastNameOrderByIdDesc('Bau'):");
			System.out.println("--------------------------------------------");
			for (Customer bauer : repository.findByLastNameOrderByIdDesc("Bau") ){
				System.out.println(bauer.toString());
			}
			System.out.println("");
            // fetch customers by  like %LastName% order by id desc
			System.out.println("Customer found with findByLastNameOrderByIdDesc('Bau'):");
			System.out.println("--------------------------------------------");
			for (Customer bauer : repository.findByLastNameContainingOrderByIdDesc("Bau") ){
				System.out.println(bauer.toString());
			}
			System.out.println("");
            // fetch customers by  findByIdBettween(3,10)
			System.out.println("Customer found with findByIdBettween(3,10):");
			System.out.println("--------------------------------------------");
			for (Customer bauer : repository.findByIdBetween(3l,10l) ){
				System.out.println(bauer.toString());
			}
			System.out.println("");
            // fetch customers by  findByIdBettween(3,10)
			System.out.println("Customer found with findByName(Bauer):");
			System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByFirstName("Bauer") ){
                System.out.println(bauer.toString());
            }
			System.out.println("");
            // fetch customers by  findByLastName
			System.out.println("Customer found with findByName(Bauer):");
			System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName("Bauer") ){
                System.out.println(bauer.toString());
            }
			System.out.println("");
            // fetch customers by  findByIdBettween(3,10)
			System.out.println("Customer found with findByName2(Bauer,Bauer):");
			System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName2("Bauer","Bauer") ){
                System.out.println(bauer.toString());
            }
			System.out.println("");
            // fetch customers by  findByLastName
            System.out.println("Customer found with findByName3(Bauer):");
            System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName3("Bauer") ){
                System.out.println(bauer.toString());
            }
            System.out.println("");
            // fetch customers by  findByLastName
            System.out.println("Customer found with findByName4(Bauer):");
            System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName4("Bauer") ){
                System.out.println(bauer.toString());
            }
            System.out.println("");
            // fetch customers by  findByLastName
            System.out.println("Customer found with findByName5(Bauer,Sort):");
            System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName5("Bauer",new Sort("firstName"))){
                System.out.println(bauer.toString());
            }
            System.out.println("");
            // fetch customers by  findByLastName
            System.out.println("Customer found with findByName5(Bauer,Sort):");
            System.out.println("--------------------------------------------");
            for (Customer bauer : repository.findByName5("Bauer",new Sort(Sort.Direction.DESC,"firstName"))){
                System.out.println(bauer.toString());
            }
            System.out.println("");
		};
	}

}
