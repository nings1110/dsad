# SSO Demo 单点登录


#### Instructions

1.  xxxx
2.  xxxx
3.  xxxx

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request

SELECT 
    city,
    driver_id,
    AVG(grade) AS avg_grade,
    COUNT(order_id) / DATEDIFF(MAX(order_time), MIN(order_time)) AS daily_order_count,
    ROUND(SUM(mileage) / COUNT(order_id), 3) AS daily_avg_mileage
FROM 
    tb_get_car_order
WHERE 
    grade IS NOT NULL
GROUP BY 
    city, driver_id
HAVING 
    AVG(grade) = (
        SELECT 
            MAX(avg_grade)
        FROM (
            SELECT 
                city,
                driver_id,
                AVG(grade) AS avg_grade
            FROM 
                tb_get_car_order
            WHERE 
                grade IS NOT NULL
            GROUP BY 
                city, driver_id
        ) AS t
        WHERE 
            t.city = tb_get_car_order.city
    )
ORDER BY 
    daily_order_count ASC;

#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
