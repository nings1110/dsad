# SSO Demo 单点登录

#### Description
一个简单的单点登录(SSO)的demo.简单来说就是在一个具有多个子系统的系统中，只用登录一个子系统，然后访问其他子系统时不需要再次登录，即“一次登录，多处访问”，能够有效的提升用户体验。

#### Software Architecture
Software architecture description
餐馆有n张桌子，每张桌子可以最多容纳a_i个人。 有m批客人，每批客人的人数为b_i，预计消费金额为c_i。 在不允许拼桌的情况下，请问总预计消费金额最大是多少？ 第一行两个整数n(1 <= n <= 50000),m(1 <= m <= 50000) ，分别代表桌子数和客人的批次。
第二行为n个参数ai,即每个桌子可容纳的最大人数,以空格分隔，范围均在32位int范围内。
接下来m行，每行两个参数bi,ci。分别表示第i批客人的人数和预计消费金额,以空格分隔,范围均在32位int范围内
#### Installation
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt(); // 桌子数
        int m = scanner.nextInt(); // 客人批次数
        
        int[] tableCapacity = new int[n];
        for (int i = 0; i < n; i++) {
            tableCapacity[i] = scanner.nextInt(); // 每张桌子的容纳人数
        }
        
        PriorityQueue<Customer> customerQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            int b = scanner.nextInt(); // 客人人数
            int c = scanner.nextInt(); // 预计消费金额
            customerQueue.offer(new Customer(b, c)); // 将客人按消费金额从大到小加入优先队列
        }
        
        long totalRevenue = 0;
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.poll();
            for (int i = 0; i < n; i++) {
                if (tableCapacity[i] >= customer.numPeople) {
                    totalRevenue += customer.expectedRevenue;
                    tableCapacity[i] -= customer.numPeople;
                    break;
                }
            }
        }
        
        System.out.println(totalRevenue);
    }
    
    static class Customer implements Comparable<Customer> {
        int numPeople;
        int expectedRevenue;
        
        public Customer(int numPeople, int expectedRevenue) {
            this.numPeople = numPeople;
            this.expectedRevenue = expectedRevenue;
        }
        
        @Override
        public int compareTo(Customer other) {
            return Integer.compare(this.expectedRevenue, other.expectedRevenue);
        }
    }
}

1.  xxxx
2.  xxxx
3.  xxxx
    DROP TABLE IF EXISTS tb_get_car_record,tb_get_car_order;
    CREATE TABLE tb_get_car_record (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    uid INT NOT NULL COMMENT '用户ID',
    city VARCHAR(10) NOT NULL COMMENT '城市',
    event_time datetime COMMENT '打车时间',
    end_time datetime COMMENT '打车结束时间',
    order_id INT COMMENT '订单号'
    ) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE tb_get_car_order (
id INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
order_id INT NOT NULL COMMENT '订单号',
uid INT NOT NULL COMMENT '用户ID',
driver_id INT NOT NULL COMMENT '司机ID',
order_time datetime COMMENT '接单时间',
start_time datetime COMMENT '开始计费的上车时间',
finish_time datetime COMMENT '订单结束时间',
mileage FLOAT COMMENT '行驶里程数',
fare FLOAT COMMENT '费用',
grade TINYINT COMMENT '评分'
) CHARACTER SET utf8 COLLATE utf8_bin;

INSERT INTO tb_get_car_record(uid, city, event_time, end_time, order_id) VALUES
(101, '北京', '2021-10-01 07:00:00', '2021-10-01 07:02:00', null),
(102, '北京', '2021-10-01 09:00:30', '2021-10-01 09:01:00', 9001),
(101, '北京', '2021-10-01 08:28:10', '2021-10-01 08:30:00', 9002),
(103, '北京', '2021-10-02 07:59:00', '2021-10-02 08:01:00', 9003),
(104, '北京', '2021-10-03 07:59:20', '2021-10-03 08:01:00', 9004),
(105, '北京', '2021-10-01 08:00:00', '2021-10-01 08:02:10', 9005),
(106, '北京', '2021-10-01 17:58:00', '2021-10-01 18:01:00', 9006),
(107, '北京', '2021-10-02 11:00:00', '2021-10-02 11:01:00', 9007),
(108, '北京', '2021-10-02 21:00:00', '2021-10-02 21:01:00', 9008),
(109, '北京', '2021-10-08 18:00:00', '2021-10-08 18:01:00', 9009);

INSERT INTO tb_get_car_order(order_id, uid, driver_id, order_time, start_time, finish_time, mileage, fare, grade) VALUES
(9002, 101, 202, '2021-10-01 08:30:00', null, '2021-10-01 08:31:00', null, null, null),
(9001, 102, 202, '2021-10-01 09:01:00', '2021-10-01 09:06:00', '2021-10-01 09:31:00', 10.0, 41.5, 5),
(9003, 103, 202, '2021-10-02 08:01:00', '2021-10-02 08:15:00', '2021-10-02 08:31:00', 11.0, 41.5, 4),
(9004, 104, 202, '2021-10-03 08:01:00', '2021-10-03 08:13:00', '2021-10-03 08:31:00', 7.5, 22, 4),
(9005, 105, 203, '2021-10-01 08:02:10', null, '2021-10-01 08:31:00', null, null, null),
(9006, 106, 203, '2021-10-01 18:01:00', '2021-10-01 18:09:00', '2021-10-01 18:31:00', 8.0, 25.5, 5),
(9007, 107, 203, '2021-10-02 11:01:00', '2021-10-02 11:07:00', '2021-10-02 11:31:00', 9.9, 30, 5),
(9008, 108, 203, '2021-10-02 21:01:00', '2021-10-02 21:10:00', '2021-10-02 21:31:00', 13.2, 38, 4),
(9009, 109, 203, '2021-10-08 18:01:00', '2021-10-08 18:11:50', '2021-10-08 18:51:00', 13, 40, 5); （order_id-订单号, uid-用户ID, driver_id-司机ID, order_time-接单时间, start_time-开始计费的上车时间,  finish_time-订单完成时间, mileage-行驶里程数, fare-费用, grade-评分） 问题：请统计每个城市中评分最高的司机平均评分、日均接单量和日均行驶里程数。
注：有多个司机评分并列最高时，都输出。
平均评分和日均接单量保留1位小数，
日均行驶里程数保留3位小数，按日均接单数升序排序。

用户提交打车请求后，在用户打车记录表生成一条打车记录，order_id-订单号设为null；

当有司机接单时，在打车订单表生成一条订单，填充order_time-接单时间及其左边的字段，start_time-开始计费的上车时间及其右边的字段全部为null，并把order_id-订单号和order_time-接单时间（end_time-打车结束时间）写入打车记录表；若一直无司机接单，超时或中途用户主动取消打车，则记录end_time-打车结束时间。

若乘客上车前，乘客或司机点击取消订单，会将打车订单表对应订单的finish_time-订单完成时间填充为取消时间，其余字段设为null。

当司机接上乘客时，填充订单表中该start_time-开始计费的上车时间。
当订单完成时填充订单完成时间、里程数、费用；评分设为null，在用户给司机打1~5星评价后填充。

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
