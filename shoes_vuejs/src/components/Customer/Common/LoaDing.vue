<template>
  <div class="loading"></div>
  <div class="group_load">
    <span class="item delay1"></span>
    <span class="item delay2"></span>
    <span class="item delay3"></span>
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
.loading {
  background: rgba(7, 7, 7, 0.772);
  position: fixed;
  inset: 0;
  z-index: 300;
}
.group_load {
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  top: 50%;
  left: 50%;
  z-index: 301;
  transform: translate(-50%, -50%);
}
.group_load > span {
  font-weight: 900;
  color: white;
  animation: animation 0.5s ease-in infinite;
}
.item {
  width: 10px;
  height: 10px;
  display: block;
  margin: 5px;
  border-radius: 50%;
  background: white;
  z-index: 1;
}
.delay1 {
  animation-delay: 0.1s !important;
}
.delay2 {
  animation-delay: 0.5s !important;
}
.delay3 {
  animation-delay: 0.9s !important;
}
@keyframes animation {
  0% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
  100% {
    transform: translateY(0);
  }
}
</style>
