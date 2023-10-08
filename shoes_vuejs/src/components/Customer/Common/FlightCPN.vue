<template>
  <form @submit.self.prevent="onsubmit" class="flight">
    <div class="form">
      <div class="select_from">
        <label>
          <font-awesome-icon :icon="['fas', 'location-dot']" /> Điểm đi</label
        >
        <div @click="show_from = !show_from" class="input_from">
          <span>{{ $store.state.list.name.from }}</span>
        </div>
        <div v-if="show_from" class="option_from">
          <div
            v-for="item in location"
            :key="item.id"
            @click="
              ($store.state.list.search.from = item.id),
                (show_from = false),
                ($store.state.list.name.from = item.name)
            "
            class="item_option"
          >
            <h4>{{ item.name }}</h4>
            <span>{{ item.code }} - {{ item.airfield }}</span>
          </div>
        </div>
      </div>
      <label class="date_from">
        <label
          ><font-awesome-icon :icon="['fas', 'calendar-days']" /> Ngày đi
          {{ $store.state.search.date }}
          {{ $store.state.list.search.date }}
        </label>
        <input
          class="input_time_from"
          v-model="$store.state.list.search.date"
          type="date"
        />
      </label>
    </div>

    <div class="to">
      <div class="select_to">
        <label>
          <font-awesome-icon :icon="['fas', 'location-dot']" /> Điểm đến</label
        >
        <div @click="show_to = !show_to" class="input_to">
          <span>{{ $store.state.list.name.to }}</span>
        </div>
        <div v-if="show_to" class="option_to">
          <div
            v-for="item in location"
            :key="item.id"
            @click="
              ($store.state.list.search.to = item.id),
                (show_to = false),
                ($store.state.list.name.to = item.name)
            "
            class="item_option"
          >
            <h4>{{ item.name }}</h4>
            <span>{{ item.code }} - {{ item.airfield }}</span>
          </div>
        </div>
      </div>
      <label class="date_from_bf">
        <label for="">Khứ Hồi</label>
        <input
          :checked="
            $store.state.list.search.round_trip == true ||
            $store.state.list.search.round_trip == 'true'
          "
          @click="changeround"
          type="checkbox"
        />
        <div class="check"></div>
      </label>
    </div>

    <div class="chair">
      <div class="select_chair">
        <label
          ><font-awesome-icon :icon="['fas', 'chart-area']" /> Hạng ghế</label
        >
        <div @click="show_chair = !show_chair" class="input_chair">
          <span>{{ $store.state.list.name.chair }}</span>
        </div>
        <div v-if="show_chair" class="option_chair">
          <div
            v-for="item in typechair"
            :key="item.id"
            class="item_option"
            @click="
              (show_chair = !show_chair),
                ($store.state.list.search.exits_chair = item.id),
                ($store.state.list.name.chair = item.name)
            "
          >
            <h4>{{ item.name }}</h4>
          </div>
        </div>
      </div>
      <div
        v-if="
          $store.state.list.search.round_trip == true ||
          $store.state.list.search.round_trip == 'true'
        "
        class="date_from"
      >
        <label
          ><font-awesome-icon :icon="['fas', 'calendar-days']" /> Ngày Về
          {{ $store.state.list.search.Dates_to }}</label
        >
        <input
          v-model="$store.state.list.search.Dates_to"
          class="input_time_to"
          type="date"
        />
      </div>
    </div>
    <button type="submit" class="search">Tìm Chuyến bay</button>
  </form>
</template>
<script>
import { computed, ref } from "vue";
import store from "@/store";
import validate from "../Other/validateflight";
export default {
  setup() {
    const { onsubmit } = validate();
    store.dispatch("list/get_all_location");
    store.dispatch("list/get_typechair");

    const location = computed(() => {
      return store.state.list.Location;
    });

    const typechair = computed(() => {
      return store.state.list.Typechair;
    });

    const changeround = () => {
      if (store.state.list.search.round_trip == "true") {
        store.state.list.search.round_trip = "false";
      } else if (store.state.list.search.round_trip == "false") {
        store.state.list.search.round_trip = "true";
      } else {
        store.state.list.search.round_trip =
          !store.state.list.search.round_trip;
      }
    };

    const show_from = ref(false);
    const show_to = ref(false);
    const show_chair = ref(false);
    const checked = ref(false);

    return {
      checked,
      onsubmit,
      show_from,
      show_to,
      show_chair,
      location,
      changeround,
      typechair,
    };
  },
};
</script>
<style scoped>
.input_from,
.input_to,
.input_chair,
.input_time_from,
.input_time_to {
  border: 1px solid rgb(167, 167, 167);
  padding: 20px;
  cursor: pointer;
  border-radius: 10px;
}
.option_from,
.option_to,
.option_chair {
  height: 200px;
  background: rgb(236, 236, 236);
  overflow-y: scroll;
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 10;
}

.date_from,
.select_from,
.select_to,
.select_chair {
  display: flex;
  text-align: center;
  flex-direction: column;
  padding: 10px;
  position: relative;
}

.date_from_bf > input {
  display: none;
}

.date_from_bf > input:checked ~ .check {
  background: rgb(29, 29, 30) !important;
  border: 2px solid rgb(167, 152, 152);
}
.date_from_bf {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.check {
  width: 30px;
  height: 30px;
  background: transparent;
  border: 3px solid rgb(94, 90, 90);
  border-radius: 50%;
}

h4 {
  margin: 0;
}

.item_option:hover {
  background: grey;
}
.item_option {
  padding: 10px 0;
}
.item_content {
  display: flex;
}

label {
  margin: 10px;
  font-weight: 800;
}
.flight {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  padding: 30px;
  background: rgb(255, 255, 255);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
  min-width: 800px;
}
.search {
  position: absolute;
  top: calc(100% + 20px);
  padding: 20px 40px;
  left: 50%;
  border-radius: 10px;
  font-weight: 700;
  transform: translateX(-50%);
  background: rgb(17, 38, 236);
  color: rgb(255, 240, 240);
  border: none;
}
</style>
