package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //DB에서 조회하고
            Member findMember = em.find(Member.class, 101L);
            //조회 후 영속성 컨텍스트 1차캐시에 있는 것을 조회
            Member findMember2 = em.find(Member.class, 101L);
            //쿼리는 한번만 나간다.

            System.out.println("result=" + (findMember == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
