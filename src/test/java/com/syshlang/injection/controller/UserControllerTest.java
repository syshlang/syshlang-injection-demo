package com.syshlang.injection.controller;

import com.syshlang.injection.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserControllerTest {

    @Test
    public void getUser() {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8083/");
        WebTarget details = base.path("/user");
        List<User> userlist = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>() {
        });
        log.info("list:"+userlist);
        client.close();
    }

    @Test
    public void findUserByid() {
    }

    @Test
    public void findUser() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void updateArticle() {
    }

    @Test
    public void deleteArticle() {
    }
}