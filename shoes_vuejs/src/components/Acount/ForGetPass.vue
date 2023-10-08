<template>
  <div class="forget">
    <div v-if="$store.state.post.emailvalid == null" class="from">
      <label> Nhập email</label>
      <input v-model="data_email.email" type="text" placeholder="Nhập..." />
      <span v-if="$store.state.post.err.email" class="erro">{{
        $store.state.post.err.email
      }}</span>
      <div>
        <button @click="call">Tạo mật khẩu mới</button>
      </div>
    </div>
    <div v-if="$store.state.post.emailvalid" class="get">
      <h1>
        Send email success
        <font-awesome-icon class="icon" :icon="['fas', 'check']" />
      </h1>
    </div>
    <p>Mã xác nhận sẻ được gửi vào email của bạn !</p>
    <p>vui lòng check email sao khi lấy mã !</p>
  </div>
  <loading v-if="status"></loading>
</template>
<script>
import loading from "@/components/Customer/Common/LoaDing.vue";
import { reactive, ref } from "vue";
import store from "@/store";
export default {
  components: {
    loading: loading,
  },
  setup() {
    const data_email = reactive({
      email: null,
    });
    const checkmail = () => {
      if (data_email.email == null) {
        store.state.post.err.email = "Không bỏ trống";
      } else {
        store.state.post.err.email = null;
      }
    };
    const status = ref(false);
    const call = () => {
      checkmail();
      if (store.state.post.err.email == null) {
        status.value = true;
        store.dispatch("post/callemail", data_email);
      }
      const dfate = setInterval(() => {
        if (store.state.post.emailvalid != null) {
          clearInterval(dfate);
          status.value = false;
        }
      }, 200);
    };
    return {
      data_email,
      call,
      status,
    };
  },
};
</script>
<style scoped>
.icon {
  color: rgb(98, 197, 98);
}
.erro {
  color: red;
  margin-left: 20px;
}
.get {
  padding: 20px;
}
.from_get {
  display: flex;
  flex-direction: row;
}
.ipcode {
  width: 200px;
  font-size: 13px;
}
.join {
  background: blue;
}
.time {
  color: rgb(70, 70, 70);
  font-weight: 600;
}
.forget {
  width: 500px;
  min-height: 350px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background: white;
  border-radius: 10px;
  padding: 40px;
}
.from {
  display: flex;
  flex-direction: column;
}
input {
  padding: 20px 30px;
  margin: 10px;
  border-radius: 10px;
  border: 1px solid black;
}
button {
  border-radius: 10px;
  padding: 15px 40px;
  border: none;
  background: rgb(240, 134, 12);
  color: white;
  margin: 10px;
  font-weight: 700;
  cursor: pointer;
}
label {
  font-weight: 700;
  margin: 10px;
}
p {
  margin: 0;
  color: gray;
  margin-left: 10px;
}
</style>
