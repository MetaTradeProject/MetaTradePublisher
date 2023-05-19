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
提交一条`Fake Trade`需要以下字段：
- `Store Address`：非用户（商店）节点的地址
- `Receiver Address`：接收物品的节点地址，需要是真实节点
- `item id`：提交的物品id（`0`默认为`coin`）
- `amount`：数量

其中，`Store Address`和`item id`需要在`Url`路径中直接请求，可以通过其他的`GET`请求来获得所有`Store`或者`Item`的信息，请参阅[`Api`接口介绍](#`Api`接口介绍)

### 如何使用`Cron job`来实现自定义的`Fake Trade`服务
todo

## `Api`接口介绍
### 查询所有商店信息
- `path`: `/stores`
- `method`: `GET`
- `response`:
```json
[
    {
        "address": "123", 
        "id": "123",
        "description": "none"
    },

    {
        "address": "123", 
        "id": "123",
        "description": "none"
    }
]
```

### 查询指定商店信息
- `path`: `/store/{address}`
- `path params`: `address`
- `method`: `GET`
- `response`:
```json
{
    "address": "123", 
    "id": "123",
    "description": "none"
}
```
- `status code`: `404 - Store not found`

### 查询指定商店所有物品信息
- `path`: `/store/{address}/items`
- `path params`: `address`
- `method`: `GET`
- `response`:
```json
[
    {
        "id": "123",
        "amount": 500,
        "store_address": "123", 
        "description": "none"
    },
    {
        "id": "1234",
        "amount": 500,
        "store_address": "456", 
        "description": "none"
    }
]
```
- `status code`: `404 - Store not found`

### 查询指定商店指定物品信息
- `path`: `/store/{address}/item/{item_id}`
- `path params`: `address`, `item_id`
- `method`: `GET`
- `response`:
```json
{
    "id": "1234",
    "amount": 500,
    "store_address": "456", 
    "description": "none"
}
```
- `status code`: `404 - Store, Item not found`

### 提交单次`Fake Trade`信息（即刻生效，一次生命周期）
- `path`: `/store/{address}/item/{item_id}/simple-trade`
- `path params`: `address`, `item_id`
- `method`: `POST`
- `request`:
```json
{
    "receiverAddress": "1234",
    "amount": 100
}
```
- `response`:
```json
{
    "res": "ok"
}
```
- `status code`: `404 - Store or item not found`