<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div class="setlocation">
      <div class="airplane">
        <label>Máy Bay</label>
        <select
          :style="erro.id_airplane == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_flight.data_check.id_airplane"
        >
          <option
            v-for="item in array_airplane"
            :key="item.id"
            :value="item.id"
          >
            {{ item.name }}
          </option>
        </select>
        <span v-if="erro.id_airplane" class="erro">{{ erro.id_airplane }}</span>
      </div>
      <div class="airfield">
        <label>Sân Bay</label>
        <select
          :style="erro.id_airfield == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_flight.data_check.id_airfield"
        >
          <option
            v-for="item in array_airfield"
            :key="item.id"
            :value="item.id"
          >
            {{ item.name }}
          </option>
        </select>
        <span v-if="erro.id_airfield" class="erro">{{ erro.id_airfield }}</span>
      </div>
      <div class="start_location">
        <label>Điểm Bắt đầu</label>
        <select
          :style="
            erro.id_locationgo == null ? null : { border: '1px solid red' }
          "
          v-model="$store.state.md_flight.data_check.id_locationgo"
        >
          <option
            v-for="item in array_location"
            :key="item.id"
            :value="item.id"
          >
            {{ item.name }}
          </option>
        </select>
        <span v-if="erro.id_locationgo" class="erro">{{
          erro.id_locationgo
        }}</span>
      </div>
      <div class="end_location">
        <label>Điểm kết thúc</label>
        <select
          :style="
            erro.id_locationand == null ? null : { border: '1px solid red' }
          "
          v-model="$store.state.md_flight.data_check.id_locationand"
        >
          <option
            v-for="item in array_location"
            :key="item.id"
            :value="item.id"
          >
            {{ item.name }}
          </option>
        </select>
        <span v-if="erro.id_locationand" class="erro">{{
          erro.id_locationand
        }}</span>
      </div>
      <div class="price">
        <label>Giá Vé bán</label>
        <input
          :style="erro.price == null ? null : { border: '1px solid red' }"
          v-model="$store.state.md_flight.data_check.price"
          type="number"
          name=""
          id=""
        />
        <span v-if="erro.price" class="erro">{{ erro.price }}</span>
      </div>
    </div>
    <div class="settime">
      <div class="time_go">
        <span class="title_time_go">Thời gian khởi hành</span>
        <div class="hour">
          <label>Giờ </label>
          <input
            :style="erro.hour_go == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_flight.data_check.hour_go"
            class="ip_hour"
            type="number"
            placeholder="hh"
          />
          <span v-if="erro.hour_go" class="erro">{{ erro.hour_go }}</span>
        </div>
        <div class="minu">
          <label>Phút</label>
          <input
            :style="erro.min_go == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_flight.data_check.min_go"
            placeholder="mm"
            class="ip_min"
            type="number"
          />
          <span v-if="erro.min_go" class="erro">{{ erro.min_go }}</span>
        </div>
        <div class="date">
          <label>Ngày</label>
          <input
            :style="erro.date_go == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_flight.data_check.date_go"
            type="date"
          />
          <span v-if="erro.date_go" class="erro">{{ erro.date_go }}</span>
        </div>
      </div>
      <div class="time_move">
        <span class="title_time_move">Thời Gian duy chuyển</span>
        <div class="hour">
          <label>Giờ</label>
          <input
            :style="erro.hour_move == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_flight.data_check.hour_move"
            class="ip_hour"
            type="number"
          />
          <span v-if="erro.hour_move" class="erro">{{ erro.hour_move }}</span>
        </div>
        <div class="minu">
          <label>Phút</label>
          <input
            :style="erro.min_move == null ? null : { border: '1px solid red' }"
            v-model="$store.state.md_flight.data_check.min_move"
            class="ip_min"
            type="number"
          />
          <span v-if="erro.min_move" class="erro">{{ erro.min_move }}</span>
        </div>
      </div>
    </div>
    <div class="setting">
      <button
        class="add"
        type="submit"
        v-if="$store.state.md_flight.data_check.id == null"
      >
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create {{ $store.state.md_flight.data_check.id }}
      </button>
      <button
        @click="update"
        v-if="$store.state.md_flight.data_check.id"
        class="update"
      >
        Update
      </button>
      <button
        @click="$store.state.md_flight.data_check.id = null"
        v-if="$store.state.md_flight.data_check.id"
        class="reload"
      >
        <font-awesome-icon :icon="['fas', 'repeat']" /> Reload
      </button>
    </div>
  </form>
</template>
<script>
import checkflight from "@/store/Admin/Extra/checkfieldflight";
import store from "@/store";
import { computed } from "vue";
export default {
  setup() {
    store.dispatch("md_location/get_all_location");
    store.dispatch("md_airplane/get_all_airplane");
    store.dispatch("md_airfield/set_all_airfield");
    const { validate, erro } = checkflight();
    const onsubmit = () => {
      validate();
    };
    const array_location = computed(() => {
      return store.state.md_location.Location;
    });
    const array_airplane = computed(() => {
      return store.state.md_airplane.airplane;
    });
    const array_airfield = computed(() => {
      return store.state.md_airfield.airfield;
    });
    const update = () => {
      validate();
    };
    return {
      onsubmit,
      erro,
      array_location,
      array_airplane,
      array_airfield,
      update,
    };
  },
};
</script>
<style scoped>
.erro {
  color: red;
  position: absolute;
  bottom: -20px;
  left: 50%;
  font-size: 13px;
  transform: translateX(-50%);
  white-space: nowrap;
}
.setlocation {
  padding: 20px;
}
.settime,
.setlocation,
.setting {
  background: white;
  display: flex;
  justify-content: space-around;
}
.settime > div > div,
.setlocation > div {
  position: relative;
}
.setting {
  justify-content: start;
  gap: 10px;
  padding: 10px;
}
button {
  padding: 10px 30px;
  background: rgb(88, 202, 88);
  cursor: pointer;
  font-weight: 800;
  border: none;
  border-radius: 5px;
}
.update {
  background: rgb(255, 223, 15) !important;
}
.reload {
  background: rgb(97, 146, 184);
}
/* time  */
.time_go,
.time_move,
.time_end {
  display: flex;
  gap: 10px;
  padding: 25px;
  position: relative;
}
.ip_hour,
.ip_min {
  width: 50px;
}
input {
  padding: 10px;
  border-radius: 5px;
}
label {
  font-weight: 600;
  margin-right: 5px;
}
.title_time_go,
.title_time_move,
.title_time_end {
  position: absolute;
  top: 0;
  left: 0;
}
select {
  padding: 10px 30px;
}
/* end time go */
/* set location */
/* end set location  */
</style>
