# springboot-other
springboot 2.6.2 with other tools

# 上下文切换

```text
   上下文切换
   多线程编程中一般线程的数量都大于CPU核心的个数，而一个CPU核心在任意时刻只能被一个线程使用，为了让这些线程都能得到有效执行，CPU采取的策略
是为每个线程分配时间片并轮转的形式。当一个线程的时间片用完之后就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。
   概括来说： 当前任务在执行完CPU时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换回这个任务时，可以再加载这个任务的状态。
   任务从保存到再加载的过程称为一次上下文切换。
   上下文切换通常是计算密集型的。也就是说，它需要相当客观的处理器时间，在每秒几十上百次的切换中，每次切换都需要纳秒量级的时间。所以，上下文
切换意味着消耗大量的CPU时间。事实上，可能是操作系统中时间消耗最大的操作。
   Linux 相比其他操作系统（包括其他类Unix系统）有很多优点。其中有一项就是，其上下文切换和模式切换的时间消耗非常少。
```

# AQS

```text
   谈一下对AQS的理解
   AQS的核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效线程，并且将共享资源设置为锁定状态。如果被请求的资源被占用
那么就需要一个线程阻塞等待以及被唤醒时锁分配的机制。这个机制AQS是用CLH（虚拟的双向队列-> 即不存在队列实例，只存在节点之间的关联关系）队列
锁实现。即将暂时获取锁的线程添加到队列中。
   AQS 使用一个int 类型的成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。AQS使用CAS对该同步状态进行原子操作
实现对其值的修改。
   AQS 资源共享的方式
   1、独占
      ReentrantLock
      1、公平锁
      2、非公平锁
      两者的不同
      1、非公平锁在调用lock后，首先就会调用CAS进行一次抢锁，如果这个时候恰巧锁没有被占用，那么直接就获取到锁返回。
      2、非公平锁在CAS失败后，，和公平锁一样都会进入到 tryAcquire 方法，在 tryAcquire 方法中，如果发现这个时候锁被释放了（state == 0）
   非公平锁会直接进行CAS抢锁。 
         公平锁会判断等待队列中是否有线程处于等待状态，如果有则不去抢锁，然后乖乖的排到队列后面。
      公平锁和非公平锁就这两点不同，如果这两次CAS操作都不成功，那么它们的后续是一样的，都会进入到阻塞队列等待唤醒。
      相对来说，非公平锁拥有更好的性能。因为它的吞吐量比较大。但是这也让获取锁的时间变得更加不确定，可能导致在阻塞队列中的线程
   长期处于饥饿状态。
   2、共享
      多个线程可公式执行
      如 CountDownLatch、CycleBarrier, ReadWriteLock、Semaphore

      ReentrantReadWriteLock 可以看做事组合式的，也就是读写锁允许多个线程同时对某一资源进行读。
   
   不同的自定义同步器的争抢共享资源的方式也不同。自定义同步器在实现时只需要实现共享资源 state 的获取和释放方式即可。至于具体线程
等待队列的维护，AQS已经帮我们实现好了。

```

## AQS 底层模板方法，自定义同步器是只需要实现以下钩子方法 

```code
    protected boolean tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
    protected boolean tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
    protected boolean tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
    protected boolean tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
    protected boolean isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。

```

## Semaphore

```text
   Synchronized 和 ReentrantLock 一次只允许一个线程获取到锁 访问某一个资源。
   Semaphore 可以允许多个线程同时访问某一个资源
   执行 acquire() 方法阻塞，直到有一个许可证可以获得然后拿走一个许可证，这可能会释放一个阻塞 acquire 方法。然而，并没有实际的许可证
这个对象，Semaphore 只是维持了一个可获得许可证的数量。它经常被用于限制某种的资源的线程数量。
   
   当然一次也可以一次拿取和释放多个许可，不过一般没有必要这样做。
```

```code
   semaphore.acquire(5); // 获取5个许可，则可运行的线程数量则为 20/5=4
   test(threadNum);
   semaphore.release(5);
```

```text
   除了 acquire 方法外，另一个比较常用的与之对应的方法是 tryAcquire() ，该方法如果获取不到许可证就会立即放回false

   semaphore 有两个模式
   1、公平模式
      调用acquire 方法的顺序就是获得许可证的顺序，遵循FIFO
   2、非公平模式
      抢占式
   
```   
```code
    public Semaphore(int permits) {
        sync = new NonfairSync(permits);
    }

    public Semaphore(int permits, boolean fair) {
        sync = fair ? new FairSync(permits) : new NonfairSync(permits);
    }
```
```
   这两个构造方法，都必须提供许可的数量，第二个构造方法可以指定是公平模式还是非公平模式，默认非公平模式。

   issue645 补充内容 ：Semaphore 与 CountDownLatch 一样，也是共享锁的一种实现。它默认构造 AQS 的 state 为 permits。
当执行任务的线程数量超出 permits，那么多余的线程将会被放入阻塞队列 Park,并自旋判断 state 是否大于 0。只有当 state 大于 0 的时候，
阻塞的线程才能继续执行,此时先前执行任务的线程继续执行 release() 方法，release() 方法使得 state 的变量会加 1，那么自旋的线程便会判断成功。
 如此，每次只有最多不超过 permits 数量的线程能自旋成功，便限制了执行任务线程的数量。
```

## CountDownLatch

```text
   CountDownLatch 允许 count 个线程阻塞在一个地方，直到所有线程的任务都执行完毕。
   CountDownLatch 是共享锁的一种实现。
   它默认构造AQS的 state 为 count。当线程使用 countDown() 方法时，其实调用了 tryReleaseShared 方法以CAS操作来减少state，直至state
为0。当 调用 await 方法的时候，如果 state 不为0，那就证明任务还没有执行完毕， await 方法就会一直阻塞。也就是说 await 方法之后的语句不会
被执行。然后 CountDownLatch 会自旋CAS判断 state == 0， 如果 state == 0 的话就会释放所有等待的线程。await 方法之后的语句就会得到执行。
   
   CountDownLatch 两种典型用法
   1、某一线程在开始运行前等待 n 个线程执行完毕。
   将 CountDownLatch 的计数器初始化为 n （new CountDownLatch(n)），每当一个任务线程执行完毕，就将计数器减 1 （countdownlatch.countDown()，
当计数器的值变为 0 时，在 CountDownLatch 上 await() 的线程就会被唤醒。
一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
   
   2、实现多个线程开始执行任务的最大并行性。
   
   注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
做法是初始化一个共享的 CountDownLatch 对象，将其计数器初始化为 1 （new CountDownLatch(1)），多个线程在开始执行任务前
首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为 0，多个线程同时被唤醒。

   CountDownLatch 的不足
   CountDownLatch 是一次性的，计数器的值只能在构造器中初始化一次，之后没有任何机制在对其进行设置。使用完毕后，不能再被使用。
```

## CyclicBarrier 循环栅栏

   CyclicBarrier 和 CountDownLatch 非常类似。他也可以实现线程间的技术等待。但是它的功能比CountDownLatch 更加负责和强大。
两者的场景类似。
```text
   CountDownLatch 的实现是基于AQS的，而 CyclicBarrier 是 ReentrantLock (它也属于 AQS 同步器) 和 Condition 的。
```
   CyclicBarrier 字面意思是可循环使用的屏障。它让一组线程到达一个屏障（也可以叫同步点）时被阻塞， 直到最后一个线程到达屏障时，屏障才会开门
所有被屏障拦截的线程才会继续执行。
   它默认的构造方法时 new CyclicBarrier(int parties) ，参数表示要拦截的线程数量，每个线程调用 await 方法告诉 CyclicBarrier 我已到达
屏障，然后当前线程被阻塞。 当拦截的线程数量达到 parties 这个值的时候，就会打开栅栏，让所有线程通过。
   CyclicBarrier 还提供了一个更高级别的构造函数 CyclicBarrier(int parties, Runnable barrierAction), 用于在线程到达屏障时，优先执行
barrierAction, 方便处理更复杂的业务场景。

   CyclicBarrier 内部通过一个count 变量作为计数器，count 初始值为parties属性的初始值。每当一个线程到达栅栏这里，那么就将计数器减一
。如果count 值为0，则代表这一波最后一个线程到达栅栏，就尝试执行我们构造方法中输入的任务。

### CyclicBarrier 和 CountDownLatch 的区别
   1、CountDownLatch 是计数器，只能使用一次，而CyclicBarrier的计数器reset 功能，可以重复使用。
   2、对于 CountDownLatch 来说，重点一个线程（多个线程）等待， 而其他N个线程在完成某件事情后，可以终止，可以等待。而对于CyclicBarrier
重点是多个线程，在任意一个线程没有完成之前，所有线程都必须等待。
   3、CountDownLatch 是计数器，线程完成一个记录一个，只不过计数器不是递增而是递减。 CyclicBarrier 更像是一个阀门，需要所有线程都到达，
阀门才能打开，然后继续执行。

### ReentrantLock 和 ReentrantReadWriteLock
    1、ReentrantLock 悲观锁
    2、ReentrantReadWriteLock 可以保证多个线程可以同时读，所以在读操作远大于写操纵的情况
    
### JUC 应用场景

 同步工具 | 与  AQS 的关联
 --- | --- 
 ReentrantLock | 使用AQS保存锁重复持有的次数。当一个线程获取锁时，ReentrantLock 记录当时获得锁的线程标识，用于检测是否重复获取。以及错误
 ReentrantLock | 以及错误线程试图解锁操作时异常情况的处理。
 Semaphore | 使用 AQS 同步状态保存信号量的当前计数。 TryRelease 会增加计数，acquireShared 会减少计数
 CountDownLatch | 使用 AQS 同步状态来表示计数，计数为0；所有的 Acquire操作（CountDownLatch 的 await 方法）才可以通过。
 ReentrantReadWriteLock | 使用AQS 同步状态中的16位来保存写锁持有的次数，剩下的16位来保存读锁持有的次数。
 ThreadPoolExecutor | Worker 利用 AQS 同步状态来实现对独占线程变量的设置
    

## JVM 

Java 虚拟机可能会抛出两种错误 <code>StackOverFlowError</code>和 <code>OutOfMemoryError</code>
```text
  1、StackOverFlowError
     若 Java 虚拟机栈的内存大小不允许动态扩展，那么当线程请求栈的深度超过当前 Java 虚拟机栈的最大深度，则抛出 StackOverFlowError
  2、OutOfMemoryError
     若 Java 虚拟机栈的内存大小可以动态扩展，如果虚拟机在动态扩展栈时无法申请到足够的内存空间，则抛出 OutOfMemoryError
```

### 字符串常见问题
```text
   1、对于基本数据类型来说，==比较的是值。对于引用数据类型来说，==比较的是对象的内存地址。
   2、在编译过程中，Javac 编译器（下文中统称为编译器）会进行一个叫做 常量折叠(Constant Folding) 的代码优化。
常量折叠会把常量表达式的值求出来作为常量嵌在最终生成的代码中，这是 Javac 编译器会对源代码做的极少量优化措施之一(代码优化几乎都在即时编译器中进行)。
   3、一般来说，我们要尽量避免通过 new 的方式创建字符串。使用双引号声明的 String 对象（ String s1 = "java" ）更利于让编译器有机会优化我们的代码，
同时也更易于阅读。
   4、被 final 关键字修改之后的 String 会被编译器当做常量来处理，编译器程序编译期就可以确定它的值，其效果就相当于访问常量。
```

### Java 垃圾回收详解
```text
    如何判断对象是否死亡（两种方法）。
    简单的介绍一下强引用、软引用、弱引用、虚引用（虚引用与软引用和弱引用的区别、使用软引用能带来的好处）。
    如何判断一个常量是废弃常量
    如何判断一个类是无用的类
    垃圾收集有哪些算法，各自的特点？
    HotSpot 为什么要分为新生代和老年代？
    常见的垃圾回收器有哪些？
    介绍一下 CMS,G1 收集器。
    Minor Gc 和 Full GC 有什么不同呢？
```
调式代码的参数
-verbose:gc
-Xmx200M
-Xms200M
-Xmn50M
-XX:+PrintGCDetails
-XX:TargetSurvivorRatio=60
-XX:+PrintTenuringDistribution
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:MaxTenuringThreshold=3
-XX:+UseConcMarkSweepGC
-XX:+UseParNewGC

#### HotSpot 为什么要分为新生代和老年代
```text
   分代收集算法是HotSpot 虚拟机采用的垃圾收集算法。这种算法没有什么新的思想，它根据对象存活周期的
不同，将内存分成几块。一般将Java 堆分为新生代和老年代，这样我们就可以根据各个年代的特点选择合适的垃圾回收
算法。
   比如在新生代中，每次收集都会有大量对象死亡，所以可以选择 “标记-复制” 算法。只需要付出少量对象的复制成本
就可以完成每次的垃圾收集。而老年代的对象存活几率是比较高的，而且没有额外的空间对它进行分配担保。所有我们
必须要选择 “标记-清除” 或 “标记-整理” 算法进行垃圾收集。
```