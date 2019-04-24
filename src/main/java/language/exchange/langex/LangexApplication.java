package language.exchange.langex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LangexApplication {

    public static void main(String[] args) {

        SpringApplication.run(LangexApplication.class, args);

        /*for (int i = 1; i < 10; i++) {
            File file = new File(i + ".txt");
            if (file.delete());
        }*/

        File file = new File("undefined");
        if (file.delete());

    }

}