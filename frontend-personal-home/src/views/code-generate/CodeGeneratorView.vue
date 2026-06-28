<template>
  <section class="code-generator-page">
    <aside class="output-nav">
      <el-menu :default-active="form.outputType" @select="handleOutputTypeSelect">
        <el-menu-item
          v-for="item in outputTypeOptions"
          :key="item.value"
          :index="item.value"
        >
          {{ item.label }}
        </el-menu-item>
      </el-menu>
    </aside>

    <main class="generator-main">
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
  </section>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { generateCode, type CodeGenerateRequest, type OutputType } from '@/api/codeGenerator'

interface OutputTypeOption {
  label: string
  value: OutputType
}

const outputTypeOptions: OutputTypeOption[] = [
  { label: '数据库假数据生成 SQL', value: 'FAKE_DATA_INSERT_SQL' },
  { label: 'projection生成', value: 'PROJECTION' },
  { label: 'bean生成', value: 'BEAN' },
  { label: 'resVo生成', value: 'RES_VO' },
  { label: 'reqVo生成', value: 'REQ_VO' },
]

const form = reactive<CodeGenerateRequest>({
  inputType: 'CREATE_TABLE_SQL',
  databaseType: 'POSTGRESQL',
  outputType: 'FAKE_DATA_INSERT_SQL',
  inputContent: '',
})

const generating = ref(false)
const outputContent = ref('')

const handleOutputTypeSelect = (value: string) => {
  form.outputType = value as OutputType
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
  justify-content: flex-end;
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
