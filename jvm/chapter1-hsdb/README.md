## **JHSDB：基于服务性代理的调试工具**


JHSDB是一款基于服务性代理（Serviceability Agent，SA）实现的进程外调试工具。服务性代理是 HotSpot虚拟机中一组用于映射Java虚拟机运行信息的、主要基于Java语言（含少量JNI代码）实现的 API集合。
服务性代理以HotSpot内部的数据结构为参照物进行设计，把这些C++的数据抽象出Java模 型对象，相当于HotSpot的C++代码的一个镜像。
通过服务性代理的API，可以在一个独立的Java虚拟 机的进程里分析其他HotSpot虚拟机的内部数据，或者从HotSpot虚拟机进程内存中dump出来的转储快 照里还原出它的运行状态细节。服务性代理的工作原理跟Linux上的GDB或者Windows上的Windbg是相似的。

---

## **jdk9以前的版本中JHSDB的使用方式**

**在java9之前**，JAVA_HOME/lib目录下有个sa-jdi.jar，可以通过如下命令启动HSDB(图形界面)及CLHSDB(命令行)
java -cp your_javahome_path/lib/sa-jdi.jar sun.jvm.hotspot.HSDB


```
java -cp .\sa-jdi.jar sun.jvm.hotspot.HSDB
```


**sa-jdi.jar中的sa的全称为Serviceability Agent**，它之前是sun公司提供的一个用于协助调试HotSpot的组件，而HSDB便是使用Serviceability Agent来实现的
**HSDB就是HotSpot Debugger的简称**，由于Serviceability Agent在使用的时候会先attach进程，然后暂停进程进行snapshot，最后deattach进程(进程恢复运行)，所以在使用HSDB时要注意

---

## **使用HSDB分析程序代码中的内存**

本次，我们要借助JHSDB来分析一下程序1-1中的代码,，并通过实验来回答一个简单问 题：staticObj、instanceObj、localObj这三个变量本身（**而不是它们所指向的对象**）存放在哪里？也就是开篇提到的问题
**程序1-1**
**public** **class** **ObjectHolder** **{**     **private** **int** age**;** **}**

_/**  * staticObj、instanceObj、localObj存放在哪里？  */_ **public** **class** **JHSDB_TestCase** **{**     **static** ObjectHolder staticObj **=** **new** ObjectHolder**();**     ObjectHolder instanceObj **=** **new** ObjectHolder**();**     **public** **void** **foo()** **{**         ObjectHolder localObj **=** **new** ObjectHolder**();**         System**.**out**.**println**(**"done"**);** _// 这里设一个断点 _    **}**     **public** **static** **void** **main(**String**[]** args**)** **{**         JHSDB_TestCase test **=** **new** JHSDB_TestCase**();**         test**.**foo**();**     **}** **}**

答案可能会出乎你的意料
**staticObj**随着ObjectHolder的类型信息存放在**堆区**中
**instanceObj**随着JHSDB_TestCase的对象实例存放在**堆区**
**localObject**则是存放在foo()方法栈帧的局部变量表中,这个是在**java虚拟机栈区**。
这个答案不仅是通过前面的系列教程学习的理论知识得出的，更是要从今天的可视化工具JHSDB来实践验证这一点

---

## **使用JHSDB验证内存分布**


**Step 1: 启动程序与HSDB**
首先，我们要确保这三个变量已经在内存中分配好，然后将程序暂停下来，以便有空隙进行实 验，这只要把断点设置在代码中加粗的打印语句上，然后在调试模式下运行程序即可。
由于JHSDB本 身对压缩指针的支持存在很多缺陷，建议用64位系统的读者在实验时禁用压缩指针，另外为了后续操 作时可以加快在内存中搜索对象的速度，也建议读者限制一下Java堆的大小。
本例中，笔者采用的运行参数如下：
-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
注意: -XX:-UseCompressedOops和-XX:+UseCompressedOops 前者是减号, 表示不使用压缩指针
程序执行后通过jps查询到测试程序的进程ID，具体如下：
jps -l 4755 sun.tools.jps.Jps 4437 org.jetbrains.jps.cmdline.Launcher 4438 chapt4.JHSDB_TestCase
这里我们知道了,正在因debug被暂停的程序的pid为 4438, 后面我们会用到这个pid
先获取到本地jdk的home目录
使用以下命令进入JHSDB的图形化模式
sudo java -cp ./lib/sa-jdi.jar sun.jvm.hotspot.HSDB

命令打开的HSDB的界面如图所示
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110067704-34fab0dd-79ad-4352-9f7a-8b976daa04f7.png#clientId=u2f9f7632-2a23-4&from=paste&id=u3e17372f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1079&originWidth=1440&originalType=url&ratio=1&size=135327&status=done&style=none&taskId=u28e5ad87-9681-475c-ba9e-d6a8d9ba423)

之前我们提到过, HSDB基于的服务性代理,可以在一个独立的Java虚拟机的进程里**分析其他HotSpot虚拟机**的内部数据,那么我现在需要输入一个我们希望检测的hotspot虚拟机的pid
点击File -> Attach to HotSpot process
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110067833-3465970f-2365-45ca-959b-17d25c807945.png#clientId=u2f9f7632-2a23-4&from=paste&id=u4a1a43af&margin=%5Bobject%20Object%5D&name=image.png&originHeight=408&originWidth=1338&originalType=url&ratio=1&size=210936&status=done&style=none&taskId=u4e04b220-2ef5-479d-9ebc-2636cdb3c5d)

在弹出的界面中输入我们要检测的程序的pid
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110067808-63cbf62e-aa83-4d34-adcd-590d99cecd67.png#clientId=u2f9f7632-2a23-4&from=paste&id=u343757e3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=622&originWidth=838&originalType=url&ratio=1&size=219422&status=done&style=none&taskId=ude9934c2-0e13-4058-b975-9182bcd94b7)

点ok之后进入如下界面
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110067718-1b712a93-22e0-4651-9d47-9d8def92c13f.png#clientId=u2f9f7632-2a23-4&from=paste&id=u21d895ab&margin=%5Bobject%20Object%5D&name=image.png&originHeight=446&originWidth=1390&originalType=url&ratio=1&size=162104&status=done&style=none&taskId=u46781dd8-45aa-4265-9533-43bad4600c3)

阅读程序1-1 可知，运行至断点位置**一共会创建三个ObjectHolder对象**的实例
只要是**对象实 例必然会在Java堆中分配**
既然我们**要查找引用这三个对象的指针存放在哪里**，**不妨从这堆中的三个对象实例开始着手, 先把它们从Java堆中找出来**。
先找到对象实例,后反向找到指向对象实例的引用.

## **Step 2: 找出在堆中存储的三个对象实例**

我们知道 new 出的**对象的实例**,**一般是在堆中新生代分配**,并且上面创建的三个对象在当前debug的暂停时刻不满足任何进入老年代的条件

## **Step 2-1: 先获取到新生代的内存分布区间**

首先点击菜单中的Tools->Heap Parameters ，结果下图所示，因为笔者的运行参数中指定了使 用的是Serial收集器，图中我们看到了典型的Serial的分代内存布局，
Heap Parameters窗口中清楚列出了 新生代的Eden、S1、S2和老年代的容量（单位为字节）以及它们的虚拟内存地址起止范围
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110068099-cf032043-645f-4bb9-a7a2-ea26937c77c7.png#clientId=u2f9f7632-2a23-4&from=paste&id=uf3079eaa&margin=%5Bobject%20Object%5D&name=image.png&originHeight=750&originWidth=1440&originalType=url&ratio=1&size=575641&status=done&style=none&taskId=u6a972782-dfe7-4fd2-b638-d199d01be3b)

Head Parameters中的完整信息如下
Heap Parameters: Gen 0:   eden [0x000000010c000000,0x000000010c1ed678,0x000000010c2b0000) space capacity = 2818048, 71.71574082485465 used   from [0x000000010c2b0000,0x000000010c2b0000,0x000000010c300000) space capacity = 327680, 0.0 used   to   [0x000000010c300000,0x000000010c300000,0x000000010c350000) space capacity = 327680, 0.0 usedInvocations: 0 Gen 1:   old  [0x000000010c350000,0x000000010c350000,0x000000010ca00000) space capacity = 7012352, 0.0 usedInvocations: 0

这个方括号里面的内容
[0x000000010c000000,0x000000010c1ed678,0x000000010c2b0000)
分别表示 **内存起始地址**，**使用空间结束地址**，**整体空间结束地址**
我们不难看出,当前程序中, 只有Eden区的起始地址和使用空间结束地址不相同(分配有对象),而from区和to区的使用空间地址和起始地址相同(空使用区域)

从Eden区的左侧地址开始 到 to区的右侧结束
也就是从 0x000000010c000000 到 0x000000010c350000

## **Step 2-2: 在新生代内存分布空间中查找指定类的实例**


打开Windows->Console窗口，使用scanoops命令在Java堆的新生代（从Eden起始地址到To Survivor结束地址）范围内查找 ObjectHolder的实例，结果如下所示：
hsdb> scanoops 0x000000010c000000 0x000000010c350000 chapt4.ObjectHolder 0x000000010c1ddee0 chapt4/ObjectHolder 0x000000010c1ddf28 chapt4/ObjectHolder 0x000000010c1ddf40 chapt4/ObjectHolder hsdb>

果然找出了三个实例的地址, 到这一步,大致能说明, 这三个**对象的实例**都是**在堆区分配**的, 但是我们的**任务还没有完成**,因为我们是**希望通过这三个实例,反向找到指向他们的引用**
它们的地址也都落到了Eden的范围之内，算是顺带验证了一般情况下新对象在Eden中创建的分配规则。

---

## **Step 3: 通过**实例找出引用实例的位置并确定存储引用所在的区域

通过Step 2我们在堆区中找到了这三个对象实例的地址, 现在我们进一步使用Tools->Inspector功能详细查看这三个对象的内容

## **Step 3-1: 第一个对象**

**第一个对象** 0x000000010c1ddee0 chapt4/ObjectHolder
我们首先得知道这是三个对象中的哪一个
在Inspector中输入上面的地址 0x000000010c1ddee0 然后按下回车
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110069138-d27c119b-f73c-427c-9691-425d6abf8940.png#clientId=u2f9f7632-2a23-4&from=paste&id=u75d170af&margin=%5Bobject%20Object%5D&name=image.png&originHeight=798&originWidth=1440&originalType=url&ratio=1&size=554812&status=done&style=none&taskId=u7ba4c3d4-a7d3-4cf9-8416-048bbbcb116)
我们可以看到有age属性,还有一个叫做 reverse pointer, 直译是反向指针, 其实就是指, 有哪些对象在引用当前对象,那个引用的名称叫 staticObj, 证明第一个对象就是staticObj对象,并且这个对象的引用是存在于一个Class类创建的实例中的,可以看到这个 **staticObj 引用持有者,也就是这个Class对象的地址**, **0x000000010c1d6118**
Step 1 中, 我们已经知道了新生代的内存区域范围
从Eden区的左侧地址开始 到 to区的右侧结束
也就是从
0x000000010c000000 到
0x000000010c350000
我们的**staticObj 引用持有者Class对象的地址**
0x000000010c1d6118
**正在其中,说明Class对象,是在堆区中分配的**

进一步的,其中Eden区的已分配内存空间范围为
0x000000010c000000 到
0x000000010c1ed678
我们的staticObj 引用持有者Class对象的地址
0x000000010c1d6118
正在其中,说明Class对象,是在堆区的Eden区中被分配的
必须要说明的是, 现在**全网,大概90%的文章再说到方法区的作用的时候,都说到了静态变量是存储在方法区中的,java8中方法区是由元空间(jvm空间之外的内存)**
但是在**这个例子里面我们可以看到**,**在java8中静态变量的持有者Class对象,当然也包括这个静态变量,是在堆区中分配的**
**结论 : staticObj**随着ObjectHolder的类型信息存放在**堆区**中

---

## **Step 3-2: 第二个对象**

**第二个对象** 0x000000010c1ddf28 chapt4/ObjectHolder
我们首先得知道这是剩下的两个对象中的哪一个, 剩下一个是成员变量instanceObj,另一个是局部变量localObj
在Inspector中输入上面的地址 0x000000010c1ddf28 然后按下回车
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110069244-a35cc3e8-e5c1-45f1-afc4-abe3f1cebf2d.png#clientId=u2f9f7632-2a23-4&from=paste&id=u83e22a7d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=785&originWidth=1440&originalType=url&ratio=1&size=642546&status=done&style=none&taskId=u987b7c37-cc13-4eab-8474-f9c738b772a)
我们可以看到有age属性,还有一个叫做 reverse pointer, 直译是反向指针, 其实就是指, 有哪些对象在引用当前对象,那个引用显示的是一个文件夹,表示当前对象被另一个对象持有
这个对象就是JHSDB_TestCase的实例对象, 证明第二个对象就是instanceObj对象,并且这个对象的引用是存储在了JHSDB_TestCase的实例中的,我们可以看到这个 instanceObj 引用持有者JHSDB_TestCase实例对象的地址, 0x000000010c1ddf10

前两步我们已经知道了新生代的内存区域范围
从Eden区的左侧地址开始 到 to区的右侧结束
也就是从
0x000000010c000000 到
0x000000010c350000
我们的instanceObj 引用持有者JHSDB_TestCase对象的地址
0x000000010c1ddf10
正在其中,说明JHSDB_TestCase对象,是在堆区中分配的,这是预测之中的
**结论:** **instanceObj**随着JHSDB_TestCase的对象实例存放在**堆区**

---

## **Step 3-3: 第三个对象**

**第三个对象** 0x000000010c1ddf40 chapt4/ObjectHolder
前两个对象我们已经找到,所以第三个对象一定是localObj
在Inspector中输入上面的地址 0x000000010c1ddf40 然后按下回车
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110069065-d21bef8b-a33f-472b-8a2e-a5f90184e9a3.png#clientId=u2f9f7632-2a23-4&from=paste&id=u5b056d4b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=378&originWidth=748&originalType=url&ratio=1&size=191693&status=done&style=none&taskId=u66e07c96-c1a1-4b05-9f5b-84009283eaa)

我们可以看到有age属性,还有一个叫做 reverse pointer, 直译是反向指针, 其实就是指, 有哪些对象在引用当前对象,那个引用显示的是Stack 以及 main字样
我们就知道了 ,当前对象的引用localObj的持有者,在Stack,也就是栈区,我们可以看到这个 localObj 引用持有者所在的地址, 0x000070000989c9a0
前两步我们已经知道了新生代的内存区域范围
从Eden区的左侧地址开始 到 to区的右侧结束
也就是从
0x000000010c000000 到
0x000000010c350000
我们的localObj 引用持有者所在的地址
0x000070000989c9a0
显然不在其中,说明localObj对象,并不是在堆区中分布
这时候我们在 HSDB 的 桌面上的 java thread窗口 (默认一直开启的那个) 选中main 然后点击上方第二个图标 Stack Memory 查看 main线程 在栈中的内存
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110069019-eadf8afe-ef16-4d08-9abe-7dd038020b3f.png#clientId=u2f9f7632-2a23-4&from=paste&id=udb49cafd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=494&originWidth=1004&originalType=url&ratio=1&size=139721&status=done&style=none&taskId=ue9a12c5c-9525-4736-806d-24b0660745d)

点击之后出先下午,注意其中,找到了栈区中,包含有localObj引用的那块内存
![image.png](https://cdn.nlark.com/yuque/0/2021/png/21985811/1626110070137-5e1e2b5e-cf7a-4c92-94a4-aacad460932c.png#clientId=u2f9f7632-2a23-4&from=paste&id=u02d10848&margin=%5Bobject%20Object%5D&name=image.png&originHeight=732&originWidth=1368&originalType=url&ratio=1&size=990353&status=done&style=none&taskId=u38dc5f94-41d9-46d9-bc4c-ddc216b9e62)

**结论: localObject**则是存放在foo()方法栈帧的局部变量表中,这个是在**java虚拟机栈区**。


---

**至此，本次实验中三个对象均已找到，并成功追溯到引用它们的地方,也就实践验证了开篇中提出的这些对象的引用是存储在什么地方的问题。**

## **结论**

运行如下代码
_/**  * staticObj、instanceObj、localObj存放在哪里？  */_ **public** **class** **JHSDB_TestCase** **{**     **static** ObjectHolder staticObj **=** **new** ObjectHolder**();**     ObjectHolder instanceObj **=** **new** ObjectHolder**();**     **public** **void** **foo()** **{**         ObjectHolder localObj **=** **new** ObjectHolder**();**         System**.**out**.**println**(**"done"**);** _// 这里设一个断点 _    **}**     **public** **static** **void** **main(**String**[]** args**)** **{**         JHSDB_TestCase test **=** **new** JHSDB_TestCase**();**         test**.**foo**();**     **}** **}**

**public** **class** **ObjectHolder** **{**     **private** **int** age**;** **}**

staticObj、instanceObj、localObj这三个变量本身（**而不是它们所指向的对象**）存放在哪里？

**staticObj**随着ObjectHolder的类型信息存放在**堆区**中
**instanceObj**随着JHSDB_TestCase的对象实例存放在**堆区**
**localObject**则是存放在foo()方法栈帧的局部变量表中,这个是在**java虚拟机栈区**。
这个答案不仅是通过前面的系列教程学习的理论知识得出的，更是从今天的可视化工具JHSDB来实践验证了这一点