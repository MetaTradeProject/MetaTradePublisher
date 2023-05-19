# MetaTradePublisher
集成自定义`Fake Trade`功能的`Http`服务，作为`Metatrade Gateway`的功能补充

## 目录

## `Fake Trade`服务介绍
### `Fake Trade`
`Fake Trade`专指由非用户发起的交易(`Sender Address`不代表具体的用户`Node`节点)

在实际情况中，某些签到服务，奖励服务等的实现，本质上是某些非用户节点的`Coin`流出，用户节点`Coin`流入，在这一过程中，用户是被动参加了这一次交易，因为不应该由用户主动发起

接下来列举几个常见的用户互动交易场景，来展示`Fake trade`是如何工作的：
- 物品购买服务：
将此类过程抽象成两个`Trade`：
1. 用户(`sender`)主动发起，商店(`receiver`)为接收方
2. 本地客户端主动请求(`post`)添加一条商店(`sender`)发起，用户(`receiver`)为接收方的`Fake Trade`

- 奖励服务：
可以抽象成上一个例子只有第二个的服务，可以通过客户端逻辑提交实现

为了方便批量`Fake Trade`的部署，服务端支持`Load Trades`功能，即动态读取`Cron Job`配置，来配置如签到，奖励等基本任务

- 借贷服务：
Todo

`Fake Trade`的签名过程在服务端进行，因此客户端逻辑只需要实现提交请求

### 如何使用`Fake Trade`服务

### 如何使用`Cron job`来实现自定义的`Fake Trade`服务

## `Api`接口介绍