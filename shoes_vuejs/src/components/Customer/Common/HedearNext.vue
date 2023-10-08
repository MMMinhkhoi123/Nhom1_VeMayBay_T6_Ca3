<template>
  <div class="header" style="z-index: 100">
    <div class="logo">
      <a @click="home">airMax <strong class="hin">Pro</strong> </a>
    </div>
    <div class="plan">
      <div class="ob">
        <span
          :style="
            numbers(this.$route.query.step) >= 1 ? { background: 'red' } : null
          "
          >1</span
        >
        Đặt
      </div>
      <span class="hef"></span>
      <div class="ob">
        <span
          :style="
            numbers(this.$route.query.step) >= 1 ? { background: 'red' } : null
          "
          >2</span
        >
        Xem Lại
      </div>
      <span class="hef"></span>
      <div class="ob">
        <span
          :style="
            numbers(this.$route.query.step) >= 3 ? { background: 'red' } : null
          "
          >3</span
        >
        Thanh Toán
      </div>
      <span class="hef"></span>
      <div class="ob">
        <span
          :style="
            numbers(this.$route.query.step) >= 4 ? { background: 'red' } : null
          "
          >4</span
        >
        vé điện tử
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
export default {
  setup() {
    const get = () => {
      router.push({ name: "list" });
    };
    const numbers = (value) => {
      return Number(value);
    };
    const home = () => {
      router.push({ name: "home" });
    };
    return { get, home, numbers };
  },
};
</script>
<style scoped>
.ob {
  display: flex;
  gap: 10px;
}
.ob > span {
  width: 20px;
  display: block;
  height: 20px;
  background: rgb(13, 12, 12);
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
}
.hef {
  width: 20px;
  height: 2px;
  background: rgb(255, 255, 255);
  margin: 0 5px;
}
.plan {
  display: flex;
  color: white;
  align-items: center;
}
.left {
  display: flex;
  gap: 30px;
}
.menu {
  display: flex;
  border: 1px solid black;
}
.menu > div {
  padding: 20px 10px;
}
.header {
  padding: 20px;
  display: flex;
  justify-content: space-between;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10;
}
.header > div {
  padding: 0 10px;
}
a {
  font-size: 30px;
  color: rgb(8, 22, 16);
}
.logo {
  font-weight: 800;
}
.hin {
  color: white;
}
</style>
