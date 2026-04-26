package com.example.ryservice.controller;

import com.example.ryservice.model.RyOrderSyncResult;
import com.example.ryservice.service.RyOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 瑞云订单接口控制器。
 */
@RestController
@RequestMapping("/api/ry/orders")
public class RyOrderController {

    private final RyOrderService ryOrderService;

    /**
     * 创建瑞云订单控制器。
     *
     * @param ryOrderService 瑞云订单服务
     */
    public RyOrderController(RyOrderService ryOrderService) {
        this.ryOrderService = ryOrderService;
    }

    /**
     * 同步瑞云订单到本地数据库。
     *
     * @param pageIndex 页码，从 1 开始
     * @param pageSize 每页条数
     * @param filter 瑞云筛选条件
     * @return 同步结果
     */
    @PostMapping("/sync")
    public RyOrderSyncResult syncOrders(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String filter) {
        return ryOrderService.syncOrders(pageIndex, pageSize, filter);
    }
}
