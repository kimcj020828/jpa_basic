import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaPersistenceContextMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 아직까진 비영속 상태
        Member member = new Member();
        member.setId(100L);
        member.setName("HelloJPA");

        // 영속상태
        // persist 함수의 실행 시, query문이 작성되어 DB에 직접 저장이 되는
        // 과정이 이루어 지는지 확인하는 확인 코드
        System.out.println("====BEFORE====");
        em.persist(member);
        System.out.println("=====AFTER====");

    }
}