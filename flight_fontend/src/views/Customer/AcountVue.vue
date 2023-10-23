<template>
  <div class="bg">
    <div class="def_bg"></div>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  setup() {},
};
</script>
<style scoped>
.bg {
  height: 100vh;
}
.def_bg {
  background: url("https://wallpaperaccess.com/full/398881.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  position: absolute;
  inset: 0;
  filter: brightness(60%);
  z-index: 0;
}
</style>
