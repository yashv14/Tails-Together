package pet_manage.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int id;
    private String username;
    private String ownerName;
    private String petName;
    private String service;
    private LocalDate apptDate;
    private LocalTime apptTime;
    private String notes;
    
    // --- THIS FIELD WAS MISSING ---
    private String status; 

    // --- GETTERS AND SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public LocalDate getApptDate() { return apptDate; }
    public void setApptDate(LocalDate apptDate) { this.apptDate = apptDate; }

    public LocalTime getApptTime() { return apptTime; }
    public void setApptTime(LocalTime apptTime) { this.apptTime = apptTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // --- NEW GETTER/SETTER FOR STATUS ---
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}