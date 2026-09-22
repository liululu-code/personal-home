<template>
  <div class="info-card">
    <div class="card-header">
      <div class="card-type">
        <div class="card-box">
          <img class="card-icon" :src="item.icon" alt="" />
        </div>

        <span>{{ item.name }}</span>
      </div>

      <span class="sequence">
        {{ item.sequenceNum }}
      </span>
    </div>

    <div class="card-main">
      <h2 class="card-title">
        {{ item.title }}
      </h2>

      <div class="card-sub-title">
        {{ item.subTitle }}
      </div>

      <!--
        这里不规定具体内容。父组件决定这里放什么。
      -->
      <div class="card-content">
        <slot name="content"></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps({
  item: {
    type: Object,
    required: true,
  },
})
</script>

<style scoped lang="scss">
.info-card {
  width: 100%;
  min-height: 220px;
  padding: 20px;

  border: 1px solid #eee;
  border-radius: 16px;
  background-color: #fcfbf7;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.card-type {
  display: flex;
  align-items: center;
  gap: 10px;

  font-size: 14px;
  font-weight: bold;
  letter-spacing: 2px;
  color: var(--card-color);
}

.card-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  background-color: var(--card-color);

  .card-icon {
    width: 70%;
    aspect-ratio: 1;
  }
}

.sequence {
  font-size: 20px;
  color: var(--card-color);
}

.card-title {
  margin: 24px 0 4px;

  font-size: 24px;
}

.card-sub-title {
  margin-bottom: 18px;

  font-size: 12px;
}

.card-content {
  font-size: 14px;
  line-height: 1.6;
}
</style>
