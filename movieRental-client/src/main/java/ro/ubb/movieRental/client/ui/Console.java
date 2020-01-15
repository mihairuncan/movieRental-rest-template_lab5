package ro.ubb.movieRental.client.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.movieRental.client.service.ClientServiceClient;
import ro.ubb.movieRental.client.service.MovieServiceClient;
import ro.ubb.movieRental.client.service.RentServiceClient;
import ro.ubb.movieRental.web.dto.ClientDto;
import ro.ubb.movieRental.web.dto.MovieDto;
import ro.ubb.movieRental.web.dto.RentDto;

import java.util.Scanner;

@Component
public class Console {
    @Autowired
    private ClientServiceClient clientService;

    @Autowired
    private MovieServiceClient movieService;

    @Autowired
    private RentServiceClient rentService;

    public void runMenu() {
        printMenu();
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        while (!option.equals("x")) {
            switch (option) {
                case "1":
                    printAddSubmenu();
                    option = sc.nextLine();
                    while (!option.equals("x")) {
                        switch (option) {
                            case "1":
                                addClient();
                                break;
                            case "2":
                                addMovie();
                                break;
                            case "3":
                                addRent();
                                break;
                            default:
                                System.out.println("Option not available");
                        }
                        printAddSubmenu();
                        option = sc.nextLine();
                    }
                    break;

                case "2":
                    printUpdateSubmenu();
                    option = sc.nextLine();
                    while (!option.equals("x")) {
                        switch (option) {
                            case "1":
                                updateClient();
                                break;
                            case "2":
                                updateMovie();
                                break;
                            case "3":
                                updateRent();
                                break;
                            default:
                                System.out.println("Option not available");
                        }
                        printUpdateSubmenu();
                        option = sc.nextLine();
                    }
                    break;

                case "3":
                    printRemoveSubmenu();
                    option = sc.nextLine();
                    while (!option.equals("x")) {
                        switch (option) {
                            case "1":
                                removeClient();
                                break;
                            case "2":
                                removeMovie();
                                break;
                            case "3":
                                removeRent();
                                break;
                            default:
                                System.out.println("Option not available");
                        }
                        printRemoveSubmenu();
                        option = sc.nextLine();
                    }
                    break;

                case "4":
                    printShowSubmenu();
                    option = sc.nextLine();
                    while (!option.equals("x")) {
                        switch (option) {
                            case "1":
                                showClients();
                                break;
                            case "2":
                                showMovies();
                                break;
                            case "3":
                                showRents();
                                break;
                            default:
                                System.out.println("Option not available");
                        }
                        printShowSubmenu();
                        option = sc.nextLine();
                    }
                    break;

                default:
                    System.out.println("Option not available");
            }
            printMenu();
            option = sc.nextLine();
        }
    }

    /**
     * Add a client with the fields given by user
     */
    private void addClient() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ADD CLIENT");

        System.out.println("First name: ");
        String fname = sc.nextLine();

        System.out.println("Last name: ");
        String lname = sc.nextLine();

        System.out.println("Date of birth: ");
        String dob = sc.nextLine();

        System.out.println("CNP: ");
        String cnp = sc.nextLine();

        ClientDto clientDto = new ClientDto(fname, lname, dob, cnp);

        clientService.saveClient(clientDto);
    }

    /**
     * Add a movie with the fields given by user
     */
    private void addMovie() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ADD MOVIE");

        System.out.println("Movie name: ");
        String name = sc.nextLine();

        System.out.println("Movie genre: ");
        String genre = sc.nextLine();

        System.out.println("Movie year: ");
        int year = sc.nextInt();

        System.out.println("Movie rental price: ");
        int rentalPrice = sc.nextInt();

        MovieDto movieDto = new MovieDto(name, genre, year, rentalPrice);

        movieService.saveMovie(movieDto);
    }

    private void addRent() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ADD RENT");

        System.out.println("Movie id: ");
        long movieId = sc.nextLong();

        System.out.println("Client id: ");
        long clientId = sc.nextLong();

        System.out.println("Pick up date: ");
        String pickUpDate = sc.next();

        RentDto rentDto = new RentDto(movieId, clientId, pickUpDate);

        rentService.saveRent(rentDto);
    }

    private void updateClient() {
        Scanner sc = new Scanner(System.in);

        System.out.println("UPDATE CLIENT");

        System.out.println("Client id: ");
        Long id = sc.nextLong();

        System.out.println("First name: ");
        String fname = sc.next();

        System.out.println("Last name: ");
        String lname = sc.next();

        System.out.println("Date of birth: ");
        String dob = sc.next();

        System.out.println("CNP: ");
        String cnp = sc.next();

        ClientDto clientDto = new ClientDto(fname, lname, dob, cnp);
        clientDto.setId(id);

        clientService.updateClient(id, clientDto);

    }

    private void updateMovie() {
        Scanner sc = new Scanner(System.in);

        System.out.println("UPDATE MOVIE");

        System.out.println("Movie id: ");
        Long id = sc.nextLong();

        System.out.println("Movie name: ");
        String name = sc.next();

        System.out.println("Movie genre: ");
        String genre = sc.next();

        System.out.println("Movie year: ");
        int year = sc.nextInt();

        System.out.println("Movie rental price: ");
        int rentalPrice = sc.nextInt();

        MovieDto movieDto = new MovieDto(name, genre, year, rentalPrice);
        movieDto.setId(id);

        movieService.updateMovie(id, movieDto);
    }

    private void updateRent() {
        Scanner sc = new Scanner(System.in);

        System.out.println("UPDATE RENT");

        System.out.println("Rent id: ");
        Long id = sc.nextLong();


        System.out.println("Client id: ");
        long clientId = sc.nextLong();

        System.out.println("Movie id: ");
        long movieId = sc.nextLong();

        System.out.println("Pick up date: ");
        String pickUpDate = sc.next();

        RentDto rentDto = new RentDto(movieId, clientId, pickUpDate);
        rentDto.setId(id);

        rentService.updateRent(id, rentDto);
    }

    /**
     * Removes the client with the given id by user
     */
    private void removeClient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("REMOVE CLIENT");
        System.out.println("Please enter the id of the client that you want to remove: ");
        Long id = sc.nextLong();

        clientService.deleteById(id);
    }

    /**
     * Removes the movie with the given id by user
     */
    private void removeMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("REMOVE MOVIE");
        System.out.println("Please enter the id of the movie that you want to remove: ");
        Long id = sc.nextLong();

        movieService.deleteById(id);
    }

    private void removeRent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("REMOVE RENT");
        System.out.println("Please enter the id of the rent that you want to remove: ");
        Long id = sc.nextLong();

        rentService.deleteById(id);
    }

    /**
     * Prints all clients
     */
    private void showClients() {
        clientService.getAllClients()
                .getClients()
                .forEach(System.out::println);
        System.out.println();
    }

    /**
     * Prints all movies
     */
    private void showMovies() {
        movieService.getAllMovies()
                .getMovies()
                .forEach(System.out::println);
        System.out.println();
    }

    private void showRents() {
        rentService.getAllRents()
                .getRents()
                .forEach(System.out::println);
        System.out.println();
    }

    private void printMenu() {
        System.out.println("1 - Add Entity");
        System.out.println("2 - Update Entity");
        System.out.println("3 - Remove Entity");
        System.out.println("4 - Show Entities");
        System.out.println("x - Exit");
    }

    private void printAddSubmenu() {
        System.out.println("1.Add Client");
        System.out.println("2.Add Movie");
        System.out.println("3.Add Rent");
        System.out.println("x - Back");
    }

    private void printUpdateSubmenu() {
        System.out.println("1.Update Client");
        System.out.println("2.Update Movie");
        System.out.println("3.Update Rent");
        System.out.println("x - Back");
    }

    private void printRemoveSubmenu() {
        System.out.println("1.Remove Client");
        System.out.println("2.Remove Movie");
        System.out.println("3.Remove Rent");
        System.out.println("x - Back");
    }

    private void printShowSubmenu() {
        System.out.println("1.Show all Clients");
        System.out.println("2.Show all Movies");
        System.out.println("3.Show all Rents");
        System.out.println("x - Back");
    }
}