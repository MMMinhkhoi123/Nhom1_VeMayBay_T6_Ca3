<template>
  <div class="navbar">
    <div class="content"><strong>Admin-Creater</strong> wealcome</div>
    <div class="acount">
      <h4>Mai Vu Viet Han</h4>
      <img
        src="https://tapchianhdep.com/wp-content/uploads/2017/06/hinh-anh-hot-girl-xinh-de-thuong-1.jpg"
      />
    </div>
  </div>
</template>
<script>
export default {
  setup() {},
};
</script>
<style scoped>
.navbar {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  background: white;
  display: flex;
  justify-content: space-between;
}
img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}
.acount,
.content {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 0 30px;
  padding: 10px;
  border-radius: 0 0 30px 20px;
}
.content {
  font-size: 20px;
}
</style>
