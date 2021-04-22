
## aop
Aop全称是Aspect Oriented Programming，即‘面向切面编程’，是通过预编译方式和运行期动态代理
实现核心业务逻辑之外的横切行为的统一维护的一种技术。

## Aop核心概念
| 名词 | 理解 |
|通知（Advice）|我们要实现的功能，如日志记录，性能统计，安全控制，事务处理，异常处理等等，说明什么时候要干什么|
|连接点（Joint Point）|Spring 允许你用通知的地方，方法有关的前前后后（包括抛出异常）|
|切入点（Pointcut）|指定通知到哪个方法，说明在哪干|
|切面（Aspect）|切面就是通知和切入点的结合|
|目标对象（Target Object）|业务逻辑本身|
|织入（Weaving）|切点定义了哪些连接点会得到通知|
|引入（Introduction ）|引入就是在一个接口/类的基础上引入新的接口增强功能|
|AOP 代理（AOP Proxy ）|通过代理来对目标对象应用切面|

## Spring Aop

在spring中，aop代理可以是JDK动态代理，也可以是CGLIB代理

### 注解
|注解|说明|
|@Aspect|将一个 java 类定义为切面类|
|@Pointcut|定义一个切入点，可以是一个规则表达式，比如下例中某个 package 下的所有函数，也可以是一个注解等|
|@Before|在切入点开始处切入内容|
|@After|在切入点结尾处切入内容|
|@AfterReturning|在切入点 return 内容之后处理逻辑|
|@Around|在切入点前后切入内容，并自己控制何时执行切入点自身的内容|
|@AfterThrowing|用来处理当切入内容部分抛出异常之后的处理逻辑|
|@Order(100)|AOP 切面执行顺序， @Before 数值越小越先执行，@After 和 @AfterReturning 数值越大越先执行|

**@Before、@After、@AfterReturning、@Around、@AfterThrowing 都属于通知（Advice）。**
