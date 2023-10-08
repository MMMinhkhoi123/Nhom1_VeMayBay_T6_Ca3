<template>
  <div id="pay" class="parent_pay">
    <div @click="show_detail_pay" class="group_pay">
      <span>Giá bạn trả</span>
      <div>
        <span v-if="$store.state.list.tick.length > 0"
          >{{
            $store.state.list.my_ticker.id_tick.length < 2
              ? $store.getters["list/tick"](
                  $store.state.list.my_ticker.id_tick[0]
                )[0].price +
                $store.state.list.my_payment.sum2 +
                $store.state.list.my_payment.sum1
              : $store.getters["list/tick"](
                  $store.state.list.my_ticker.id_tick[1]
                )[0].price +
                $store.getters["list/tick"](
                  $store.state.list.my_ticker.id_tick[0]
                )[0].price +
                $store.state.list.my_payment.sum2 +
                $store.state.list.my_payment.sum1
          }}
          VND</span
        >
      </div>
      <div id="pay2" class="detail_pay">
        <div
          v-for="item in $store.state.list.my_ticker.id_tick"
          :key="item"
          class="detail_1"
        >
          <h4>
            {{
              $store.getters["list/trademark_id"](
                $store.getters["list/tick"](item)[0].trademark
              )[0].name
            }}
            (Người lớn) x1
          </h4>
          <span>{{ $store.getters["list/tick"](item)[0].price }} VND</span>
        </div>
        <div class="more">
          <div class="detail_1">
            <h4>+ Phí chọn ghế ngồi:</h4>
            <span>
              {{
                $store.state.list.my_payment.sum2 +
                $store.state.list.my_payment.sum1
              }}
              VND</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
export default {
  setup() {
    const status = ref(false);
    const show_detail_pay = () => {
      const data = document.getElementById("pay");
      const element_hiden = document.getElementById("pay2");
      if (status.value == true) {
        data.style.paddingBottom = 0 + "px";
        status.value = false;
      } else {
        status.value = true;
        data.style.paddingBottom = element_hiden.offsetHeight + "px";
      }
    };
    return {
      show_detail_pay,
    };
  },
};
</script>
<style scoped>
h4 {
  margin: 0;
  padding: 0;
}
.parent_pay {
  margin: 10px;
  overflow: hidden;
  box-shadow: 0 0 3px black;
  background: rgb(255, 255, 255);
  transition: 0.3s;
}
.group_pay {
  padding: 20px;
  display: flex;
  justify-content: space-between;
  position: relative;
  border-bottom: 1px solid gray;
}
.detail_pay {
  position: absolute;
  left: 0;
  right: 0;
  top: calc(100% + 10px);
  padding: 20px;
  background: rgb(255, 254, 254);
}
.detail_1 {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
