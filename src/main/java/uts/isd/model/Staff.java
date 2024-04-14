package uts.isd.model;

import java.util.Date;

public class Staff extends User {
    private Date empDate;

    public Staff(String email, String username, String password, Date empDate) {
        super(email, username, password);
        this.empDate = empDate;
    }

    public Date getEmpDate() {
        return this.empDate;
    }

    public void setEmploymentDate(Date empDate) {
        this.empDate = empDate;
    }
}