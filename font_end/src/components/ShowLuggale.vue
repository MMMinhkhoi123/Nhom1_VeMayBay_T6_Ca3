<template>
  <div class="bg_luggage">Helo cac ban</div>
  <div class="from_luggage">
    <div class="left">
      <div class="content">
        <h2>Hành Lý</h2>
        <h4>Chọn Chuyến bay</h4>
        <span>Thêm hành lý kí gửi vào chuyến bay của bạn</span>
      </div>
      <div class="item1">
        <p>1.Đà nẵng - TPHCM</p>
        <div class="filer">
          <span class="kilogram">30kg</span>
          <span class="price">126.000 vnd</span>
        </div>
      </div>
      <div class="item2">
        <p>1.Đà nẵng - TPHCM</p>
        <div class="filer">
          <span class="kilogram">30kg</span>
          <span class="price">126.000 vnd</span>
        </div>
      </div>
    </div>
    <div class="right">
      <h2>Đà nẳng - tp hcm</h2>
      <div class="firm">
        <img src="" width="30" height="30" alt="" />
        <span>Bamboo Airways</span>
      </div>
      <div class="form_select">
        <div class="tile_select">
          <span>Hàng Khách</span>
          <h4>Nguyễn Minh Khôi</h4>
        </div>
        <div class="sum_lugguge">
          <div>
            <span>Đã bao gồm (Miễn phí)</span>
            <h4>20kg</h4>
          </div>
          <div>
            <span>Tổng số hành lý</span>
            <h4>30kg</h4>
          </div>
        </div>
        <div class="select_luggage">
          <label>
            <input type="checkbox" />
            Không có hành lý bổ sung
          </label>
          <label>
            <input type="checkbox" />
            +20kg
          </label>
          <label>
            <input type="checkbox" />
            +30kg
          </label>
          <label>
            <input type="checkbox" />
            +20kg
          </label>
          <label>
            <input type="checkbox" />
            +30kg
          </label>
          <label>
            <input type="checkbox" />
            +20kg
          </label>
          <label>
            <input type="checkbox" />
            +30kg
          </label>
        </div>
      </div>
    </div>
    <div class="sum">
      <div class="sum_left">
        <div class="sum_let_chid">
          <h4>Tổng Tiền</h4>
          <h4>136.000 vnd</h4>
        </div>
        <p>(Đối với tất cả các chuyến bay và hành khách)</p>
      </div>
      <button
        @click="
          ($store.state.show_luggage = false),
            ($store.state.chonse_luggage = true)
        "
      >
        Hoàn Tất
      </button>
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
.form_select {
  background: rgb(255, 255, 255);
  border-radius: 10px;
  box-shadow: 0 0 4px black;
  margin: 10px;
}
.right {
  margin: 10px;
}
.sum_lugguge {
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
}
.select_luggage > label {
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 0 3px black;
  margin: 5px;
}
.select_luggage {
  position: relative;
  height: 300px;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
}
.select_luggage,
.tile_select {
  padding: 20px;
}
.firm {
  display: flex;
  align-items: center;
  padding: 10px;
  gap: 0 10px;
}
.content {
  padding: 10px;
}
.left {
  box-shadow: 0 0 4px black;
}
p {
  margin: 0;
  padding: 0;
}
.sum_let_chid {
  display: flex;
  gap: 10px;
}
.sum {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  box-shadow: 0 0 4px black;
}
button {
  padding: 10px 30px;
  background: orangered;
  border: none;
  border-radius: 5px;
  font-weight: 700;
  color: white;
}
.sum {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50px;
  background: rgb(255, 255, 255);
}
.item1 {
  background: rgb(172, 206, 235);
}
.item1,
.item2 {
  padding: 10px 30px;
}
.filer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
h4 {
  margin: 0;
}
.bg_luggage {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.386);
  z-index: 1000;
}
.from_luggage {
  display: grid;
  position: fixed;
  width: 800px;
  min-height: 500px;
  top: 50%;
  left: 50%;
  border-radius: 10px;
  transform: translate(-50%, -50%);
  z-index: 1001;
  background: white;
  grid-template-columns: 300px 1fr;
}
</style>
