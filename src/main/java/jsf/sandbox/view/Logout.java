package jsf.sandbox.view;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;

@Named
@RequestScoped
public class Logout {

    public static final String HOME_URL = "login.xhtml";

    public void submit() throws IOException {
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }

}
