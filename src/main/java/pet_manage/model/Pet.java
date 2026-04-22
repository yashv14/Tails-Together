package pet_manage.model;





public class Pet {
    private int id;
    private String ownerName;
    private String petName;
    private String species;
    private String breed;

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
}
