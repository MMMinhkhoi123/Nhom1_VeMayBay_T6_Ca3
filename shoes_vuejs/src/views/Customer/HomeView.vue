<template>
  <div v-if="this.$route.href == '/'">
    <div class="main">
      <!-- header -->
      <baseheader>
        <template v-slot:linkhome>
          <button
            @click="this.$router.push({ name: 'list' })"
            class="acount_link"
          >
            Home
          </button>
        </template>
      </baseheader>
      <!-- end header -->

      <div class="content">
        <!-- Filter -->
        <flight></flight>
        <!-- End Filter -->
      </div>
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
  </div>
  <div class="parent_container">
    <div class="container">
      <router-view></router-view>
    </div>
    <div class="filter"></div>
  </div>
</template>
<script>
import flight from "@/components/Customer/Common/FlightCPN.vue";
import Headers from "@/components/Customer/Common/HeaDer.vue";
import basenotification from "@/components/Customer/Notification/TypeOne.vue";
import store from "@/store";
import router from "@/router";
export default {
  name: "HomeView",
  components: {
    baseheader: Headers,
    flight: flight,
    basenotification,
  },
  setup() {
    if (localStorage.getItem("jwt") != undefined) {
      store.dispatch("post/authen");
      store.dispatch("list/gethistory");
      const test = setInterval(() => {
        if (store.state.post.authentication != null) {
          console.log(store.state.post.authentication);
          if (!store.state.post.authentication.role.includes("user")) {
            router.push({ name: "admin" });
            clearInterval(test);
          } else if (store.state.post.authentication.role.includes("user")) {
            clearInterval(test);
          }
        }
      }, 100);
    }
    return {};
  },
};
</script>
<style scoped>
.container {
  position: relative;
  min-height: 100vh;
  margin-left: 200px;
  margin-right: 200px;
  margin-top: 50px;
}
.bg_black {
  position: fixed;
  inset: 0;
  background: rgba(21, 21, 21, 0.396);
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
.main {
  display: flex;
  flex-direction: column;
  height: 100vh;
  z-index: 0;
}
.content {
  padding-top: 200px;
  height: calc(100% - 100px);
  z-index: 10;
}
.filter {
  background: url("https://wallpaperaccess.com/full/398881.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  position: fixed;
  inset: 0;
  filter: brightness(60%);
  z-index: 0;
}
.acount_link {
  padding: 10px 30px;
  border: none;
  font-weight: 900;
  margin-right: 10px;
  background: rgb(120, 109, 109);
  color: white;
}
</style>
