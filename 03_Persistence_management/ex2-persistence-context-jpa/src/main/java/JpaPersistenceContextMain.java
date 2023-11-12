import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistenceContextMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 아직까진 비영속 상태
//            Member member = new Member();
//            member.setId(102L);
//            member.setName("HelloJPA");
//
//            // 영속상태
//            // persist 함수의 실행 시, query문이 작성되어 DB에 직접 저장이 되는
//            // 과정이 이루어 지는지 확인하는 확인 코드
//            System.out.println("====BEFORE====");
//            em.persist(member);
////            em.detach(member); // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
////            em.remove(member); // 객체를 삭제한 상태(삭제)
//            System.out.println("=====AFTER====");
//
//            /*
//             ! 1차 캐시 확인
//             확인 방법: 조회 쿼리가 콘솔에 출력되는가? 확인
//
//             */
//            Member findMember = em.find(Member.class, 102L);
//            System.out.println("findMember:="+ findMember.getId());
//            System.out.println("findMember:="+ findMember.getName());
            
            /*
            1차 캐시 확인
            같은 객체를 2번 이상 조회할 때,쿼리문이 1번만 나가는지 확인하는 방법
            이상적인 방향: 1번의 쿼리문 작성
             */
//            Member member1 = em.find(Member.class, 101L);
//            Member member2 = em.find(Member.class, 101L);
//            System.out.println("result=" + (member1==member2)); // 영속 엔티티의 동일성 보장(true)

//            /*
//            트랜젝션을 지원하는 쓰기 지연
//             */
//            Member member1 = new Member(150L,"A");
//            Member member2 = new Member(160L,"B");
//
//            em.persist(member1);
//            em.persist(member2);

//            // 객체가 변경되면 commit 외, 별도의 로직 처리는 필요x
//            final Member findMember = em.find(Member.class, 150L);
//            findMember.setName("ZZZZZZ");
//
//            // em.persist(findMember); 의미 없는 코드
            
            /*
            Flush 강제 호출
             */
//            Member member = new Member(200L, "200Member");
//            em.persist(member);
//
//            em.flush();

            /*
             * 영속 -> 준영속
             */
            Member findMember = em.find(Member.class, 150L);

            findMember.setName("AAAAA");

//            em.detach(findMember); // 특정 객체만 준영속 상태로 만듬
            em.clear(); //영속성 컨텍스트를 모두 초기화
            Member findMember2 = em.find(Member.class, 150L);

            System.out.println("==================");

            tx.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}