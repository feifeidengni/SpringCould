package com.feifei.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *  服务网关 java代码实现~
 * */
@Configuration
public class GatWayConfig
{
    /**
     *  配置了一个id为route-name 的路由规则，
     *  当访问地址为 http://localhost:9527/guonei时，会自动转发到地址为：http://news.baidu.com/guonei
     * */
    @Bean
    public RouteLocator customRouterLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_feifei",
                r ->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator cystomRouterLocator2(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_feifei2",
                feifei ->feifei.path("/test")
                 .uri("https://www.baidu.com/")).build();
        return routes.build();
    }
}
