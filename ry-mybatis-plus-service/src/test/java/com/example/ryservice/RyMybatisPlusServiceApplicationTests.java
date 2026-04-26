package com.example.ryservice;

import com.example.ryservice.model.RyOrderSyncResult;
import com.example.ryservice.service.RyOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RyMybatisPlusServiceApplicationTests {

    @Autowired
    private RyOrderService ryOrderService;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldFetchRyOrders() {
        RyOrderSyncResult response = ryOrderService.syncOrders(1, 20, "(new_dealstatus eq 1 or new_dealstatus eq 2 or new_dealstatus eq 3 or new_dealstatus eq 4 or new_dealstatus eq 5 or new_dealstatus eq 6)");
        System.out.println("瑞云订单同步结果: total="
                + response.getTotal()
                + ", saved="
                + response.getSaved()
                + ", skipped="
                + response.getSkipped());
    }
}
