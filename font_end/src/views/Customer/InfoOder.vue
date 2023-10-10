<template>
  <div class="mainlist">
    <base-hear></base-hear>
    <div class="container">
      <div class="split">
        <div class="left">
          <div class="name_firt">
            <div class="tike">
              <h2>Đặt chỗ của tôi</h2>
              <p>Điền Thông tin và xem lại đặt chỗ</p>
            </div>
            <div v-if="$store.state.post.authentication" class="acpunt">
              <div class="img">
                <font-awesome-icon :icon="['far', 'user']" />
              </div>
              <div>
                <div>
                  <h4>
                    Đã đăng nhập với tên
                    {{ $store.state.post.authentication.fullname }}
                  </h4>
                  <span style="color: gray">acount</span>
                </div>
              </div>
            </div>
          </div>
          <basinfo v-if="!$store.state.post.authentication"></basinfo>
          <baseInfocus></baseInfocus>
          <basecomfort v-if="$store.state.shownext2"></basecomfort>
          <div>
            <button v-if="!$store.state.info" @click="next" class="next">
              Tiếp tục
            </button>
            <button v-if="$store.state.info" @click="nextpayment" class="next">
              Đến Thanh Toán
            </button>
          </div>
        </div>
        <div
          v-if="
            $store.state.list.Location.length > 0 &&
            $store.state.list.trademark.length > 0 &&
            $store.state.list.tick.length > 0
          "
          class="right"
        >
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
                      $store.getters['list/trademark_id'](data1[0].trademark) !=
                      null
                    "
                    :style="{ width: '100%', height: '50px' }"
                    :path="
                      'trademark/' +
                      $store.getters['list/trademark_id'](data1[0].trademark)[0]
                        .img
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
                      $store.getters['list/trademark_id'](data2[0].trademark) !=
                      null
                    "
                    :style="{ width: '100%', height: '50px' }"
                    :path="
                      'trademark/' +
                      $store.getters['list/trademark_id'](data2[0].trademark)[0]
                        .img
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
  <!-- custom thông báo -->
  <div
    :class="$store.state.hasNotifi == true ? 'show_info_bg' : ''"
    class="bg_black"
  ></div>
  <basenotification
    :class="$store.state.hasNotifi == true ? 'show_info_cpn' : ''"
  >
    <template v-slot:title>
      <h1>Thông Báo</h1>
    </template>
    <template v-slot:content>
      <p>Vui lòng chọn đủ số ghế !</p>
    </template>
    <template v-slot:button>
      <button @click="$store.state.hasNotifi = false" class="btn_re">
        Nhập Lại
      </button>
    </template>
  </basenotification>
</template>
<script>
import Headers from "../../components/Customer/Common/HedearNext.vue";
import infoship from "../../components/Customer/From/InfoReship.vue";
import infocustom from "../../components/Customer/From/Info_Customer.vue";
import mored from "../../components/Customer/From/ComFort.vue";
import img from "@/components/Admin/ImagesVue.vue";
import validate from "@/components/Customer/Other/validate_info_user";
import baseinfo from "@/components/Customer/Notification/TypeOne.vue";
import { computed, ref } from "vue";
// import router from "@/router";
import store from "@/store";
export default {
  components: {
    baseHear: Headers,
    basinfo: infoship,
    baseInfocus: infocustom,
    basecomfort: mored,
    baseimg: img,
    basenotification: baseinfo,
  },
  created() {
    store.state.list.my_ticker.id_tick = [];
    store.state.list.my_ticker.id_chair = [];
    store.state.list.my_ticker.id_chair.push(Number(this.$route.query.idchair));
    store.state.list.my_ticker.id_tick.push(Number(this.$route.query.id_tick1));
    if (this.$route.query.idchair2 != undefined) {
      store.state.list.my_ticker.id_chair.push(
        Number(this.$route.query.idchair2)
      );
      store.state.list.my_ticker.id_tick.push(
        Number(this.$route.query.id_tick2)
      );
    }
  },
  setup() {
    const { next, nextpayment } = validate();
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
    const datas = ref(false);
    return { datas, next, culdate, nextpayment, data1, data2 };
  },
};
</script>
<style scoped>
.bg_black {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.322);
  z-index: 99;
  opacity: 0;
  pointer-events: none;
}
.btn_re {
  background: blue;
  color: white;
  padding: 15px 40px;
  border-radius: 5px;
  border: none;
}
.show_info_bg {
  pointer-events: visible !important;
  opacity: 1 !important;
}

.show_info_cpn {
  top: 50% !important;
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
.left {
  padding-bottom: 200px;
  position: relative;
}
.next {
  padding: 10px 40px;
  position: absolute;
  right: 30px;
  background: blue;
  color: white;
  font-weight: 500px;
  border: none;
  border-radius: 5px;
}
.group_birtday {
  display: flex;
  gap: 5px;
}
.info_group {
  padding: 10px;
}
.info_content {
  position: relative;
}
.info_content > div {
  display: flex;
  flex-direction: column;
  padding: 10px;
}
.info_content > div > label {
  font-weight: 700;
}
.info_content > div > span {
  font-size: 13px;
}
.info_content > div > input,
.split {
  display: grid;
  grid-template-columns: 1fr 600px;
}
.acpunt {
  margin: 0 30px;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 0 2px black;
  display: flex;
  gap: 10px;
  align-items: center;
  margin-top: 10px;
}
.img {
  width: 50px;
  height: 50px;
  color: white;
  background: rgb(73, 73, 75);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
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
</style>
