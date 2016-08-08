package bmf.services.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String firstname;
   private String lastname;
   private String email;
   private String password;
   private Boolean is_active=false;

   public User(){}
   
   public User(int id, String firstname, String lastname, String email, String password, Boolean is_active){
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.password = password;
      this.is_active = is_active;
   }

   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getFirstName() {
      return firstname;
   }
   @XmlElement
   public void setFirstName(String firstname) {
      this.firstname = firstname;
   }
   public String getLastName() {
	      return lastname;
   }
   @XmlElement
   public void setLastName(String lastname) {
      this.lastname = lastname;
   }
   public String getPassword() {
	      return password;
   }
   @XmlElement
   public void setPassword(String password) {
      this.password = password;
   }
   public String getEmail() {
	      return email;
	}
	@XmlElement
	public void setEmail(String email) {
	   this.email = email;
	}
   public Boolean getIs_active() {
	      return is_active;
	}
	@XmlElement
	public void setIs_active(Boolean is_active) {
	   this.is_active = is_active;
	}
}

