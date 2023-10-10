<template>
  <!-- mess -->
  <div class="info">
    <slot name="content"></slot>
    <font-awesome-icon class="close_mess" :icon="['fas', 'xmark']" />
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
  padding: 20px 40px;
  background: red;
  opacity: 0;
  transition: 0.6s;
  color: white;
  font-weight: 700;
}
.close_mess {
  position: absolute;
  top: 0;
  right: 0;
  margin: 10px;
  font-size: 20px;
}
</style>
