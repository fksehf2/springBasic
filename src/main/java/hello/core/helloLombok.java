package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class helloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        helloLombok helloLombok = new helloLombok();
        helloLombok.setName("asdfg");

        System.out.println("helloLombok = " + helloLombok);

    }
}
