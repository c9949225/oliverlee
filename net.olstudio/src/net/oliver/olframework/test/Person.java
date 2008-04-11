/*
 * Created on 2008-1-3 ÏÂÎç02:16:54 by oliverlee
 */
package net.oliver.olframework.test;


public class Person {
    private String firstname;
    private String lastname;
    private PhoneNumber phone;
    private PhoneNumber fax;
    
    public Person(String firstname, String lastname)
    {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Person(String firstname, String lastname, PhoneNumber phone, PhoneNumber fax)
    {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.fax = fax;
    }

    public Person(){
        super();
    }
    public PhoneNumber getFax()
    {
        return fax;
    }

    public void setFax(PhoneNumber fax)
    {
        this.fax = fax;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public PhoneNumber getPhone()
    {
        return phone;
    }

    public void setPhone(PhoneNumber phone)
    {
        this.phone = phone;
    }

    
    
  }
