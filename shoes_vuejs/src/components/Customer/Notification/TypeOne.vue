<template>
  <div class="infomation">
    <div class="title">
      <slot name="title"></slot>
    </div>
    <div class="content">
      <slot name="content"></slot>
    </div>
    <div class="button">
      <slot name="button"></slot>
    </div>
  </div>
</template>
<style scoped>
.infomation {
  position: fixed;
  top: -100%;
  transition: 0.4s;
  left: 50%;
  transform: translate(-50%, -50%);
  background: rgb(255, 255, 255);
  min-width: 500px;
  min-height: 200px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  z-index: 100;
  padding: 20px;
}
.title,
.content {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 10px;
}
.button {
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
