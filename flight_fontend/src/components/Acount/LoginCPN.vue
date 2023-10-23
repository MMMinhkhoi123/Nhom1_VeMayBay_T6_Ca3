<template>
  <form
    @keydown.enter="onsubmit"
    @submit.prevent="onsubmit"
    class="login_parent"
  >
    <h1>Đăng nhập {{ x }}</h1>
    <div class="form__group">
      <input
        v-model="data.email"
        class="form__field"
        type="text"
        placeholder="Enter email"
      />
      <label class="form__label" for="username">gmail</label>
      <span class="erro" v-if="err.email">{{ err.email }}</span>
    </div>
    <div class="form__group">
      <input
        v-model="data.password"
        id="password"
        class="form__field"
        type="password"
        placeholder="Enter password"
      />
      <label class="form__label">mật khẩu</label>
      <span class="erro" v-if="err.password">{{ err.password }}</span>
      <font-awesome-icon
        class="icon"
        id="icon_pass"
        @click="show(event, 'password', 'icon_pass')"
        :icon="['far', 'eye']"
      />
    </div>
    <div class="rmb_fof">
      <span class="remenber">
        <input v-model="remenber" type="checkbox" />
        <label>ghi nhớ tài khoản</label>
      </span>
      <span class="fofget" @click="this.$router.push({ name: 'forget' })"
        >Quên mật khẩu ?</span
      >
    </div>
    <div class="setting">
      <div class="select_button">
        <button
          class="submit"
          @click="this.$router.push({ name: 'home' })"
          type="button"
        >
          Trang chủ
        </button>
        <button class="submit" type="submit">Đăng nhập</button>
      </div>
      <span>hoặc</span>
      <span @click="this.$router.push({ name: 'signup' })" class="register"
        >Đăng ký</span
      >
    </div>
  </form>
  <div
    :class="$store.state.post.mess_login != null ? 'animation_mess' : null"
    class="noti"
  >
    {{ $store.state.post.mess_login }}
  </div>
  <loading v-if="loading && $store.state.post.mess_login == null"></loading>
</template>
<script>
import check from "@/components/Customer/Other/validate_info_login";
import store from "@/store";
import loading from "@/components/Customer/Common/LoaDing.vue";
export default {
  components: {
    loading: loading,
  },
  setup() {
    const { loading, onsubmit, data, err, show, remenber } = check();
    store.state.post.mess_login = null;
    return { onsubmit, loading, data, err, show, remenber };
  },
};
</script>
<style scoped>
.rmb_fof {
  display: flex;
  justify-content: space-between;
  padding: 20px 40px;
}
/* notification */
.noti {
  width: 200px;
  height: 50px;
  background: rgba(194, 15, 15, 0.628);
  color: white;
  font-weight: 600;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  transform: translateX(-50%);
  left: 50%;
  opacity: 0;
}
.animation_mess {
  animation: noti 5s cubic-bezier(0.19, 1, 0.22, 1) forwards;
}
@keyframes noti {
  0% {
    opacity: 0;
    top: -100%;
  }
  50% {
    opacity: 1;
    top: 70px;
  }
  100% {
    opacity: 0;
    top: -100%;
  }
}
/* end notification */
.register {
  font-weight: 700;
  cursor: pointer;
}
.setting {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}
.signup {
  cursor: pointer;
  font-weight: 800;
}
.login_parent {
  text-align: center;
  min-width: 500px;
  border-radius: 10px;
  background: white;
}
.icon {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  color: #9b9b9b;
}
.erro {
  color: red;
  position: absolute;
  top: calc(100% + 2px);
  font-size: 15px;
  white-space: nowrap;
}
h1 {
  color: black;
}
form {
  display: flex;
  flex-direction: column;
  padding: 20px 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  padding-bottom: 50px !important;
  transform: translate(-50%, -50%);
}
.select_button {
  display: flex;
  gap: 20px;
}
button {
  width: 170px;
  background: rgb(9, 93, 204);
  cursor: pointer;
  border: none;
  padding: 15px 40px;
  font-weight: 700;
  border-radius: 10px;
  color: white;
}

.form__group {
  position: relative;
  padding: 20px 0 0;
  display: flex;
  margin: 10px 30px;
  flex-direction: column;
}

.form__field {
  width: 100%;
  margin-bottom: 10px;
  border: none;
  outline: none;
  border-bottom: 2px solid #9b9b9b;
  font-size: 17px;
  padding: 7px 0;
  background: transparent;
  transition: border-color 0.2s;
}
.form__field::placeholder {
  color: transparent;
}
.form__field:placeholder-shown ~ .form__label {
  font-size: 17px;
  cursor: text;
  top: calc(50% - 10px);
}
.form__label {
  position: absolute;
  top: 0;
  display: block;
  transition: 0.2s;
  font-size: 17px;
  color: #9b9b9b;
  pointer-events: none;
}

.form__field:focus {
  font-weight: 700;
  border-width: 3px;
  border-image: linear-gradient(to right, #116399, #38caef);
  border-image-slice: 1;
}

.form__field:focus ~ .form__label {
  position: absolute;
  top: 0;
  display: block;
  transition: 0.2s;
  font-size: 17px;
  color: #38caef;
  font-weight: 700;
}
</style>
