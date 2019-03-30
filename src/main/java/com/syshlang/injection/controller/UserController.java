package com.syshlang.injection.controller;


import com.syshlang.injection.model.User;
import com.syshlang.injection.repository.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/user")
@Transactional
@Component
@Slf4j
public class UserController {
    @Autowired
    private UserDao userDao;

    @Resource(name = "myDataSource")
    private DataSource myDataSource;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList(@QueryParam("account") String account){
        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(myDataSource);
        String sql = "select * from sys_user where account ='"+account+"'";
        List<?> resultList = jdbcTemplate.queryForList(sql);
        return Response.ok(resultList).build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserListPreprareStatement(@QueryParam("account") String account){
        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(myDataSource);
        String sql = "select * from sys_user where account = ?";
        List resultList = jdbcTemplate.queryForList(sql,account);
        return Response.ok(resultList).build();
    }

   /* @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserByid(@PathParam("id")Long id){
        Optional<User> user= userDao.findById(id);
        return Response.ok(user).build();
    }*/


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(){
        List<User> userlist= userDao.findAll();
        return Response.ok(userlist).build();

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        User us= userDao.save(user);
        if (us==null) {
            log.info("user already exits.");
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.created(URI.create("/user/" + us.getId())).build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArticle(User user) {
        userDao.save(user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") Long id) {
        userDao.deleteById(id);
        return Response.noContent().build();
    }
}
