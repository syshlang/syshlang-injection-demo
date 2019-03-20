package com.syshlang.injection.controller;


import com.syshlang.injection.model.User;
import com.syshlang.injection.repository.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/user")
@Transactional
@Component
@Slf4j
public class UserController {
    @Autowired
    private UserDao userDao;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserByid(@PathParam("id")Long id){
        Optional<User> user= userDao.findById(id);
        return Response.ok(user).build();
    }


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
