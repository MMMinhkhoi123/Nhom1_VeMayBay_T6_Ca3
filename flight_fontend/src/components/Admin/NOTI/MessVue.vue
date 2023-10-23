<template>
  <!-- mess -->
  <div class="info">
    <slot name="content"></slot>
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
/* mess */
.info {
  position: fixed;
  left: -100%;
  bottom: 20px;
  padding: 10px 40px;
  background: red;
  opacity: 0;
  transition: 0.6s;
  font-weight: 700;
  border-radius: 10px;
}
.close_mess {
  position: absolute;
  top: 0;
  right: 0;
  margin: 10px;
  font-size: 20px;
}
</style>
