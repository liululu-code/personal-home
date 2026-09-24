<template>
  <FileTransferCardView
    :status="transferStatus"
    :progress="progress"
    :source-device="sourceDevice"
    :destination-device="destinationDevice"
    estimated-time="2 minutes"
    transfer-rate="12 MB/s"
    file-types="Images, Videos"
    total-file-size="1.2 GB"
    @cancel="handleCancel"
    @toggle-pause="handleTogglePause"
  />
</template>

<script setup lang="ts">
import FileTransferCardView from '@/views/learn-demo/file-transfer-card/FileTransferCardView.vue'
import { ref } from 'vue'

type TransferStatus = 'in-progress' | 'paused' | 'completed' | 'connecting'

const transferStatus = ref<TransferStatus>('in-progress')
const progress = ref(35)

const sourceDevice = {
  name: 'My Phone',
  type: 'phone' as const,
}

const destinationDevice = {
  name: 'Office Laptop',
  type: 'laptop' as const,
}

const handleCancel = () => {
  progress.value = 0
  console.log('已取消文件传输')
}

const handleTogglePause = () => {
  transferStatus.value =
    transferStatus.value === 'paused' ? 'in-progress' : 'paused'
}
</script>
