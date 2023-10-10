<template>
  <div class="inforlship">
    <h3>Thông tin liên hệ</h3>
    <div v-if="data == false" class="info_group">
      <div class="info_tilte">
        <div class="item_tile">
          <h4>Thông tin liên hệ (nhận vé/phiếu thanh toán)</h4>
          <a @click="checkinfo1">Lưu</a>
        </div>
      </div>
      <div class="info_content">
        <div class="name">
          <label for="">Họ (vd Nguyen)</label>
          <input
            v-model="$store.state.dataf1.lastname"
            type="text"
            name=""
            id=""
          />
          <span
            :style="$store.state.err.lastname != null ? { color: 'red' } : null"
            >{{
              $store.state.err.lastname == null
                ? "Tên Trên CMND (không dấu)"
                : $store.state.err.lastname
            }}
          </span>
        </div>
        <div class="lastname">
          <label for="">Tên Đệm - Tên (vd: Thi Ngoc Anh)* </label>
          <input
            v-model="$store.state.dataf1.firtname"
            type="text"
            name=""
            id=""
          />
          <span
            :style="$store.state.err.firtname != null ? { color: 'red' } : null"
            >{{
              $store.state.err.firtname == null
                ? "Tên Trên CMND (không dấu)"
                : $store.state.err.firtname
            }}
          </span>
        </div>
        <div class="phone">
          <label for="">Điện thoại di động*</label>
          <input
            v-model="$store.state.dataf1.phone"
            type="text"
            name=""
            id=""
          />
          <span
            :style="$store.state.err.phone != null ? { color: 'red' } : null"
            >{{ $store.state.err.phone == null ? "" : $store.state.err.phone }}
          </span>
        </div>
        <div class="email">
          <label for="">Email*</label>
          <input
            v-model="$store.state.dataf1.email"
            type="text"
            name=""
            id=""
          />
          <span
            :style="$store.state.err.email != null ? { color: 'red' } : null"
            >{{ $store.state.err.email == null ? "" : $store.state.err.email }}
          </span>
        </div>
      </div>
    </div>
    <div v-if="data" class="save_info_group">
      <div class="top_save">
        <h4>
          khách hàng :
          {{
            $store.state.dataf1.firtname + " " + $store.state.dataf1.lastname
          }}
        </h4>
        <a @click="checkinfo1">Chỉnh sửa</a>
      </div>
      <div class="mid_save">
        <div class="left_mid_save">
          <span class="lb_phone_save">điện thoại</span>
          <h4 class="phone_save">{{ $store.state.dataf1.phone }}</h4>
        </div>
        <div class="right_mid_save">
          <span class="lb_nationaly_save">Email</span>
          <h4 class="nationaly_save">{{ $store.state.dataf1.email }}</h4>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import validate from "@/components/Customer/Other/validate_info_user";
export default {
  setup() {
    const { data, checkinfo1 } = validate();
    return { data, checkinfo1 };
  },
};
</script>
<style scoped>
.top_save > a,
.item_tile > a {
  font-weight: 800;
  cursor: pointer;
}
.inforlship {
  padding: 10px;
}
.save_info_group {
  margin: 10px;
  box-shadow: 0 0 4px black;
  padding: 10px;
  border-radius: 10px;
}
.lb_phone_save {
  padding-bottom: 10px;
}
h3 {
  margin-left: 10px;
}
h4 {
  margin: 0;
}
.mid_save {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 70px;
}
.top_save {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 50px;
}
.top {
  padding: 30px;
}
.top,
.detail {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.detail {
  gap: 10px;
}
.tike {
  padding-left: 30px;
}
.left {
  padding-bottom: 50px;
  position: relative;
}
.next {
  padding: 10px 40px;
  position: absolute;
  right: 30px;
  background: blue;
  color: white;
  font-weight: 500px;
  border: none;
  border-radius: 5px;
}
.group_birtday {
  display: flex;
  gap: 5px;
}
.info_group {
  padding: 10px;
  background: rgb(255, 255, 255);
  margin: 10px;
  border-radius: 10px;
  box-shadow: 0 0 4px black;
}
.info_content {
  position: relative;
}
.info_content > div {
  display: flex;
  flex-direction: column;
  padding: 10px;
}
.info_content > div > label {
  font-weight: 700;
  font-size: 13px;
}
.info_content > div > span {
  font-size: 13px;
}
.info_content > div > input,
.group_birtday > input {
  padding: 10px;
  border-radius: 5px;
  outline: none;
  border: 1px solid gray;
  margin: 5px 0;
}
.info_content {
  display: grid;
  grid-template-areas: "a b" "c d";
}
.info_content > div:nth-child(1) {
  grid-area: a;
}
.info_content > div:nth-child(2) {
  grid-area: b;
}
.info_content > div:nth-child(3) {
  grid-area: c;
}
.info_content > div:nth-child(4) {
  grid-area: d;
}
.item_tile {
  display: flex;
  justify-content: space-between;
}
</style>
