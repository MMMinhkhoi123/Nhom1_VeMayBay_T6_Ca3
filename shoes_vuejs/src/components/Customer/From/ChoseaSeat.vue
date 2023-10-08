<template>
  <div class="group_choseseat">
    <div class="top_choseseat">
      <h4>Số Ghế</h4>
      <a @click="$store.state.chonse_seat = true">Chọn Ghế</a>
    </div>
    <div class="mid_save">
      <p>
        Cửa sổ hay lối đi? Chọn chỗ ngồi tốt nhất giúp bạn thoải mái suốt chuyến
        đi.
      </p>
      <div v-if="$store.state.list.my_aply.chose_seat.length > 0">
        <div
          v-for="(item, index) in $store.state.list.my_ticker.id_tick"
          :key="index"
          class="item"
        >
          <h4>
            {{
              $store.getters["list/location_id"](
                $store.getters["list/tick"](item)[0].location_departure
              )[0].name
            }}
            -
            {{
              $store.getters["list/location_id"](
                $store.getters["list/tick"](item)[0].location_complete
              )[0].name
            }}
          </h4>
          <span
            >{{ $store.state.dataf1.lastname + $store.state.dataf1.firtname }}
            -
            {{
              $store.state.list.my_aply.chose_seat[index] == null
                ? "Chưa chọn ghế "
                : $store.state.list.my_aply.chose_seat[index].stt
            }}
            {{
              $store.getters["list/typechair"](
                $store.state.list.my_ticker.id_chair[index]
              )[0].price
            }}
            vnd</span
          >
        </div>
      </div>
    </div>
  </div>
  <baseshowchoseseat v-if="$store.state.chonse_seat"></baseshowchoseseat>
</template>
<script>
import showchoseseat from "./ShowChoseseat.vue";
export default {
  components: {
    baseshowchoseseat: showchoseseat,
  },
  setup() {
    return {};
  },
};
</script>
<style scoped>
.group_choseseat {
  margin: 10px;
  box-shadow: 0 0 3px black;
  border-radius: 10px;
}
.top_choseseat {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 60px;
  border-bottom: 1px solid black;
}
.top_choseseat > a {
  font-weight: 700;
}
.mid_save {
  padding: 10px;
}
</style>
