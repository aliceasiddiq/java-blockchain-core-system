# Java Blockchain Core System
企业级区块链底层核心系统，基于Java开发，支持分布式账本、加密交易、智能合约、P2P网络、共识机制、挖矿激励等完整区块链功能，可直接用于联盟链、公链、数字资产发行等场景。

## 项目特性
- 完整的区块链核心结构与区块生成机制
- SHA-256加密、RSA非对称加密签名验证
- 去中心化P2P网络通信与节点管理
- 工作量证明(PoW)共识机制
- 钱包管理、交易验证、交易池缓存
- 智能合约部署与执行
- 区块数据持久化、监控、统计
- RESTful API服务、容器化部署
- 多配置文件支持、测试用例完备

## 文件清单与功能说明
1. BlockchainCore.java - 区块链核心管理类，负责链创建、区块添加、合法性校验
2. Block.java - 区块实体类，包含哈希计算、挖矿逻辑
3. CryptoUtils.java - 通用加密工具类，支持RSA加解密、签名
4. Wallet.java - 数字钱包，支持地址生成、余额查询、交易创建
5. Transaction.java - 交易实体类，包含签名与合法性校验
6. HashUtil.java - 哈希工具类，提供SHA256/MD5通用哈希方法
7. P2PNetwork.java - P2P网络管理，节点广播与同步
8. Node.java - 网络节点实体，处理区块与交易接收
9. ConsensusMechanism.java - 共识机制实现，全网链同步
10. SmartContract.java - 智能合约基类，支持状态管理与函数执行
11. ContractDeployer.java - 合约部署器，生成合约地址并上链
12. TransactionPool.java - 交易池，缓存待打包交易
13. Miner.java - 矿工类，支持挖矿与奖励发放
14. BlockchainMonitor.java - 区块链状态监控器
15. ChainSyncService.java - 链同步服务，定时同步全网区块
16. AddressGenerator.java - 地址生成器，生成钱包/节点地址
17. DataSerializer.java - 数据序列化工具，JSON持久化存储
18. ChainConfig.java - 全局配置常量类
19. TransactionValidator.java - 交易合法性批量校验
20. BlockParser.java - 区块解析工具，提取交易与合约信息
21. NodeHealthCheck.java - 节点健康检查与故障剔除
22. TokenContract.java - 代币合约，支持转账、余额查询
23. ChainStatistics.java - 区块链数据统计分析
24. ApiServer.java - REST API服务，提供链查询/交易提交接口
25. GenesisBlockCreator.java - 创世区块生成器
26. EncryptedStorage.java - 加密存储工具，安全保存链数据
27. PeerManager.java - 节点对等网络管理
28. ContractExecutor.java - 合约执行器，自动执行链上合约
29. BlockValidator.java - 区块完整性与合法性校验
30. MainApplication.java - 项目主启动类
31. log_config.json - 日志配置文件
32. node_config.yaml - 节点网络配置文件
33. Dockerfile - 容器化部署配置
34. build.gradle - Gradle构建脚本
35. BlockchainTest.java - 单元测试用例
36. MessageSigner.java - 消息签名与验签工具
37. MempoolMonitor.java - 交易池监控工具
38. NetworkMessage.java - 网络消息协议实体
39. ChainExporter.java - 区块链数据导出工具(CSV/报告)
40. SystemMonitor.java - 系统资源监控工具

## 技术栈
- 开发语言：Java（主）、JSON/YAML/Dockerfile（辅助）
- 加密算法：SHA-256、RSA
- 共识机制：Proof of Work
- 网络：P2P、REST API
- 存储：文件持久化、JSON序列化
- 部署：Docker、Gradle

## 快速启动
1. 编译项目：gradle clean build
2. 启动主程序：运行 MainApplication.java
3. API服务：http://localhost:8080/chain
4. 查看区块链状态：控制台输出监控信息

## 适用场景
- 数字资产发行与管理
- 联盟链底层系统
- 区块链教学与研究
- 去中心化应用(DApp)底层支撑
