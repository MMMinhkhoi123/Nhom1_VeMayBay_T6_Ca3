<template>
  <div class="trademark">
    <baseadd></baseadd>
    <div class="group_trademark">
      <div class="filter">
        <div class="search">
          <input
            v-model="$store.state.md_trademark.page.key"
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
import add from "@/components/Admin/Add/AddTrademark.vue";
import list from "@/components/Admin/LIST/List_Trademark.vue";
import store from "@/store";
export default {
  components: {
    baseadd: add,
    baselist: list,
  },
  setup() {
    const callkey = () => {
      store.dispatch("md_trademark/set_trademarks");
    };
    return {
      callkey,
    };
  },
};
</script>
<style scoped>
.trademark {
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
.group_trademark {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
