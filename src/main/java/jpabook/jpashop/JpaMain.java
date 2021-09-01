package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

           Book book = new Book();
           book.setName("Jpa");
           book.setAuthor("김영한");

           em.persist(book);

            // 양방향 관계로 각각 엔티티에도 메소드를 만들고 순환 루프를 만들지 않고 단방향으로 해결하는 과정
            // 하나를 저장하고 그것으로 다른 하나를 저장하고 하는 방식(간단하고 생각해야 할 내용이 적어짐)

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();  // 앞쪽에 커밋 전에 하면 영속성 없어짐
        }

        emf.close();    }
}
