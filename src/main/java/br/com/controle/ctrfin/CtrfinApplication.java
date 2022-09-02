package br.com.controle.ctrfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CtrfinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtrfinApplication.class, args);
	}

}
