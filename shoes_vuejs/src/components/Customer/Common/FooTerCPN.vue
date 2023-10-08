<template>
  <div class="footer">
    <ul>
      <div class="logo">
        <a @click="home"
          >airMax <strong class="hin">Pro</strong>
          <font-awesome-icon class="icon" :icon="['far', 'copyright']" />
        </a>
      </div>
      <li>Đảm bảo an toàn chất lượng</li>
      <li>xữ lý các vấn đề nhanh chống</li>
      <li>Uy tính thương hiệu hàng đầu việt nam</li>
      <li>Thanh toán nhanh chống</li>
    </ul>
    <div class="foter_right">
      <h4>Chúng tôi vì bạn</h4>
      <span>Hotline : 0329162803</span>
      <span>CN 1 Địa chỉ: 222/2222 Phan Duy Thủ đức TP Hồ Chí Minh</span>
      <span>CN 2 Địa chỉ: 223/2222 Phan Duy Thủ đức TP Hồ Chí Minh</span>
    </div>
  </div>
</template>
<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  justify-content: start;
}
li {
  margin-bottom: 4px;
  text-align: start !important;
}
.footer {
  text-align: center;
  margin-top: auto;
  height: 200px;
  background: rgb(30, 29, 29);
  color: white;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.foter_right {
  display: flex;
  flex-direction: column;
}
.logo {
  display: flex;
  padding: 20px;
  font-weight: 800;
}
.hin {
  color: rgb(64, 31, 255);
}
a {
  font-size: 30px;
  color: white !important;
}
</style>
