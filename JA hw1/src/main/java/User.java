import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class User {
    private String fullName;
    private Address address;
    private List<Animal> animalList;

    public User(String fullName) {
        this.fullName = fullName;
    }

    public int userAdult(int age){
        if (age >= 18){
            System.out.println("You adult");
        }else {
            throw new IllegalArgumentException("you have not reached the age of majority");
        }
        return age;
    }

    public void setAddress(Address address){
        if (address == null){
            throw new IllegalArgumentException();
        }
        this.address=address;
    }

    public void addressRemove(Address addressn){
        if ( address == addressn){
            throw new IllegalArgumentException();
        }
        this.address=null;
    }

    public void setAnimal(Animal smallPet){
        animalList.add(smallPet);
    }

    public void setAnimal(User user){
        animalList.remove(user);
    }

    public User setFullName(User user, String fullName){
        user.setFullName(fullName);
        return user;
    }
}
