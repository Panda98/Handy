import com.handy.support.entity.User;
import com.handy.support.pojo.dto.UserDto;
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
        UserDto user = iUserService.getUserByID("2");
        assertTrue(user != null);
    }

    @Test
    public void loginTest(){
        UserController controller = new UserController();
        controller.login("15927444722@163.com","123456");
    }
}
