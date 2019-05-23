package linkList;

/**
 * Description:单链表
 *
 * @Author Fann
 * @Data 2018/8/24
 */
public class LinkList{
    /*
     *节点类
     */
    class Entry{
        int data;
        Entry next;
        //节点的默认值
        public Entry(){
            this.data = -1;
            this.next = null;
        }
        //根据data创造出一个节点
        public Entry(int data){
            this.data = data;
            this.next = null;
        }
    }
    //单链表的头部
    private Entry head;
    public Entry getHead(){
        return this.head;
    }

    //单链表的默认构造函数
    public LinkList(){
        this.head = new Entry();
    }

    //单链表的构造函数,根据head来进行构造
    public LinkList(LinkList.Entry head){
        this.head = head;
    }

    //得到该单链表有多少个数据节点
    public int getLength(){
        int len = 0;
        Entry entry = this.head.next;
        while (entry != null){
            len++;
            entry = entry.next;
        }
        return len;
    }

    /**
     * 头插法,将val值做成节点插入到单链表的表头
     * @param val
     * @return
     */
    public boolean headInsert(int val){
        //1.根据val得到实体节点
        Entry entry = new Entry(val);

        //2.先处理该节点
        entry.next = this.head.next;
        //3.进行连接
        this.head.next = entry;
        return true;
    }

    /**
     * 尾插法,根据val值生成节点然后插入到单链表的表尾
     * @param val
     * @return
     */
    public boolean tailInsert(int val){
        //1.根据val得到实体节点
        Entry entry = new Entry(val);
        //2.找到尾巴
        Entry tail = this.head;
        while (tail.next != null){
            tail = tail.next;
        }
        //插入
        tail.next = entry;
        return true;
    }

    /**
     * 将val插入到pos位置处,第一个节点为0号下标
     * @param val
     * @param pos
     * @return
     */
    public boolean insertPos(int val,int pos){
        if(pos > getLength()+1 || pos < 0){//等于getLength的话可以进行尾插法,所以要+1
            return false;
        }

        //1.根据val生成节点
        Entry entry = new Entry(val);

        //2.找到pos位置
        Entry temp = this.head;
        while (pos != 0){
            temp = temp.next;
            pos--;
        }

        //3.先处理当前节点尾部,再进行连接
        entry.next = temp.next;
        temp.next = entry;
        return true;
    }

    /**
     * 删除单链表中所有值为key的节点
     * @param key
     * @return
     */
    public boolean deleteAllKey(int key){
        //一个为父节点,一个为当前节点
        Entry pre = this.head;
        Entry del = this.head.next;

        while (del != null){
            if(del.data == key){
                pre.next = del.next;
                del = del.next;
            }else{
                pre = pre.next;
                del = del.next;
            }
        }
        return true;
    }

    /**
     * 单链表的逆置
     */
    public void reverse(){
        Entry cur = this.head.next;
        Entry curNext = null;  //用来存储真正的下一个值
        this.head.next = null;
        while (cur != null){
            curNext = cur.next;
            cur.next = this.head.next;
            this.head.next = cur;
            cur = curNext;
        }
    }

    /**
     * 求单链表倒数第k个节点,返回对应的data值
     * @param k
     * @return
     */
    public int lastK(int k){
        if(k < 0 || k > this.getLength()){
            return -1;
        }
        //定义两个节点,entry1先走k-1步.
        Entry entry1 = this.head;
        Entry entry2 = this.head;
        for (int i = 0; i < k-1; i++) {
            entry1 = entry1.next;
        }

        while (entry1.next != null){
            entry1 = entry1.next;
            entry2 = entry2.next;
        }

        return entry2.data;
    }

    /**
     * 将该单链表的尾部指向其中某一处,做成一个环
     */
    public void creatLoop(){
        Entry cur = this.head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = this.head.next.next;
    }

    /**
     * 判断一个单链表是否有环
     * @return
     */
    public boolean isLoop(){
        //定义两个节点,一个快节点,一个慢节点
        Entry fast = this.head;
        Entry slow = this.head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 求环的入口
     * @return
     */
    public int enterEntry(){
        Entry fast = this.head;
        Entry slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }

        slow = this.head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }

    /**
     * 得到环的长度
     * @return
     */
    public int getLoopLen(){
        Entry fast = this.head;
        Entry slow = this.head;
        int len = 0;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }

        Entry cur = slow;//将碰撞点记录下来,等他再次走到这一个节点的时候就是长度
        while (slow.next != cur){
            slow = slow.next;
            len++;
        }
        return len+1;
    }

    //show函数
    public void show(){
        Entry entry = this.head.next;
        while (entry.next != null){
            System.out.print(entry.data+"  ");
            entry = entry.next;
        }
        System.out.println(entry.data);
    }
}
