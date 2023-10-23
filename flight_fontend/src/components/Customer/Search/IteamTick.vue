<template>
  <div
    v-if="
      $store.state.list.Location.length > 0 &&
      $store.state.list.trademark.length > 0 &&
      $store.state.list.tick.length > 0
    "
  >
    <div class="notfind" v-if="datas.length == 0">
      <span>Not found valid filter !</span>
    </div>
    <div v-for="item in datas" :key="item.id" class="item_card">
      <div class="menat_iteam">
        <div class="logo">
          <img
            width="50"
            height="50"
            :src="
              'http://localhost:8081/img/trademark/' +
              item.trademark +
              '&3&' +
              item.imgtrademark
            "
          />
          <p>
            {{ $store.getters["list/trademark_id"](item.trademark)[0].name }}
          </p>
        </div>
        <div class="location">
          Từ
          {{
            $store.getters["list/location_id"](item.location_departure)[0].name
          }}
          -
          {{
            $store.getters["list/location_id"](item.location_complete)[0].name
          }}
          <span>miễn phí:<strong>32kg</strong></span>
        </div>
        <div style="display: flex; gap: 10px">
          <p>
            {{ culdate(item.time_departure) }} -
            {{ culdate(item.time_complete) }}
          </p>
        </div>
      </div>
      <div class="submit_item">
        <h4 class="price">{{ item.price }} VND/ khách</h4>
        <button class="add">
          <font-awesome-icon :icon="['fas', 'circle-check']" /> Chọn
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { computed } from "vue";
export default {
  created() {
    store.state.list.search.from = this.$route.query.from;
    store.state.list.search.to = this.$route.query.to;
    store.state.list.search.exits_chair = this.$route.query.chair;
    store.state.list.search.date = this.$route.query.date;
    store.state.list.search.Dates_to = this.$route.query.date2;
    store.state.list.search.round_trip = this.$route.query.round_trip;
    if (this.$route.query.date == undefined) {
      store.state.list.search.select = this.$route.query.to;
    }
  },
  setup() {
    store.dispatch("list/get_tickx");
    store.dispatch("list/get_all_trademarks");
    store.dispatch("list/get_typechair");

    const datas = computed(() => {
      if (store.state.list.search.price > 200000) {
        return store.getters["list/searchdata"](
          store.getters["list/searchdata"]()
        );
      }
      if (store.state.list.search.group_trademark.length > 0) {
        return store.getters["list/searchdata"](
          store.getters["list/searchdata"]()
        );
      }
      if (store.state.list.search.sle == 1) {
        const array = store.getters["list/searchdata"]();
        bubbleSort2(array);
        return array;
      }
      if (store.state.list.search.sle == 2) {
        const array = store.getters["list/searchdata"]();
        bubbleSort(array);
        return array;
      }
      return store.getters["list/searchdata"]();
    });

    function bubbleSort(array) {
      var size = array.length;
      for (var i = 0; i < size - 1; i++) {
        for (var j = 0; j < size - i - 1; j++) {
          if (array[j].price > array[j + 1].price) {
            var temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        }
      }
    }
    function bubbleSort2(array) {
      var size = array.length;
      for (var i = 0; i < size - 1; i++) {
        for (var j = 0; j < size - i - 1; j++) {
          if (array[j].price < array[j + 1].price) {
            var temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        }
      }
    }
    const culdate = (value) => {
      const dates = new Date(value);
      const minu = dates.getMinutes();
      const hour = dates.getHours();
      return (
        hour +
        ":" +
        minu +
        "  " +
        dates.getDate() +
        "/" +
        (dates.getUTCMonth() + 1) +
        "/" +
        dates.getFullYear()
      );
    };
    return { culdate, datas };
  },
};
</script>
<style scoped>
.notfind {
  color: gray;
  text-align: center;
}
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
}
.price {
  color: red;
}
.location_from,
.location_to {
  font-weight: 800;
}
.hand_luggage,
.luggage {
  margin: 0 5px;
}
.location {
  display: flex;
  gap: 30px;
}
.p,
h4 {
  margin: 0;
  padding: 0;
}
.item_card {
  padding: 10px;
  box-shadow: 0 0 4px black;
  margin-bottom: 20px;
  background: white;
  border-radius: 10px;
}
.menat_iteam {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.submit_item {
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.add {
  background: blue;
  font-weight: 800;
  border: none;
  color: white;
  font-weight: 500;
  border-radius: 5px;
  padding: 10px 30px;
}
.f1 {
  pointer-events: none;
}
p,
h4 {
  margin: 0;
  padding: 0;
}
</style>
