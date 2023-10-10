<template>
  <div class="mainlist">
    <base-hear></base-hear>
    <div class="filter"></div>
    <div class="container">
      <div class="split">
        <div class="left">
          <basefroms></basefroms>
        </div>
        <div class="right">
          <div v-if="$store.state.list.tick.length > 0" class="right">
            <div class="cardright">
              <div class="tiles_x">
                <h3>
                  {{
                    $store.getters["list/location_id"](
                      data1[0].location_departure
                    )[0].name
                  }}
                  -
                  {{
                    $store.getters["list/location_id"](
                      data1[0].location_complete
                    )[0].name
                  }}
                </h3>
              </div>
              <div class="from">
                <div class="top">
                  <div class="tiles">
                    <p>
                      {{
                        $store.getters["list/location_id"](
                          data1[0].location_departure
                        )[0].name
                      }}
                      -
                      {{
                        $store.getters["list/location_id"](
                          data1[0].location_complete
                        )[0].name
                      }}
                    </p>
                  </div>
                  <div class="detail">
                    <baseimg
                      v-if="
                        $store.getters['list/trademark_id'](
                          data1[0].trademark
                        ) != null
                      "
                      :style="{ width: '100%', height: '50px' }"
                      :path="
                        'trademark/' +
                        $store.getters['list/trademark_id'](
                          data1[0].trademark
                        )[0].img
                      "
                    ></baseimg>
                    <div>
                      <h4>
                        {{
                          $store.getters["list/trademark_id"](
                            data1[0].trademark
                          )[0].name
                        }}
                      </h4>
                    </div>
                  </div>
                </div>
                <div class="time">
                  <h4>
                    {{ culdate(data1[0].time_departure) }} -
                    {{ culdate(data1[0].time_complete) }}
                  </h4>
                </div>
              </div>
              <div v-if="this.$route.query.id_tick2 != null" class="bottom_2">
                <div class="top">
                  <div class="tiles">
                    <p>
                      {{
                        $store.getters["list/location_id"](
                          data2[0].location_departure
                        )[0].name
                      }}
                      -
                      {{
                        $store.getters["list/location_id"](
                          data2[0].location_complete
                        )[0].name
                      }}
                    </p>
                  </div>
                  <div class="detail">
                    <baseimg
                      v-if="
                        $store.getters['list/trademark_id'](
                          data2[0].trademark
                        ) != null
                      "
                      :style="{ width: '100%', height: '50px' }"
                      :path="
                        'trademark/' +
                        $store.getters['list/trademark_id'](
                          data2[0].trademark
                        )[0].img
                      "
                    ></baseimg>
                    <div>
                      <h4>
                        {{
                          $store.getters["list/trademark_id"](
                            data2[0].trademark
                          )[0].name
                        }}
                      </h4>
                    </div>
                  </div>
                </div>
                <div class="time">
                  <h4>
                    {{ culdate(data2[0].time_departure) }} -
                    {{ culdate(data2[0].time_complete) }}
                  </h4>
                </div>
              </div>
            </div>
            <div class="note">
              <h4>Ở điểm dừng này bạn cần:</h4>
              <p>
                Kiểm tra quy định địa phương liên quan đến lệnh cấm đi lại và
                chuẩn bị thị thực quá cảnh nếu quốc gia này yêu cầu
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Headers from "../../components/Customer/Common/HedearNext.vue";
import { computed, ref } from "vue";
import fromx from "@/components/Customer/From/FromPayment.vue";
import store from "@/store";
import img from "@/components/Admin/ImagesVue.vue";
export default {
  components: {
    baseHear: Headers,
    basefroms: fromx,
    baseimg: img,
  },
  setup() {
    store.dispatch("list/get_all_location");
    store.dispatch("list/get_all_trademarks");
    store.dispatch("list/get_tickx");
    store.dispatch("list/get_typechair");
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
    const data1 = computed(() => {
      return store.getters["list/tick"](store.state.list.my_ticker.id_tick[0]);
    });
    const data2 = computed(() => {
      return store.getters["list/tick"](store.state.list.my_ticker.id_tick[1]);
    });
    const save = () => {};
    const datas = ref(false);
    return { save, datas, data2, data1, culdate };
  },
  created() {
    store.state.list.my_ticker.id_tick = [];
    store.state.list.my_ticker.id_tick.push(Number(this.$route.query.id_tick1));
    if (this.$route.query.id_tick2 != undefined) {
      store.state.list.my_ticker.id_tick.push(
        Number(this.$route.query.id_tick2)
      );
    }
  },
};
</script>
<style scoped>
.split {
  display: grid;
  grid-template-columns: 1fr 500px;
}
.right {
  padding: 10px;
}
.tiles_x {
  padding: 0 50px;
  border-bottom: 1px solid black;
}
.cardright {
  margin: 10px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 4px black;
}
.note {
  padding: 30px 20px;
}
.top {
  padding: 30px;
}
.top,
.detail {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.detail {
  gap: 10px;
}
p {
  margin: 0;
}
.tike {
  padding-left: 30px;
}
h4 {
  margin: 0;
}
.mainlist {
  border-radius: 10px;
  overflow: hidden;
  margin-top: 100px;
  min-height: 100vh;
  position: relative;
  z-index: 10;
  background: white;
}

.left {
  padding: 30px;
}
</style>
