<template>
  <div class="parent">
    <div
      v-if="
        $store.state.md_tick.tick.list && $store.state.md_customer.customer.list
      "
      class="count"
    >
      <span>Số Lượng</span>
      <div class="count_tick">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'location-dot']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
      <div class="count_airfild">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'location-crosshairs']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
      <div class="count_trademark">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'trademark']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
      <div class="count_typechair">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'chart-bar']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
      <div class="count_flight">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'plane']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
      <div class="count_tic">
        <div class="child">
          <div>
            <font-awesome-icon :icon="['fas', 'ticket']" />
            {{ $store.state.md_tick.tick.list.length }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
// import store from "@/store";
export default {
  setup() {
    store.dispatch("md_customer/get_customer");
    store.dispatch("md_tick/get_tick");
    store.dispatch("md_bill/get_bill_all");
    store.dispatch("md_trademark/set_all_trademarks");
  },
};
</script>
<style scoped>
.count_flight {
  background: rgb(9, 239, 255) !important;
}
.count_typechair {
  background: rgb(15, 71, 255) !important;
}
.count_trademark {
  background: rgb(127, 23, 231) !important;
}
.count_airfild {
  background: rgb(156, 110, 202) !important;
}
.parent {
  display: flex;
  padding: 20px;
  gap: 20px;
  flex-wrap: wrap;
}
.count > span {
  font-weight: 800;
  position: absolute;
  top: 10px;
  left: 10px;
  color: black;
}
.count {
  position: relative;
  padding: 20px;
  padding-top: 40px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  border-radius: 10px;
  background: rgb(255, 255, 255);
  box-shadow: 0 0 0 0.4px rgba(0, 0, 0, 0.598);
}
.count > div {
  border-radius: 10px;
  width: 160px;
  height: 100px;
  background: gray;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 25px;
  font-weight: 900;
}
.count_tick {
  background: rgba(49, 106, 231, 0.852) !important;
}
.count_customer {
  background: rgba(31, 32, 95, 0.852) !important;
}
</style>
