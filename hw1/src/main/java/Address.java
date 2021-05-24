import lombok.Getter;

@Getter
public class Address {
    private String addressUser;

    public Address(String addressUser) {
        this.addressUser = addressUser;
    }
}
