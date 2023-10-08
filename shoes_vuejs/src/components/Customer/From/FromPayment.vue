<template>
  <div class="payment">
    <div class="info">
      <h4>Đang Chờ Bạn Thanh Toán !</h4>
      <span
        >Số Tiền:
        <strong class="price"
          >{{ $store.state.post.data_update.price }} vnd</strong
        >
      </span>
      <span class="code"
        >Mã Đặt chỗ : {{ $store.state.post.data_update.idbill }}</span
      >
    </div>
    <div class="from">
      <div class="btn_pay">
        <span>Thanh toán để nhận hóa đơn điện tử nhé</span>
        <a class="btn" :href="$store.state.post.data_update.url">
          <font-awesome-icon :icon="['fas', 'money-bill']" /> Thanh Toán
        </a>
      </div>
    </div>
    <div class="note">
      <font-awesome-icon class="icon" :icon="['fas', 'triangle-exclamation']" />
      <h4>
        Nếu bạn không thanh toán trong vòng 1h. rất tiết chúng tôi có quyền hủy
        vé bay bạn.
      </h4>
      <strong>chú ý ! cảm ơn bạn !</strong>
    </div>
  </div>
  <baseloading v-if="test"></baseloading>
</template>
<script>
import loading from "@/components/Customer/Common/LoaDing.vue";
import { ref } from "vue";
export default {
  components: {
    baseloading: loading,
  },
  setup() {
    const test = ref(false);
    return { test };
  },
};
</script>
<style scoped>
.code {
  margin-left: 10px;
}
.price {
  color: red;
  font-size: 23px;
}
input {
  margin: 5px;
  width: 400px;
  padding: 15px;
  border-radius: 5px;
  outline: none;
  border: 1px solid rgb(205, 201, 201);
}
button {
  padding: 10px 30px;
  margin: 10px;
}
.note {
  background: rgba(255, 255, 0, 0.354);
  padding: 20px;
  border-radius: 10px;
  position: relative;
  margin: 30px 0;
}
.icon {
  position: absolute;
  top: 10px;
  left: 20px;
}
.btn {
  display: block;
  background: orangered;
  color: white;
  border: none;
  padding: 10px;
  font-weight: 800;
  border-radius: 5px;
}
.from {
  padding: 30px;
}
.from > div {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.btn_pay {
  color: white;
  display: flex;
  justify-content: space-between;
  background: rgb(44, 56, 194);
  margin-top: 10px;
  padding: 20px;
}
</style>
