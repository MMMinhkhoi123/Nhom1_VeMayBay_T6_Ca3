<template>
  <div class="chose_chair">
    <h3>Chọn loại vé khởi hành</h3>
    <div v-if="$store.state.my_tick_none.id_chose == 0" class="list_item">
      <label
        :class="$store.state.err_choseseat.chair1 ? 'status_erro' : null"
        @click="check(item.price, item.id)"
        v-for="item in data"
        :key="item.id"
        class="item"
        :style="
          $store.state.my_tick_none.idchair1 == item.id
            ? { background: 'rgb(172, 189, 255)' }
            : null
        "
      >
        <div class="chosess">
          <div class="content_item">
            <p>
              <font-awesome-icon :icon="['fas', 'chart-area']" />
              {{ item.name }}
            </p>
            <p>{{ item.price }}/khach</p>
          </div>
        </div>
        <div class="improve">
          <p>Hành lý xách tay 1 x {{ item.luggage }}kg</p>
        </div>
        <input class="check" type="radio" name="data" />
      </label>
    </div>
    <div v-if="$store.state.my_tick_none.id_chose == 1" class="list_item">
      <label
        :class="$store.state.err_choseseat.chair2 ? 'status_erro' : null"
        @click="check(item.price, item.id)"
        v-for="item in data"
        :key="item.id"
        class="item"
        :style="
          $store.state.my_tick_none.idchair2 == item.id
            ? { background: 'rgb(172, 189, 255)' }
            : null
        "
      >
        <div class="chosess">
          <div class="content_item">
            <p>
              <font-awesome-icon :icon="['fas', 'chart-area']" />
              {{ item.name }}
            </p>
            <p>{{ item.price }}/khach</p>
          </div>
        </div>
        <div class="improve">
          <p>Hành lý sách tay 1 x {{ item.luggage }}kg</p>
        </div>
        <input class="check" type="radio" name="data" />
      </label>
    </div>
  </div>
</template>
<script>
import { computed } from "vue";
import store from "@/store";
export default {
  setup() {
    const check = (value, id) => {
      if (store.state.my_tick_none.id_chose == 0) {
        store.state.my_tick_none.price_chair_go = value;
        store.state.my_tick_none.idchair1 = id;
        store.state.list.my_ticker.id_chair[0] = id;
        store.state.err_choseseat.chair1 = false;
      } else {
        store.state.list.my_ticker.id_chair[1] = id;
        store.state.my_tick_none.price_chair_back = value;
        store.state.my_tick_none.idchair2 = id;
        store.state.err_choseseat.chair2 = false;
      }
    };
    // get type chair for ticker
    const data = computed(() => {
      return store.getters["list/typechairtick"](
        store.state.my_tick_none.id_chose
      );
    });
    return { data, check };
  },
};
</script>
<style scoped>
.status_erro {
  border: 1px solid red;
}
input {
  display: none;
}
h3 {
  margin: 0;
  padding: 10px 20px;
}
.list_item {
  display: flex;
  gap: 10px;
  padding: 13px;
}
.chosess {
  display: flex;
  align-items: center;
  gap: 20px;
}
.item {
  display: block;
  background: rgb(199, 199, 199);
  color: rgb(0, 0, 0);
  padding: 15px;
  position: relative;
  border-radius: 10px;
  box-shadow: 0 0 2px black;
}
.check {
  position: absolute;
  right: 20px;
  top: 20px;
}
</style>
