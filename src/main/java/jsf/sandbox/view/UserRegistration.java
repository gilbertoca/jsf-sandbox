
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

@Named
@RequestScoped
public class UserRegistration {

  @Inject
  private IdentityManager identityManager;

  private String loginName;
  private String firstName;
  private String lastName;
  private String password;

  public String register() {
    User newUser = new User(this.loginName);

    newUser.setFirstName(this.firstName);
    newUser.setLastName(this.lastName);

    this.identityManager.add(newUser);

    Password psword = new Password(this.password);

    this.identityManager.updateCredential(newUser, psword);

    return "/signin.xhtml";
  }

    public IdentityManager getIdentityManager() {
        return identityManager;
    }

    public void setIdentityManager(IdentityManager identityManager) {
        this.identityManager = identityManager;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
