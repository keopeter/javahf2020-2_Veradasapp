package container;

public class PersonContainer {
    private String name;
    private Double weight;
    private String city;
    private Gender gender;
    private Bloodtype blood;

    //add bloodtype
    public PersonContainer(String name, Double weight, String city, Gender gender, Bloodtype blood) {
        this.name = name;
        this.weight = weight;
        this.city = city;
        this.gender = gender;
        this.blood = blood;
    }
}
