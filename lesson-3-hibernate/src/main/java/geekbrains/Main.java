package geekbrains;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


//        EntityManager em = emFactory.createEntityManager();
        //       User user1 = new User(null, "alex", "alex");
//        User user2 = new User(null, "ivan", "ivan");
//        User user3 = new User(null, "petr", "petr");
//
//        em.getTransaction().begin();
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT
//        EntityManager em = emFactory.createEntityManager();
//
//        User user = em.find(User.class, 1);
//        System.out.println(user);
//
//        List<User> users = em.createQuery("from User", User.class).getResultList();
//        System.out.println(users);
//
//        user = em.createQuery("from User where login = :login", User.class)
//                .setParameter("login", "petr")
//                .getSingleResult();
//        System.out.println(user);

        // UPDATE
//        EntityManager em = emFactory.createEntityManager();
//
//        User user = em.find(User.class, 1);
//        System.out.println(user);
//
//        em.getTransaction().begin();
//        user.setPassword("new_password");
//        em.getTransaction().commit();
//
//        em.close();

//        EntityManager em = emFactory.createEntityManager();
//
//        User user = em.find(User.class, 1);
//        Contact contact = new Contact(null, "mobile phone", "123456789", user);
//
//        em.getTransaction().begin();
//        em.persist(contact);
//        em.getTransaction().commit();
//
//        EntityManager em = emFactory.createEntityManager();
//
//        Customer customer = new Customer(null, "Alex");
//        Customer customer2 = new Customer(null, "Ivan");
//        Customer customer3 = new Customer(null, "Petr");
//
//        em.getTransaction().begin();
//        em.persist(customer);
//        em.persist(customer2);
//        em.persist(customer3);
//        em.getTransaction().commit();
//
//        Customer customerBase = em.find(Customer.class, 1);
//        Product product = new Product(null, "box", 32, customerBase);
//        Product product2 = new Product(null, "telephone", 23, customerBase);
//        Product product3 = new Product(null, "pen", 1, customerBase);
//        Product product4 = new Product(null, "chair", 555, customerBase);
//
//        em.getTransaction().begin();
//        em.persist(product);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//        em.getTransaction().commit();
//
//        customerBase = em.find(Customer.class, 2);
//        product = new Product(null, "monitor", 342, customerBase);
//        product2 = new Product(null, "cup", 55, customerBase);
//        product3 = new Product(null, "paper", 434, customerBase);
//        product4 = new Product(null, "ball", 666, customerBase);
//
//        em.getTransaction().begin();
//        em.persist(product);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//        em.getTransaction().commit();
//
//
//        customerBase = em.find(Customer.class, 3);
//        product = new Product(null, "computer", 234, customerBase);
//        product2 = new Product(null, "keyboard", 11, customerBase);
//        product3 = new Product(null, "mouse", 256, customerBase);
//        product4 = new Product(null, "mat", 5, customerBase);
//
//        em.getTransaction().begin();
//        em.persist(product);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//        em.getTransaction().commit();
//        em.close();

        System.out.println(getCustomersByNameProduct("computer", emFactory));
        System.out.println(getProductsByCustomer(1, emFactory));
    }
    
// получение списка товаров клиета
    public static List<Product> getProductsByCustomer(Integer id, EntityManagerFactory entityManagerFactory) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer.getProductsBought();

    }


    // получения списка покупателей по имени продукта
    public static List<Customer> getCustomersByNameProduct(String nameProduct, EntityManagerFactory entityManagerFactory) {
        List<Customer> customers = new ArrayList<>();
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Product> products = em.createQuery("from Product where title = :title", Product.class)
                .setParameter("title", nameProduct)
                .getResultList();

        em.close();
        for (Product product:
             products) {
           customers.add(product.getCustomer());
        }
        return customers;
    }

}

