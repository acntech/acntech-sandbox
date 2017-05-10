package no.acntech.prototype.entity.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "ROLES")
@Entity
public class Role implements Serializable {

    @Id
    private String role;
    /*@JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "ROLE"),
            inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;*/

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
/*
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/
}
