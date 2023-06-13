package fr.digi.jdbc.entites;

public abstract class BaseEntity {
    protected int ID;

    public BaseEntity() {
    }

    public BaseEntity(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
