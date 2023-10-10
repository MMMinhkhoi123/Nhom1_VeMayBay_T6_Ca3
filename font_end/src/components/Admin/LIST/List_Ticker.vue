<template>
  <div v-if="$store.state.md_tick.tick" class="list_tick">
    <div
      @click="checks(item.id)"
      v-for="item in list.list"
      :key="item.id"
      class="item_card"
    >
      <div class="menat_iteam">
        <div v-if="$store.state.list.trademark.length > 0" class="logo">
          <baseimg
            :style="{ width: '100%', height: '50px' }"
            :path="
              'trademark/' +
              $store.getters['list/trademark_id'](item.trademark)[0].img
            "
          ></baseimg>
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
        <h4 class="price">Số Lượng vé {{ item.quality }}</h4>
      </div>
    </div>

    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_tick.page.page - 1)"
        v-if="list.page > 1"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </div>
      <div v-for="item in list.toltalpage" :key="item">
        <div
          @click="chagepage(item)"
          :style="
            item == list.page ? { background: 'blue', color: 'white' } : null
          "
          class="item_page"
        >
          {{ item }}
        </div>
      </div>
      <div
        @click="chagepage($store.state.md_tick.page.page + 1)"
        v-if="list.page < list.toltalpage"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-right']" />
      </div>
    </div>
  </div>
</template>
<script>
import { computed } from "vue";
import img from "@/components/Admin/ImagesVue.vue";
import store from "@/store";
import router from "@/router";
export default {
  components: {
    baseimg: img,
  },
  setup() {
    const checks = (value) => {
      if (!store.state.post.authentication.role.includes("checker")) {
        console.log("not role");
      } else {
        router.push({
          name: "customer-ticker",
          params: { ticker: value },
        });
      }
    };
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
    store.dispatch("list/get_all_trademarks");
    store.dispatch("md_tick/get_tick");
    store.dispatch("list/get_all_location");
    const list = computed(() => {
      if (store.state.md_tick.filter != null) {
        console.log(
          store.getters["md_tick/filter_tick"](store.state.md_tick.filter)
        );
        return store.getters["md_tick/filter_tick"](store.state.md_tick.filter);
      }
      return store.state.md_tick.tick;
    });
    const chagepage = (item) => {
      store.state.md_tick.page.page = item;
      store.dispatch("md_tick/get_tick");
    };
    return { list, culdate, chagepage, checks };
  },
};
</script>
<style scoped>
.list_tick {
  padding: 10px 50px;
  padding-bottom: 60px;
  position: relative;
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

/* phan trang */

.group_page {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  position: absolute;
  right: 10px;
  bottom: 10px;
}
.item_page {
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  border: 1px solid gray;
  cursor: pointer;
}
</style>
