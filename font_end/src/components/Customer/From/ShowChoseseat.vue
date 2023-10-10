<template>
  <div class="bg_chosesaet"></div>
  <div class="from_chosesaet">
    <div class="left">
      <div class="content">
        <h2>Số Ghế</h2>
        <h4>Chuyến bay 1 trong 2</h4>
      </div>
      <div
        @click="$store.state.my_tick_none.id_chose = index"
        :style="
          $store.state.my_tick_none.id_chose == index
            ? { background: 'blue' }
            : null
        "
        v-for="(item, index) in $store.state.list.my_ticker.id_tick"
        :key="index"
        class="item1"
      >
        <p>
          {{
            $store.getters["list/location_id"](
              $store.getters["list/tick"](item)[0].location_departure
            )[0].name
          }}
          -
          {{
            $store.getters["list/location_id"](
              $store.getters["list/tick"](item)[0].location_departure
            )[0].name
          }}
        </p>
        <div class="filer">
          <span class="kilogram"
            >{{
              $store.state.list.my_aply.chose_seat[index] != null
                ? $store.state.list.my_aply.chose_seat[index].stt
                : null
            }}
          </span>
          <span class="price"
            >+
            {{
              $store.state.list.my_aply.chose_seat[index] != null
                ? $store.getters["list/typechair"](
                    $store.state.list.my_aply.chose_seat[index].typechair
                  )[0].price
                : null
            }}
            vnd
          </span>
        </div>
      </div>
    </div>
    <div v-if="$store.state.list.Typechair.length > 0" class="right">
      <div class="form_select">
        <!-- set empersion -->
        <div class="group_ampersand">
          <div
            v-for="item in ampersand[0].typechair"
            :key="item"
            class="ampersand"
          >
            <span :class="'side' + item"></span>
            <label for="">{{
              $store.getters["list/typechair"](item)[0].name
            }}</label>
            <label for="">{{
              $store.getters["list/typechair"](item)[0].price
            }}</label>
          </div>
        </div>
        <div class="select_ampersand"></div>
      </div>
      <div class="group_chair">
        <div v-for="item in ampersand[0].typechair" :key="item" class="slow">
          <div class="left_slow">
            <div
              @click="
                ($store.state.list.my_aply.chose_seat[
                  $store.state.my_tick_none.id_chose
                ] = iteml),
                  check(iteml.id, iteml.typechair)
              "
              :style="
                iteml.status != 'empty' ||
                $store.state.list.my_ticker.id_chair[
                  $store.state.my_tick_none.id_chose
                ] != iteml.typechair
                  ? { 'pointer-events': 'none', opacity: '0.5' }
                  : iteml ==
                    $store.state.list.my_aply.chose_seat[
                      $store.state.my_tick_none.id_chose
                    ]
                  ? { background: 'red !important' }
                  : {}
              "
              :class="'side' + iteml.typechair"
              v-for="iteml in $store.getters['list/getchosechair'](
                ampersand,
                item,
                'left'
              )"
              :key="iteml.id"
              class="itemv"
            >
              {{ iteml.stt }}
            </div>
          </div>
          <div class="mid_slow">
            <div>1</div>
            <div>2</div>
            <div>3</div>
          </div>
          <div class="right_slow">
            <div
              @click="
                ($store.state.list.my_aply.chose_seat[
                  $store.state.my_tick_none.id_chose
                ] = iteml),
                  check(iteml.id, iteml.typechair)
              "
              :style="
                iteml.status != 'empty' ||
                $store.state.list.my_ticker.id_chair[
                  $store.state.my_tick_none.id_chose
                ] != iteml.typechair
                  ? { 'pointer-events': 'none', opacity: '0.5' }
                  : iteml ==
                    $store.state.list.my_aply.chose_seat[
                      $store.state.my_tick_none.id_chose
                    ]
                  ? { background: 'red !important' }
                  : {}
              "
              :class="'side' + iteml.typechair"
              v-for="iteml in $store.getters['list/getchosechair'](
                ampersand,
                item,
                'right'
              )"
              :key="iteml.id"
              class="itemv"
            >
              {{ iteml.stt }}
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="sum">
      <div class="sum_left">
        <h4>
          Tổng Tiền :
          {{
            $store.state.list.my_payment.sum2 +
            $store.state.list.my_payment.sum1
          }}
        </h4>
        <p>(Đối với tất cả các chuyến bay và hành khách)</p>
      </div>
      <div class="button">
        <button @click="status_button = true" v-if="statuss_button == false">
          Chuyến kế tiếp
        </button>
        <button
          @click="
            ($store.state.chose_seat_success = true),
              ($store.state.chonse_seat = false)
          "
        >
          Xong
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import { computed, ref } from "vue";
import store from "@/store";
export default {
  setup() {
    store.dispatch("list/get_tickx");
    store.dispatch("list/get_typechair");
    const status_button = ref(false);
    //getid chair
    const data = computed(() => {
      return store.getters.get_chair_tick(store.state.my_tick_none.id_chose);
    });
    const ampersand = computed(() => {
      return store.getters["list/tick"](
        store.state.list.my_ticker.id_tick[store.state.my_tick_none.id_chose]
      );
    });
    const sumprice1 = ref(0);
    const sumprice2 = ref(0);
    const check = (id, idtype) => {
      if (store.state.my_tick_none.id_chose == 0) {
        store.state.list.my_ticker.id_seat[0] = id;
        store.state.list.my_payment.sum1 =
          store.getters["list/typechair"](idtype)[0].price;
      } else {
        store.state.list.my_ticker.id_seat[1] = id;
        store.state.list.my_payment.sum2 =
          store.getters["list/typechair"](idtype)[0].price;
      }
    };
    return {
      status_button,
      data,
      location,
      ampersand,
      check,
      sumprice1,
      sumprice2,
    };
  },
};
</script>
<style scoped>
.content > h2 {
  font-weight: 700;
}
.itemv {
  cursor: pointer;
}
.distable {
  opacity: 0.2;
  pointer-events: none;
}
.content {
  padding: 20px 20px;
}
.button {
  display: flex;
  gap: 10px;
}
.hight {
  padding-bottom: 30px;
}
.item1 {
  color: white;
}
.itemv:hover {
  background: rgb(0, 0, 0);
  color: white;
}
.itemv {
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: 800;
}
.group_chair {
  display: flex;
  flex-direction: column;
  background: rgb(239, 239, 239);
  align-items: center;
  height: 300px;
  overflow-y: scroll;
}
.slow,
.medium,
.hight {
  display: flex;
}
.left_slow,
.right_slow,
.left_medium,
.right_medium,
.left_hight,
.right_hight {
  display: grid;
  gap: 10px;
  padding: 10px;
  grid-template-areas: "a b c";
}

.left_slow > div,
.right_slow > div {
  width: 30px;
  height: 30px;
  background: green;
}

.left_medium > div,
.right_medium > div {
  width: 30px;
  height: 30px;
  background: rgb(20, 53, 245);
}

.left_hight > div,
.right_hight > div {
  width: 30px;
  height: 30px;
  background: rgb(24, 212, 212);
}

.mid_slow,
.mid_medium,
.mid_hight {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px;
}
.mid_slow > div,
.mid_medium > div,
.mid_hight > div {
  width: 30px;
  height: 30px;
  background: rgb(255, 255, 255);
  display: flex;
  justify-content: center;
  align-items: center;
}
.group_ampersand {
  display: flex;
  justify-content: space-around;
  padding: 20px 50px;
}
.ampersand {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.ampersand > span {
  display: block;
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
  border-radius: 5px;
}
.ampersand:nth-child(1) > span {
  background: rgb(55, 197, 45);
}
.ampersand:nth-child(2) > span {
  background: rgb(36, 137, 144);
}
.ampersand:nth-child(3) > span {
  background: rgb(55, 0, 255);
}
.group_choseseat {
  background: red;
}
.form_select {
  background: rgb(255, 255, 255);
  border-radius: 10px;
  box-shadow: 0 0 4px black;
  margin: 10px;
}
.right {
  margin: 10px;
}
.firm {
  display: flex;
  align-items: center;
  padding: 10px;
  gap: 0 10px;
}
.left {
  box-shadow: 0 0 4px black;
}
p {
  margin: 0;
  padding: 0;
}

.sum {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  box-shadow: 0 0 4px black;
}
button {
  width: 200px;
  height: 40px;
  border: none;
  border-radius: 5px;
  font-weight: 700;
  color: rgb(74, 72, 72);
}
.sum {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50px;
  background: rgb(255, 255, 255);
}
.item1 {
  background: rgb(172, 206, 235);
}
.item1,
.item2 {
  padding: 10px 30px;
}
.filer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
h4 {
  margin: 0;
}
.bg_chosesaet {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.386);
  z-index: 1000;
}
.from_chosesaet {
  display: grid;
  position: fixed;
  width: 800px;
  min-height: 500px;
  top: 50%;
  left: 50%;
  border-radius: 10px;
  transform: translate(-50%, -50%);
  z-index: 1001;
  background: white;
  grid-template-columns: 300px 1fr;
}
.side6 {
  background: rgb(0, 128, 255) !important;
}
.side7 {
  background: rgb(48, 177, 224) !important;
}
.side8 {
  background: rgb(129, 193, 25) !important;
}
</style>
