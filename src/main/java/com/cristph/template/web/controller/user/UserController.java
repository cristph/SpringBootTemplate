package com.cristph.template.web.controller.user;

import com.cristph.template.constraint.Code;
import com.cristph.template.constraint.Page;
import com.cristph.template.constraint.Response;
import com.cristph.template.core.pojos.dto.UserDTO;
import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.core.service.user.UserService;
import com.cristph.template.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param userDTO 用户对象
     * @return Response封装类
     */
    @RequestMapping(method = RequestMethod.POST)
    public Response add(@RequestBody UserDTO userDTO) {
        if (!isDTOParamLegal(userDTO)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_LACK_REQUIRED_PARAM));
        }
        String name = userDTO.getName();
        if (userService.isUserNameExist(name)) {
            //user name already exist
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_NAME_ALREADY_EXIST));
        } else {
            User user = userDTO.toEntity(User.class);
            //here the operatorId should come from user SESSION, 131250191L is just a temp value
            int flag = userService.addSelective(user, 131250191L);
            if (flag == 1) {
                logger.info("ADD USER " + user.toString());
                return Response.success();
            } else {
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_CREATE));
            }
        }
    }

    /**
     * 批量删除用户
     *
     * @param ids 用户id
     * @return Response封装类
     */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public Response batchDel(@PathVariable Long[] ids) {
        if (!isLongParamPositive(ids)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        //here the operatorId should come from user SESSION, 131250191L is just a temp value
        int flag = userService.batchSoftDelete(ids, 131250191L);
        if (flag >= 1) {
            logger.info("BATCH DEL " + flag + " USERS " + ids.toString());
            return Response.success();
        } else {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_DELETE));
        }
    }

    /**
     * 更新用户
     *
     * @param id      用户id
     * @param userDTO 用户对象
     * @return Response封装类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Response update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        if (!isLongParamPositive(id)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        if (!isDTOParamLegal(userDTO)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_LACK_REQUIRED_PARAM));
        }
        if (userService.isUserIdExist(id)) {
            User user = userDTO.toEntity(User.class);
            user.setId(id);
            if (!userService.isOtherUserNameDuplicate(id, user.getName())) {
                int flag = userService.updateSelective(user, 131250191L);
                if (flag == 1) {
                    logger.info("UPDATE USER {id=" + id + ";" + user.toString() + "}");
                    return Response.success();
                } else {
                    return Response.error(new Code(Code.ERROR.ERROR_COMMON_UPDATE));
                }
            } else {
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_NAME_ALREADY_EXIST));
            }
        } else {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_ID_NOT_EXIST));
        }
    }

    /**
     * 根据用户名模糊查询用户并分页
     *
     * @param keyword     用户名查询关键字
     * @param currentPage 当前页页码，从1开始
     * @param pageSize    页大小
     * @return Response封装类
     */
    @RequestMapping(method = RequestMethod.GET)
    public Response list(String keyword, @RequestParam int currentPage, @RequestParam int pageSize) {
        if (!isPageParamLegal(currentPage, pageSize)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_PAGE_PARAMS));
        }
        List<User> list = null;
        if (StringUtils.isEmptyOrWhitespaceOnly(keyword)) {
            list = userService.listPage(currentPage, pageSize);
        } else {
            list = userService.fuzzySearchByName(keyword, currentPage, pageSize);
        }
        if (list != null) {
            List<UserDTO> userDTOList = list.stream()
                    .map(user -> user.toDTO(UserDTO.class))
                    .collect(Collectors.toList());
            PageInfo pageInfo = new PageInfo(list);
            Page page = new Page(currentPage, pageSize, pageInfo.getTotal());
            HashMap<String, Object> map = new HashMap();
            map.put("page", page);
            return Response.success(userDTOList, map);
        } else {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_RETRIEVE));
        }
    }

}
