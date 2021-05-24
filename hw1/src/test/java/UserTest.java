import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    public void before(){
        user = new User("Sergiy");
    }

    @Test
    @DisplayName("перевірки чи користувач повнолітній")
    void userAdult() {
        int ageMen = user.userAdult(20);
        Assertions.assertEquals(20, ageMen);
    }

    @Test
    @DisplayName("додавання адреси до користувача")
    void setAddress(){
        user.setAddress(new Address("stysa 2"));
        user.getAddress();
    }

    @Test
    @DisplayName("видалення адреси до користувача")
    void addressRemove(){
        user.addressRemove(new Address("stysa 2"));
    }

    @Test
    @DisplayName("додати тваринку до списку тваринок і видалити")
    void setAnimal(){
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Broad", 2));
        animalList.remove(new Animal("Broad", 2));
    }

    @Test
    public void userAdultThrow(){
        try {
            user.userAdult(17);

        }catch (IllegalArgumentException e){
            Assertions.assertEquals(e.getMessage(),"you have not reached the age of majority");
        }
    }

    @Test
    void setFullName(){
//        user.setFullName("Sergiy");
//        user.getFullName(1,"Sergiy");

    }
}