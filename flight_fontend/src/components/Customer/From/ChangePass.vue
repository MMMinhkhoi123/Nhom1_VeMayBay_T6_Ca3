<template>
  <form @submit.prevent="onsubmit" class="parent_change">
    <div class="change">
      <div class="old_pass">
        <label>Mật khẩu cũ</label>
        <input v-model="data.passold" type="password" placeholder="Nhập..." />
        <span class="err" v-if="err.passold">{{ err.passold }}</span>
      </div>
      <div class="old_pass">
        <label>Mật khẩu mới</label>
        <input v-model="data.passnew" type="password" placeholder="Nhập..." />
        <span class="err" v-if="err.passnew">{{ err.passnew }}</span>
      </div>
      <div class="setting">
        <button type="submit">Thay đổi</button>
      </div>
    </div>
  </form>
  <div
    :style="
      $store.state.post.mess_changepass == 'Password change success !'
        ? { background: 'rgba(50, 255, 81, 0.703)' }
        : null
    "
    :class="$store.state.post.mess_changepass != null ? 'animation_mess' : null"
    class="noti"
  >
    {{ $store.state.post.mess_changepass }}
  </div>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";
export default {
  setup() {
    store.state.post.mess_changepass = null;
    const data = reactive({
      token: localStorage.getItem("jwt"),
      passold: null,
      passnew: null,
    });
    const regexp_pasword = /^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,})\S$/;
    const checkpass = () => {
      if (data.passold == null) {
        err.passold = "Không bỏ trống";
      } else if (regexp_pasword.test(data.passold) == false) {
        err.passold = "Không hợp lệ";
      } else {
        err.passold = null;
      }
      if (data.passnew == null) {
        err.passnew = "Không bỏ trống";
      } else if (regexp_pasword.test(data.passnew) == false) {
        err.passnew = "Không hợp lệ";
      } else {
        err.passnew = null;
      }
    };
    const err = reactive({
      token: null,
      passold: null,
      passnew: null,
    });
    const onsubmit = () => {
      checkpass();
      if (err.passnew == null && err.passnew == null) {
        store.state.post.mess_changepass = null;
        store.dispatch("post/chang_pass", data);
      }
    };
    return { data, err, checkpass, onsubmit };
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
.err {
  color: red;
}
.random_pass {
  padding: 20px;
}
.parent_change {
  display: flex;
  justify-content: center;
}
.change {
  padding: 30px;
  width: 300px;
}
.change > div:not(.setting) {
  display: flex;
  flex-direction: column;
  justify-content: start;
  margin: 10px;
  gap: 10px;
}
.setting {
  padding: 20px;
  text-align: center;
}
.setting > button {
  padding: 15px 40px;
  border: none;
  background: blue;
  color: white;
  font-weight: 600;
  border-radius: 10px;
}
input {
  padding: 15px;
  border-radius: 10px;
}
</style>
