<template>
  <div class="airplane">
    <baseadd></baseadd>
    <div class="group_airplane">
      <div class="filter">
        <div class="search">
          <input
            v-model="$store.state.md_airplane.page.key"
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
import add from "@/components/Admin/Add/AddairPlane.vue";
import list from "@/components/Admin/LIST/List_airplane.vue";
import store from "@/store";
export default {
  components: {
    baseadd: add,
    baselist: list,
  },
  setup() {
    const callkey = () => {
      store.dispatch("md_airplane/get_airplane");
    };
    return {
      callkey,
    };
  },
};
</script>
<style scoped>
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
.airplane {
  margin: 10px 10px;
}
.group_airplane {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
