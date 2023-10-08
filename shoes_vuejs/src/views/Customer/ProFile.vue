<template>
  <div v-if="$store.state.post.authentication != null" class="mainlist">
    <div>
      <div class="bg">
        <h3 @click="this.$router.go(-1)" class="title">
          <font-awesome-icon :icon="['fas', 'chevron-left']" /> Quay về
        </h3>
        <div class="acount">
          <span><font-awesome-icon :icon="['far', 'user']" /></span>
          <p>
            {{ $store.state.post.authentication.fullname }}
            <font-awesome-icon class="icon_check" :icon="['fas', 'check']" />
          </p>
        </div>
      </div>
      <div v-if="!satus" class="content">
        <div class="info">
          <table>
            <tr>
              <th>Email</th>
              <td>{{ $store.state.post.authentication.email }}</td>
            </tr>
            <tr>
              <th>Phone</th>
              <td>{{ $store.state.post.authentication.phone }}</td>
            </tr>
          </table>
        </div>
        <div class="detail">
          <div class="change_pass" @click="satus = true">Đổi Mật khẩu</div>
          <div @click="logout" class="change_pass logout">
            Đăng xuất tài khoản
          </div>
        </div>
      </div>
      <basechangepass v-if="satus"></basechangepass>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import store from "@/store";
import changepass from "@/components/Customer/From/ChangePass.vue";
import { ref } from "vue";
export default {
  components: {
    basechangepass: changepass,
  },
  setup() {
    const satus = ref(false);
    const logout = () => {
      localStorage.clear("jwt");
      store.state.post.authentication = null;
      router.replace({ name: "login" });
    };
    return {
      logout,
      satus,
    };
  },
};
</script>
<style scoped>
/* table  */
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td,
th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
th {
  width: 200px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
/* and table */
.logout {
  color: rgb(152, 17, 17);
}
.icon_check {
  color: rgb(14, 18, 255);
  margin-left: 10px;
}
.title {
  margin: 0;
  padding: 20px;
  cursor: pointer;
}
.mainlist {
  border-radius: 10px;
  overflow: hidden;
  margin-top: 10px;
  min-height: 100vh;
  position: relative;
  z-index: 10;
  background: white;
}
.acount {
  display: flex;
  gap: 10px;
  padding: 20px;
  align-items: center;
}
.acount > span {
  display: block;
  width: 100px;
  height: 100px;
  background: gray;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 40px;
}
.acount > p {
  font-size: 30px;
  font-weight: 700;
}
.bg {
  background: rgb(216, 216, 216);
}
.detail {
  padding: 30px;
}
.detail > div {
  font-size: 20px;
  padding: 10px;
  font-weight: 600;
  cursor: pointer;
}
.info {
  padding: 30px;
}
</style>
