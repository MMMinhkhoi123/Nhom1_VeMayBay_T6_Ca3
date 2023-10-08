<template>
  <form class="parent_from" @submit.prevent="onsubmit">
    <h1>Đăng ký</h1>
    <div class="form__group">
      <input
        class="form__field"
        placeholder="Name"
        v-model="data.fullname"
        type="text"
      />
      <label class="form__label">Họ và tên</label>
      <span class="erro" v-if="err.fullname">{{ err.fullname }}</span>
    </div>
    <div class="form__group">
      <input
        class="form__field"
        placeholder="email"
        v-model="data.phone"
        type="tel"
      />
      <label class="form__label">Số điện thoại</label>
      <span class="erro" v-if="err.phone">{{ err.phone }}</span>
    </div>
    <div class="form__group">
      <input
        class="form__field"
        placeholder="email"
        v-model="data.gmail"
        type="text"
      />
      <label class="form__label">Email</label>
      <span class="erro" v-if="err.email">{{ err.email }}</span>
    </div>
    <div class="form__group">
      <input
        class="form__field"
        v-model="data.password"
        id="password"
        type="password"
        placeholder="Enter Password"
      />
      <label class="form__label">Mật khẩu</label>
      <span class="erro" v-if="err.pass">{{ err.pass }}</span>
    </div>
    <div class="group_btn">
      <button @click="this.$router.push({ name: 'login' })" class="back">
        Quay về
      </button>
      <button class="submit" type="submit">Đăng ký</button>
    </div>
  </form>
  <div
    :class="$store.state.post.mess_regiter != null ? 'animation_mess' : null"
    class="noti"
  >
    {{ $store.state.post.mess_regiter }}
  </div>
</template>
<script>
import check from "@/components/Customer/Other/validate_info_register";
import store from "@/store";
export default {
  setup() {
    const { data, err, onsubmit } = check();
    store.state.post.mess_regiter = null;
    return {
      onsubmit,
      data,
      err,
    };
  },
};
</script>
<style scoped>
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
.group_btn {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}
.parent_from {
  text-align: center;
}
.icon {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  color: #9b9b9b;
}
.form__group {
  position: relative;
  padding: 20px 0 0;
  display: flex;
  margin: 10px;
  flex-direction: column;
}

.form__field {
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
  color: #3884ef;
  font-weight: 700;
}

h1 {
  padding: 30px;
}

form {
  display: flex;
  flex-direction: column;
  min-width: 500px;
  background: rgb(255, 255, 255);
  color: rgb(0, 0, 0);
  padding: 20px 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
  padding-bottom: 40px;
}
.submit,
.back {
  background: rgb(9, 93, 204);
  border: none;
  margin-top: 30px;
  padding: 15px 40px;
  border: none;
  font-weight: 600;
  color: white;
  border-radius: 10px;
  cursor: pointer;
}
.erro {
  color: red;
  position: absolute;
  top: calc(100% + 2px);
  font-size: 15px;
  white-space: nowrap;
}
</style>
