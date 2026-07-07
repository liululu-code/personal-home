<template>
  <section class="code-generator-page">
    <aside class="output-nav">
      <el-menu :default-active="activeMenu" @select="handleMenuSelect">
        <el-menu-item index="LOCAL_GENERATE">
          本地生成
        </el-menu-item>
        <el-menu-item
          v-for="item in outputTypeOptions"
          :key="item.value"
          :index="item.value"
        >
          {{ item.label }}
        </el-menu-item>
      </el-menu>
    </aside>

    <main v-if="activeMenu !== 'LOCAL_GENERATE'" class="generator-main">
      <el-form class="generator-form" label-position="top">
        <div class="selector-row">
          <el-form-item label="输入类型">
            <el-select v-model="form.inputType">
              <el-option label="建表 SQL" value="CREATE_TABLE_SQL" />
            </el-select>
          </el-form-item>
          <el-form-item label="数据库类型">
            <el-select v-model="form.databaseType">
              <el-option label="PostgreSQL" value="POSTGRESQL" />
            </el-select>
          </el-form-item>
        </div>

        <el-form-item label="输入内容">
          <el-input
            v-model="form.inputContent"
            :autosize="{ minRows: 12, maxRows: 24 }"
            placeholder="请输入数据库建表 SQL"
            type="textarea"
          />
        </el-form-item>

        <div class="action-row">
          <el-button :loading="generating" type="primary" @click="handleGenerate">
            生成
          </el-button>
        </div>
      </el-form>

      <section class="result-section">
        <div class="result-title">生成结果</div>
        <el-input
          v-model="outputContent"
          :autosize="{ minRows: 12, maxRows: 24 }"
          readonly
          type="textarea"
        />
      </section>
    </main>

    <main v-else class="generator-main">
      <el-form class="generator-form" label-position="top">
        <div class="selector-row">
          <el-form-item label="数据库类型">
            <el-select v-model="localForm.databaseType">
              <el-option label="PostgreSQL" value="POSTGRESQL" />
            </el-select>
          </el-form-item>
          <el-form-item label="本地生成文件夹">
            <el-input
              v-model="localForm.outputDirectory"
              placeholder="请输入本地生成文件夹，例如 D:\codeGenerator"
            />
          </el-form-item>
          <el-form-item label="Entity 包名">
            <el-input
              v-model="localForm.packageName"
              placeholder="请输入包名，例如 top.lll44556.demo.entity"
            />
          </el-form-item>
          <el-form-item label="Entity 类名">
            <el-input
              v-model="localForm.entityClassName"
              placeholder="请输入类名，例如 UserEntity"
            />
          </el-form-item>
          <el-form-item label="响应类包名">
            <el-input
              v-model="localForm.responseClassPackageName"
              placeholder="请输入响应类包名，例如 top.lll44556.common.util"
            />
          </el-form-item>
          <el-form-item label="响应类名称">
            <el-input
              v-model="localForm.responseClassName"
              placeholder="请输入响应类名称，例如 Result"
            />
          </el-form-item>
          <el-form-item label="成功响应函数">
            <el-input
              v-model="localForm.responseSuccessMethodName"
              placeholder="请输入成功响应函数名，例如 success"
            />
          </el-form-item>
        </div>

        <el-form-item label="建表语句">
          <el-input
            v-model="localForm.createTableSql"
            :autosize="{ minRows: 10, maxRows: 20 }"
            placeholder="请输入 PostgreSQL 建表 SQL"
            type="textarea"
          />
        </el-form-item>

        <el-form-item label="本地生成内容">
          <el-checkbox-group v-model="localForm.contentTypes" class="content-type-group">
            <el-checkbox
              v-for="item in localContentTypeOptions"
              :key="item.value"
              :label="item.value"
            >
              {{ item.label }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <div class="action-row">
          <el-button :loading="parsingLocalTable" @click="handleParseLocalTable">
            解读字段
          </el-button>
          <el-button :loading="generatingLocal" type="primary" @click="handleLocalGenerate">
            本地生成
          </el-button>
        </div>
      </el-form>

      <section class="result-section">
        <div class="result-title">字段解读结果</div>
        <el-table :data="editableLocalColumns" border>
          <el-table-column prop="columnName" label="字段名" min-width="140" />
          <el-table-column prop="dataType" label="类型" min-width="120" />
          <el-table-column prop="entityType" label="Entity 类型" min-width="170">
            <template #default="{ row }: { row: LocalColumnInfo }">
              <el-select
                v-model="row.entityType"
                allow-create
                default-first-option
                filterable
                placeholder="请选择或输入类型"
              >
                <el-option
                  v-for="item in entityTypeOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="entityName" label="Entity 名称" min-width="170">
            <template #default="{ row }: { row: LocalColumnInfo }">
              <el-input v-model="row.entityName" placeholder="请输入成员名称" />
            </template>
          </el-table-column>
          <el-table-column prop="length" label="长度" width="100">
            <template #default="{ row }: { row: LocalColumnInfo }">
              {{ row.length ?? '-' }}
            </template>
          </el-table-column>
          <el-table-column prop="primaryKey" label="主键" width="90">
            <template #default="{ row }: { row: LocalColumnInfo }">
              {{ row.primaryKey ? '是' : '否' }}
            </template>
          </el-table-column>
          <el-table-column prop="nullable" label="可空" width="90">
            <template #default="{ row }: { row: LocalColumnInfo }">
              {{ row.nullable ? '是' : '否' }}
            </template>
          </el-table-column>
          <el-table-column prop="defaultValue" label="默认值" min-width="140">
            <template #default="{ row }: { row: LocalColumnInfo }">
              {{ row.defaultValue ?? '-' }}
            </template>
          </el-table-column>
          <el-table-column prop="comment" label="字段注释" min-width="170">
            <template #default="{ row }: { row: LocalColumnInfo }">
              <el-input v-model="row.comment" placeholder="请输入字段注释" />
            </template>
          </el-table-column>
        </el-table>
      </section>

      <section class="result-section">
        <div class="result-title">本地生成结果</div>
        <el-input
          v-model="localGenerateResult"
          :autosize="{ minRows: 6, maxRows: 12 }"
          readonly
          type="textarea"
        />
      </section>
    </main>
  </section>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import {
  generateCode,
  generateLocalCode,
  parseLocalTable,
  type CodeGenerateRequest,
  type LocalColumnInfo,
  type LocalEntityField,
  type LocalGenerateContentType,
  type LocalGenerateRequest,
  type LocalParseTableResponse,
  type OutputType,
} from '@/api/code-generator/codeGenerator'

interface OutputTypeOption {
  label: string
  value: OutputType
}

type CodeGeneratorMenu = OutputType | 'LOCAL_GENERATE'

interface LocalContentTypeOption {
  label: string
  value: LocalGenerateContentType
}

const outputTypeOptions: OutputTypeOption[] = [
  { label: '数据库假数据生成 SQL', value: 'FAKE_DATA_INSERT_SQL' },
  { label: 'projection生成', value: 'PROJECTION' },
  { label: 'bean生成', value: 'BEAN' },
  { label: 'resVo生成', value: 'RES_VO' },
  { label: 'reqVo生成', value: 'REQ_VO' },
]

const localContentTypeOptions: LocalContentTypeOption[] = [
  { label: 'Entity', value: 'ENTITY' },
  { label: 'Bean', value: 'BEAN' },
  { label: 'Repository', value: 'REPOSITORY' },
  { label: 'NativeQuery', value: 'NATIVE_QUERY' },
  { label: 'NativeQueryPostgreSQL', value: 'NATIVE_QUERY_POSTGRESQL' },
  { label: 'Service', value: 'SERVICE' },
  { label: 'ServiceImpl', value: 'SERVICE_IMPL' },
  { label: 'Controller', value: 'CONTROLLER' },
  { label: 'Convert', value: 'CONVERT' },
  { label: 'ReqVO', value: 'REQ_VO' },
  { label: 'ResVO', value: 'RES_VO' },
]

const entityTypeOptions = [
  'String',
  'Long',
  'Integer',
  'Boolean',
  'BigDecimal',
  'LocalDate',
  'LocalDateTime',
]

const activeMenu = ref<CodeGeneratorMenu>('FAKE_DATA_INSERT_SQL')

const form = reactive<CodeGenerateRequest>({
  inputType: 'CREATE_TABLE_SQL',
  databaseType: 'POSTGRESQL',
  outputType: 'FAKE_DATA_INSERT_SQL',
  inputContent: '',
})

const localForm = reactive<LocalGenerateRequest>({
  databaseType: 'POSTGRESQL',
  createTableSql: '',
  packageName: 'top.lll44556',
  entityClassName: '',
  contentTypes: ['ENTITY', 'REPOSITORY', 'SERVICE', 'SERVICE_IMPL', 'CONTROLLER'],
  entityFields: [],
  outputDirectory: 'D:\\codeGenerator',
  responseClassPackageName: 'top.lll44556.common.util',
  responseClassName: 'Result',
  responseSuccessMethodName: 'success',
})

const generating = ref(false)
const outputContent = ref('')
const parsingLocalTable = ref(false)
const generatingLocal = ref(false)
const localParsedTable = ref<LocalParseTableResponse | null>(null)
const editableLocalColumns = ref<LocalColumnInfo[]>([])
const localGenerateResult = ref('')

const handleMenuSelect = (value: string) => {
  activeMenu.value = value as CodeGeneratorMenu
  if (value !== 'LOCAL_GENERATE') {
    form.outputType = value as OutputType
  }
}

const handleGenerate = async () => {
  if (!form.inputContent.trim()) {
    ElMessage.warning('请输入数据库建表 SQL')
    return
  }

  generating.value = true
  try {
    // 当前只打通前后端生成链路，具体 SQL 解析与假数据规则由后续迭代补充。
    const response = await generateCode({ ...form })
    outputContent.value = response.data.outputContent
  } finally {
    generating.value = false
  }
}

const handleParseLocalTable = async () => {
  if (!localForm.createTableSql.trim()) {
    ElMessage.warning('请输入数据库建表 SQL')
    return
  }

  parsingLocalTable.value = true
  try {
    // 本地生成先只做字段结构解读，复杂的字段注释、Java 类型映射和模板变量后续在后端统一补齐。
    const response = await parseLocalTable({
      databaseType: localForm.databaseType,
      createTableSql: localForm.createTableSql,
    })
    localParsedTable.value = response.data
    // 字段解读结果是后续生成的唯一 Entity 数据源，用户修改后的类型和名称会随生成请求提交到后端。
    editableLocalColumns.value = response.data.columns.map((column) => ({ ...column }))
    ElMessage.success('字段解读完成')
  } finally {
    parsingLocalTable.value = false
  }
}

const handleLocalGenerate = async () => {
  if (!localForm.createTableSql.trim()) {
    ElMessage.warning('请输入数据库建表 SQL')
    return
  }
  if (!localForm.outputDirectory.trim()) {
    ElMessage.warning('请输入本地生成文件夹')
    return
  }
  if (!localForm.packageName.trim()) {
    ElMessage.warning('请输入 Entity 包名')
    return
  }
  if (!localForm.entityClassName.trim()) {
    ElMessage.warning('请输入 Entity 类名')
    return
  }
  if (localForm.contentTypes.length === 0) {
    ElMessage.warning('请选择至少一种本地生成内容')
    return
  }
  if (editableLocalColumns.value.length === 0) {
    ElMessage.warning('请先解读字段')
    return
  }
  if (editableLocalColumns.value.some((column) => !column.entityType.trim() || !column.entityName.trim())) {
    ElMessage.warning('请完善每个字段的 Entity 类型和名称')
    return
  }

  generatingLocal.value = true
  try {
    // 当前后端只返回计划生成结果，真实文件写入逻辑保留 TODO，先确保前后端请求链路稳定。
    const entityFields: LocalEntityField[] = editableLocalColumns.value.map((column) => ({
      columnName: column.columnName,
      entityType: column.entityType.trim(),
      entityName: column.entityName.trim(),
      entityComment: column.comment?.trim() || '',
    }))
    const response = await generateLocalCode({ ...localForm, entityFields })
    localGenerateResult.value = [
      response.data.message,
      `输出目录：${response.data.outputDirectory}`,
      ...response.data.plannedFiles,
    ].join('\n')
  } finally {
    generatingLocal.value = false
  }
}
</script>

<style lang="scss" scoped>
.code-generator-page {
  display: grid;
  grid-template-columns: 220px minmax(0, 1fr);
  gap: 20px;
  align-items: start;
}

.output-nav {
  border-right: 1px solid var(--el-border-color-light);
}

.output-nav :deep(.el-menu) {
  border-right: 0;
}

.generator-main {
  display: flex;
  min-width: 0;
  flex-direction: column;
  gap: 20px;
}

.generator-form,
.result-section {
  width: 100%;
}

.selector-row {
  display: grid;
  grid-template-columns: repeat(2, minmax(180px, 1fr));
  gap: 16px;
}

.action-row {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
}

.content-type-group {
  display: flex;
  flex-wrap: wrap;
  gap: 8px 16px;
}

.result-title {
  margin-bottom: 8px;
  font-size: 14px;
  font-weight: 600;
}

@media (max-width: 768px) {
  .code-generator-page {
    grid-template-columns: 1fr;
  }

  .output-nav {
    border-right: 0;
    border-bottom: 1px solid var(--el-border-color-light);
  }

  .selector-row {
    grid-template-columns: 1fr;
  }
}
</style>
