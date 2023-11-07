package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /*
         - persistence.xml에 작성한 unit name 작성
         - Application이 올라갈 때 최초로 하나를 생성
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        /*
            EntityManager는 고객의 요청이 올때마다 계속 생성(사용하고 버려야 하는 단위-쓰레드간 공유X)
         */
        EntityManager em = emf.createEntityManager();

        // JPA에서 데이터를 변경하는 행위는 무조건 트랜잭션 안에서 작업을 해야함
        final EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            //code
            // INSERT
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); // save

            // Select Entity.class, key (단순한 조회)
            Member findMember = em.find(Member.class, 1L);

            // JPQL로 쿼리 단위 조회하고 싶을 경우
             List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
                     .setFirstResult(1) // pagination 의 Limit 첫번째 
                     .setMaxResults(10) // pagination 의 Limit 두번째 
                     .getResultList();

             for (Member member : result) {
                 System.out.println(member.getName());
             }

            // Delete
//            em.remove(findMember);

            /*
            Update: 굳이 별도의 업데이트를 하는 ex)persistence 의 행위를 하지 않아도 set만으로도 변경됨
            
            Transaction을 이용하여 커밋하기 전에 객체의 변경된 사항을 확인하여 Update 쿼리문을 생성
             */
            findMember.setName("HelloJPA");
            

            System.out.println(findMember.getId());
            System.out.println(findMember.getName());
//            findMember.setName();
            //공통
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }
}