import javax.persistence.Entity;
import javax.persistence.Id;

// 추후에 집에 있는 코드로 병합해야함
@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    public Member(){

    }

    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

