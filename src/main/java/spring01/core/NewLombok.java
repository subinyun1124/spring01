package spring01.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        NewLombok newLombok = new NewLombok();
        newLombok.setName("subin");

//        String name = newLombok.getName();
        System.out.println(newLombok.toString());

    }
}
