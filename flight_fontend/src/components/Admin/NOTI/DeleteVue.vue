<template>
  <div class="Notification">
    <h1>Thông báo</h1>
    <div class="content">
      <slot name="content"></slot>
    </div>
    <div class="setting">
      <slot name="button"></slot>
    </div>
  </div>
</template>
<script>
export default {
  setup() {
    return {};
  },
};
</script>
<style scoped>
.Notification {
  text-align: center;
  position: fixed;
  z-index: 100;
  top: -100%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  min-width: 300px;
  opacity: 0;
  transition: 0.3s;
  padding: 20px;
  border-radius: 10px;
}
.setting {
  display: flex;
  gap: 10px;
  justify-content: center;
}
.content {
  padding: 10px;
}
</style>
