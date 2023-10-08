<template>
  <div class="mainlist">
    <baseheader></baseheader>
    <div class="content">
      <flight></flight>
    </div>
    <baselisthost></baselisthost>
    <basetrademark></basetrademark>
    <baselistcontry></baselistcontry>
    <basefooter></basefooter>
  </div>
  <div
    :class="$store.state.hasNotifi == true ? 'show_info_bg' : ''"
    class="bg_black"
  ></div>
  <!-- custom thông báo -->
  <basenotification
    :class="$store.state.hasNotifi == true ? 'show_info_cpn' : ''"
  >
    <template v-slot:title>
      <h1>Thông Báo</h1>
    </template>
    <template v-slot:content>
      <p>Vui Lòng Chọn đủ lựa chọn !!</p>
    </template>
    <template v-slot:button>
      <button @click="$store.state.hasNotifi = false" class="btn_re">
        Nhập Lại
      </button>
    </template>
  </basenotification>
</template>
<script>
import flight from "@/components/Customer/Common/FlightCPN.vue";
import Header from "../../components/Customer/Common/HeaDer.vue";
import listhost from "../../components/Customer/List/ListHost.vue";
import ListContry from "../../components/Customer/List/ListContry.vue";
import Footer from "../../components/Customer/Common/FooTerCPN.vue";
import baseinfo from "@/components/Customer/Notification/TypeOne.vue";
import Listtrademark from "@/components/Customer/List/GroupTrademark.vue";
export default {
  components: {
    baseheader: Header,
    baselisthost: listhost,
    baselistcontry: ListContry,
    flight: flight,
    basefooter: Footer,
    basenotification: baseinfo,
    basetrademark: Listtrademark,
  },
  setup() {},
};
</script>
<style scoped>
.bg_black {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.396);
  z-index: 99;
  transition: 0.3s;
  opacity: 0;
  pointer-events: none;
}
.btn_re {
  background: blue;
  color: white;
  padding: 15px 40px;
  border-radius: 5px;
  border: none;
}
.show_info_bg {
  opacity: 1 !important;
}

.show_info_cpn {
  top: 50% !important;
}

.mainlist {
  border-radius: 10px;
  overflow: hidden;
  margin-top: 100px;
  min-height: 100vh;
  position: relative;
  z-index: 10;
  background: white;
}
.content {
  height: 500px;
  background: url("https://howconvenient634355862.files.wordpress.com/2019/08/city-wallpaper-13.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
</style>
