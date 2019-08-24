package com.xlyd.eureka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

	@Autowired
	private LoadBalancerClient client; //ribbon负载均衡器
	
		
	public Object getAll() {
		// 选择调用的服务名称：通过服务名称选择服务
		// serviceInstance：封装了服务的基本信息，ip port
		ServiceInstance instance = client.choose("eureka-provider");
		// 拼接访问服务的url
		StringBuffer sb = new StringBuffer();
		sb.append("http://").append(instance.getHost()).append(":")
		  .append(instance.getPort());
		sb.append("/user");
		
		// resttemplate
		RestTemplate template = new RestTemplate();
		ParameterizedTypeReference<List<Object>> ref = new ParameterizedTypeReference<List<Object>>() {
			
		};
		
		ResponseEntity<List<Object>> res = template.exchange(sb.toString(), HttpMethod.GET, null, ref);
		
		return res.getBody();
	}
	
}
