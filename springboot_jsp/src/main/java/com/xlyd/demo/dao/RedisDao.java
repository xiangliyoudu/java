package com.xlyd.demo.dao;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisDao {

	@Autowired
	private RedisTemplate<String, Object> template;
	
	public void testConn() {
		RedisConnection rc =  template.getConnectionFactory().getConnection();
		// connection test
		String connString = rc.ping();
		System.out.println(connString);
		// switch database 0-15
		rc.select(1);
		// count database size
		rc.dbSize();
		// slave connect to master
		rc.slaveOf("masterhost", 26379);
		
	}
	
	public Object getMethod() {
		BoundValueOperations<String, Object> bvo = template.boundValueOps("key1");
		bvo.set("value1");
		Long expireTime = bvo.getExpire();
		System.out.println(expireTime);

		// single value ops
		ValueOperations<String, Object> vo = template.opsForValue();
		vo.set("key1", "value11");
		Duration duration = Duration.of(30, ChronoUnit.MINUTES);
		vo.set("key1", "value11", duration);
		vo.get("key1");
		vo.append("key2", "append value");
		vo.size("key1");
		
		// list ops
		ListOperations<String, Object> lo = template.opsForList();
		lo.rightPush("key", "value");
		lo.range("key1", 0, -1);
		lo.index("key1", 0);
		lo.trim("key2", 0, 1);
		
		// set ops
		SetOperations<String, Object> seto = template.opsForSet();
		seto.add("key2", 1, 2, 3);
		ScanOptions so = ScanOptions.NONE;
		so = ScanOptions.scanOptions().build();
		try {
			Cursor<Object> cursor = seto.scan("key1", so);
			cursor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		seto.members("key1");
		
		// hash ops
		HashOperations<String, String, Object> ho = template.opsForHash();
		ho.put("key3", "hashkey1", "vlaue");
		ho.get("key3", "hashkey1");
		
		// cluster ops
		RedisClusterNode rcn = new RedisClusterNode("host3", 16379);
		ClusterOperations<String, Object> co = template.opsForCluster();
		co.bgSave(rcn);
		co.getSlaves(rcn);
		co.ping(rcn);
		
		// ZSet ops
		ZSetOperations<String, Object> zo = template.opsForZSet();
		zo.add("key1", "zkey1", 86);
		zo.remove("key1", "zkey");
		zo.size("key1");
		
		//
		DataType dt = template.type("key1");
		dt.code();
	
		
		
		return "";
	}

	public void trancMethod() {
		template.multi();
		
		template.watch("key1");
		template.unwatch();

		template.exec();
	}

}
