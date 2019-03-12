package com.gupao.service.impl;
import com.gupao.po.Person;
import com.gupao.po.Storage;
import com.gupao.service.PersonService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@WebService
public class PersonServiceImpl implements PersonService{
    @Override
    public List<Person> getUsers() {
        return Storage.personList;
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public Person getUser(int id) {
        return null;
    }

    @Override
    public Response insert(Person user) {
        return null;
    }

    @Override
    public Response update(Person user) {
        return null;
    }
}

