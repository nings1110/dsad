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

#### Instructions

1.  xxxx
2.  xxxx
3.  xxxx

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
