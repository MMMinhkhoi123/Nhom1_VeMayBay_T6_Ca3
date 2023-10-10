<template>
  <div v-if="$store.state.list.Location.length > 0" class="your_trip">
    <div>
      <div class="content_top">
        <font-awesome-icon
          @click="
            ($store.state.shownext = false),
              ($store.state.list.my_ticker.id_tick = []),
              ($store.state.my_tick_none.sum_price = 0),
              ($store.state.my_tick_none.sum_price_back = 0)
          "
          :icon="['fas', 'xmark']"
        />
        <h4>Your trip</h4>
      </div>
      <div class="time_get">
        <div>
          <span
            class="chose"
            :style="
              $store.state.chosechair == false
                ? { 'border-bottom': '3px solid blue' }
                : {}
            "
            @click="
              ($store.state.chosechair = false),
                ($store.state.my_tick_none.id_chose = 0)
            "
            >Chuyến đi</span
          >
        </div>
        <div>
          <span
            v-if="this.$route.query.round_trip == 'true'"
            class="chose"
            :style="
              $store.state.chosechair == true
                ? { 'border-bottom': '3px solid blue' }
                : {}
            "
            @click="
              ($store.state.chosechair = true),
                ($store.state.my_tick_none.id_chose = 1)
            "
            >Chuyến về</span
          >
        </div>
      </div>
      <!-- //detailc tiker -->
      <div class="detail">
        <basedetailtour v-for="itemx in data" :key="itemx.id">
          <template v-slot:from>
            <h4>
              {{
                $store.getters["list/location_id"](
                  data[0].location_departure
                )[0].name
              }}
            </h4>
          </template>
          <template v-slot:to>
            <h4>
              {{
                $store.getters["list/location_id"](data[0].location_complete)[0]
                  .name
              }}
            </h4>
          </template>
          <template v-slot:trademark>
            <div class="logo">
              <baseimg
                v-if="
                  $store.getters['list/trademark_id'](data[0].trademark) != null
                "
                :style="{ width: '100%', height: '50px' }"
                :path="
                  'trademark/' +
                  $store.getters['list/trademark_id'](data[0].trademark)[0].img
                "
              ></baseimg>
              <p>
                {{
                  $store.getters["list/trademark_id"](data[0].trademark)[0].name
                }}
              </p>
            </div>
          </template>
          <template v-slot:timego>
            <h4>{{ culdate(data[0].time_departure) }}</h4>
          </template>
          <template v-slot:time_fished>
            <h4>{{ culdate(data[0].time_complete) }}</h4>
          </template>
        </basedetailtour>
        <basechair></basechair>
      </div>
    </div>
    <div class="sum">
      <basesumprice></basesumprice>
    </div>
  </div>
</template>
<script>
import setail_tour from "../../Detail_Tour.vue";
import chosechair from "../From/ChoseChair.vue";
import img from "@/components/Admin/ImagesVue.vue";
import sumprice from "../../SumPrice.vue";
import store from "@/store";
import { computed } from "vue";
export default {
  components: {
    basedetailtour: setail_tour,
    basechair: chosechair,
    basesumprice: sumprice,
    baseimg: img,
  },
  setup() {
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
        dates.getUTCMonth() +
        "/" +
        dates.getFullYear()
      );
    };
    const data = computed(() => {
      return store.getters["list/tick"](
        store.state.list.my_ticker.id_tick[store.state.my_tick_none.id_chose]
      );
    });
    return {
      data,
      culdate,
    };
  },
};
</script>
<style scoped>
.logo {
  display: flex;
}
.sum {
  margin-top: auto;
  padding: 10px;
}
.your_trip {
  opacity: 1;
  pointer-events: none;
  display: flex;
  flex-direction: column;
  padding: 10px;
  transition: 0.4s;
}
.chose {
  cursor: pointer;
}
.time_get {
  display: flex;
  border-bottom: 1px solid;
}
.time_get > div {
  width: 50%;
}
.time_get > div > span {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}
.content_top {
  display: flex;
  gap: 15px;
  padding: 10px 10px;
  align-items: center;
}
.your_trip {
  position: fixed;
  right: -100%;
  top: 0;
  min-width: 500px;
  height: 100vh;
  z-index: 1000;
  background: rgb(255, 255, 255);
}
h4 {
  margin: 0;
}
.content_top > button {
  padding: 10px 30px;
  background: rgb(94, 94, 247);
  border-radius: 10px;
  font-weight: 600;
  border: none;
  color: white;
}
</style>
