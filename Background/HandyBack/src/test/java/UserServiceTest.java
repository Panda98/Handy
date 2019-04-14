import com.handy.support.entity.User;
import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.pojo.user.vo.UserVO;
import com.handy.support.service.User.IUserService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    public void addUserTest(){
        String msg = iUserService.addUser("1234@qq.com","1234");
        assertTrue(msg.equals("success"));
    }
    @Test
    public void getUserByEmailTest(){
        User user = iUserService.getUserByEmail("1234@qq.com");
        assertFalse(user==null);
    }
    @Test
    public void updateUserTest(){
        UserDto dto = new UserDto();
        dto.setUserId(232);
        dto.setSex((byte)1);
        String msg = iUserService.updateUser(dto);
        assertTrue(msg.equals("success"));
    }

    @Test
    public void addUserLabelsTest(){
        int uid = 232;
        List<Integer> labels = new ArrayList<Integer>();
        labels.add(1);
        labels.add(2);
        String msg = iUserService.addUserLabels(uid,labels);
        assertTrue(msg.equals("success"));
    }

    @Test
    public void revert2VOTest(){
        User user = new User();
        user.setUserId(4);
        user.setEmail("123@qq.com");
        UserVO vo = iUserService.revert2VO(user);
        assertTrue(vo != null);
    }


}
