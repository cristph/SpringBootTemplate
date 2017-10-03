package com.cristph.template.web.controller.usermanager;


import com.cristph.template.constraint.Code;
import com.cristph.template.constraint.Page;
import com.cristph.template.constraint.Response;
import com.cristph.template.core.pojos.dto.ManagerDTO;
import com.cristph.template.core.pojos.entity.Manager;
import com.cristph.template.core.service.usermanager.ManagerService;
import com.cristph.template.utils.StringUtils;
import com.cristph.template.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/8/10.
 */

@RestController
@RequestMapping("/manager")
public class ManagerController extends BaseController {

    @Autowired
    private ManagerService managerService;

    /**
     * @api {POST} /api/admin/manager 新建管理员
     * @apiGroup manager
     * @apiVersion 0.0.1
     * @apiDescription 用于新建管理员
     * @apiParam {ManagerDTO} managerDTO 管理员对象@ResponseBody
     * @apiParamExample {String} 请求样例：
     * /api/admin/manager
     * @apiParamExample {json} 请求Body样例：
     * {
     * "userName":"admin",
     * "password":"admin1234",
     * "roleName":"普通管理员"
     * }
     * @apiSuccess (200) {Response} response 返回信息封装类
     * @apiSuccessExample {json} 返回样例:
     * {
     * "status": "SUCCESS",
     * "code": null,
     * "data": null,
     * "extend": null
     * }
     */
    @RequestMapping(method = RequestMethod.POST)
    public Response add(@RequestBody ManagerDTO managerDTO) {
        if (!isDTOParamLegal(managerDTO)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        String userName = managerDTO.getUserName();
        if (!managerService.isUserNameExist(userName)) {
            Manager manager = managerDTO.toEntity(Manager.class);
//            try {
//                manager.setPassword(MD5Encrypt.MD5Encode(manager.getPassword()));
//            } catch (NoSuchAlgorithmException e) {
//                logger.error("Server error[fail to encode password with md5]");
//                e.printStackTrace();
//            }
            int flag = managerService.addWithDefaultValues(manager);
            if (flag == 1) {
                logger.info("add administrator{" + manager.toString() + "} success");
                return Response.success();
            } else {
                logger.error("add administrator{" + manager.toString() + "} fail");
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_CREATE));
            }
        } else {
            logger.error("add User fail[userName already exist]");
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_NAME_ALREADY_EXIST));
        }
    }


    /**
     * @api {DELETE} /api/admin/manager 删除管理员
     * @apiGroup manager
     * @apiVersion 0.0.1
     * @apiDescription 用于删除管理员
     * @apiParam {Long[]} ids 管理员id数组
     * @apiParamExample {String} 请求样例：
     * /api/admin/manager?ids=2,3
     * @apiSuccess (200) {Response} response 返回信息封装类
     * @apiSuccessExample {json} 返回样例:
     * {
     * "status": "SUCCESS",
     * "code": null,
     * "data": null,
     * "extend": null
     * }
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Response bulkDel(@RequestParam Long[] ids) {
        if (!isLongParamPositive(ids)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        Long[] permitToDelIds = removeSuperAdministratorId(ids);
        //如果要删除的id数组只包含超级管理员，则返回错误
        //如果同时包含超级管理员id和普通管理员id，则按照正常流程处理，只删除普通管理员
        if (permitToDelIds.length > 0) {
            int flag = managerService.batchDelete(permitToDelIds, 300L);
            if (flag >= 1) {
                logger.info("bulkDel " + flag + " administrators{id=" + permitToDelIds.toString() + "} success");
                return Response.success();
            } else {
                logger.error("bulkDel administrators{id=" + permitToDelIds.toString() + "} fail");
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_DELETE));
            }
        } else {
            return Response.error(new Code(Code.ERROR.ERROR_MANAGER_DENY));
        }
    }


    //移除超级管理员id
    private Long[] removeSuperAdministratorId(Long[] ids) {
        List<Long> permitToDelIdList = Arrays.stream(ids)
                .filter(id -> !managerService.isSuperAdministrator(id))
                .collect(Collectors.toList());
        Long[] permitToDelIds = permitToDelIdList.toArray(new Long[permitToDelIdList.size()]);
        return permitToDelIds;
    }


    /**
     * @api {PUT} /api/admin/manager 更新管理员
     * @apiGroup manager
     * @apiVersion 0.0.1
     * @apiDescription 用于更新管理员
     * @apiParam {Long} id 管理员id
     * @apiParam {ManagerDTO} managerDTO 管理员对象@ResponseBody
     * @apiParamExample {String} 请求样例：
     * /api/admin/manager?id=2
     * @apiParamExample {json} 请求Body样例：
     * {
     * "userName":"admin",
     * "password":"admin1234",
     * "roleName":"普通管理员"
     * }
     * @apiSuccess (200) {Response} response 返回信息封装类
     * @apiSuccessExample {json} 返回样例:
     * {
     * "status": "SUCCESS",
     * "code": null,
     * "data": null,
     * "extend": null
     * }
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Response update(@RequestParam Long id, @RequestBody ManagerDTO managerDTO) {
        if (!isLongParamPositive(id)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        if (!isDTOParamLegal(managerDTO)) {
            return Response.error(new Code(Code.ERROR.Exception_001));
        }
        if (managerService.isUserExist(id)) {
            Manager manager = managerDTO.toEntity(Manager.class);
            manager.setId(id);
            if (!managerService.isOtherUserNameExist(manager.getUserName(), id)) {
                int flag = managerService.updateNotNull(manager);
                if (flag == 1) {
                    logger.info("update administrator{id=" + id + ";" + manager.toString() + "} success");
                    return Response.success();
                } else {
                    logger.error("update administrator{id=" + id + ";" + manager.toString() + "} fail");
                    return Response.error(new Code(Code.ERROR.ERROR_COMMON_UPDATE));
                }
            } else {
                logger.error("fail to update Administrator[userName already exist]");
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_NAME_ALREADY_EXIST));
            }
        } else {
            logger.error("fail to update Administrator[id not exist]");
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_ID_NOT_EXIST));
        }
    }


    /**
     * @api {GET} /api/admin/manager 获取管理员列表
     * @apiGroup manager
     * @apiVersion 0.0.1
     * @apiDescription 用于获取管理员列表
     * @apiParam {int} currentPage 当前页页码
     * @apiParam {int} pageSize 页大小(包含对象数量)
     * @apiParam {String} [keyword] 搜索关键词
     * @apiParamExample {String} 请求样例：
     * /api/admin/manager?currentPage=1&pageSize=2&keyword=admin
     * @apiSuccess (200) {Response} response 返回信息封装类
     * @apiSuccessExample {json} 返回样例:
     * {
     * "status": "SUCCESS",
     * "code": null,
     * "data": [
     * {
     * "id": 8,
     * "accessId": -1,
     * "userName": "admin1",
     * "password": "enlink",
     * "roleName": "普通管理员",
     * "email": "",
     * "tel": "",
     * "maxLogonNum": 1,
     * "ip": "",
     * "mac": "",
     * "ipMac": "",
     * "status": "Enabled",
     * "legal": true
     * },
     * {
     * "id": 9,
     * "accessId": -1,
     * "userName": "admin2",
     * "password": "enlink",
     * "roleName": "普通管理员",
     * "email": "",
     * "tel": "",
     * "maxLogonNum": 1,
     * "ip": "",
     * "mac": "",
     * "ipMac": "",
     * "status": "Enabled",
     * "legal": true
     * }
     * ],
     * "extend": {
     * "page": {
     * "currentPage": 1,
     * "pageSize": 2,
     * "totalHits": 6,
     * "pageCount": 3
     * }
     * }
     * }
     */
    @RequestMapping(method = RequestMethod.GET)
    public Response list(String keyword, @RequestParam int currentPage, @RequestParam int pageSize) {
        if (!isPageParamLegal(currentPage, pageSize)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_PAGE_PARAMS));
        }
        List<Manager> list = null;
        if (StringUtils.isEmptyOrWhitespaceOnly(keyword)) {
            list = managerService.search(currentPage, pageSize);
        } else {
            list = managerService.search(currentPage, pageSize, keyword);
        }
        List<ManagerDTO> managerDTOList = list.stream()
                .map(manager -> manager.toDTO(ManagerDTO.class))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(list);
        Page page = new Page(currentPage, pageSize, pageInfo.getTotal());
        HashMap<String, Object> map = new HashMap();
        map.put("page", page);
        return Response.success(managerDTOList, map);
    }



    /**
     * @api {PATCH} /api/admin/manager 更新管理员密码
     * @apiGroup manager
     * @apiVersion 0.0.1
     * @apiDescription 用于更新管理员密码
     * @apiParam {Long} id 管理员id
     * @apiParam {String} password 管理员密码@ResponseBody
     * @apiParamExample {String} 请求样例：
     * /api/admin/manager?id=2
     * @apiParamExample {json} 请求Body样例：
     * {
     * "password":"admin1234"
     * }
     * @apiSuccess (200) {Response} response 返回信息封装类
     * @apiSuccessExample {json} 返回样例:
     * {
     * "status": "SUCCESS",
     * "code": null,
     * "data": null,
     * "extend": null
     * }
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public Response changePassword(@RequestParam Long id, @RequestBody String password) {
        if (!isLongParamPositive(id)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_FORMAT));
        }
        if (!isStringParamLegal(password)) {
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_LACK_REQUIRED_PARAM));
        }
        if (managerService.isUserExist(id)) {
            Manager manager = new Manager();
            manager.setId(id);
//            try {
//                manager.setPassword(MD5Encrypt.MD5Encode(password));
//            } catch (NoSuchAlgorithmException e) {
//                logger.error("Server error[fail to encode password with md5]");
//                e.printStackTrace();
//            }
            int flag = managerService.updateNotNull(manager);
            if (flag == 1) {
                logger.info("change password{id=" + id + ";" + manager.toString() + "} success");
                return Response.success();
            } else {
                logger.error("change password{id=" + id + ";" + manager.toString() + "} fail");
                return Response.error(new Code(Code.ERROR.ERROR_COMMON_UPDATE));
            }
        } else {
            logger.error("fail to change password[id not exist]");
            return Response.error(new Code(Code.ERROR.ERROR_COMMON_ID_NOT_EXIST));
        }
    }
}
