package com.cristph.template.web.controller.user;

import com.cristph.template.constraint.Code;
import com.cristph.template.constraint.Response;
import com.cristph.template.core.pojos.dto.UserDTO;
import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.core.service.user.UserService;
import com.cristph.template.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Response add(@RequestBody UserDTO userDTO){
        if(!isDTOParamLegal(userDTO)){
            return Response.error(new Code(Code.ERROR.ERROR_User_200));
        }
        User user=userDTO.toEntity(User.class);
        int flag=userService.addWithDefaultValues(user);
        if(flag==1){
            logger.info("ADD USER "+user.toString());
            return Response.success();
        }else{
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_001));
        }
    }

}
