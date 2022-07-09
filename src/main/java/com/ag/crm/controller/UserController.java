package com.ag.crm.controller;

import com.ag.crm.domain.User;
import com.ag.crm.domain.LoginParam;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.config.QiniuUtils;
import com.ag.crm.domain.UsersRoles;
import com.ag.crm.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(value = "用户管理",tags = "用户管理")
@RestController
//@PreAuthorize("hasAuthority('sys:set:user')")
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private QiniuUtils qiniuUtils;

    @ApiOperation("增加用户")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @PostMapping("create")
    public ResultVO delete(@RequestBody User user){
        return userService.createUser(user);
    }

    @ApiOperation("修改用户")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @PostMapping("update")
    public ResultVO update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @ApiOperation("分页查询所有用户信息")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页数", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "页大小", required = true)
    })
    @GetMapping("listAll")
    public ResultVO selectAllUser(int pageNum, int pageSize){
        return userService.selectAllUser(pageNum, pageSize);
    }

    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @PostMapping("delete")
    public ResultVO delete(@RequestBody Integer id){
        System.out.println(id);
        return userService.deleteUser(id);
    }

    @ApiOperation("删除多个用户")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @PostMapping("delete/users")
    public ResultVO deleteUsers(@RequestBody List<Integer> list){
        return userService.deleteUsersById(list);
    }

    @ApiOperation("为用户分配角色")
    @PreAuthorize("hasAuthority('sys:set:user')")
    @PostMapping("allocate")
    public ResultVO allocateRoles(@RequestBody UsersRoles usersRoles){
//        return userService.deleteUsersById(list);
        System.out.println(usersRoles);
        userService.allocate(usersRoles);
        return new ResultVO(200,"操作成功",null);
    }

    @ApiOperation("查询所有客户客户经理")
    @GetMapping("selectAllManager")
    public ResultVO selectAllManager(){
        return userService.selectAllManager();
    }


//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ApiOperation(value = "上传头像", notes = "上传头像", httpMethod = "POST")
//    public ResultVO upload(@ApiParam(value = "头像") @RequestParam("image") MultipartFile file){
//        //原始文件名称 比如说aa.png
//        String originalFilename = file.getOriginalFilename();
//        //唯一的文件名称
//        String fileName =  UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(originalFilename, ".");
//        //上传文件上传到那里呢？　七牛云　云服务器
//        //降低我们自身应用服务器的带宽消耗
//        boolean upload = qiniuUtils.upload(file, fileName);
//        if (upload) {
//            int i = userService.uploadImage(QiniuUtils.url+fileName);
//            if (i > 0){
//                return ResultVO.success(QiniuUtils.url+fileName,null);
//            }
//            else{
//                throw new RuntimeException("存储数据库失败");
//            }
//        }
//        return ResultVO.fail("上传失败");
//
//    }

//

//
//    @PostMapping(value = "/editUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ApiOperation(value = "修改用户信息", notes = "上传头像", httpMethod = "POST")
//    @ApiImplicitParams({
//        @ApiImplicitParam(dataType = "int",name = "id", value = "用户id",required = true),
//        @ApiImplicitParam(dataType = "string",name = "account", value = "用户注册账号",required = true),
//        @ApiImplicitParam(dataType = "string",name = "password", value = "用户注册密码",required = true),
//        @ApiImplicitParam(dataType = "string",name = "tel", value = "用户电话号码",required = true)
//    })

//    public ResultVO editUser(@ApiParam(value = "头像") @RequestParam("image") MultipartFile file, User user){
//        //原始文件名称 比如说aa.png
//        String originalFilename = file.getOriginalFilename();
//        //唯一的文件名称
//        String fileName =  UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(originalFilename, ".");
//        boolean upload = qiniuUtils.upload(file, fileName);
//        String fileSrc = QiniuUtils.url+fileName;
//        return userService.editUser(fileSrc,upload,user);
//    }
}
