<template>
  <dir
    v-if="$store.state.shownext == true"
    id="sum"
    @click.self="onsubmit"
    class="sum"
  >
    <div class="mate">
      <p>Tổng Cộng Cho 1 khách</p>
      <span
        >{{
          $store.state.my_tick_none.sum_price +
          $store.state.my_tick_none.sum_price_back +
          $store.state.my_tick_none.price_chair_go +
          $store.state.my_tick_none.price_chair_back
        }}
        vnd</span
      >
    </div>
    <div>
      <button @click="next">Tiếp Tục đặt chỗ</button>
    </div>
    <div
      id="detail"
      :class="data == true ? 'show' : ''"
      v-show="data"
      class="detail"
    >
      <div>
        <h4 v-for="item in $store.state.trademark" :key="item.id">
          {{ item.id == idtrademark[0].id_trademark ? item.trademark : null }}
        </h4>
        <div class="more_detail">
          <p>Giá vé x1</p>
          <p>{{ $store.state.my_tick_none.sum_price }} VND</p>
        </div>
        <div class="more_detail">
          <p>Vé khởi hành x1</p>
          <p>{{ $store.state.my_tick_none.price_chair_go }} VND</p>
        </div>
      </div>
      <div v-if="this.$route.query.round_trip == 'true'">
        <h4 v-for="item in $store.state.trademark" :key="item.id">
          {{ item.id == idtrademark2[0].id_trademark ? item.trademark : null }}
        </h4>
        <div class="more_detail">
          <p>Giá vé x1</p>
          <p>{{ $store.state.my_tick_none.sum_price_back }} VND</p>
        </div>
        <div class="more_detail">
          <p>Vé khởi hành x1</p>
          <p>{{ $store.state.my_tick_none.price_chair_back }} VND</p>
        </div>
      </div>
    </div>
  </dir>
</template>
<script>
import { computed, ref } from "vue";
import router from "@/router";
import store from "@/store";
export default {
  setup() {
    const next = () => {
      if (store.state.list.search.round_trip == "false") {
        router.push({
          name: "booking",
          query: {
            idchair: store.state.list.my_ticker.id_chair[0],
            id_tick1: store.state.list.my_ticker.id_tick[0],
            step: 1,
          },
        });
      } else {
        router.push({
          name: "booking",
          query: {
            idchair: store.state.list.my_ticker.id_chair[0],
            idchair2: store.state.list.my_ticker.id_chair[1],
            id_tick1: store.state.list.my_ticker.id_tick[0],
            id_tick2: store.state.list.my_ticker.id_tick[1],
            step: 1,
          },
        });
      }
    };
    const idtrademark = computed(() => {
      return store.getters.get_tick_id(store.state.my_tick_none.id_tick[0]);
    });
    const idtrademark2 = computed(() => {
      return store.getters.get_tick_id(store.state.my_tick_none.id_tick[1]);
    });

    const data = ref(false);
    const onsubmit = () => {
      const sum = document.getElementById("sum");
      const detail = document.getElementById("detail");
      data.value = !data.value;
      detail.style.display = "block";
      if (data.value == false) {
        sum.style.marginBottom = 0 + "px";
      } else {
        sum.style.marginBottom = detail.offsetHeight + "px";
      }
    };
    return {
      data,
      onsubmit,
      next,
      idtrademark,
      idtrademark2,
    };
  },
};
</script>
<style scoped>
.mate {
  pointer-events: none;
}
.show {
  top: 100% !important;
}
.detail {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: white;
}
.more_detail {
  display: flex;
  padding: 0 20px;
  justify-content: space-between;
}
.detail > div > h4 {
  margin: 0;
}
.detail > div {
  padding: 0 1px;
}
span {
  color: red;
  font-size: 20px;
  font-weight: 600;
}
.sum {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: rgb(255, 255, 255);
  position: relative;
  transition: 0.2s;
}
button {
  padding: 15px 30px;
  border: none;
  border-radius: 10px;
  background: orangered;
  color: white;
  font-weight: 800;
}
p {
  margin: 10px;
}
</style>
