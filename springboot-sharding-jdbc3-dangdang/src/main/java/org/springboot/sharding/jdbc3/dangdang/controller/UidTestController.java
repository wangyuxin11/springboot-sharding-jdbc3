package org.springboot.sharding.jdbc3.dangdang.controller;

import org.springboot.sharding.jdbc3.dangdang.service.UidGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UidTestController {

    @Autowired
    private UidGenService uidGenService;

    
    /*
     * http://localhost:8090/springboot-sharding-jdbc3-dangdang/testuid
     * 
     * 
     * # 雪花ID生成的是一个64位的二进制正整数，然后转换成10进制的数。
     * # 64位二进制数由如下部分组成：
     * 
     * 1位标识符：始终是0，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0。
     * 
     * 41位时间戳：41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截 )得到的值，这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的。
     * 
     * 10位机器标识码：可以部署在1024个节点，如果机器分机房（IDC）部署，这10位可以由 5位机房ID + 5位机器ID 组成。
     * 
     * 12位序列：毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
     * 
     * 
		优点
		简单高效，生成速度快。
		时间戳在高位，自增序列在低位，整个ID是趋势递增的，按照时间有序递增。
		灵活度高，可以根据业务需求，调整bit位的划分，满足不同的需求。
		
		缺点
		依赖机器的时钟，如果服务器时钟回拨，会导致重复ID生成。
		在分布式环境上，每个服务器的时钟不可能完全同步，有时会出现不是全局递增的情况。
		
		作者：Misout
		链接：https://www.jianshu.com/p/9d7ebe37215e
     * 
     * 
     * 
     */
    @GetMapping("/testuid")
    public String test() {
    	
    	String uidStr = String.valueOf( uidGenService.getUid() );
    	
        return uidStr + "  -  "  + uidStr.length(); 
    }
}