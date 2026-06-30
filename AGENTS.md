


# 项目概览

本仓库是一个前后端分离的全栈项目：

- 前端目录：`frontend-personal-home`
- 前端技术栈：`pnpm + Vue 3 + Vite + TypeScript + Element Plus`
- 后端目录：`backend-personal-home`
- 后端技术栈：`Spring Boot 3 + Maven + Java 17 + PostgreSQL`

# 目录约定

- `frontend-personal-home`：前端应用代码、单元测试、E2E 测试
- `backend-personal-home`：后端应用代码、接口测试、单元测试
- 根目录：仅放置仓库级说明、协作规范与必要配置文件


# 常见错误避免

1. 在 Windows PowerShell 中编写命令时，禁止自定义变量名使用 `$PID`、`$PSVersionTable` 等内置只读或自动变量；涉及进程 ID 等场景时，统一使用 `$targetPid`、`$processId` 等非保留名称，避免出现变量不可写错误。
2. 使用 Windows PowerShell 中读取包含中文的仓库文件时，必须显式指定 UTF-8 编码，例如使用 `Get-Content -Encoding UTF8 -Path <文件路径>`；禁止直接使用未指定编码的 `Get-Content` 判断文件内容，避免将 UTF-8 无 BOM 文件误读为 GBK/ANSI 导致中文乱码。



# 开发原则

1. 未明确要求时，不要重复造轮子，优先复用成熟库、官方组件、Spring / Vue 生态已有方案。
2. 所有代码和注释统一按 UTF-8 读取与编写，避免中文乱码；在 Windows PowerShell 环境下读取文件时应显式指定 `-Encoding UTF8`。
3. 重要变量、核心函数、复杂逻辑必须添加中文注释，注释应解释“为什么这样做”以及关键约束。
4. 前端代码必须符合 TypeScript 编码规范，优先保证类型完整，避免滥用 `any`。
5. 后端数据库访问使用 Spring Data 技术栈实现。
6. 修改现有代码前，如果对当前代码调用逻辑不清楚，必须先阅读相关代码，了解整体调用逻辑，然后再修改；如果已经清楚，可以直接修改。


# 前端约束

- 包管理器统一使用 `pnpm`
- 前端开发目录固定为 `frontend-personal-home`
- 优先复用现有 Vue 组件、组合式 API、Pinia、Vue Router 配置
- 样式或页面改动应兼顾桌面端与移动端基本可用性
- 新增逻辑时应补充必要的类型声明与中文注释

# 后端约束

- 后端开发目录固定为 `backend-personal-home`
- 关键业务函数建议补充单元测试，确保行为符合预期
- 提交前必须保证相关测试通过
- 优先使用 Spring Boot 官方推荐方式组织配置、依赖注入、参数校验与测试
- 后端接口入参与出参统一使用 `vo` 包组织，不使用 `record`；例如代码生成模块放在 `src/main/java/top/lll44556/codeGenerator/vo/codeGenerator` 下，并按 `req`、`res` 子包区分请求与响应对象。
- 后端 VO 类统一使用普通 `class`，并使用 Lombok 注解简化样板代码，至少包含 `@Data`、`@NoArgsConstructor`、`@AllArgsConstructor`，便于 Spring JSON 反序列化和后续字段扩展。
- Controller 对外响应统一使用 `backend-personal-home/common/src/main/java/common/lll44556/top/util/R.java` 进行封装，例如返回 `R.ok(data)`，避免直接返回裸 VO。

# 常用命令

## 前端

在 `frontend-personal-home` 目录执行：

```bash
pnpm install
pnpm dev
pnpm build
pnpm type-check
pnpm lint
pnpm test:unit
pnpm test:e2e
```

## 后端

在 `backend-personal-home` 目录执行：

```bash
./mvnw spring-boot:run
./mvnw test
./mvnw clean package
```


# 变更要求

1. 涉及前端改动时，至少执行受影响范围内的类型检查、Lint 或测试。
2. 不要顺手修复与当前任务无关的问题，除非它阻塞当前实现。
3. 更新依赖、脚手架配置、构建流程时，应同步更新相关文档或说明。
4. 新增或修改复杂业务代码时，必须按功能分段添加中文注释；不仅要给函数添加注释，函数实现内部的关键语句块也要添加中文注释，说明该段代码的职责、为什么这样处理以及关键约束，不能只描述语法动作。
5. 每次修改 `AGENTS.md` 文件时，必须在最终回复中打印本次新增或修改的具体内容。

# Git 规则

1. 未经明确要求，不要执行破坏性 Git 操作，如 `reset --hard`、强制覆盖、删除用户未确认内容。
2. 分支合并默认使用 `--no-ff`，除非用户明确指定其他方式。
3. 提交前先确认本次修改范围，避免把无关文件一起提交。

# 协作建议

- 开始实现前，先确认修改发生在 `frontend-personal-home`、`backend-personal-home` 还是两者联动。
- 优先给出可验证的结果，例如测试通过、构建通过、接口可调用。
- 如果发现仓库约束与实际结构不一致，应先说明差异，再继续修改。
