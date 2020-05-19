package container;

public class PersonContainer {
    public String name;
    public Double weight;
    public String city;
    public Gender gender;
    public Bloodtype blood;

    //add bloodtype
    public PersonContainer(String name, Double weight, String city, Gender gender, Bloodtype blood) {
        this.name = name;
        this.weight = weight;
        this.city = city;
        this.gender = gender;
        this.blood = blood;
    }
}
