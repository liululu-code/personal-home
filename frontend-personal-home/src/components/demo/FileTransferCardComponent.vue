<template>
  <div class="file-transfer-card">
    <el-card shadow="never">
      <template #header>
        <h2 class="card-title">Smart WiFi Transfer</h2>
      </template>

      <div class="device-section">
        <div class="device-info">
          <component :is="deviceIcon(sourceDevice.type)" class="device-icon" />
          <span class="device-label">Sending from</span>
          <strong class="device-name">{{ sourceDevice.name }}</strong>
        </div>

        <div class="connection-status" aria-label="Devices are connected through WiFi">
          <Connection class="connection-icon" />
          <span class="connection-dot connection-dot--first"></span>
          <span class="connection-dot connection-dot--second"></span>
          <span class="connection-dot"></span>
        </div>

        <div class="device-info">
          <component :is="deviceIcon(destinationDevice.type)" class="device-icon" />
          <span class="device-label">Sending to</span>
          <strong class="device-name">{{ destinationDevice.name }}</strong>
        </div>
      </div>

      <section class="progress-section">
        <h3>Transfer progress</h3>
        <el-progress :percentage="normalizedProgress" :stroke-width="8" :show-text="false" />
        <p>Your file transfer is {{ normalizedProgress }}% completed</p>
      </section>

      <section class="transfer-details">
        <div class="details-title">
          <InfoFilled />
          <h3>Transfer Details</h3>
        </div>

        <dl class="details-list">
          <div>
            <dt>Estimated Time Remaining</dt>
            <dd>{{ estimatedTime }}</dd>
          </div>
          <div>
            <dt>Transfer Rate (Speed)</dt>
            <dd>{{ transferRate }}</dd>
          </div>
          <div>
            <dt>File types</dt>
            <dd>{{ fileTypes }}</dd>
          </div>
          <div>
            <dt>Total File Size</dt>
            <dd>{{ totalFileSize }}</dd>
          </div>
        </dl>
      </section>

      <div class="actions">
        <el-button :disabled="isCompleted" @click="emit('cancel')">Cancel</el-button>
        <el-button type="primary" :disabled="isCompleted" @click="emit('toggle-pause')">
          {{ status === 'paused' ? 'Resume' : 'Pause' }}
        </el-button>
      </div>

      <footer class="security-message">
        <Lock />
        <span>Your transfer is encrypted and secure</span>
      </footer>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { Connection, InfoFilled, Iphone, Lock, Monitor } from '@element-plus/icons-vue'
import { computed, type Component } from 'vue'

type TransferStatus = 'in-progress' | 'paused' | 'completed' | 'connecting'
type DeviceType = 'phone' | 'laptop'

interface TransferDevice {
  name: string
  type: DeviceType
}

interface FileTransferCardProps {
  status: TransferStatus
  progress: number
  sourceDevice: TransferDevice
  destinationDevice: TransferDevice
  estimatedTime: string
  transferRate: string
  fileTypes: string
  totalFileSize: string
}

const props = defineProps<FileTransferCardProps>()

const emit = defineEmits<{
  cancel: []
  'toggle-pause': []
}>()

const isCompleted = computed(() => props.status === 'completed')

// Element Plus 的进度条只接受 0 到 100，先规整外部数据，避免越界值破坏显示。
const normalizedProgress = computed(() => Math.min(100, Math.max(0, Math.round(props.progress))))

// 用组件映射替代重复模板，保证发送端和接收端使用一致的设备图标规则。
const deviceIcon = (type: DeviceType): Component => (type === 'laptop' ? Monitor : Iphone)
</script>

<style scoped lang="scss">
.file-transfer-card {
  width: min(100%, 448px);
  margin: 0 auto;

  :deep(.el-card) {
    overflow: hidden;
    border-radius: 12px;
  }

  :deep(.el-card__header) {
    padding: 20px 24px;
  }

  :deep(.el-card__body) {
    padding: 24px;
  }
}

.card-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  text-align: center;
}

.device-section {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 8px;
  margin-bottom: 32px;
  text-align: center;
}

.device-info {
  display: flex;
  min-width: 0;
  flex: 1;
  flex-direction: column;
  align-items: center;
  gap: 8px;
}

.device-icon {
  width: 40px;
  height: 40px;
  color: var(--el-text-color-secondary);
}

.device-label {
  color: var(--el-text-color-secondary);
  font-size: 12px;
}

.device-name {
  max-width: 100%;
  overflow: hidden;
  font-size: 14px;
  font-weight: 500;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.connection-status {
  display: flex;
  align-items: center;
  gap: 4px;
  padding-top: 8px;
  color: var(--el-color-primary);
}

.connection-icon {
  width: 20px;
  height: 20px;
}

.connection-dot {
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background-color: currentColor;
  animation: connection-pulse 1.5s ease-in-out infinite;

  &--first {
    animation-delay: -0.3s;
  }

  &--second {
    animation-delay: -0.15s;
  }
}

.progress-section {
  margin-bottom: 24px;

  h3 {
    margin: 0 0 8px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
  }

  p {
    margin: 8px 0 0;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    text-align: center;
  }
}

.transfer-details {
  padding: 12px 16px 16px;
  border: 1px solid var(--el-border-color-lighter);
  border-radius: 8px;
  background-color: var(--el-fill-color-light);
}

.details-title {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 12px;

  svg {
    width: 16px;
    height: 16px;
  }

  h3 {
    margin: 0;
    font-size: 16px;
    font-weight: 600;
  }
}

.details-list {
  display: flex;
  margin: 0;
  flex-direction: column;
  gap: 12px;

  div {
    display: flex;
    justify-content: space-between;
    gap: 16px;
    font-size: 14px;
  }

  dt {
    color: var(--el-text-color-secondary);
  }

  dd {
    margin: 0;
    font-weight: 500;
    text-align: right;
  }
}

.actions {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 16px;
  margin-top: 24px;

  .el-button {
    width: 100%;
    margin: 0;
  }
}

.security-message {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  margin-top: 24px;
  color: var(--el-text-color-secondary);
  font-size: 12px;

  svg {
    width: 14px;
    height: 14px;
  }
}

@keyframes connection-pulse {
  0%,
  100% {
    opacity: 0.35;
    transform: scale(0.8);
  }

  50% {
    opacity: 1;
    transform: scale(1);
  }
}

@media (max-width: 480px) {
  .file-transfer-card {
    :deep(.el-card__body) {
      padding: 20px 16px;
    }
  }

  .details-list div {
    flex-direction: column;
    gap: 4px;
  }

  .details-list dd {
    text-align: left;
  }
}

@media (prefers-reduced-motion: reduce) {
  .connection-dot {
    animation: none;
  }
}
</style>
