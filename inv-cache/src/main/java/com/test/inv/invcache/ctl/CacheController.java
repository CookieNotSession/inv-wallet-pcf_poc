package com.test.inv.invcache.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.repo.UserDataCache;
import com.test.inv.entity.UserData;

@RestController
@RequestMapping("/cache")
public class CacheController {


    @Autowired
    private UserDataCache userDataCache;
    
    @GetMapping("/userdatas")
    @ResponseBody
    public List<UserData> findUserDataAll() {
        return userDataCache.findAll();
    }

    
    @GetMapping("/userdata/{id}")
    @ResponseBody
    public UserData findUserDataById(@PathVariable("id") Long uid) {
        return userDataCache.findById(uid);
    }
    
    @GetMapping("/userdata/name/{name}")
    @ResponseBody
    public UserData findUserDataByName(@PathVariable("name") String name) {
        return userDataCache.findByName(name);
    }
}
