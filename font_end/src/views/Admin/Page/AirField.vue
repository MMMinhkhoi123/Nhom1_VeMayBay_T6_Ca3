<template>
  <div class="airfield">
    <baseadd></baseadd>
    <div class="group_airfield">
      <div class="filter">
        <div class="search">
          <input
            v-model="page.key"
            type="text"
            placeholder="Nhập Tên Địa Điểm"
          /><font-awesome-icon
            @click="callkey"
            class="icon"
            :icon="['fas', 'magnifying-glass']"
          />
        </div>
      </div>
      <baselist></baselist>
    </div>
  </div>
</template>
<script>
import add from "@/components/Admin/Add/AddAirFiled.vue";
import list from "@/components/Admin/LIST/List_AirField.vue";
import store from "@/store";
import { reactive } from "vue";
export default {
  components: {
    baseadd: add,
    baselist: list,
  },
  setup() {
    const page = reactive({
      limit: store.state.md_location.page.limit,
      page: store.state.md_location.page.page,
      key: "",
    });
    const callkey = () => {
      store.dispatch("md_airfield/set_airfield", page);
    };
    return {
      callkey,
      page,
    };
  },
};
</script>
<style scoped>
.airfield {
  margin: 10px;
}

/* filter */
.filter {
  padding: 10px;
  padding-bottom: 30px;
  display: flex;
}
.search {
  position: relative;
}
.search > input {
  padding: 15px 30px;
  border-radius: 5px;
}
.icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 20px;
}
/* list  */
.group_airfield {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
