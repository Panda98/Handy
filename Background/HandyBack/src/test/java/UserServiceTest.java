import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.service.User.IUserService;
import com.handy.web.controller.UserController;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pan on 2019/4/12.
 */
public class UserServiceTest extends BaseTest{
    @Autowired
    private IUserService iUserService;

    @Test
    public void serviceTest(){
        assertTrue(iUserService != null);
    }
    @Test
    public void getUserByIDTest(){
        UserDto user = iUserService.getUserByID(2);
        assertTrue(user != null);
    }

    @Test
    public void loginTest(){
        UserController controller = new UserController();
    }
}
