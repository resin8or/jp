/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jamietobin
 */
public class Main {
    // ======================================
    // =             Entry point            =
    // ======================================

    public static void main(String[] args) {

        // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy series created by Douglas Adams.");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Creates an instance of book
        Book book2 = new Book();
        book2.setTitle("Java EE 6");
        book2.setPrice(23.4F);
        book2.setDescription("Java EE 6 book written by Antonio");
        book2.setIsbn("1-34567-111-4");
        book2.setNbOfPage(400);
        book2.setIllustrations(false);
        List<String> tags2 = new ArrayList<String>();
        tags2.add("java");
        tags2.add("english");
        book2.setTags(tags2);

        // Gets an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("e2ePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Persists the book to the database
        tx.begin();
        em.persist(book);
        em.persist(book2);
        tx.commit();

        // Displays the books
        System.out.println("====== All books");
        List<Book> books = em.createNamedQuery("findAllBooks").getResultList();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println("==> " + b);
        }

        System.out.println("\n====== All Scifi books");
        books = em.createNamedQuery("findAllScifiBooks").getResultList();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println("==> " + b);
        }
        
        em.close();
        emf.close();
    }    
}
