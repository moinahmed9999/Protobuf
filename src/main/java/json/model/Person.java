package json.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Person {
    private String name;
    private List<Address> address;
    private List<String> mobile;
    private List<String> email;
}
