package ro.ubb.movieRental.client;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.movieRental.client.ui.Console;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "ro.ubb.movieRental.client.config"
        );

        Console console = context.getBean(Console.class);
        console.runMenu();

        System.out.println("bye ");
    }
}
