<template>
  <div class="filter">
    <div v-if="$store.state.md_trademark.trademark" class="trademark">
      <label for="">Hãng bay</label>
      <select v-model="data.idtrademark">
        <option value="" selected>--chose--</option>
        <option
          v-for="item in $store.state.md_trademark.trademark"
          :key="item.id"
          :value="item.id"
          selected
        >
          {{ item.name }}
        </option>
      </select>
    </div>
    <div class="trademark">
      <label for="">Trong khoảng thời gian</label>
      <div>
        <label>Từ</label>
        <input v-model="data.datestart" type="datetime-local" />
      </div>
      <div>
        <label>Đến</label>
        <input v-model="data.dateend" type="datetime-local" />
      </div>
    </div>
    <div v-if="$store.state.md_location.Location" class="location">
      <label for="">Địa điểm đến</label>
      <select v-model="data.idlocation">
        <option value="" selected>--chose--</option>
        <option
          v-for="item in $store.state.md_location.Location"
          :key="item.id"
          :value="item.id"
          selected
        >
          {{ item.name }}
        </option>
      </select>
    </div>
    <div @click="search" class="setting">
      <button>Tìm kiếm</button>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { reactive } from "vue";
export default {
  setup() {
    store.dispatch("md_trademark/set_all_trademarks");
    store.dispatch("md_location/get_all_location");
    const data = reactive({
      idtrademark: null,
      datestart: null,
      dateend: null,
      idlocation: null,
    });
    const search = () => {
      if (
        data.idlocation == null ||
        data.datestart == null ||
        data.dateend == null ||
        data.idtrademark == null
      ) {
        console.log("trống");
      } else {
        store.state.md_tick.filter = data;
      }
    };
    return {
      data,
      search,
    };
  },
};
</script>
<style scoped>
.setting {
  display: flex;
  justify-content: end;
}
button {
  padding: 10px 20px;
  border: none;
  background: blue;
  font-weight: 600;
  color: white;
  border-radius: 10px;
  cursor: pointer;
}
.filter {
  display: flex;
  justify-content: center;
  gap: 30px;
}
.filter > div {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.filter > div > div {
  display: flex;
  gap: 10px;
  justify-content: space-between;
  align-items: center;
}
label {
  font-weight: 600;
}

select,
input {
  padding: 10px 30px;
}
</style>
