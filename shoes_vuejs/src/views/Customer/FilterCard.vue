<template>
  <div class="mainlist">
    <div class="filter"></div>
    <baseheader></baseheader>
    <div class="container">
      <div class="list_filter">
        <div class="left">
          <basemenu></basemenu>
        </div>
        <div class="right">
          <div
            class="bg"
            :class="$store.state.search.filters == true ? 'showops_bg' : ''"
            style="opacity: 0; transition: 0.3s; pointer-events: none"
            @click.self="showop"
          >
            <div
              class="shownm"
              :class="$store.state.search.filters == true ? 'showops' : ''"
            >
              <fillters></fillters>
            </div>
          </div>
          <div class="search" style="padding: 30px">
            <div class="item_seach">
              <!-- //location -->
              <div class="location">
                <div v-for="items in location" :key="items.id">
                  <span v-if="items.id == this.$route.query.from"
                    >Từ {{ items.name }}
                  </span>
                </div>
                <div v-for="items in location" :key="items.id">
                  <span v-if="items.id == this.$route.query.to"
                    >Đến {{ items.name }}</span
                  >
                </div>
              </div>
              <div class="search_btn">
                <a @click="showop"
                  >Thay đổi
                  <font-awesome-icon :icon="['fas', 'magnifying-glass']" />
                </a>
              </div>
            </div>

            <!-- //set round trip -->
            <div class="item_round_trip">
              <div class="item_child">
                <span
                  :style="
                    $store.state.list.my_ticker.id_tick[0] != undefined
                      ? { background: 'blue' }
                      : { background: 'white' }
                  "
                  >1</span
                >
                <span>Chọn vé đi </span>
              </div>
              <div
                v-if="this.$route.query.round_trip == 'true'"
                class="item_child"
              >
                <span
                  :style="
                    $store.state.list.my_ticker.id_tick[1] != undefined
                      ? { background: 'blue' }
                      : { background: 'white' }
                  "
                  >2</span
                >
                <span>Chọn vé về </span>
              </div>
            </div>
            <!-- end round trip -->
          </div>
          <div class="list_card">
            <base-tick></base-tick>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div>
    <div
      @click="shoenext"
      :class="$store.state.shownext == true ? 'bgl' : ''"
      class="bgx"
    ></div>
    <div>
      <basetrip
        :class="$store.state.shownext == true ? 'shows' : ''"
      ></basetrip>
    </div>
  </div>
  <div
    :class="$store.state.hasNotifi == true ? 'show_info_bg' : ''"
    class="bg_black"
  ></div>
  <!-- custom thông báo -->
  <basenotification
    :class="$store.state.hasNotifi == true ? 'show_info_cpn' : ''"
  >
    <template v-slot:title>
      <h1>Thông Báo</h1>
    </template>
    <template v-slot:content>
      <p>Không hợp lệ</p>
    </template>
    <template v-slot:button>
      <button
        @click="
          ($store.state.hasNotifi = false),
            ($store.state.list.my_ticker.id_tick = [])
        "
        class="btn_re"
      >
        Nhập Lại
      </button>
    </template>
  </basenotification>
</template>
<script>
import { computed, ref } from "vue";
import Header from "../../components/Customer/Common/HeaDer.vue";
import Search from "@/components/Customer/Common/FlightCPN.vue";
import YourTrip from "@/components/Customer/Search/SelectLV.vue";
import Tick from "@/components/Customer/Search/IteamTick.vue";
import store from "@/store";
import menu from "@/components/Customer/Search/MenuFilter.vue";
import baseinfo from "@/components/Customer/Notification/TypeOne.vue";
export default {
  components: {
    baseheader: Header,
    fillters: Search,
    basetrip: YourTrip,
    baseTick: Tick,
    basemenu: menu,
    basenotification: baseinfo,
  },
  setup() {
    const location = computed(() => {
      return store.state.list.Location;
    });
    const filter1 = ref(false);
    const filter2 = ref(false);

    const shoenext = () => {
      store.state.list.my_ticker.id_tick = [];
      store.state.my_tick_none.sum_price = 0;
      store.state.my_tick_none.sum_price_back = 0;
      store.state.shownext = !store.state.shownext;
    };
    const show1 = () => {
      filter1.value = !filter1.value;
    };
    const show2 = () => {
      filter2.value = !filter2.value;
      const s = document.getElementById("sort2");
      const s2 = document.getElementById("menu_check");
      s2.style.display = "block";
      if (filter2.value == false) {
        s.style.marginBottom = 0 + "px";
      } else {
        s.style.marginBottom = s2.offsetHeight + "px";
      }
    };
    const showop = () => {
      store.state.search.filters = !store.state.search.filters;
    };

    return {
      filter1,
      filter2,
      show1,
      show2,
      showop,
      shoenext,
      location,
    };
  },
};
</script>
<style scoped>
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
.item_child {
  display: flex;
  gap: 10px;
  justify-content: center;
  align-items: center;
}

.item_child > span:nth-child(1) {
  width: 30px;
  height: 30px;
  background: rgb(232, 232, 232);
  border: 2px solid gray;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.item_round_trip {
  display: flex;
  padding: 10px;
  gap: 10px;
}
.search_btn {
  cursor: pointer;
}
.list_card {
  background: rgb(216, 213, 213);
  margin: 10px;
  border-radius: 10px;
}
.search {
  font-weight: 800;
  background: rgb(216, 213, 213);
  margin: 10px;
  border-radius: 10px;
}
.location {
  display: flex;
  gap: 2px;
}
.shows {
  opacity: 1 !important;
  right: 0 !important;
  pointer-events: visible !important;
}
.bgl {
  opacity: 1 !important;
  pointer-events: visible !important;
}
.bgx {
  transition: 2s;
  opacity: 0;
  background: rgba(0, 0, 0, 0.705);
  position: fixed;
  inset: 0;
  z-index: 100;
  pointer-events: none;
}
#helko {
  transition: 0.3s;
}
.bg {
  background: rgba(0, 0, 0, 0.434);
  position: fixed;
  inset: 0;
  z-index: 100;
}
.item_seach {
  padding: 20px 30px;
  border-radius: 10px;
  box-shadow: 0 0 2px black;
  display: flex;
  justify-content: space-between;
  color: white;
  background: rgb(63, 63, 235);
}
.group {
  position: sticky;
  top: 0;
}
.list_card {
  padding: 30px;
}
.show1 {
  animation: example 0.2s ease-in forwards;
}
@keyframes example {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
.shownm {
  position: absolute;
  left: 50%;
  top: -50%;
  transform: translate(-50%, -50%);
  transition: 0.2s;
}
.showops {
  top: 50%;
}

.showops_bg {
  pointer-events: visible !important;
  opacity: 1 !important;
}

.f1 {
  pointer-events: none;
}
.menu_check {
  z-index: 10;
}
.tilte_filter {
  padding: 10px;
  display: flex;
  justify-content: space-between;
}
.left {
  padding: 10px;
  position: sticky;
  top: 0;
}
.sortchange {
  padding: 10px 20px;
  background: rgb(255, 255, 255);
  box-shadow: 0 0 4px black;
  position: relative;
  transition: 0.2s !important;
}
.menu_check {
  transition: 0.2s;
  padding: 10px 20px;
  position: absolute;
  left: 0;
  right: 0;
  background: rgb(87, 86, 86);
  top: 100%;
}
p,
h4 {
  margin: 0;
  padding: 0;
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
.list_filter {
  display: grid;
  grid-template-columns: 300px 1fr;
}
</style>
