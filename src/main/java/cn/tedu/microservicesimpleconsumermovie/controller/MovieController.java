package cn.tedu.microservicesimpleconsumermovie.controller;

import cn.tedu.microservicesimpleconsumermovie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Description :
 * Author : zhanghao
 * Create Date : 2019/7/25 16:16
 * Project Name : 公自用项目
 */
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return this.restTemplate.getForObject("http://localhost:8090/"+id, User.class);
    }

    /**
     * 查询microservice-provider-user服务的信息并返回
     * @return microservice-provider-user服务的信息
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return this.discoveryClient.getInstances("microservice-provider-user");
    }
}
