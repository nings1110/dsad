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

给定一个经过编码的亨符串，按照特定规则返回它解码后的字符串。
编码规则为: k{string}，表示大括号内部的string经过解码后重复k次，k保证为正整数, string经过解码后为由a-z之间的字符组成的字符串，即大括号可能会有嵌套的情况。
你可以认为输入字符串总是有效的;输入字符串中没有额外的空格，且输入的括号总是符合格式要求的。
原始数据不包含数字，所有的数字只表示重复的次数k，例如不会出现像3a或2{4}的输入，但是会出现像2{a3{b4{c}d}e}的情况。
通过java实现

import java.util.*;



public class Solution {
/**
* Note: 类名、方法名、参数名已经指定，请勿修改
*
*
*
* @param s string字符串  
* @return string字符串
*/
public String decodeString(String s) {
// write code here
}
}


#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
